package task;

/**
 * Provisionlar bir taskın çalıştırılabilmesi için sağlanması gereken
 * koşullardır.
 */
public class Provision {
	String provName = null;
	Object value = null;
	String type = null;

	public synchronized Object getValue() {
		return value;
	}

	public synchronized void setValue(Object value) {
		this.value = value;
		this.type = value.getClass().getName();
	}

	public synchronized String getprovName() {
		return provName;
	}

	public synchronized void setprovName(String provName) {
		this.provName = provName;
	}

	public synchronized boolean isSet() {
		// System.out.println("isset "+ value.toString());
		if (value == "" || value == null) {
			return false;
		} else
			return true;
	}
}
