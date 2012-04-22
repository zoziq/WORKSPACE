package rekursif;

public class pow {

	public static long pow(long a, int b){
		if (b==0) {
			return 1;
		}
		if (b==0) {
			return a;
		}
		if (b%2==0) {
			return pow(a*a, b/2);
		}
		else if (b%2==1) {
			return pow(a*a, b/2)*a;
		}
		return 1;
	}
	public static void main(String[] args) {
		System.out.println(pow(3,6));
	}
	
}
