package tiryaki.action;

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

public class ac5002 extends Action {

	int PlanNum;
	public Provision Propose;
	public Outcome Reject_Proposal;
	public Outcome Proposal;
	public Outcome Accept_Proposal;

	public ac5002() {
		this.setId("ac2008");
		this.setName("Teklif Degerlendir");
		(Propose = new Provision()).setprovName("Propose");
		(Reject_Proposal = new Outcome()).setName("Reject_Proposal");
		(Accept_Proposal = new Outcome()).setName("Accept_Proposal");
		(Proposal = new Outcome()).setName("Proposal");
		addOutcome(Reject_Proposal);
		addOutcome(Accept_Proposal);
		addOutcome(Proposal);
		addProvision(Propose);
	}


	@Override
	public void doAction() {
		
		
		if(Propose.getValue().toString().equalsIgnoreCase("Reject_Proposal"))
		{
			Reject_Proposal.addOutput(null);
			Proposal.setOutputList(null);
			Accept_Proposal.setOutputList(null);
		}
		else if(Propose.getValue().toString().equalsIgnoreCase("Accept_Proposal"))
		{
			Accept_Proposal.addOutput(null);
			Reject_Proposal.setOutputList(null);
			Proposal.setOutputList(null);
		}
		
		else if(Propose.getValue().toString().equalsIgnoreCase("proposal"))
		{
			FipaMessage fm=prepareQueryMsg();
			sendMessage(fm);
			Proposal.addOutput(null);
			Reject_Proposal.setOutputList(null);
			Accept_Proposal.setOutputList(null);
		}
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
		addrReceiver.add("rmi://localhost/MMDepo_Agent");
		aidReceiver.setAddresses(addrReceiver);
		acl.addReceiver(aidReceiver);
		acl.setPerformative(CommunicativeActLibrary.CFP);
		acl.setOntology("otel");
	    env.setTransportBehavior("rmi");
	    Content con=new Content();
	    FIPARDFContent0 content=new FIPARDFContent0();
		content.setAct("sorgu");
		content.setActor("MMDepo_Agent");
		ArrayList ar=new ArrayList();
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
