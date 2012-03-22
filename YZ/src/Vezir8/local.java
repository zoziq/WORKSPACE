package Vezir8;

public class local {

	static int tablo[][] = new int[8][8];
	
	static void yaz(){
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(tablo[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	static int[] bul(){
		int dizi[] = new int[8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (tablo[j][i]==-1) {
					dizi[i] = j;
				}
			}
		}
		return dizi;
	}
	
	
	
	
	public static void main(String[] args) {

		//her sutuna bir vezir gelecek sekilde rasgele yerlestir
		for (int i = 0; i < 8; i++) {
			int r = (int)(Math.random()*8);
			tablo[r][i] = -1;
		}
		
		yaz();
		System.out.println();
		
		for (int i = 0; i < 1; i++) {
	//		System.out.println(bul()[i]);
		}
		
		
		
		for (int i = 0; i < 8; i++) {	
			
			int yer = bul()[i];
			int cakisma = 0;
			for (int j = 0; j < 8; j++) {//sutunda gezdir
				
				if(yer!=j) {
					tablo[i][j] = -1;
					
					//satirdaki cakismalar
					for (int k = 0; k < 8; k++) {
						for (int m = k+1; m < 8; m++) {
							if (tablo[i][k]==-1 && tablo[i][m]==-1) {
								cakisma++;
							}
						}
					}
					
			
					
					tablo[i][j] = cakisma;
					cakisma = 0;
				}
			}
		}
		
		yaz();

	}
	
	
	
	
	
	static void testt(){
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
}
