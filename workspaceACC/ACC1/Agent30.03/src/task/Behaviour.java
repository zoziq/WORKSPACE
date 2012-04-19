package task;

import java.util.LinkedList;

import links.Link;

/**
 * HTN yapisindaki Complex Task'larin ger�ekle�tirilmi�
 * sinifidir. Icerisinde gerekli link baglantilarini tutar.
 * Bu baglantilar Reduction Schema'yi olusturur. <i>Scheduler</i> bu
 * schema'yi kullanarak <i>Behaviour</i>'u decompose eder, yani
 * baglantilarini kurar ve <i>Action</i> nesnelerini uygun
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
	protected LinkedList<Link> links; // Bu linklerin listesini programc�

	// yazmas� gerekli
	// protected HashMap<Link?String,??> links; // Can be wrapped with
	// Collections.synchronizedMap(m) for thread-safe; ayr�ca link mi olsun
	// string mi?

	public LinkedList<Task> getChildTasks() {
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
	 * S�n�f�n bir child <code>Task</code>'a sahip olup olmad���n�
	 * ��renmeye yarar. E�er child task yok ise bu nesne bir
	 * <i>leaf(yaprak d���m)</i> oldu�unu g�sterir.
	 * 
	 * @return E�er bu <code>Task</code> bir child <code>Task</code>
	 *         referans�na sahip ise <code>true</code> yoksa
	 *         <code>false</code> g�nderir.
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
		// TODO: Child'lar� gez ve nesnelerini al, uygun kuyruklara
		// g�nder...

	}
}