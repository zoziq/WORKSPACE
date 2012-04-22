package sudoku;

public class sudoku {

	
	static int tablo[][] = new int[9][9];
	static Sayi sayilar[] = new Sayi[9];
	
	public static void yaz(){
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(tablo[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
	}
	
	public static boolean test(int i, int j, int sayi){
		
		int kTabloI = i - (i%3);
		int kTabloJ = j - (j%3);
		
		for (int k = 0; k < 3; k++) {
			for (int l = 0; l < 3; l++) {
				if (tablo[kTabloI+k][kTabloJ+l]==sayi) {
					return false;
				}
			}
		}
		for (int k = 0; k < 9; k++) {
			if (tablo[i][k]==sayi) {
				return false;
			}
			if (tablo[k][j]==sayi) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean search(int derinlik) {
		if (derinlik==9) {
			yaz();
			return true;
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (tablo[i][j]!=0) {
					continue;	
				}
				for (int k = 1; k < 10; k++) {
					boolean b = test(i, j, sayilar[k].deger);System.out.println(b + "-" + i + "-" + j + "-" + sayilar[k].deger);
					if (b) {
						tablo[i][j]=sayilar[i].deger;
					}
					search(derinlik+1);
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			sayilar[i-1] = new Sayi(i);
			sayilar[i-1].deger = i;
		}
/*		for (int i = 0; i < 9; i++) {
			System.out.print(sayilar[i].deger+ "-");
		}
	*/	
		tablo[0][1] = 9;
		tablo[0][3] = 3;
		tablo[0][5] = 6;
		tablo[0][6] = 5;
		tablo[0][8] = 2;
		tablo[1][1] = 6;
		tablo[1][2] = 5;
		tablo[1][5] = 1;
		tablo[1][7] = 3;
		tablo[2][2] = 1;
		tablo[2][4] = 9;
		tablo[2][8] = 8;
		tablo[3][0] = 8;
		tablo[3][3] = 5;
		tablo[3][5] = 4;
		tablo[3][7] = 7;
		tablo[3][8] = 6;
		tablo[4][0] = 3;
		tablo[4][4] = 6;
		tablo[4][8] = 4;
		tablo[5][0] = 5;
		tablo[5][1] = 4;
		tablo[5][3] = 7;
		tablo[5][8] = 1;
		tablo[6][0] = 9;
		tablo[6][4] = 5;
		tablo[6][6] = 4;
		tablo[7][1] = 8;
		tablo[7][3] = 6;
		tablo[7][6] = 7;
		tablo[7][7] = 2;
		tablo[8][0] = 6;
		tablo[8][2] = 3;
		tablo[8][3] = 4;
		tablo[8][5] = 7;
		tablo[8][7] = 8;

		search(0);
		
	}
	
	
	
}
