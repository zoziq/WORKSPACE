package tiryaki.planner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

import amsPlan.beh1;
import amsPlan.Register;

import task.Action;
import task.Behaviour;
import task.Output;
import task.Task;
import links.DisinheritanceLink;
import links.ExternalLink;
import links.InheritanceLink;
import links.Link;
import links.ProvisionOutcomeLink;

public class Scheduler extends Thread {

	private LinkedBlockingQueue BehaviourQ;

	private LinkedBlockingQueue ReadyQ;

	private LinkedBlockingQueue PendingQ;

	private LinkedBlockingQueue RunningBehaviourQ;

	private HashMap SonucQ;

	private HashMap InterruptQ;

	private Agent local;

	private Semaphore scheduler_wait_notify;

	private Semaphore executer_notify;

	private Semaphore Exec;

	ArrayList linkList = new ArrayList();

	private static int planNum;

	private int currentPlanNum;
	private ArrayList<Link> linksBuffer; // set edilmemis linkleri tutsun,

	public Scheduler(LinkedBlockingQueue behaviourQ,
			LinkedBlockingQueue RunningBehaviourQ, LinkedBlockingQueue readyQ,
			LinkedBlockingQueue pendingQ, HashMap SonucQ, HashMap interruptQ,
			Agent local, Semaphore scheSem, Semaphore execSem, Semaphore Exec) {

		this.BehaviourQ = behaviourQ;
		this.PendingQ = pendingQ;
		this.ReadyQ = readyQ;
		this.RunningBehaviourQ = RunningBehaviourQ;
		this.SonucQ = SonucQ;
		this.InterruptQ = interruptQ;
		this.local = local;
		this.scheduler_wait_notify = scheSem;
		this.executer_notify = execSem;
		this.Exec = Exec;
		Scheduler.planNum = 0;
		linksBuffer = new ArrayList<Link>();
	}

	public void run() {
		while (true) {
			scheduler_wait_notify.P();
//			System.out.println("scheduler: run() metodu çalışıyor.");
			try {
				runScheduler();
			} catch (Exception e) {
				//System.out.println("scheduler calisirken hata: " + e.toString());
				e.printStackTrace();
			}
		}
	}

	public void runScheduler() {
		if (BehaviourQ == null)
			System.out.println("BehaviourQ null");
		if (BehaviourQ.isEmpty())
			System.out.println("behaviourQ bos");
		
		BehaviourQCell behQCell = (BehaviourQCell) BehaviourQ.poll();
		RunningBehaviourQ.add(behQCell);
		reductBehaviour(behQCell);
	}

	private void reductBehaviour(BehaviourQCell gelenBehQCell) {
		Behaviour gelenBehaviour = gelenBehQCell.getBehaviour();
		int msgNum = gelenBehQCell.getMsgNum();
		String BehID = gelenBehaviour.getId();
		System.out.println("Scheduler Behaviour ID: " + gelenBehaviour.getId());

		// Linkleri kontrol et, kullanabildigini aktar,
		// kullanamadigini ise linksBuffer'a at.
		linkleriKontrolEt(gelenBehaviour, msgNum);
		// Provisionlarinin durumuna gore action veya subBehaviour'lari
		// uygun kuyruklara at.
		uygunKuyruklaraGonder(gelenBehaviour, msgNum);

	}

	private void linkleriKontrolEt(Behaviour gelenBeh, int msgNum) {
		// Once inheritanceLink kullanilabiliyor mu ona bakalim,
		// cunku bu kullanilamiyorsa semayi cozmenin bir anlami da yok,
		// sonra cozeriz.
		for (Link link : gelenBeh.getLinks()) {
			if (link.getClass().getName().contains("InheritanceLink")) {
				InheritanceLink il = (InheritanceLink) link;
				System.out.println("Scheduler InheritanceLink Sender Provision Name: " + il.getSenderProvision().getprovName().toString());

				// BURAYA KADAR SORUNSUZ GELİNDİ!!!
				if (il.getSenderProvision().getValue() != null) {
					il.update();
					System.out.println("1 "
							+ il.getSenderProvision().getValue());
				} else {
					PendingQCell pend = new PendingQCell(msgNum, gelenBeh);
					PendingQ.add(pend);
					return;
				}
			}
			
			if (link.getClass().getName().contains("ExternalLink")) {
				ExternalLink ex = (ExternalLink) link;
				System.out.println("Scheduler PendingQ externalLink "
						+ ex.getReceiverTask().getId() + " "
						+ ex.getsourceTask().getId());
				PendingQCell pend = new PendingQCell(msgNum, ex
						.getReceiverTask(), ex.getsourceTask(), ex
						.getReceiverProvision());
				PendingQ.add(pend);
			}

			if (link.getClass().getName().contains("ProvisionOutcomeLink")) {
				ProvisionOutcomeLink pol = (ProvisionOutcomeLink) link;
				Output out = new Output();
				out.setName(pol.getReceiverProvision().getprovName());
				System.out
						.println("Scheduler PendingQ ProvisionOutcomeLink Task "
								+ pol.getReceiverTask().getId()
								+ " "
								+ pol.getSenderTask().getId()
								+ " "
								+ pol.getReceiverProvision().getprovName()
								+ " " + pol.getSenderOutcome().getName());
				PendingQCell pend = new PendingQCell(msgNum, pol
						.getReceiverTask(), pol.getSenderTask(), pol
						.getReceiverProvision(), pol.getSenderOutcome(), out
						.getName());
				PendingQ.add(pend);
			}

			if (link.getClass().getName().contains("DisinheritanceLink")) {
				DisinheritanceLink dis = (DisinheritanceLink) link;
				System.out.println("scheduler DisinheritanceLink task "
						+ dis.getReceiverTask().getId() + " "
						+ dis.getSenderTask().getId() + " "
						+ dis.getOutcomeName());
				linkList.add(dis);
				System.out.println("scheduler link list size "
						+ linkList.size());
			}

		}
	}

	private void uygunKuyruklaraGonder(Behaviour b, int msgNum) {

		int a = 0;
		LinkedList<Task> tasks = b.getSubTasks();
		System.out.println("Scheduler uygunKuyruk() Subtask: " + tasks.size());
		System.out.println("Tasks size = " + tasks.size());
		for (int i = 0; i < tasks.size(); i++) {
			// Kullanilan action Action sinifindan turetildigi
			// icin superclass'i aliyoruz, 
			// iki defa turetildiyse hapi yuttuk
			if (tasks.get(i).getClass().getSuperclass().getName().contains("Action")) {

				System.out.println("Scheduler uygunKuyruk() Task Name: " + tasks.get(i).getName());
				if (tasks.get(i).isAllProvisionsAreSet()) {
					System.out.println("Scheduler uygunKuyruk() Action " + tasks.get(i).getId()	+ " tum provision setedildi");
					a = 1;
					System.out.println("Scheduler uygunKuyruk() list size "	+ linkList.size());
					ReadyQCell red = new ReadyQCell((Action) tasks.get(i),
							Integer.toString(msgNum), linkList);
					ReadyQ.add(red);
				
				}

				else {
					// System.out.println("Scheduler uygunKuyruk() pendingQ  atilan  Action "
					// + tasks.get(i).getId() +" plan no "+msgNum);
					// PendingQCell pend=new PendingQCell(msgNum, tasks.get(i));
					// PendingQ.add(pend);

				}
			}

			else /* Bu bir Behaviour'dur */
			{
				if (tasks.get(i).isAllProvisionsAreSet()) {
					BehaviourQCell beh = new BehaviourQCell(((Behaviour) tasks
							.get(i)), msgNum);
					BehaviourQ.add(beh);
					System.out.println("Scheduler uygunKuyruk() "
							+ ((Behaviour) tasks.get(i)).getId()
							+ " behaviur isAllprovision set edildi ");
					scheduler_wait_notify.V();

				} else {
					/*
					 * PendingQCell pend=new
					 * PendingQCell(msgNum,(tasks.get(i)));
					 * System.out.println("pendingQ "
					 * +(tasks.get(i)).getId()+" behaviur pending atıldı");
					 * PendingQ.add(pend);
					 */
				}

			}

		}

		if (a == 1) {
			executer_notify.V();
		}
	}

}