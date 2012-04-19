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

public class ac2007 extends Action {

	int PlanNum;
	public Provision Cevap;
	public Outcome Refuse;
	public Outcome Not_Understood;
	public Outcome Proposal;

	public ac2007() {
		this.setId("ac2007");
		this.setName("Pazarlik Cevap Degerlendir");
		(Cevap = new Provision()).setprovName("Cevap");
		(Refuse = new Outcome()).setName("Refuse");
		(Not_Understood = new Outcome()).setName("Not_Understood");
		(Proposal = new Outcome()).setName("Proposal");
		addOutcome(Refuse);
		addOutcome(Not_Understood);
		addOutcome(Proposal);
		addProvision(Cevap);
	}


	@Override
	public void doAction() {
		if(Cevap.getValue().toString().equalsIgnoreCase("refuse"))
		{
			Refuse.addOutput(null);
			Proposal.setOutputList(null);
			Not_Understood.setOutputList(null);
		}
		else if(Cevap.getValue().toString().equalsIgnoreCase("not_understood"))
		{
			Not_Understood.addOutput(null);
			Refuse.setOutputList(null);
			Proposal.setOutputList(null);
		}
		
		else if(Cevap.getValue().toString().equalsIgnoreCase("proposal"))
		{
		Proposal.addOutput(null);
		Refuse.setOutputList(null);
		Not_Understood.setOutputList(null);
		}
	}


	@Override
	public void sendMessage(FipaMessage fm) {
	
		getSendingMessages().add(fm);	
		
		
	}

}
