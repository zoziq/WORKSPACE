package test;

import java.util.ArrayList;

import task.Action;
import task.Outcome;
import agent.AgentIdentifier;
import agent.URLSequence;
import agent.Word;
import fipa.ACLMessage;
import fipa.CommunicativeActLibrary;
import fipa.Content;
import fipa.Envelope;
import fipa.FIPARDFContent0;
import fipa.FipaMessage;

public class Act3 extends Action{

	Outcome Ok;
	Outcome Fail;
	
	public Act3() {
	
		this.setId("2");
		this.setName("DF'ye bilgileri gonder");
		
		Ok = new Outcome();
		Ok.setName("Ok");
		Fail = new Outcome();
		Fail.setName("Fail");

		addOutcome(Ok);
		addOutcome(Fail);
		
	}
	
	@Override
	public void doAction() {
	
		System.out.println("DF'ye bilgileri gonder => doAction()...");
		FipaMessage fm = prepareMsg();
		System.out.println("--- Mesaj olu�turuldu. ---");
		System.out.println("--- Mesaj g�nderiliyor. ---");
		sendMessage(fm);
		
	}

	private FipaMessage prepareMsg() {
		
		Envelope envelope = new Envelope();
		ACLMessage aclMessage = new ACLMessage();
		
		AgentIdentifier sender = new AgentIdentifier();
		sender.setName(new Word("Sender Agent"));
		aclMessage.setSender(sender);
	
		AgentIdentifier receiver = new AgentIdentifier();
		receiver.setName(new Word("Receiver Agent"));
		
		URLSequence addrReceiver = new URLSequence();
		addrReceiver.add("rmi://127.0.0.1/ACC");
		receiver.setAddresses(addrReceiver); 
		
		aclMessage.addReceiver(receiver);
		aclMessage.setPerformative(CommunicativeActLibrary.REQUEST);
		aclMessage.setOntology("multimedia");
	    envelope.setTransportBehavior("rmi");
	    envelope.setFrom(sender);
	    envelope.addTo(receiver);
	    Content con = new Content();
	    aclMessage.setLanguage("fipa-sl0");
	    FIPARDFContent0 content=new FIPARDFContent0();
		content.setAct("mesaj");
		content.setActor("Test");
		ArrayList ar=new ArrayList();
		ar.add("Selam");
		ar.add("Agent");
		content.setArguments(ar);
		con.addList(content);
		aclMessage.setContent(con);
		aclMessage.setConversation_id("1");
		aclMessage.setReply_with(aclMessage.getConversation_id()+"-"+getId());
		
		FipaMessage message = new FipaMessage(envelope,aclMessage);
		return message;
		
	}

	@Override
	public void sendMessage(FipaMessage fm) {
		
		getSendingMessages().add(fm);
		
	}

	
}