package rekursif;

public class toplam {

	private static int topla(int n) {
		
		if (n==0) {
			return 0;
		}
		return topla(n-1)+n;
	}
	
	public static void main(String[] args) {
		System.out.println(topla(11));
	}
	
}
