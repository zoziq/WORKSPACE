package Vezir8;

public class Vezir {

	  static int tablo[][] = new int[8][8];

	    public static boolean test(int x, int y) {
	        for (int i = 0; i < 8; i++) {
	            if (tablo[x][i] == 1 && i != y) {
	                return false;
	            }
	            if (tablo[i][y] == 1 && i != x) {
	                return false;
	            }
	        }
	        for (int i = 1; i < 8; i++) {
	            if (x - i >= 0 && y + i < 8 && tablo[x - i][y + i] == 1) {
	                return false;
	            }
	            if (x - i >= 0 && y - i >= 0 && tablo[x - i][y - i] == 1) {
	                return false;
	            }
	            if (x + i < 8 && y + i < 8 && tablo[x + i][y + i] == 1) {
	                return false;
	            }
	            if (x + i < 8 && y - i >= 0 && tablo[x + i][y - i] == 1) {
	                return false;
	            }
	        }
	        return true;
	    }
	    static int cozum = 0;

	    public static void yaz() {
	        System.out.println("Cozum" + cozum++);
	        for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	                System.out.print(tablo[i][j] + " ");
	            }
	            System.out.println("");
	        }

	    }

	    public static void vezir(int i) {
	        if (i == 8) {
	            yaz();
	            return;
	        }
	        for (int j = 0; j < 8; j++) {
	            tablo[i][j] = 1;
	            if (test(i, j)) {
	                vezir(i + 1);
	            }
	            tablo[i][j] = 0;
	        }
	    }
	    
	    public static void main(String[] args) {
			vezir(0);
		}
	
}
