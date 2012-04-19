package tiryaki.action;

import java.util.ArrayList;
import java.util.Vector;

import agent.AgentIdentifier;
import agent.OdaOntoloji;
import agent.URLSequence;
import agent.Word;

import fipa.ACLMessage;
import fipa.CommunicativeActLibrary;
import fipa.Content;
import fipa.Envelope;
import fipa.FIPARDFContent0;
import fipa.FipaMessage;
import task.Action;
import task.Outcome;
import task.Output;
import task.Provision;

public class ac4001 extends Action {

	int PlanNum;
	public Provision location;
	public Outcome Ok;
	public Outcome Fail;

	public ac4001() {
		System.out.println("ac4001 burasi");
		this.setId("ac4001");
		this.setName("Sorgu Olustur");
	
		(location = new Provision()).setprovName("Location");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		addOutcome(Ok);
		addOutcome(Fail);
		addProvision(location);
		
	}

	@Override
	public void doAction() {
	System.out.println("location "+location.getValue().toString());
		OdaOntoloji on=new OdaOntoloji();
		FipaMessage fm=prepareQueryMsg(on.getPlan(location.getValue().toString()));
		sendMessage(fm);
		
	}

	@Override
	public void sendMessage(FipaMessage fm) {
		getSendingMessages().add(fm);	
		System.out.println("ac4001 doaction 5");
		
		
	}
	
	private FipaMessage prepareQueryMsg(ArrayList list) {
		Envelope env=new Envelope();
		ACLMessage acl=new ACLMessage();
		AgentIdentifier sender = new AgentIdentifier();
		sender.setName(new Word("Kullanici_Agent"));
		acl.setSender(sender);
		
		AgentIdentifier aidReceiver = new AgentIdentifier();
		aidReceiver.setName(new Word("kullanici@hero.com"));
		
		URLSequence addrReceiver = new URLSequence();
		addrReceiver.add("rmi://192.168.2.23:1099/Agent01");
		aidReceiver.setAddresses(addrReceiver); 
		
		 env.setFrom(sender);
		env.addTo(aidReceiver);
		    
		acl.addReceiver(aidReceiver);
		acl.setPerformative(CommunicativeActLibrary.INFORM);
		acl.setOntology("multimedia");
		 acl.setLanguage("fipa-sl0");
	    Content con=new Content();
	    FIPARDFContent0 content=new FIPARDFContent0();
		content.setAct("sorgu2");
		content.setActor("MMDepo_Agent");
		content.setArguments(list);
		con.addList(content);
		acl.setContent(con);
		acl.setConversation_id("1");
		 env.setTransportBehavior("rmi");
		 
		FipaMessage message = new FipaMessage(env,acl);
		return message;

	}


	

}
