

package tiryaki.planner;

public class NonCriticalAgentException extends Exception {
        public NonCriticalAgentException() { }

        public NonCriticalAgentException(String msg) {
          super(msg);
          //System.out.println("Non-critical error"+msg);
        }
}
