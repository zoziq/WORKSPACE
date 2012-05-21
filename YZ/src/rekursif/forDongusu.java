package rekursif;

public class forDongusu {
	static int i;
	static int a;
	
	static void f() {
		if(a>9) {
			return;
		}
		a++;
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " ");
			f();
	
			for (int j = 0; j < 5; j++) {
				System.out.print(j + " ");
				f();
			}
		}
	}
	
	public static void main(String[] args) {
		f();
	}
	
}
