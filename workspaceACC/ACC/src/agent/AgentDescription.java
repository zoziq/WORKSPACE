package agent;

import java.io.Serializable;

public class AgentDescription implements Serializable{

	private AID _aid;
	private String _ownership;
	private AgentState _state;
	
	public AgentDescription()
	{
		_aid=new AID();
		
	}
	public AID get_aid() {
		return _aid;
	}
	public void set_aid(AID aid) {
		_aid = aid;
	}
	public String get_ownership() {
		return _ownership;
	}
	public void set_ownership(String ownership) {
		_ownership = ownership;
	}
	public AgentState get_state() {
		return _state;
	}
	public void set_state(AgentState state) {
		_state = state;
	}

}
