package tiryaki.planner;

import task.Behaviour;

public class Key {

	private String actionID;
	private String PendType;
	private String Name;
	private String ProvOutcome;
	private String Sender;
	private long puttingTime;
	private Behaviour behaviour;
	private int period;
	private int planNum;
	private int incomeMessageNum;
	private int relatedMessageNum = -1;

	Key(String PendType, String actionID, String Sender, String provOutcome,
			int PlanNum) {
		this.PendType = PendType;
		this.Sender = Sender;
		this.ProvOutcome = provOutcome;
		this.actionID = actionID;
		this.puttingTime = System.currentTimeMillis();
		this.planNum = PlanNum;
	}

	Key(String PendType, String Sender, String provOutcome) {
		this.PendType = PendType;
		this.Sender = Sender;
		this.Name = provOutcome;
		this.actionID = null;
		this.puttingTime = System.currentTimeMillis();
	}

	Key(String AcID, String name) {
		this.actionID = AcID;
		this.Name = name;
		this.ProvOutcome = null;
		this.puttingTime = System.currentTimeMillis();
	}

	Key(String AcID, String provOutcome, String name, int PlanNum) {
		this.actionID = AcID;
		this.Name = name;
		this.ProvOutcome = provOutcome;
		this.planNum = PlanNum;
		this.puttingTime = System.currentTimeMillis();
	}

	Key(Behaviour beh, int t) {
		this.behaviour = beh;
		this.period = t;
		this.puttingTime = System.currentTimeMillis();
	}

	Key(int msgNum) {
		this.incomeMessageNum = msgNum;
	}

	Key(int msgNum, int relatedMsg) {
		this.incomeMessageNum = msgNum;
		this.relatedMessageNum = relatedMsg;
	}

	public String getActionID() {
		return this.actionID;
	}

	public String getPendType() {
		return this.PendType;
	}

	public String getName() {
		return this.Name;
	}

	public String getSener() {
		return this.Sender;
	}

	public String getProvOutcome() {
		return this.ProvOutcome;
	}

	public long getPuttingTime() {
		return puttingTime;
	}

	public int getPeriod() {
		return period;
	}

	/**
	 * @return Returns the planNum.
	 */
	public int getPlanNum() {
		return planNum;
	}

	/**
	 * @param planNum
	 *            The planNum to set.
	 */
	public void setPlanNum(int planNum) {
		this.planNum = planNum;
	}

	/**
	 * @return Returns the incomeMessageNum.
	 */
	public int getIncomeMessageNum() {
		return incomeMessageNum;
	}

	/**
	 * @return Returns the relatedMessageNum.
	 */
	public int getRelatedMessageNum() {
		return relatedMessageNum;
	}
}