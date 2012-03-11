
public class FNET {

	public static double fnet(double net, double bias) {
		return 1 / (1 + (Math.pow(Math.E, -1*(net + bias))));
	}
	
	public static void main(String[] args) {
		System.out.println(fnet(.517,-.993));
	}
	
	
}
