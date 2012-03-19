package Vezir8;

public class local {

	static int tablo[][] = new int[8][8];
	
	static void yaz(){
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(tablo[i][j] + "\t");
			}
			System.out.println("\n");
		}
	}
	
	static void bul(){
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (tablo[j][i]==1) {
					System.out.println(j);
				}
			}
		}
	}
	
	static void test(){
		int cakisma = 0;
		for (int sutun = 0; sutun < 8; sutun++) {
			for (int satir = 0; satir < 8; satir++) {
				for (int i = 0; i < 8; i++) {
					if (sutun!=i) {
						if (tablo[satir][i]==1) {
							cakisma++;
						}
						int sayac = 0;
						while(satir+i<8 && sayac<8) {
							sayac++;
							if (tablo[satir+i][sutun+i]==1) {
								cakisma++;
							}
						}
						sayac = 0;
						while(satir-i>0) {
							sayac++;
							if (tablo[satir-i][sutun-i]==1) {
								cakisma++;
							}
						}
						sayac = 0;
						while(satir-i>0 && satir+i<8) {
							sayac++;
							if (tablo[satir+i][sutun-i]==1) {
								cakisma++;
							}
							if (tablo[satir-i][sutun+i]==1) {
								cakisma++;
							}
						}
					}	
				}
				System.out.println(cakisma);
				cakisma = 0;
			}
		}
	}
	
	public static void main(String[] args) {

		//her sutuna bir vezir gelecek sekilde rasgele yerlestir
		for (int i = 0; i < 8; i++) {
			int r = (int)(Math.random()*8);
			tablo[r][i] = 1;
		}
	//	bul();
		test();
		yaz();
	}
	
}
