package rekursif;

public class faktoriyel {

	private static int faktoriyelAl(int i) {
		if (i==0) {
			return 1;
		}
		return faktoriyelAl(i-1)*i;
	}
	
	public static void main(String[] args) {
		
		System.out.println(faktoriyelAl(32));
	}
	
}
