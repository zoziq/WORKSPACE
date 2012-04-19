package tiryaki.action;

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

public class ac2002 extends Action {

	int PlanNum;
	public Provision sorgu;

	public ac2002() {
		this.setId("ac2002");
		this.setName("Sorgu Gonder");
		(sorgu = new Provision()).setprovName("Sorgu");
		addProvision(sorgu);
	}


	@Override
	public void doAction() {
		System.out.println("doaction prov deger "+sorgu.getValue().toString());
		System.out.println("sorgu doaction");
		FipaMessage fm=prepareQueryMsg();
		sendMessage(fm);
		System.out.println("sorgu doaction 2");
	}

	private FipaMessage prepareQueryMsg() {
		Envelope env=new Envelope();
		ACLMessage acl=new ACLMessage();
		AgentIdentifier sender = new AgentIdentifier();
		sender.setName(new Word("Kullanici_Agent"));
		acl.setSender(sender);
	
		AgentIdentifier aidReceiver = new AgentIdentifier();
		aidReceiver.setName(new Word("df@hero.com"));
		
		
		/*
		URLSequence addrReceiver = new URLSequence();
		addrReceiver.add("rmi://192.168.2.23:1099/Agent02");
		aidReceiver.setAddresses(addrReceiver); 
		*/
		
		acl.addReceiver(aidReceiver);
		acl.setPerformative(CommunicativeActLibrary.REQUEST);
		acl.setOntology("multimedia");
	    env.setTransportBehavior("rmi");
	    env.setFrom(sender);
	    env.addTo(aidReceiver);
	    Content con=new Content();
	    acl.setLanguage("fipa-sl0");
	    FIPARDFContent0 content=new FIPARDFContent0();
		content.setAct("sorgu1");
		content.setActor("MMDepo_Agent");
		ArrayList ar=new ArrayList();
		ar.add(sorgu.getValue());
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
