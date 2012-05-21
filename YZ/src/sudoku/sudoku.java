package sudoku;

public class sudoku {

	
	static int tablo[][] = new int[9][9];
	
	public static void yaz(){
		for (int i = 0; i < 9; i++) {
			if (i%3 ==  0)
				System.out.println();
			for (int j = 0; j < 9; j++) {
				if (j%3 ==  0)
					System.out.print(" ");
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
	
	public static void search(int derinlik) {
		if (derinlik==81) {
			yaz();
			return;
		}
		
				if (tablo[derinlik/9][derinlik%9] == 0) {
					for (int sayi = 1; sayi < 10; sayi++) {	
						
						if(test(derinlik/9, derinlik%9, sayi)) {
							tablo[derinlik/9][derinlik%9] = sayi;
							search(derinlik + 1);
							
						}	
							tablo[derinlik/9][derinlik%9] = 0;
					}
					
				}	
				else {
					search(derinlik + 1);
				}

		return;
	
	}
	
	public static void main(String[] args) {

//		tablo[0][1] = 9;
//		tablo[0][3] = 3;
//		tablo[0][5] = 6;
//		tablo[0][6] = 5;
//		tablo[0][8] = 2;
//		tablo[1][1] = 6;
//		tablo[1][2] = 5;
//		tablo[1][5] = 1;
//		tablo[1][7] = 3;
//		tablo[2][2] = 1;
//		tablo[2][4] = 9;
//		tablo[2][8] = 8;
//		tablo[3][0] = 8;
//		tablo[3][3] = 5;
//		tablo[3][5] = 4;
//		tablo[3][7] = 7;
//		tablo[3][8] = 6;
//		tablo[4][0] = 3;
//		tablo[4][4] = 6;
//		tablo[4][8] = 4;
//		tablo[5][0] = 5;
//		tablo[5][1] = 4;
//		tablo[5][3] = 7;
//		tablo[5][8] = 1;
//		tablo[6][0] = 9;
//		tablo[6][4] = 5;
//		tablo[6][6] = 4;
//		tablo[7][1] = 8;
//		tablo[7][3] = 6;
//		tablo[7][6] = 7;
//		tablo[7][7] = 2;
//		tablo[8][0] = 6;
//		tablo[8][2] = 3;
//		tablo[8][3] = 4;
//		tablo[8][5] = 7;
//		tablo[8][7] = 8;
		
//		tablo[0][2] = 5;
//		tablo[0][3] = 3;
//		tablo[1][0] = 8;
//		tablo[1][7] = 2;
//		tablo[2][1] = 7;
//		tablo[2][4] = 1;
//		tablo[2][6] = 5;
//		tablo[3][0] = 4;
//		tablo[3][5] = 5;
//		tablo[3][6] = 3;
//		tablo[4][1] = 1;
//		tablo[4][4] = 7;
//		tablo[4][8] = 6;
//		tablo[5][2] = 3;
//		tablo[5][3] = 2;
//		tablo[5][7] = 8;
//		tablo[6][1] = 6;
//		tablo[6][3] = 5;
//		tablo[6][8] = 9;
//		tablo[7][2] = 4;
//		tablo[7][7] = 3;
//		tablo[8][5] = 9;
//		tablo[8][6] = 7;


		tablo[0][0] = 8;
		tablo[0][1] = 6;
		tablo[0][4] = 2;
		tablo[1][3] = 7;
		tablo[1][7] = 5;
		tablo[1][8] = 9;
		tablo[3][4] = 6;
		tablo[3][6] = 8;
		tablo[4][1] = 4;
		tablo[5][2] = 5;
		tablo[5][3] = 3;
		tablo[5][8] = 7;
		tablo[7][1] = 2;
		tablo[7][6] = 6;
		tablo[8][2] = 7;
		tablo[8][3] = 5;
		tablo[8][5] = 9;
		search(0);
		
	
		
	}
	
	
	
}
