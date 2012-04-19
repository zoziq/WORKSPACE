package agent;

import java.io.Serializable;

public class AgentState implements Serializable{
	public static final String ACTIVE = "active";
	public static final String INITIATED = "initiated";
	public static final String SUSPENDED = "suspended";
	public static final String TRANSIT = "transit";
	public static final String UNKNOWN = "unknown";
	public static final String WAITING = "waiting";

	private String state;
	private int stateID;

	public AgentState(int stateID) {
		setState(stateID);
	}

	
	public AgentState(String state) {
		setState(state);
	}

	
	public String getState() {
		return state;
	}

	public int getStateID() {
		return stateID;
	}

	public void setState(int stateID) {
		this.stateID = stateID;
		if (stateID == 0) {
			this.state = INITIATED;
		}
		else if (stateID == 1) {
			this.state = ACTIVE;
		} 
		else if (stateID == 2) {
			this.state = SUSPENDED;
		} 
		else if (stateID == 3) {
			this.state = WAITING;
		} 
		else if (stateID == 4) {
			this.state = TRANSIT;
		}
		else {
			this.state = UNKNOWN;
			this.stateID = -1;
		}
	}

// TODO find an alternative solution for these if statements, doesn't look esthetic
	public void setState(String state) {
		this.state = state;
		if (state.equals(INITIATED)) {
			this.stateID = 0;
		} else if (state.equals(ACTIVE)) {
			this.stateID = 1;
		} else if (state.equals(SUSPENDED)) {
			this.stateID = 2;
		} else if (state.equals(WAITING)) {
			this.stateID = 3;
		} else if (state.equals(TRANSIT)) {
			this.stateID = 4;
		} else {
			this.state = UNKNOWN;
			this.stateID = -1;
		}
	}
	
	public String toString() {
	   return getState();
	}
}
