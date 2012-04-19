package tiryaki.planner;

import task.Behaviour;

public class BehaviourQCell {

	private Behaviour behaviour;
	private int msgNum;

	/**
	 * @param behaviour
	 *            The behaviour to set.
	 */
	public void setBehaviour(Behaviour behaviour) {
		this.behaviour = behaviour;
	}

	/**
	 * @param msgNum
	 *            The msgNum to set.
	 */
	public void setMsgNum(int msgNum) {
		this.msgNum = msgNum;
	}

	public BehaviourQCell(Behaviour beh, int messageNum) {
		this.behaviour = beh;
		this.msgNum = messageNum;
	}

	/**
	 * @return Returns the behaviour.
	 */
	public Behaviour getBehaviour() {
		return behaviour;
	}

	/**
	 * @return Returns the msgNum.
	 */
	public int getMsgNum() {
		return msgNum;
	}
}