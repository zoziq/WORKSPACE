package test;

import java.util.ArrayList;

import agent.AgentIdentifier;
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
import task.Provision;

public class Act2 extends Action{

	Provision Agent;
	Outcome Ok;
	Outcome Fail;
	
	public Act2() {
	
		this.setId("3");
		this.setName("act2");
		
		Agent = new Provision();
		Agent.setprovName("Agent");
		Ok = new Outcome();
		Ok.setName("Ok");
		Fail = new Outcome();
		Fail.setName("Fail");

		addProvision(Agent);
		addOutcome(Ok);
		addOutcome(Fail);
		
	}
	
	@Override
	public void doAction() {
	
		System.out.println("bi�eler...");
		
	}

	@Override
	public void sendMessage(FipaMessage fm) {
		
		getSendingMessages().add(fm);
		
	}

}
