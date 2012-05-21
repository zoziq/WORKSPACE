package minmax;

import java.util.Scanner;

public class SayiOyunu {

    public static int gtop = 0;
    public static Sayi sayilar[] = new Sayi[10];

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            sayilar[i] = new Sayi(i);
        }
        
        while (gtop < 30) {
            
        	if (!maxFonksiyonu(gtop, 0)) {
                break;
            }
        	
            Scanner s = new Scanner(System.in);
            System.out.println("Aþaðýdaki sayýlardan birini giriniz!");
            for (int i = 0; i < 10; i++) {

                if (!sayilar[i].used) {
                    System.out.print(i + " ");
                }
            }
            int sayi = s.nextInt();
            while (sayilar[sayi].used) {
                sayi = s.nextInt();
            }
            System.out.println("Kabul edilen sayi :" + sayi);
            gtop += sayi;
            System.out.println("Toplam = " + gtop);
            if (gtop >= 30) {
                System.out.println("Oyuncu kaybetti");
                break;
            }
            sayilar[sayi].used = true; 
            
            
            
        }
    }

    private static boolean maxFonksiyonu(int toplam, int derinlik) {
        if (toplam >= 30) {
            return true;
        }
        for (int i = 0; i < 10; i++) {
            if (sayilar[i].used) {
                continue;
            }
            sayilar[i].used = true;
            if (minFonksiyonu(toplam + i, derinlik + 1)) {
                if (derinlik == 0) {
                    System.out.println("Ben " + i + " sayýsýný oynuyorum");
                    gtop += i;
                    System.out.println("Toplam " + gtop + " oldu");
                    return true;
                } else {
                    sayilar[i].used = false;
                    return true;
                }
            }
            sayilar[i].used = false;

        }
        return false;

    }

    private static boolean minFonksiyonu(int toplam, int derinlik) {
        if (toplam >= 30) {
            return false;
        }
        for (int i = 0; i < 10; i++) {
            if (sayilar[i].used) {
                continue;
            }
            sayilar[i].used = true;
            if (!maxFonksiyonu(toplam + i, derinlik + 1)) {
                sayilar[i].used = false;
                return false;
            }

            sayilar[i].used = false;

        }

        return true;

    }
}
