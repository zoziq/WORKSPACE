package pazarlik;

import java.util.ArrayList;
import java.util.Vector;

import agent.AgentIdentifier;
import agent.URLSequence;
import agent.Word;

import fipa.ACLMessage;
import fipa.CommunicativeActLibrary;
import fipa.Content;
import fipa.FIPARDFContent0;
import fipa.Envelope;
import fipa.FipaMessage;
import task.Action;
import task.Outcome;
import task.Provision;

public class ac2006 extends Action {


	public Provision Oda;

	public ac2006() {
		this.setId("ac2006");
		this.setName("Pazarlýk Ýstegi Gonder");
		(Oda = new Provision()).setprovName("Oda");
		addProvision(Oda);
	}


	@Override
	public void doAction() {
		
		System.out.println("Action içinde doaction provision deger "+Oda.getValue().toString());
		FipaMessage fm=prepareQueryMsg();
		sendMessage(fm);
	}

	private FipaMessage prepareQueryMsg() {
		Envelope env=new Envelope();
		ACLMessage acl=new ACLMessage();
		AgentIdentifier sender = new AgentIdentifier();
		sender.setName(new Word("Kullanici_Agent"));
		acl.setSender(sender);
		AgentIdentifier agent = sender;
		AgentIdentifier aidReceiver = sender;
		
		URLSequence addrReceiver = new URLSequence();
		addrReceiver.add("rmi://192.168.2.23:1099/Agent02");
		aidReceiver.setAddresses(addrReceiver); 
		
		 env.setFrom(sender);
		env.addTo(aidReceiver);
		
		acl.addReceiver(aidReceiver);
		acl.setPerformative(CommunicativeActLibrary.CFP);
		acl.setOntology("otel");
		 acl.setLanguage("fipa-sl0");
		 
	    env.setTransportBehavior("rmi");
	    Content con=new Content();
	    FIPARDFContent0 content=new FIPARDFContent0();
		content.setAct("sorgu3");
		content.setActor("MMDepo_Agent");
		ArrayList ar=new ArrayList();
		ar.add(Oda.getValue());
		content.setArguments(ar);
		con.addList(content);
		acl.setContent(con);
		acl.setConversation_id("1");
		acl.setReply_with(acl.getConversation_id()+"-"+getId());
		FipaMessage message = new FipaMessage(env,acl);
		return message;

	}


	@Override
	public void sendMessage(FipaMessage fm) {
	
		getSendingMessages().add(fm);	
		
		
	}

}
