package tiryaki.planner;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

import links.ExternalLink;

import agent.AgentIdentifier;
import agent.AgentIdentifierList;
import agent.Router;

import fipa.ACLMessage;
import fipa.Envelope;
import fipa.Expression;
import fipa.FipaMessage;
import fipa.StringLiteral;

import task.Action;
import task.Outcome;
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
				System.out.println("dispatcer da:" + e);
			}
		}
	}

	private void runDispatcher() throws NonCriticalAgentException,
			CriticalAgentException {
		if ((incomingMessageQ != null) && (!(incomingMessageQ.isEmpty()))) {
			FipaMessage transportMessage = (FipaMessage)incomingMessageQ.poll();
			Dispatch(transportMessage);

		}
		if ((outMessageQ != null) && (!(outMessageQ.isEmpty()))) {
			System.out.println("gidecek mesaj dispatchere geldi :) ");
			OutgoingMessage outMsgQCell = (OutgoingMessage) outMessageQ.poll();
			System.out.println("1");
			if (outMsgQCell.getMessage().getAcl().getReply_with() == null
					|| outMsgQCell.getMessage().getAcl().getReply_with()
							.equals("")) {
				System.out.println("2");
				Iterator itr = IncomeMessageQ.keySet().iterator();
				System.out.println("3");
				while (itr.hasNext()) {
					int k = Integer.parseInt(itr.next().toString());
					System.out.println("con id "
							+ outMsgQCell.getMessage().getAcl()
									.getConversation_id());
					System.out.println(k);

					if (k == Integer.parseInt(outMsgQCell.getMessage().getAcl()
							.getConversation_id())) {
						FipaMessage fm = (FipaMessage) (IncomeMessageQ.get(k));
						System.out.println("mesaj nooooo "
								+ fm.getAcl().getReply_with());
						System.out.println("reply with "
								+ outMsgQCell.getMessage().getAcl()
										.getReply_with());
						outMsgQCell.getMessage().getAcl().setIn_reply_to(
								fm.getAcl().getReply_with());
					}

				}

			}
			System.out.println("mesaj gidiyoooooo :)");
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
			System.out.println("dispatch: dogru");
			achieve(acl); // ignore the name, this handles all
			// performatives.
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
		if (msg.getAcl().getIn_reply_to() == null) { // nothing just do the
			// usual
			System.out.println("archieve: if e girdi");
			msgNum = Integer.parseInt(msg.getAcl().getConversation_id());
			Objective obj = new Objective(msg, msgNum);
			objectiveQ.add(obj);
			matcher_notify.V();
			IncomeMessageQ.put(msgNum, msg);

		}

		else {

			System.out.println("dispacther mesaj geldi");
			// check the pending Hashtable
			String InReplyTo = msg.getAcl().getIn_reply_to();
			int a = InReplyTo.indexOf('-');
			String conversation_ID = InReplyTo.substring(0, a);
			String actionID = InReplyTo.substring(a + 1, InReplyTo.length());

			System.out.println("dispacther icinde conversation "
					+ conversation_ID + " action " + actionID);
			Iterator it = PendingQ.iterator();
			while (it.hasNext()) {

				System.out.println("dispacther mesaj geldi icinde 1 "
						+ PendingQ.size());
				PendingQCell pend = (PendingQCell) it.next();
				int msgN = pend.getPlanNo();

				Task t1 = (Task) pend.getTask();
				Task t2 = (Task) pend.getSourceTask();
				Provision targetpro = (Provision) pend.getTargetProvision();
				int mesajNo = Integer.parseInt(conversation_ID);
				System.out.println("pending mesaj no " + msgN
						+ " gelen mesja no " + mesajNo);
				System.out.println("dispacther mesaj geldi icinde 2 hedef "
						+ t1.getId());

				System.out.println("dispacther mesaj geldi icinde 3");
				if (t2 != null && actionID.equalsIgnoreCase(t2.getId())) {

					System.out.println("dispacther mesaj geldi icinde 4");
					for (int j = 0; j < pend.getTask().getProvisionList()
							.size(); j++) {
						System.out.println("dispacther mesaj geldi icinde 5");
						Provision p = new Provision();
						p = t1.getProvisionList().get(j);
						System.out.println(p.getprovName());
						System.out.println(targetpro.getprovName());
						System.out.println("dispacther mesaj geldi icinde 6");
						if (targetpro.getprovName().equalsIgnoreCase(
								p.getprovName())) {
							// System.out.println(msg.getAcl().getContent().getActions().get(0).getArguments().get(0));
							p.setValue(msg.getAcl().getContent().getActions()
									.get(0).getArguments().get(0));
							System.out
									.println("dispatcherda provision set edildi");
							it.remove();

						}
					}

					if (t1.isAllProvisionsAreSet()) {
						System.out.println("Action " + t1.getId());

						ReadyQCell read = new ReadyQCell((Action) t1, Integer
								.toString(mesajNo), null);
						readyQ.add(read);
						System.out.println("dispcther sona geldi");

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
		System.out.println("mesaj dispatcher tarafindan baska etmene gonderiliyor");
		Router rt = new Router();
		System.out.println("mesaj gidiyor 1");
		FipaMessage fm = outMessageQCell.getMessage();
		//System.out.println("sad"+fm.getEnvelope().getFrom().getName().getValue());
		System.out.println("mesaj gidiyor 2");
		rt.sendMessage(fm);
		System.out.println("mesaj gidiyor 3");

	}

	private Envelope prepareTransportMsg(AgentIdentifier sender,
			AgentIdentifierList receivers) {
		System.out.println("envelope olusturulacak");
		Envelope transportMsg = new Envelope();
		Envelope env = new Envelope();
		AgentIdentifierList to = new AgentIdentifierList(
				AgentIdentifierList.SEQUENCE);
		for (int i = 0; i < receivers.size(); i++) {
			to.add(receivers.get(i));
		}
		env.setTo(to);
		env.setFrom(sender);
		env.setIntendedReceiver(to);
		return env;

	}
}