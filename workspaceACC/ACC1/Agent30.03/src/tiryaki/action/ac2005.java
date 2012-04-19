package tiryaki.action;

import java.util.ArrayList;
import java.util.StringTokenizer;
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
import task.Output;
import task.Provision;

public class ac2005 extends Action {

	int PlanNum;
	public Provision Cevap;
	public Outcome Ok;
	public Outcome Fail;

	public ac2005() {
		this.setId("ac2005");
		this.setName("Cevabý Deðerlendir");
		(Cevap = new Provision()).setprovName("Cevap");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		addOutcome(Ok);
		addOutcome(Fail);
		addProvision(Cevap);
	}


	@Override
	public void doAction() {
		System.out.println("cevap degeri "+Cevap.getValue().toString());
		Output out=new Output();
		out.setName("Oda");
		StringTokenizer st=new StringTokenizer(Cevap.getValue().toString());
		String adres = null;
		while(st.hasMoreTokens())
		{
			
			out.setValue(st.nextToken()+" "+st.nextToken());
		}
		
		Ok.addOutput(out);
		Fail.setOutputList(null);
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
		acl.setPerformative(CommunicativeActLibrary.QUERY_REF);
		acl.setOntology("multimedia");
	    acl.setReply_with("sorgu2000");
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
		acl.setIn_reply_to(acl.getConversation_id()+"-"+getId());
		FipaMessage message = new FipaMessage(env,acl);
		return message;

	}


	@Override
	public void sendMessage(FipaMessage fm) {
	
		getSendingMessages().add(fm);	
		
		
	}

}
