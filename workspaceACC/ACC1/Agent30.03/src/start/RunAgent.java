package start;

import java.awt.Robot;
import java.io.Serializable;
import java.util.ArrayList;

import fipa.ACLMessage;
import fipa.FIPARDFContent0;
import fipa.Content;
import fipa.Envelope;
import fipa.FipaMessage;

import rmi.RemoteMessageServer;
import tiryaki.planner.Agent;
import agent.AID;
import agent.AgentDescription;
import agent.AgentState;
import agent.Router;
import ams.AmsMessage;


public class RunAgent implements Serializable
{
	public static void main(String[] args) throws Exception {
		/*
		AID aid = new AID();
		aid.setName("macnhester");
		aid.addAddress("rmi://localhost:1099/Agent01");
		AID aid1 = new AID();
		aid1.setName("valencia");
	//	Agent agent = new Agent();
	//	agent.setAid(aid1);
	//	new RemoteMessageServer(agent);
		ACLMessage msg = new ACLMessage();
//		msg.setPerformative(Performative.REQUEST.toString());
//		msg.setSender(aid);
//		msg.addReceiver(aid1);
		msg.setLanguage("fipa-sl0");
		AmsMessage reg=new AmsMessage();
		
		String sorgu =  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
        //   "PREFIX j.0: <http://www.semanticweb.org/ontologies/2010/11/AgentInd.owl#>" +
          "PREFIX Agent: <http://www.semanticweb.org/ontologies/2010/11/Agent.owl#> "+
           "SELECT ?address "+
           " WHERE {"+
           "?x Agent:name 'macnhester'.?x Agent:address ?address}";
		
		Content con=new Content();
		ArrayList ar=new ArrayList();
		//ar.add(sorgu);
		
		AgentDescription ams = null;
		FIPARDFContent0 conA=new FIPARDFContent0("address",ams, ar);
		con.addList(conA);
		msg.setContent(reg.register(aid,"real"));
		//msg.setContent(con);
		msg.setProtocol("fipa-request");
		Envelope evn = new Envelope();
		evn.setFrom(aid);
		evn.setTransport_behaviour("rmi");
		evn.addTo(aid);
		FipaMessage fm = new FipaMessage(evn, msg);
		Executor ex=new Executor(agent);
		ex.addReadyQueue(fm);
	//	agent.addincomingQueue(fm);
		agent.run();
		*/
	}
}
