package df;

import agent.AID;
import agent.AgentDescription;
import fipa.FipaMessage;

public class DirectoryFacilitator implements DFInterface {

	AID name;
	ServisDescription sd;

	@Override
	public void deregister(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modify(AgentDescription agentDesc) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean register(FipaMessage fm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String search(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
