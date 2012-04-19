package tiryaki.planner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

import agent.AgentIdentifier;
import agent.AgentIdentifierList;
import agent.Router;

import fipa.ACLMessage;
import fipa.Envelope;
import fipa.FipaMessage;

import task.Action;
import task.Provision;
import task.Task;

public class Dispatcher extends Thread {

	private static int msgNum;

	private Agent local;

	private LinkedBlockingQueue incomingMessageQ;

	private LinkedBlockingQueue readyQ;

	private LinkedBlockingQueue objectiveQ;

	private LinkedBlockingQueue outMessageQ;

	private LinkedBlockingQueue PendingQ;

	private HashMap IncomeMessageQ;

	private Semaphore dispatcher_wait; // wait for something in incomingQ

	private Semaphore matcher_notify; // tell plan_schedular something in objQ

	private Semaphore executor_notify;

	private String performs[] = { "achieve", "advertise", "ask-all", "ask-if",
			"ask-one", "broadcast", "broker-all", "broker-one", "delete-all",
			"delete-one", "deny", "discard", "error", "eos", "forward",
			"insert", "next", "ready", "recommend-all", "recommend-one",
			"recruit-all", "recruit-one", "register", "rest", "sorry",
			"subscribe", "standby", "stream-all", "tell", "transport-address",
			"uninsert", "untell", "undelete", "unachieve", "unadvertise",
			"unregister", "unsubscribe", "accept-proposal", "agree", "cancel",
			"cfp", "confirm", "disconfirm", "failure", "inform", "inform-if",
			"inform-ref", "not-understood", "propagate", "propose", "proxy",
			"query-if", "query-ref", "refuse", "reject-proposal", "request",
			"request-when", "request-whenever", "subscribe", "other",
			"query ref" };

	public Dispatcher(LinkedBlockingQueue incomingMessageQ,
			LinkedBlockingQueue ObjectiveQ, LinkedBlockingQueue readyQ,
			LinkedBlockingQueue PendingQ, LinkedBlockingQueue OutMessageQ,
			HashMap incomeMsgQ, Agent local, Semaphore one, Semaphore two,
			Semaphore three) {
		this.incomingMessageQ = incomingMessageQ;
		this.readyQ = readyQ;
		this.objectiveQ = ObjectiveQ;
		this.outMessageQ = OutMessageQ;
		this.local = local;
		this.PendingQ = PendingQ;
		this.IncomeMessageQ = incomeMsgQ;
		this.dispatcher_wait = one;
		this.matcher_notify = two;
		this.executor_notify = three;
		msgNum = 1;
	}

	public void run() {
		while (true) {
			dispatcher_wait.P();
			try {
				runDispatcher();
			} catch (Exception e) {
				System.out.println("Dispatcher'da Hata: " + e);
			}
		}
	}

	private void runDispatcher() throws NonCriticalAgentException,
			CriticalAgentException {
		if ((incomingMessageQ != null) && (!(incomingMessageQ.isEmpty()))) {
			System.out.println("runDispatcher() => 1. if içi...");
			FipaMessage transportMessage = (FipaMessage) incomingMessageQ.poll();
			Dispatch(transportMessage);

		}
		if ((outMessageQ != null) && (!(outMessageQ.isEmpty()))) {
			System.out.println("Gidecek mesaj Dispatcher'a geldi...");
			OutgoingMessage outMsgQCell = (OutgoingMessage) outMessageQ.poll();
			System.out.println("1. Kontrol Satýrý");
			if (outMsgQCell.getMessage().getAcl().getReply_with() == null || outMsgQCell.getMessage().getAcl().getReply_with().equals("")) {
				System.out.println("2. Kontrol Satýrý");
				Iterator itr = IncomeMessageQ.keySet().iterator();
				System.out.println("3. Kontrol Satýrý");
				while (itr.hasNext()) {
					int k = Integer.parseInt(itr.next().toString());
					System.out.println("Context ID: " + outMsgQCell.getMessage().getAcl().getConversation_id());
					System.out.println("k: " + k);

					if (k == Integer.parseInt(outMsgQCell.getMessage().getAcl().getConversation_id())) {
						FipaMessage fm = (FipaMessage) (IncomeMessageQ.get(k));
						System.out.println("Mesaj no: "+ fm.getAcl().getReply_with());
						System.out.println("Reply with: "+ outMsgQCell.getMessage().getAcl().getReply_with());
						outMsgQCell.getMessage().getAcl().setIn_reply_to(fm.getAcl().getReply_with());
					}

				}

			}
			System.out.println("Mesaj gidiyoooooor... :)");
			send(outMsgQCell);

			try {
				// incomingMessageQ.add(outMsgQCell.getMessage());
				// dispatcher_wait.V();
			} catch (Exception e) {
				System.out.println("Error occured while dispatcher is running");
			}
		}
	}

	private void Dispatch(FipaMessage acl) throws NonCriticalAgentException,
			CriticalAgentException {

		ACLMessage aclmessage = acl.getAcl();
		String Performative = aclmessage.getPerformative();
		String Ontology = aclmessage.getOntology().toString();
		System.out.println(Performative);
		if (inPerforms(Performative)) {
			System.out.println("Dispatch: doðru...");
			achieve(acl); // ignore the name, this handles all performatives.
		} else {
			String error = ":error Bad Performative :fault " + Performative;
			System.out.println(error);
			// local.sendError(aclmessage, error);
		}
	}

	private boolean inPerforms(String performative) {
		int length = performs.length;
		try {
			for (int i = 0; i < length; i++) {
				if (performative.equals(performs[i]))
					return true;
			}
		} catch (java.lang.Exception e) {
		}
		return false;
	}

	public void achieve(FipaMessage msg) throws NonCriticalAgentException,
			CriticalAgentException {
		Key incomeKey;
		if (msg.getAcl().getIn_reply_to() == null) { // nothing just do the usual
			System.out.println("achieve(): if koþuluna girdi...");
			msgNum = Integer.parseInt(msg.getAcl().getConversation_id());
			Objective obj = new Objective(msg, msgNum);
			System.out.println("Objective nesnesi oluþturuldu.");
			objectiveQ.add(obj);
			matcher_notify.V();
			System.out.println("Matcher notfiy() edildi...");
			IncomeMessageQ.put(msgNum, msg);

		}

		else {

			System.out.println("Dispatcher mesaj geldi...");
			// check the pending Hashtable
			String InReplyTo = msg.getAcl().getIn_reply_to();
			int a = InReplyTo.indexOf('-');
			String conversation_ID = InReplyTo.substring(0, a);
			String actionID = InReplyTo.substring(a + 1, InReplyTo.length());

			System.out.println("Dispatcher içinde conversation ID: " + conversation_ID + " , Action ID: " + actionID);
			Iterator it = PendingQ.iterator();
			while (it.hasNext()) {

				System.out.println("Dispatcher içinde 1; PendingQ size: " + PendingQ.size());
				PendingQCell pend = (PendingQCell) it.next();
				int msgN = pend.getPlanNo();

				Task t1 = (Task) pend.getTask();
				Task t2 = (Task) pend.getSourceTask();
				Provision targetpro = (Provision) pend.getTargetProvision();
				int mesajNo = Integer.parseInt(conversation_ID);
				System.out.println("Pending mesaj no: " + msgN + " , gelen mesaj no: " + mesajNo);
				System.out.println("Dispatcher içinde 2; hedef ID: " + t1.getId());

				System.out.println("Dispatcher icinde 3; ......");
				if (t2 != null && actionID.equalsIgnoreCase(t2.getId())) {

					System.out.println("Dispatcher içinde 4; ........");
					for (int j = 0; j < pend.getTask().getProvisionList().size(); j++) {
						System.out.println("Dispatcher içinde 5; ........");
						Provision p = new Provision();
						p = t1.getProvisionList().get(j);
						System.out.println(p.getprovName());
						System.out.println(targetpro.getprovName());
						System.out.println("Dispatcher içinde 6; ........");
						if (targetpro.getprovName().equalsIgnoreCase(p.getprovName())) {
							// System.out.println(msg.getAcl().getContent().getActions().get(0).getArguments().get(0));
							p.setValue(msg.getAcl().getContent().getActions().get(0).getArguments().get(0));
							System.out.println("Dispatcher'da Provision set edildi...");
							it.remove();

						}
					}

					if (t1.isAllProvisionsAreSet()) {
						System.out.println("Action ID: " + t1.getId());

						ReadyQCell read = new ReadyQCell((Action) t1, Integer.toString(mesajNo), null);
						readyQ.add(read);
						System.out.println("Dispcther sonuna gelindi...");

					}

				}

			}

			if (readyQ != null || !(readyQ.isEmpty())) {
				executor_notify.V();
			}

		}
	}

	private void send(OutgoingMessage outMessageQCell) {
		// incomingMessageQ.add(outMessageQCell.getMessage());
		// dispatcher_wait.V();
		System.out.println("Mesaj, Dispatcher tarafýndan baþka etmene gönderiliyor...");
		Router rt = new Router();
		System.out.println("Mesaj gidiyor 1.");
		FipaMessage fm = outMessageQCell.getMessage();
		// System.out.println("sad"+fm.getEnvelope().getFrom().getName().getValue());
		System.out.println("Mesaj gidiyor 2..");
		rt.sendMessage(fm);
		System.out.println("Mesaj gidiyor 3...");

	}

	private Envelope prepareTransportMsg(AgentIdentifier sender,
			AgentIdentifierList receivers) {
		System.out.println("Envelope oluþturulacak.");
		Envelope transportMsg = new Envelope();
		Envelope env = new Envelope();
		AgentIdentifierList to = new AgentIdentifierList(AgentIdentifierList.SEQUENCE);
		for (int i = 0; i < receivers.size(); i++) {
			to.add(receivers.get(i));
		}
		env.setTo(to);
		env.setFrom(sender);
		env.setIntendedReceiver(to);
		return env;

	}
}