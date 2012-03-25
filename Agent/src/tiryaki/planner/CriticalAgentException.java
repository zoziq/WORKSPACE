package tiryaki.planner;

public class CriticalAgentException extends Exception {
	public CriticalAgentException() {
	}

	public CriticalAgentException(String msg) {
		System.out.println(msg);
	}
}
