package DepthFirst;

/*
 * Satır, sütun ve köşegen toplamları eşit olan
 * matrislerin bulunması ve yazdırılması.
 */

public class SinavSorusu {

    static int tablo[][] = new int[3][3];
    static Sayi sayilar[] = new Sayi[10];
    
    public static void main(String[] args) {
    
        for (int i = 0; i < 10; i++) {
            sayilar[i] = new Sayi(i);
        }
        
        search(0);
    }
    
    public static boolean search(int derinlik){
        if (derinlik == 9){
            if (test()){
                yaz();
                return true; // Bu satırı kaldırarak tüm olası çözümleri yazabiliriz.
            }
            return false;
        }
        
        for (int i = 0; i < sayilar.length; i++) {
            if (sayilar[i].used)
                continue;
            sayilar[i].used = true;
            tablo[derinlik/3][derinlik%3] = sayilar[i].deger;
            if (search(derinlik + 1))
                return true; // Bu satırı kaldırarak da tüm olası çözümler yazdırılabilir.
            sayilar[i].used = false;
        }
        return false;
    }
    
    public static boolean test(){
        int toplam = tablo[0][0] + tablo[0][1] + tablo[0][2];
        for (int i = 0; i < 3; i++) {
            int araToplam = 0;
            for (int j = 0; j < 3; j++) {
                araToplam += tablo[i][j];
            }
            if (toplam != araToplam)
                return false;
            araToplam = 0;
            for (int j = 0; j < 3; j++) {
                araToplam += tablo[j][i];
            }
            if (toplam != araToplam)
                return false;
        }
        if (toplam != tablo[0][0] + tablo[1][1] + tablo[2][2] || 
            toplam != tablo[0][2] + tablo[1][1] + tablo[2][0]   )
            return false;
        return true;
    }
    
    public static void yaz(){
        System.out.println("Toplam = " + (tablo[0][0] + tablo[0][1] + tablo[0][2]));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tablo[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
}
