package fipa;

import fipa.FipaMessage;

public class ObjectiveMessage {
	// Bu counter static oldugu icin nesnelerden bagimsiz olarak her
	// kullanimda artirilacaktir.
	private static int counter = 0;
	FipaMessage message = null;
	int msgNumber;

	public ObjectiveMessage(FipaMessage fm) {
		this.message = fm;
		this.msgNumber = ++counter;
	}

	public FipaMessage getMessage() {
		return message;
	}

	public void setMessage(FipaMessage message) {
		this.message = message;
	}

	public static int getCounter() {
		return counter;
	}

	public int getMsgNumber() {
		return msgNumber;
	}
}
