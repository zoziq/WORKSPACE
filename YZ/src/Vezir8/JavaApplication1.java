/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vezir8;

/**
 *
 * @author BMB
 */
public class JavaApplication1 {

    public static int topla(int i) {
        if (i > 99) {
            return i;
        }
        return topla(i + 1) + i;
    }

    public static void soru2Iterative() {
        for (int i = -5; i < 6; i++) {
            for (int j = -5; j < 6; j++) {
                if (i * i + j * j == 25) {
                    System.out.println("x=" + i + "y=" + j);
                }
            }
        }
    }

    public static void soru2RecX(int x, int y) {

        if (x * x > 25) {
            return;
        }
        soru2RecY(x, -5);
        soru2RecX(x + 1, y);
    }

    public static void soru2RecY(int x, int y) {

        if (y * y > 25) {
            return;
        }
        if (x * x + y * y == 25.0) {
            System.out.println("x=" + x + "y=" + y);
        }
        soru2RecY(x, y + 1);
    }

    public static void soru2Rec(int x) {
        if (x * x > 25) {
            return;
        }
        for (int y = -5; y < 6; y++) {
            if (x * x + y * y == 25.0) {
                System.out.println("x=" + x + "y=" + y);
            }
        }
        soru2Rec(x + 1);
    }
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
    public static int dizi[] = new int[10];

    public static void yerlestir(int i) {
        if (i > 9) {
            if (dizi[0] * 100 + dizi[1] * 10 + dizi[2] + dizi[3] * 10 + dizi[4] + dizi[5]
                    == dizi[6] * 1000 + dizi[7] * 100 + dizi[8] * 10 + dizi[9]) {
                for (int l = 0; l < 10; l++) {
                    System.out.print(dizi[l] + " ");
                }
            }
            return;
        }
        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 10; k++) {
                if (dizi[k] == j) break;
                    dizi[i] = j;
                    yerlestir(i + 1);
                    dizi[i] = -1;                
            }

        }
    }

    public static void main(String[] args) {
        //System.out.println("Toplam =" + topla(0));
        //soru2Iterative();
        // soru2RecX(-5, 0);
        //soru2Rec(-5);
        for (int i = 0; i < 10; i++) {
            dizi[i] = -1;
        }
        //yerlestir(0);
        
        
        
        vezir(0);
    }
}
