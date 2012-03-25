package links;

import task.Provision;
import task.Task;

public class ExternalLink extends Link {

	private Task receiverTask;
	private Provision receiverProvision;

	// sourceTask calistirilip mesaj gonderdikten sonra gelen cevap receiver
	// taski set eder
	private Task sourceTask;

	public Task getsourceTask() {
		return sourceTask;
	}

	public void setsourceTask(Task sourceTask) {
		this.sourceTask = sourceTask;
	}

	public Task getReceiverTask() {
		return receiverTask;
	}

	public Provision getReceiverProvision() {
		return receiverProvision;
	}

	public void setReceiverTask(Task receiverTask) {
		this.receiverTask = receiverTask;
	}

	public void setReceiverProvision(Provision receiverProvision) {
		this.receiverProvision = receiverProvision;
	}

	@Override
	public void update() {

	}

}
