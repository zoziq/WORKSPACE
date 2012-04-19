package tiryaki.action;

import java.util.ArrayList;

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
import task.Provision;

public class ac2004 extends Action {

	public Provision AIDs;

	public ac2004() {
		this.setId("ac2004");
		this.setName("Uygunluk Sorgusu Gonder");
		(AIDs = new Provision()).setprovName("AIDs");
		addProvision(AIDs);

	}

	@Override
	public void doAction() {

		System.out.println("aid deger " + AIDs.getValue().toString());

		String sorgu = "kral";

		System.out.println("doaction prov deger " + AIDs.getValue().toString());
		System.out.println("sorgu doaction");
		FipaMessage fm = prepareQueryMsg(sorgu);
		sendMessage(fm);
		System.out.println("sorgu doaction 2");
	}

	private FipaMessage prepareQueryMsg(String sorgu) {
		Envelope env = new Envelope();
		ACLMessage acl = new ACLMessage();
		AgentIdentifier sender = new AgentIdentifier();
		sender.setName(new Word("Kullanici_Agent"));
		acl.setSender(sender);

		AgentIdentifier aidReceiver = new AgentIdentifier();
		aidReceiver.setName(new Word("df@hero.com"));

		URLSequence addrReceiver = new URLSequence();
		addrReceiver.add(AIDs.getValue().toString());
		aidReceiver.setAddresses(addrReceiver);

		env.setFrom(sender);

		env.addTo(aidReceiver);
		acl.addReceiver(aidReceiver);
		acl.setPerformative(CommunicativeActLibrary.REQUEST);
		acl.setOntology("otel");
		env.setTransportBehavior("rmi");

		Content con = new Content();
		acl.setLanguage("fipa-sl0");
		FIPARDFContent0 content = new FIPARDFContent0();
		content.setAct("sorgu2");
		content.setActor("MMDepo_Agent");
		ArrayList ar = new ArrayList();
		ar.add(sorgu);
		content.setArguments(ar);
		con.addList(content);
		acl.setContent(con);

		acl.setConversation_id("1");
		acl.setReply_with(acl.getConversation_id() + "-" + getId());
		FipaMessage message = new FipaMessage(env, acl);
		return message;

	}

	@Override
	public void sendMessage(FipaMessage fm) {

		getSendingMessages().add(fm);

	}

}
