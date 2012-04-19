package links;

import task.Provision;
import task.Task;

/**
 * Bir Behaviour'un subtask'� olan bir Task'a Provision'unun 
 * ba�lant�s�n� sa�lar. B�ylece bir Behaviour'un Provision'u
 * subtask'�nda kullan�lm�� olur.
 * @author Samil
 *
 */
public class InheritanceLink extends Link {

	protected Provision senderProvision;
	protected Task senderTask;
	protected Task receiverTask;
	protected Provision receiverProvision;

	
	protected synchronized Task getSenderTask() {
		return senderTask;
	}
	protected synchronized void setSenderTask(Task senderTask) {
		this.senderTask = senderTask;
	}
	public synchronized Provision getSenderProvision() {
		return senderProvision;
	}
	public synchronized void setSenderProvision(Provision senderProvision) {
		this.senderProvision = senderProvision;
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
	@Override
	public synchronized void update() {
		this.receiverProvision.setValue(senderProvision.getValue());
		
	}
	
}
