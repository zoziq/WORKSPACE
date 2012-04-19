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

public class ac5001 extends Action {

	int PlanNum;
	public Provision Cevap;

	public ac5001() {
		this.setId("ac5001");
		this.setName("Pazarlik Cevap Degerlendir");
		(Cevap = new Provision()).setprovName("Cevap");
		addProvision(Cevap);
	}

	@Override
	public void doAction() {
		System.out.println("burasi ac5001");
		FipaMessage fm = prepareQueryMsg();
		sendMessage(fm);

	}

	@Override
	public void sendMessage(FipaMessage fm) {

		getSendingMessages().add(fm);

	}

	private FipaMessage prepareQueryMsg() {

		Envelope env = new Envelope();

		ACLMessage acl = new ACLMessage();

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
		Content con = new Content();
		FIPARDFContent0 content = new FIPARDFContent0();
		content.setAct("sorgu1");
		content.setActor("MMDepo_Agent");
		ArrayList ar = new ArrayList();
		ar.add(Cevap.getValue());
		content.setArguments(ar);

		con.addList(content);
		acl.setContent(con);
		acl.setConversation_id("1");
		env.setTransportBehavior("rmi");
		acl.setLanguage("fipa-sl0");
		FipaMessage message = new FipaMessage(env, acl);
		return message;

	}

}
