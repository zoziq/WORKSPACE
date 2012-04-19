package test;

import agent.AgentIdentifier;
import agent.Word;
import tiryaki.planner.Agent;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		AgentIdentifier aid = new AgentIdentifier();
		aid.setName(new Word("agent61@mshn.com"));

		Agent agent = new Agent(aid, "rmi://192.168.2.234/Agent", "Beh1");
		
	}
	
}
