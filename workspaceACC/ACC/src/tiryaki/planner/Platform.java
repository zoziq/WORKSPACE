package tiryaki.planner;

import java.util.concurrent.LinkedBlockingQueue;

import agent.AgentIdentifier;
import agent.Word;


import fipa.ACLMessage;
import fipa.CommunicativeActLibrary;
import fipa.FIPARDFContent0;

public class Platform extends Thread {
	public ACLMessage message;

	LinkedBlockingQueue IncomingMessageQ;

	Semaphore dispatcher_notify;

	Semaphore platform_wait;

	/*
	 * FIPAMsg message2; AgentIdentifier aid; Word wrd;
	 */

	public Platform(LinkedBlockingQueue incomingMessageQ, Semaphore inMesQSem,
			Semaphore dispatcherSem) {

		this.IncomingMessageQ = incomingMessageQ;
		this.platform_wait = inMesQSem;
		this.dispatcher_notify = dispatcherSem;
		message = new ACLMessage();
		AgentIdentifier sender = new AgentIdentifier();
		sender.setName(new Word("senderAgent"));
		message.setSender(sender);
		AgentIdentifier receiver = new AgentIdentifier();
		receiver.setName(new Word("receiverAgent"));
		message.addReceiver(receiver);
		message.setPerformative(CommunicativeActLibrary.QUERY_REF);
		message.setOntology("multimedia");
		FIPARDFContent0 content = new FIPARDFContent0();
		content.setAct("sorgu");
		content.setActor("receiverAgent");
		System.out.println("message olustu");
	}

	public void run() {
		System.out.println(" platforma girdi");
		try {
			IncomingMessageQ.add(message);
			dispatcher_notify.V();
		} catch (Exception e) {
			System.out.println("dispatcher çalýþýrken hata oluþtu");
		}

	}
}