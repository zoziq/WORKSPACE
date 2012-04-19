package df;


import fipa.FipaMessage;
import agent.AgentDescription;

public interface DFInterface {
	public boolean register(FipaMessage fm);
	public void deregister(String _name);
	public void modify(AgentDescription _agentDesc);
	public String search(String _name);
	
}
