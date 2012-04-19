package tiryaki.planner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

import links.DisinheritanceLink;

import task.Action;
import task.Behaviour;
import task.Outcome;
import task.Output;
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
				System.out.println("Executor run() metodu...");
				runExecuter();
			} catch (Exception e) {
				System.out.println("Executor calisirken hata olustu!");
			}
		}
	}

	public void runExecuter() {
		if (readyQ == null)
			System.out.println("Executor => readyQ null...");
		else if (readyQ.isEmpty()) {
			System.out.println("Executor => readyQ bos...");
		} else {

			System.out.println("Executor runExecutor() metodu...");
			readQ = (ReadyQCell) readyQ.poll();
			// System.out.println("list size "+readQ.getLink().size());
			readyAction = (Action) readQ.getReady();

			execute();
		}
	}

	private void execute() {
		readyAction.doAction();
		System.out.println("Executor execute() metoduna girdi.");
		String actionID = readyAction.getId();
		int msgNum = Integer.parseInt(readQ.getConversationId());
		ArrayList gelenProvs = readyAction.getProvisionList();
		ArrayList gelenOutcomes = readyAction.getOutcomeList();

		Outcome outcome;
		Output out = null;

		if (readQ.getLink() != null) {
			disLink = readQ.getLink();
		}

		System.out.println("Executor execute() icerisinde actionID: " + actionID);
		System.out.println("Executor execute() icerisinde conversationID: "
				+ readQ.getConversationId());
		System.out.println("Executor execute() icerisinde gelen Outcome size: "
				+ gelenOutcomes.size());
		System.out.println("Executor execute() icerisinde readyAction send mesaj size: "
				+ readyAction.getSendingMessages().size());

		for (int i = 0; i < readyAction.getSendingMessages().size(); i++) {
			Behaviour b1 = null;
			FipaMessage fm = (FipaMessage) readyAction.getSendingMessages()
					.get(i);
			System.out.println("Executor execute() icerisinde readyAction sendingMessage: "
					+ readyAction.getName());
			OutgoingMessage outMsgQCell = new OutgoingMessage(fm);
			outMessageQ.add(outMsgQCell);
			dispatcher_notify.V();

		}

		System.out.println("readyAction.getOutcomeList().size() = "
				+ readyAction.getOutcomeList().size());
		for (int i = 0; i < readyAction.getOutcomeList().size(); i++) {
			System.out.println("Executor execute() icerisinde readyAction ID:  "
					+ readyAction.getId());
			outcome = (Outcome) readyAction.getOutcomeList().get(i);
			if (outcome.getOutputList() != null) {

				System.out.println("Executor execute() icerisinde disheritanceLink size:  "
						+ disLink.size());
				for (int j = 0; j < disLink.size(); j++) {
					DisinheritanceLink dis = (DisinheritanceLink) disLink.get(j);
					System.out.println("Executor execute() icerisinde disinheritance Outcome name: " + dis.getOutcomeName()	+ " senderID: " + dis.getSenderTask().getId()	+ " receiverID: " + dis.getReceiverTask().getId());
					System.out.println("Executor execute() icerisinde disinheritance Outcome name: " + outcome.getName() + "readyActionID: " + readyAction.getId());
					if (dis.getOutcomeName().equalsIgnoreCase(outcome.getName()) && readyAction.getId().equalsIgnoreCase(dis.getSenderTask().getId())) {

						System.out.println("Executor execute() icerisinde disinheritance set edilmisse: Outcome ID: " + dis.getReceiverTask().getId() + " Outcome name: " + dis.getOutcomeName());
						Task task = dis.getReceiverTask();
						dis.setReceiverOutcome(outcome);
						for (int k = 0; k < disLink.size(); k++) {
							DisinheritanceLink dis1 = (DisinheritanceLink) disLink.get(k);
							System.out.println("Executor execute() icerisinde disInhLink TaskID: " + task.getId() + " senderOutcomeName: " + dis1.getSenderOutcome().getName());

							if (dis1.getSenderTask().getId().equals(task.getId())) {
								System.out.println("TaskID: " + dis1.getReceiverTask().getId());
								dis1.setReceiverOutcome(outcome);
								System.out.println("Task Outcome set edildi; ID: " + dis1.getReceiverTask().getId());
							}
						}

						// if(task.getClass().getSuperclass().){
						// behaviourDis((Behaviour)task);
						// System.out.println(dis.getOutcomeName()+" "+dis.getReceiverTask().getId());
						// }
						for (int k = 0; k < outcome.getOutputList().size(); k++) {
							for (int t = 0; t < PendingQ.size(); t++) {
								System.out.println("Executor execute() icerisinde OutputList name: " + outcome.getOutputList().get(k).getName());
								PendingQCell pend = (PendingQCell) PendingQ.poll();
								System.out.println("Executor execute() icerisinde pend size: " + PendingQ.size());

								Output output = (Output) outcome.getOutputList().get(k);
								System.out.println(output.getName());
								if (pend.getTargetProvision() != null&& output.getName().equalsIgnoreCase(pend.getTargetProvision().getprovName())) {

									for (int l = 0; l < pend.getTask().getProvisionList().size(); l++) {

										if (output.getName().equalsIgnoreCase(pend.getTask().getProvisionList().get(l).getprovName())) {
											pend.getTask().getProvisionList().get(l).setValue(output.getValue());
											System.out.println("Executor execute() icerisinde ozel set etme islemi...");
										}
									}

									if (pend.getTask().getClass().getSuperclass().getName().contains("Action")) {

										System.out.println("Executor execute() icerisinde task exe ID: " + pend.getTask().getId());
										if (pend.getTask().isAllProvisionsAreSet()) {
											System.out.println("Executor execute() icerisinde Action ID: " + pend.getTask().getId());
											ReadyQCell read = new ReadyQCell(
													(Action) pend.getTask(),
													Integer.toString(pend
															.getTask()
															.getPlanNum()),
													disLink);
											readyQ.add(read);
											System.out.println("Executor execute() icerisinde ProvisionOutcomeLink ile set etti sona geldi...");
										}
									}

									else {
										if (pend.getTask().isAllProvisionsAreSet()) {
											System.out.println("Executor execute() icerisinde Behaviour ID: " + pend.getTask().getId());
											BehaviourQCell beh = new BehaviourQCell(((Behaviour) pend.getTask()), msgNum);
											BehaviourQ.add(beh);
											System.out.println(((Behaviour) pend.getTask()).getId() + " ID'li Behaviour'ýn tum Provision'lari set edildi.");
											scheduler_wait_notify.V();
											System.out.println("Executor execute() icerisinde ProvisionOutcomeLink Behavýour ile set etti ve sona geldi.");
										}
									}
								} else {
									PendingQ.add(pend);
								}

							}

						}

					}
				}
				System.out.println("Executor execute() icerisinde disLink size: " + disLink.size());

				for (int k = 0; k < outcome.getOutputList().size(); k++) {
					System.out.println("Executor execute() icerisinde OutputList size: " + outcome.getOutputList().size());
					for (int j = 0; j < PendingQ.size(); j++) {
						System.out.println("Executor execute() icerisinde alt 1 name: " + outcome.getOutputList().get(k).getName());
						PendingQCell pend = (PendingQCell) PendingQ.poll();
						System.out.println("Executor execute() icerisinde pend size: " + PendingQ.size());
						Output output = (Output) outcome.getOutputList().get(k);
						System.out.println("Executor execute() icerisinde Output name: " + output.getName());

						if (pend.getTargetProvision() != null && output.getName().equalsIgnoreCase(pend.getTargetProvision().getprovName())) {
							for (int l = 0; l < pend.getTask().getProvisionList().size(); l++) {

								if (output.getName().equalsIgnoreCase(pend.getTask().getProvisionList().get(l).getprovName())) {
									pend.getTask().getProvisionList().get(l).setValue(output.getValue());
									if (pend.getTask().getClass().getSuperclass().getName().contains("Action")) {
										System.out.println("Executor execute() icerisinde Task exe ID: " + pend.getTask().getId());
										if (pend.getTask().isAllProvisionsAreSet()) {
											System.out.println("Executor execute() icerisinde Action ID: " + pend.getTask().getId());
											ReadyQCell read = new ReadyQCell(
													(Action) pend.getTask(),
													Integer.toString(msgNum),
													disLink);
											readyQ.add(read);
											System.out.println("Executor execute() icerisnde en son ProvisionOutcomeLink ile set etti ve sona geldi.");
										} else {
											PendingQ.add(pend);
										}

									} else {
										if (pend.getTask().isAllProvisionsAreSet()) {
											System.out.println("Executor execute() icerisinde Behaviour ID: " + pend.getTask().getId() + " en son ProvisionOutcomeLink ile set etti ve sona geldi.");
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
			System.out.println("Executor execute()  icerisinde Pending Task'larýn hepsi set edilmiþ mi? ID: " + t.getId());
			if (t.isAllProvisionsAreSet()) {
				System.out.println("Executor execute() icerisinde Action ID: " + t.getId());
				ReadyQCell red = new ReadyQCell((Action) t, Integer
						.toString(msgNum), disLink);
				readyQ.add(red);
				System.out.println("Executor execute() icerisinde sona gelindi.");
			} else {
				PendingQ.add(pend);
			}
		}

		System.out.println("Executor execute() icerisinde son disLink size: " + disLink.size());
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

				System.out.println("Executor behaviourDis() icerisinde sonuc atandi. Name: " + outcome1.getOutputList().get(0).getName());
				out1 = (Output) outcome1.getOutputList().get(i);
				System.out.println("Executor behaviourDis() icerisinde Output name: " + out1.getName());
				System.out.println("Executor behaviourDis() icerisinded disLink size: " + disLink.size());
				for (int j = 0; j < disLink.size(); j++) {
					DisinheritanceLink dis = (DisinheritanceLink) disLink.get(j);
					System.out.println("Executor behaviourDis() icerisinde disinheritance Outcome name: "
									+ dis.getOutcomeName()
									+ " senderID: "
									+ dis.getSenderTask().getId()
									+ " receiverID: "
									+ dis.getReceiverTask().getId());
					System.out.println("Executor behaviourDis() icerisinde disinheritance Outcome name: "+ outcome1.getName() + " ID: " + beh.getId());
					if (dis.getOutcomeName().equalsIgnoreCase(outcome1.getName()) && beh.getId().equalsIgnoreCase(dis.getSenderTask().getId())) {

						System.out.println("Executor behaviourDis() icerisinde eger disinheritance set edilmisse Outcome ID:  "
										+ dis.getReceiverTask().getId()
										+ " Outcome Name: "
										+ dis.getOutcomeName());

						Task task = (Behaviour) dis.getReceiverTask();
						System.out.println("disInh Task ID: " + task.getId());
						for (int k = 0; k < task.getOutcomeList().size(); k++) {
							System.out.println("Outcome name: " + outcome1.getName()
									+ " OutcomeList name: "
									+ task.getOutcomeList().get(k).getName());
							if (outcome1.getName().equalsIgnoreCase(
									task.getOutcomeList().get(k).getName())) {
								System.out.println("Outcome ID: " + task.getId());
								task.getOutcomeList().get(k).setOutputList(
										outcome1.getOutputList());
								disLink.remove(j);
								behaviourDis((Behaviour) task);
							}
						}

						System.out.println("disOutcome name: " + dis.getOutcomeName() + " receiverID: "
								+ dis.getReceiverTask().getId());

					}
				}
			}
		}

	}

}