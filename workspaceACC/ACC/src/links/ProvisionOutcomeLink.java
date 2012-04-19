package links;

import task.Outcome;
import task.Provision;
import task.Task;

/**
 * Outcome ve Provision'lar aras�ndaki ba�lant�y� sa�lar.
 *
 */
public final class ProvisionOutcomeLink extends Link {
	protected Task senderTask;
	protected Outcome senderOutcome;
	protected Task receiverTask;
	protected Provision receiverProvision;
	protected String senderOutcomeName;
	
	public synchronized String getSenderOutcomeName() {
		return senderOutcomeName;
	}
	public synchronized void setSenderOutcomeName(String outcomeName) {
		this.senderOutcomeName = outcomeName;
	}
	public synchronized Task getSenderTask() {
		return senderTask;
	}
	public synchronized void setSenderTask(Task senderTask) {
		this.senderTask = senderTask;
	}
	public synchronized Outcome getSenderOutcome() {
		return senderOutcome;
	}
	public synchronized void setSenderOutcome(Outcome senderOutcome) {
		this.senderOutcome = senderOutcome;
	}
	public synchronized Task getReceiverTask() {
		return receiverTask;
	}
	public synchronized void setReceiverTask(Task receiverTask) {
		this.receiverTask = receiverTask;
	}
	public synchronized Provision getReceiverProvision() {
		return receiverProvision;
	}
	public synchronized void setReceiverProvision(Provision receiverProvision) {
		this.receiverProvision = receiverProvision;
	}
	/*
	public synchronized Object getOutcomeResult(){
		if (this.senderOutcome == null) { return null; }
		return this.senderOutcome.getResult(this.senderOutcomeName);
	}*/
	
	@Override
	public void update() {
	//	this.receiverProvision.setValue(getOutcomeResult());
	}
}