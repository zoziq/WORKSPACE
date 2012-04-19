package tiryaki.planner;

import fipa.FipaMessage;

public class OutgoingMessage {
	private FipaMessage message;
    private int msgNum;

	public FipaMessage getMessage() {
		return message;
	}

	public int getMsgNum() {
		return msgNum;
	}
   
	public OutgoingMessage(FipaMessage message, int msgNum) {
		this.message = message;
		this.msgNum = msgNum;
	}
	public OutgoingMessage(FipaMessage message) {
		this.message = message;
		this.msgNum=-1;
	}

    
 
}

