package links;

import task.Outcome;
import task.Task;

/**
 * Bir Behaviour'un subtask'inin Outcome'ini alabilmesini saglar.
 * Boylece sonlanan Task'in Outcome'i, Behaviour'un Outcome'i olabilir.
 * 
 * @author Samil
 * @author Mshn
 * 
 */
public final class DisinheritanceLink extends Link {
	protected Outcome senderOutcome;
	protected Task senderTask;
	protected Task receiverTask;
	protected Outcome receiverOutcome;
	protected String OutcomeName;

	public synchronized String getOutcomeName() {
		return OutcomeName;
	}

	public synchronized void setOutcomeName(String OutcomeName) {
		this.OutcomeName = OutcomeName;
	}

	public synchronized Outcome getSenderOutcome() {
		return senderOutcome;
	}

	public synchronized void setSenderOutcome(Outcome senderOutcome) {
		this.senderOutcome = senderOutcome;
	}

	public synchronized Task getSenderTask() {
		return senderTask;
	}

	public synchronized void setSenderTask(Task senderTask) {
		this.senderTask = senderTask;
	}

	public synchronized Outcome getReceiverOutcome() {
		return receiverOutcome;
	}

	public synchronized void setReceiverOutcome(Outcome receiverOutcome) {
		this.receiverOutcome = receiverOutcome;
	}

	public synchronized Task getReceiverTask() {
		return receiverTask;
	}

	public synchronized void setReceiverTask(Task receiverTask) {
		this.receiverTask = receiverTask;
	}

	/*
	 * public synchronized Object getOutcomeResult(){ return
	 * this.senderOutcome.getResult(OutcomeName); }
	 */

	public void update() {
		// this.receiverOutcome.setResult(getOutcomeName(), getOutcomeResult());
	}
}
