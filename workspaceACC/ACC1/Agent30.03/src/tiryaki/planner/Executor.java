package tiryaki.planner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JOptionPane;

import links.DisinheritanceLink;
import links.ExternalLink;
import links.Link;
import links.ProvisionOutcomeLink;

import task.Action;
import task.Behaviour;
import task.Outcome;
import task.Output;
import task.Provision;
import task.Task;
import fipa.FipaMessage;

public class Executor extends Thread {
	private LinkedBlockingQueue PendingQ;
	private HashMap sonucQ;
	private LinkedBlockingQueue readyQ;
	private LinkedBlockingQueue outMessageQ;
	private LinkedBlockingQueue RunningBehaviourQ;
	private LinkedBlockingQueue BehaviourQ;
	private ReadyQCell readQ;
	private Action readyAction;
	private Semaphore executor_wait_notify;
	private Semaphore scheduler_wait_notify;
	private Semaphore dispatcher_notify;
	private Semaphore Exec;
	ArrayList disLink = new ArrayList();

	public Executor(LinkedBlockingQueue readyQ,
			LinkedBlockingQueue OutMessageQ, LinkedBlockingQueue BehaviourQ,
			LinkedBlockingQueue RunningBehaviourQ,
			LinkedBlockingQueue PendingQ, HashMap SonucQ, Agent local,
			Semaphore execSem, Semaphore SchedSem, Semaphore Exec,
			Semaphore DispatcherSem) {

		this.PendingQ = PendingQ;
		this.outMessageQ = OutMessageQ;
		this.BehaviourQ = BehaviourQ;
		this.RunningBehaviourQ = RunningBehaviourQ;
		this.readyQ = readyQ;
		this.sonucQ = SonucQ;
		this.scheduler_wait_notify = SchedSem;
		this.executor_wait_notify = execSem;
		this.dispatcher_notify = DispatcherSem;
		this.Exec = Exec;
	}

	public void run() {
		while (true) {
			executor_wait_notify.P();
			try {
				System.out.println("Executor run()");
				runExecuter();
			} catch (Exception e) {
				System.out.println("executor calisirken hata olustu");
			}
		}
	}

	public void runExecuter() {
		if (readyQ == null)
			System.out.println("Executor  readyQ null");
		else if (readyQ.isEmpty()) {
			System.out.println("Executor readyQ bos");
		} else {

			System.out.println("Executor  executor");
			readQ = (ReadyQCell) readyQ.poll();
			// System.out.println("list size "+readQ.getLink().size());
			readyAction = (Action) readQ.getReady();

			execute();
		}
	}

	private void execute() {
		readyAction.doAction();
		System.out.println("Executor execute() girdi.");
		String actionID = readyAction.getId();
		int msgNum = Integer.parseInt(readQ.getConversationId());
		ArrayList gelenProvs = readyAction.getProvisionList();
		ArrayList gelenOutcomes = readyAction.getOutcomeList();

		Outcome outcome;
		Output out = null;

		if (readQ.getLink() != null) {
			disLink = readQ.getLink();
			// JOptionPane.showMessageDialog(null,"getlink size "+disLink.size());
		}

		System.out.println("Executor execute() " + actionID);
		System.out.println("Executor execute() convs id "
				+ readQ.getConversationId());
		System.out.println("Executor execute() gelen outcome size "
				+ gelenOutcomes.size());
		System.out.println("Executor execute() readyac send mesaj size "
				+ readyAction.getSendingMessages().size());

		for (int i = 0; i < readyAction.getSendingMessages().size(); i++) {
			Behaviour b1 = null;
			FipaMessage fm = (FipaMessage) readyAction.getSendingMessages().get(i);
			System.out.println("Executor execute() readyac sendingmessage "
					+ readyAction.getName());
			OutgoingMessage outMsgQCell = new OutgoingMessage(fm);
			outMessageQ.add(outMsgQCell);
			dispatcher_notify.V();

		}

		System.out.println("readyAction.getOutcomeList().size() = " + readyAction.getOutcomeList().size());
		for (int i = 0; i < readyAction.getOutcomeList().size(); i++) {
			System.out.println("Executor execute() readyAction "
					+ readyAction.getId());
			outcome = (Outcome) readyAction.getOutcomeList().get(i);
			if (outcome.getOutputList() != null) {

				System.out.println("Executor execute() disheritanceLink size  "
						+ disLink.size());
				for (int j = 0; j < disLink.size(); j++) {
					DisinheritanceLink dis = (DisinheritanceLink) disLink
							.get(j);
					System.out
							.println("Executor execute() disinheritance outcome name "
									+ dis.getOutcomeName()
									+ " "
									+ dis.getSenderTask().getId()
									+ " "
									+ dis.getReceiverTask().getId());
					System.out
							.println("Executor execute() disinheritance outcome name "
									+ outcome.getName() + " " + readyAction.getId());
					if (dis.getOutcomeName()
							.equalsIgnoreCase(outcome.getName())
							&& readyAction.getId().equalsIgnoreCase(
									dis.getSenderTask().getId())) {

						System.out
								.println("Executor execute() if disinheritance set edildi outcome name  "
										+ dis.getReceiverTask().getId()
										+ " "
										+ dis.getOutcomeName());
						Task task = dis.getReceiverTask();
						dis.setReceiverOutcome(outcome);
						for (int k = 0; k < disLink.size(); k++) {
							DisinheritanceLink dis1 = (DisinheritanceLink) disLink
									.get(k);
							System.out.println(task.getId() + " "
									+ dis1.getSenderOutcome().getName());

							if (dis1.getSenderTask().getId().equals(
									task.getId())) {
								System.out.println(dis1.getReceiverTask()
										.getId());
								dis1.setReceiverOutcome(outcome);
								System.out.println("task outcome set edildi "
										+ dis1.getReceiverTask().getId());
							}
						}

						// if(task.getClass().getSuperclass().){
						// behaviourDis((Behaviour)task);
						// System.out.println(dis.getOutcomeName()+" "+dis.getReceiverTask().getId());
						// }
						for (int k = 0; k < outcome.getOutputList().size(); k++) {
							for (int t = 0; t < PendingQ.size(); t++) {
								System.out.println("Executor execute() "
										+ outcome.getOutputList().get(k)
												.getName());
								PendingQCell pend = (PendingQCell) PendingQ
										.poll();
								System.out
										.println("Executor execute() pend size "
												+ PendingQ.size());

								Output output = (Output) outcome
										.getOutputList().get(k);
								System.out.println(output.getName());
								if (pend.getTargetProvision() != null
										&& output.getName().equalsIgnoreCase(
												pend.getTargetProvision()
														.getprovName())) {

									for (int l = 0; l < pend.getTask()
											.getProvisionList().size(); l++) {

										if (output.getName().equalsIgnoreCase(
												pend.getTask()
														.getProvisionList()
														.get(l).getprovName())) {
											pend.getTask().getProvisionList()
													.get(l).setValue(
															output.getValue());
											System.out
													.println("Executor execute() ozel set etme");
										}
									}

									if (pend.getTask().getClass()
											.getSuperclass().getName()
											.contains("Action")) {

										System.out
												.println("Executor execute() task exe "
														+ pend.getTask()
																.getId());
										if (pend.getTask()
												.isAllProvisionsAreSet()) {
											System.out
													.println("Executor execute() Action "
															+ pend.getTask()
																	.getId());
											ReadyQCell read = new ReadyQCell(
													(Action) pend.getTask(),
													Integer.toString(pend
															.getTask()
															.getPlanNum()),
													disLink);
											readyQ.add(read);
											System.out
													.println("Executor execute() provisioutcome link ile set etti  sona geldi");
										}
									}

									else {
										if (pend.getTask()
												.isAllProvisionsAreSet()) {
											System.out
													.println("Executor execute() Behaviour "
															+ pend.getTask()
																	.getId());
											BehaviourQCell beh = new BehaviourQCell(
													((Behaviour) pend.getTask()),
													msgNum);
											BehaviourQ.add(beh);
											System.out
													.println(((Behaviour) pend
															.getTask()).getId()
															+ " behaviur isAllprovision set edildi ");
											scheduler_wait_notify.V();
											System.out
													.println("Executor execute() provisioutcome link behavýour ile set etti  sona geldi");
										}
									}
								} else {
									PendingQ.add(pend);
								}

							}

						}

					}
				}
				System.out.println("Executor execute() dis link size "
						+ disLink.size());

				for (int k = 0; k < outcome.getOutputList().size(); k++) {
					System.out.println("output list size "
							+ outcome.getOutputList().size());
					for (int j = 0; j < PendingQ.size(); j++) {
						System.out.println("Executor execute() alt 1 "
								+ outcome.getOutputList().get(k).getName());
						PendingQCell pend = (PendingQCell) PendingQ.poll();
						System.out.println("Executor execute()  pend size"
								+ PendingQ.size());
						Output output = (Output) outcome.getOutputList().get(k);
						System.out.println("Executor execute() "
								+ output.getName());

						if (pend.getTargetProvision() != null
								&& output.getName()
										.equalsIgnoreCase(
												pend.getTargetProvision()
														.getprovName())) {
							for (int l = 0; l < pend.getTask()
									.getProvisionList().size(); l++) {

								if (output.getName().equalsIgnoreCase(
										pend.getTask().getProvisionList()
												.get(l).getprovName())) {
									pend.getTask().getProvisionList().get(l)
											.setValue(output.getValue());
									if (pend.getTask().getClass()
											.getSuperclass().getName()
											.contains("Action")) {
										System.out
												.println("Executor execute() task exe "
														+ pend.getTask()
																.getId());
										if (pend.getTask()
												.isAllProvisionsAreSet()) {
											System.out
													.println("Executor execute() Action "
															+ pend.getTask()
																	.getId());
											ReadyQCell read = new ReadyQCell(
													(Action) pend.getTask(),
													Integer.toString(msgNum),
													disLink);
											readyQ.add(read);
											System.out
													.println("Executor execute() en son provisioutcome link ile set etti  sona geldi");
										} else {
											PendingQ.add(pend);
										}

									} else {
										if (pend.getTask()
												.isAllProvisionsAreSet()) {
											System.out
													.println("Executor execute() Behaviour "
															+ pend.getTask()
																	.getId()
															+ " en son provisioutcome link ile set etti  sona geldi");
											BehaviourQCell beh = new BehaviourQCell(
													((Behaviour) pend.getTask()),
													msgNum);
											BehaviourQ.add(beh);
											scheduler_wait_notify.V();
										} else {
											PendingQ.add(pend);
										}
									}
								}
							}

						} else {
							PendingQ.add(pend);
						}

					}

				}

			}

		}

		for (int j = 0; j < PendingQ.size(); j++) {
			PendingQCell pend = (PendingQCell) PendingQ.poll();
			Task t = (Task) pend.getTask();
			int msgNum1 = pend.getPlanNo();
			System.out.println("Executor execute()  pendingtask isallset "
					+ t.getId());
			if (t.isAllProvisionsAreSet()) {
				System.out.println("Executor execute() Action " + t.getId());
				ReadyQCell red = new ReadyQCell((Action) t, Integer
						.toString(msgNum), disLink);
				readyQ.add(red);
				System.out.println("Executor execute() sona geldi");
			} else {
				PendingQ.add(pend);
			}
		}

		System.out.println("Executor execute() son dis link size "
				+ disLink.size());
		if (readyQ != null || !(readyQ.isEmpty())) {
			executor_wait_notify.V();
		}

	}

	public void behaviourDis(Behaviour beh)

	{
		boolean durum = false;
		for (int i = 0; i < beh.getOutcomeList().size(); i++) {
			Output out1 = null;
			Outcome outcome1 = (Outcome) beh.getOutcomeList().get(i);
			if (outcome1.getOutputList() != null) {

				System.out.println("Executor behaviourDis() sonuc atandi "
						+ outcome1.getOutputList().get(0).getName());
				out1 = (Output) outcome1.getOutputList().get(i);
				System.out.println("Executor behaviourDis() out name "
						+ out1.getName());
				System.out.println("Executor behaviourDis() dislink size  "
						+ disLink.size());
				for (int j = 0; j < disLink.size(); j++) {
					DisinheritanceLink dis = (DisinheritanceLink) disLink
							.get(j);
					System.out
							.println("Executor behaviourDis() disinheritance outcome name "
									+ dis.getOutcomeName()
									+ " "
									+ dis.getSenderTask().getId()
									+ " "
									+ dis.getReceiverTask().getId());
					System.out
							.println("Executor behaviourDis() disinheritance outcome name "
									+ outcome1.getName() + " " + beh.getId());
					if (dis.getOutcomeName().equalsIgnoreCase(
							outcome1.getName())
							&& beh.getId().equalsIgnoreCase(
									dis.getSenderTask().getId())) {

						System.out
								.println("Executor behaviourDis() if disinheritance set edildi outcome name  "
										+ dis.getReceiverTask().getId()
										+ " "
										+ dis.getOutcomeName());

						Task task = (Behaviour) dis.getReceiverTask();
						System.out.println("sadasdad " + task.getId());
						for (int k = 0; k < task.getOutcomeList().size(); k++) {
							System.out.println("aman " + outcome1.getName()
									+ " "
									+ task.getOutcomeList().get(k).getName());
							if (outcome1.getName().equalsIgnoreCase(
									task.getOutcomeList().get(k).getName())) {
								System.out.println("geldi mi " + task.getId());
								task.getOutcomeList().get(k).setOutputList(
										outcome1.getOutputList());
								disLink.remove(j);
								behaviourDis((Behaviour) task);
							}
						}

						System.out.println(dis.getOutcomeName() + " "
								+ dis.getReceiverTask().getId());

					}
				}
			}
		}

	}

}