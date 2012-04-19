package task;

import java.util.ArrayList;
import java.util.Vector;

import fipa.FipaMessage;

public abstract class Action extends Task {

	private ArrayList<FipaMessage> sendingMessages = new ArrayList<FipaMessage>();

	public ArrayList<FipaMessage> getSendingMessages() {
		return sendingMessages;
	}

	public void setSendingMessages(ArrayList<FipaMessage> sendingMessages) {
		this.sendingMessages = sendingMessages;
	}

	public abstract void doAction();

	public abstract void sendMessage(FipaMessage fm);

	protected Vector createOutcome(String state, String name, Object value,
			Vector outcomes) {

		return outcomes;
	}

}