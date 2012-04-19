package pazarlik;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

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
import task.Output;
import task.Provision;

public class ac2010 extends Action {

	int PlanNum;
	public Provision Propose;
	public Outcome Fail;

	public ac2010() {
		this.setId("ac2010");
		this.setName("Pazarlýktan Çekilme Bildir");
		(Propose = new Provision()).setprovName("Propose");
		(Fail = new Outcome()).setName("Fail");
		addOutcome(Fail);
		addProvision(Propose);
	}


	@Override
	public void doAction() {
		Output out=new Output();
		out.setName(Propose.getprovName());
		out.setValue(Propose.getValue());
		Fail.addOutput(out);
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
		ar.add(Propose.getprovName().toString());
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
