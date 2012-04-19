package links;

import task.Task;

public class FlowLink extends Link {

	private Task priorityTask;
	private Task nonPriorityTask;

	public Task getPriorityTask() {
		return priorityTask;
	}

	public Task getNonPriorityTask() {
		return nonPriorityTask;
	}

	public void setPriorityTask(Task priorityTask) {
		this.priorityTask = priorityTask;
	}

	public void setNonPriorityTask(Task nonPriorityTask) {
		this.nonPriorityTask = nonPriorityTask;
	}

	@Override
	public void update() {

	}

}
