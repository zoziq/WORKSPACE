package tiryaki.planner;

import task.Outcome;
import task.Output;
import task.Provision;
import task.Task;

public class PendingQCell {

	private int planNo;
	private Task task;
	private Task sourceTask;
	private Provision targetProvision;
	private Outcome outcomeName;
	private String outputName;
	
	

	public PendingQCell(int planNo, Task task, Task sourceTask,
			Provision targetProvision) {
		super();
		this.planNo = planNo;
		this.task = task;
		this.sourceTask = sourceTask;
		this.targetProvision = targetProvision;
	}
	
	

	public PendingQCell(int planNo, Task task, Task sourceTask,
			Provision targetProvision, Outcome outcomeName, String outputName) {
		super();
		this.planNo = planNo;
		this.task = task;
		this.sourceTask = sourceTask;
		this.targetProvision = targetProvision;
		this.outcomeName = outcomeName;
		this.outputName = outputName;
	}



	public Task getSourceTask() {
		return sourceTask;
	}

	public void setSourceTask(Task sourceTask) {
		this.sourceTask = sourceTask;
	}

	public Provision getTargetProvision() {
		return targetProvision;
	}

	public void setTargetProvision(Provision targetProvision) {
		this.targetProvision = targetProvision;
	}

	public Outcome getOutcomeName() {
		return outcomeName;
	}

	public void setOutcomeName(Outcome outcomeName) {
		this.outcomeName = outcomeName;
	}

	public String getOutputName() {
		return outputName;
	}

	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}

	public PendingQCell(int planNo, Task task) {
		super();
		this.planNo = planNo;
		this.task = task;
	}
	
	public int getPlanNo() {
		return planNo;
	}
	public Task getTask() {
		return task;
	}
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	
	
	

	
}
