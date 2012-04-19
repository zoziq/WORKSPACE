package task;

import java.util.LinkedList;

import links.Link;

/**
 * HTN yapisindaki Complex Task'larin gercekleþtirilmis sinifidir. Icerisinde
 * gerekli link baglantilarini tutar. Bu baglantilar Reduction Schema'yi
 * olusturur. <i>Scheduler</i> bu schema'yi kullanarak <i>Behaviour</i>'u
 * decompose eder, yani baglantilarini kurar ve <i>Action</i> nesnelerini uygun
 * kuyruklara(ready,suspended) birakir.
 * 
 * @author Samil
 * @author Mshn
 * @see {@link Task} sinifindan turetilmistir.
 * 
 */
public abstract class Behaviour extends Task {

	private String behaviourName;
	protected LinkedList<Task> childTasks = null;

	// Bu linklerin listesini programcinin yazmasi gerekir.
	protected LinkedList<Link> links; 	public LinkedList<Task> getChildTasks() {
		return childTasks;
	}

	public void setChildTasks(LinkedList<Task> childTasks) {
		this.childTasks = childTasks;
	}

	public void setLinks(LinkedList<Link> links) {
		this.links = links;
	}

	public Behaviour() {
		childTasks = new LinkedList<Task>();
		links = new LinkedList<Link>();
	}

	public synchronized LinkedList<Link> getLinks() {
		return links;
	}

	protected void setSubTask(Task task) {
		this.childTasks.add(task);
	}

	/**
	 * Sinifin bir child <code>Task</code>'a sahip olup olmadigini
	 * ogrenmeye yarar. Eger child task yok ise bu nesne bir
	 * <i>leaf(yaprak dugum)</i> oldugunu gosterir.
	 * 
	 * @return Eger bu <code>Task</code> bir child <code>Task</code>
	 *         referansina sahip ise <code>true</code> yoksa
	 *         <code>false</code> gonderir.
	 */
	public boolean hasSubTask() {
		if (childTasks == null) {
			return false;
		} else if (childTasks.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public LinkedList<Task> getSubTasks() {
		if (hasSubTask()) {
			return this.childTasks;
		} else
			return null;
	}

	public String getBehaviourName() {
		return behaviourName;
	}

	public void setBehaviourName(String behaviourName) {
		this.behaviourName = behaviourName;
	}

	protected void decompose() {
		// TODO: Child'lari gez ve nesnelerini al, uygun kuyruklara gonder...

	}
}