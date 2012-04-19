package tiryaki.planner;

public class Semaphore {

	private int count;

	public Semaphore() {
		count = 0;
	}

	public Semaphore(int value) {
		count = value;
	}

	public synchronized void V() {
		count++;
		if (count <= 0)
			notify();
	}

	public synchronized void P() {
		count--;
		if (count < 0) {
			try {
				wait();
			} catch (InterruptedException ex) {
			}
		}
	}
}