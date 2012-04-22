package sudoku;

public class s�r� {

	
	static int tablo[][] = new int[3][3];
	static sayidiyesin�f sayilar[] = new sayidiyesin�f[10];
	
	
	
	public static boolean test(){
		
		int toplam = tablo[0][0] + tablo[0][1] + tablo[0][2];
		
		int satir = 0, sutun = 0, capraz=0;
		
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				satir +=tablo[i][j];
			}
			if (satir!=toplam) {
				return false;
			}
			satir = 0;
		}
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 3; i++) {
				sutun +=tablo[i][j];
			}
			if (sutun!=toplam) {
				return false;
			}
			sutun = 0;
		}
		for (int i = 0; i < 3; i++) {
			capraz+=tablo[i][i];
		}
		if (capraz!=toplam) {
			return false;
		}
		if (tablo[0][2] + tablo[1][1] + tablo[2][0]!=toplam) {
			return false;
		}
		return true;
	}
	
	public static void yaz(){
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(tablo[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("----------");
	}
	
	public static boolean s�r�(int adim){
		if (adim==9) {
			if (test()) {
				yaz();
			}
			return false;
		}
		for (int i = 0; i < 10; i++) {
			if (sayilar[i].kullan�ld�m�) {
				continue;
			}
			sayilar[i].kullan�ld�m�=true;
			tablo[adim/3][adim%3] = i;
			s�r�(adim+1);
			sayilar[i].kullan�ld�m�=false;
		}
		

		return false;
	}
	
	
	public static void main(String[] args) {
		
		for (int i = 0; i < sayilar.length; i++) {
			sayilar[i] = new sayidiyesin�f(i);
			
		}
	
		
		s�r�(0);
		
	}
	
}
