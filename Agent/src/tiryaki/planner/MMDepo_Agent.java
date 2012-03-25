package tiryaki.planner;

import java.util.ArrayList;

import rmi.RemoteMessageServer;

import fipa.ACLMessage;
import fipa.CommunicativeActLibrary;
import fipa.Content;
import fipa.FIPARDFContent0;
import fipa.Envelope;
import fipa.FipaMessage;
import agent.AgentIdentifier;
import agent.Router;
import agent.URLSequence;
import agent.Word;

public class MMDepo_Agent extends Agent {

	public MMDepo_Agent(AgentIdentifier aid, String accAddress) {
		super(aid, accAddress);
	}

	public static void main(String[] args) {
		// start sender agent
		AgentIdentifier aidSender = new AgentIdentifier();
		aidSender.setName(new Word("username@hero.com"));

		URLSequence addrSender = new URLSequence();
		addrSender.add("rmi://192.168.106.94/Agent01");
		aidSender.setAddresses(addrSender);

		MMDepo_Agent mmDepo_Agent = new MMDepo_Agent(aidSender,
				"rmi://192.168.106.94/Agent01");

		new RemoteMessageServer(mmDepo_Agent);

		ACLMessage message = new ACLMessage();

		message.setSender(aidSender);

		AgentIdentifier receiver = new AgentIdentifier();
		receiver.setName(new Word("df@hero.com"));
		/*
		 * URLSequence addrReceiver = new URLSequence();
		 * addrReceiver.add("rmi://192.168.2.21:1099/Agent01");
		 * receiver.setAddresses(addrReceiver);
		 */

		message.addReceiver(receiver);

		message.setPerformative(CommunicativeActLibrary.REQUEST);
		message.setOntology("multimedia");
		message.setLanguage("ontoloji");
		message.setConversation_id("1");

		/*
		 * FIPARDFContent0 content = new FIPARDFContent0();
		 * content.setAct("sorgu"); content.setActor("receiverAgent"); ArrayList
		 * ar=new ArrayList(); ar.add("kayseri 5"); content.setArguments(ar);
		 * Content con=new Content(); con.addList(content);
		 * message.setContent(con);
		 */

		FIPARDFContent0 content = new FIPARDFContent0();
		content.setAct("register");
		content.setActor("ams@hero.com");
		ArrayList ar = new ArrayList();
		ar.add("kullanici@hero.com");
		ar.add("rmi://192.168.106.94/Agent01");
		ar.add("Kullanici Etmeni");
		content.setArguments(ar);
		Content con = new Content();
		con.addList(content);
		message.setContent(con);

		Envelope e = new Envelope();
		e.setFrom(aidSender);
		e.addTo(receiver);
		e.setTransportBehavior("rmi");
		FipaMessage fm = new FipaMessage(e, message);

		Router rt = new Router();
		rt.sendMessage(fm);

		// mmDepo_Agent.message(fm);

	}
}
