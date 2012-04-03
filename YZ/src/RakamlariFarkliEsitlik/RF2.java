package RakamlariFarkliEsitlik;

public class RF2 {

	/*
	 * Problem;
	 * Tum degiskenlere farkli rakamlar atanmasi sartiyla,
	 * a*c = a+b+c+d
	 * esitligini saglayacak kombinasyonlari bulmak.
	 */

	public static void k(int sayi){
		int a = sayi/1000;
		int b = (sayi-a*1000)/100;
		int c = (sayi-a*1000-b*100)/10;
		int d = (sayi-a*1000-b*100-c*10);
		
		if (sayi==9877) {
			return;
		}
		
		if (a!=b && a!=c && a!=d && b!=c && b!=d && c!=d) {
			if (a*c==a+b+c+d) {
				System.out.println(1000*a+100*b+10*c+d);
			}
		}
		k(++sayi);
	}
	
	
	public static void main(String[] args) {
		
		k(1023);
		
	}
}
