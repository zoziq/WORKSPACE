package task;

import java.util.ArrayList;

import tiryaki.planner.*;

public abstract class Task {

	private String taskId;
	private int planNum;
	private String taskName;

	public int getPlanNum() {
		return planNum;
	}

	public void setPlanNum(int planNum) {
		this.planNum = planNum;
	}

	public String getId() {
		return taskId;
	}

	public void setId(String id) {
		this.taskId = id;
	}

	private ArrayList<Provision> provisionList = new ArrayList<Provision>();
	private ArrayList<Outcome> outcomeList = new ArrayList<Outcome>();

	public ArrayList<Provision> getProvisionList() {
		return provisionList;
	}

	public ArrayList<Outcome> getOutcomeList() {
		return outcomeList;
	}

	public void setProvisionList(ArrayList<Provision> provisionList) {
		this.provisionList = provisionList;
	}

	public void setOutcomeList(ArrayList<Outcome> outcomeList) {
		this.outcomeList = outcomeList;
	}

	public void addProvision(Provision prov) {
		provisionList.add(prov);

	}

	public void setProvision(String provName, Object value) {

		for (Provision pro : provisionList) {
			if (pro.provName.equalsIgnoreCase(provName))

			{
				pro.setValue(value);
			}
		}
	}

	public void addOutcome(Outcome outcome) {
		outcomeList.add(outcome);

	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getName() {
		return taskName;
	}

	public void setName(String taskName) {
		this.taskName = taskName;
	}

	public boolean isAllProvisionsAreSet() {
		boolean isAllSet = true;

		if (provisionList.isEmpty()) {
			System.out.println("isAllProvisionsAreSet => true");
			return isAllSet;

		}

		for (Provision p : provisionList) {
			System.out.println("Task icindeki Provision: " + p.getprovName());

			if (!p.isSet()) {
				System.out.println("isAllProvisionsAreSet => false");
				isAllSet = false;
				return isAllSet;
			}
		}
		System.out.println("isAllProvisionsAreSet => true");
		return isAllSet;
	}

}
