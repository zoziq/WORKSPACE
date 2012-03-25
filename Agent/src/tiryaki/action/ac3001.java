package tiryaki.action;

import java.util.ArrayList;

import agent.AgentIdentifier;
import agent.OtelOntoloji;
import agent.Word;

import fipa.ACLMessage;
import fipa.CommunicativeActLibrary;
import fipa.Content;
import fipa.FIPARDFContent0;
import fipa.Envelope;
import fipa.FipaMessage;
import task.Action;
import task.Provision;

public class ac3001 extends Action {

	int PlanNum;
	public Provision location;

	public ac3001() {
		System.out.println("ac3001 burasi");
		this.setId("ac3001");
		this.setName("Etmen Bul");

		(location = new Provision()).setprovName("Location");
		addProvision(location);

	}

	@Override
	public void doAction() {

		OtelOntoloji on = new OtelOntoloji();
		on.getPlan(location.getValue().toString());
		// list=on.getPlan(location.getValue().toString());
		System.out.println("liste boyut " + location.getValue().toString());
		FipaMessage fm = prepareQueryMsg(on.getPlan(location.getValue()
				.toString()));
		sendMessage(fm);
		System.out.println("ac3001 doaction 4");

	}

	@Override
	public void sendMessage(FipaMessage fm) {
		getSendingMessages().add(fm);
		System.out.println("ac3001 doaction 5");

	}

	private FipaMessage prepareQueryMsg(ArrayList list) {
		Envelope env = new Envelope();
		ACLMessage acl = new ACLMessage();
		AgentIdentifier sender = new AgentIdentifier();
		sender.setName(new Word("Kullanici_Agent"));
		acl.setSender(sender);
		AgentIdentifier agent = null;

		AgentIdentifier aidReceiver = new AgentIdentifier();
		aidReceiver.setName(new Word("kullanici@hero.com"));

		/*
		 * URLSequence addrReceiver = new URLSequence();
		 * addrReceiver.add("rmi://192.168.2.23:1099/Agent01");
		 * aidReceiver.setAddresses(addrReceiver);
		 */
		env.setFrom(sender);
		env.addTo(aidReceiver);

		acl.addReceiver(aidReceiver);
		acl.setPerformative(CommunicativeActLibrary.INFORM);
		acl.setOntology("multimedia");
		Content con = new Content();
		FIPARDFContent0 content = new FIPARDFContent0();
		content.setAct("sorgu1");
		content.setActor("MMDepo_Agent");
		content.setArguments(list);
		con.addList(content);
		acl.setContent(con);
		acl.setConversation_id("1");
		env.setTransportBehavior("rmi");
		acl.setLanguage("fipa-sl0");
		FipaMessage message = new FipaMessage(env, acl);
		return message;

	}

}
