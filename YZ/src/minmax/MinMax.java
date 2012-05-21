package minmax;

import java.util.Scanner;

public class MinMax {

	
	private static int secilen;
	private static int toplam;
	private static int[] sayilar = {0,1,2,3,4,5,6,7,8,9};
	private static boolean[] kullanildi = {false,false,false,false,false,false,false,false,false,false };


	static boolean max(int derinlik, int araToplam) {
		if(toplam >= 30) {
			return true;
		}
		
		for (int i = 0; i < 10; i++) {
			if (kullanildi[i]) {
				continue;
			}
			kullanildi[i] = true;
			if(min(araToplam+i, derinlik+1)) {
				toplam += i;
				secilen = i;
				return true;
			}
			else {
				kullanildi[i] = false;
			}
		}
		return true;
		
	}


	static boolean min(int derinlik, int araToplam) {
		if(toplam >= 30) {
			return false;
		}
		
		for (int i = 0; i < 10; i++) {
			if (kullanildi[i]) {
				continue;
			}
			kullanildi[i] = true;
			if(max(araToplam+i, derinlik+1)) {
				kullanildi[i] = false;
				return false;
			}		
		}
		return true;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			sayilar[i] = i;
		}
		int sira = 0;
		
		while(toplam < 30) {
			if (sira%2 == 0) {
				System.out.println("Seçilen:\n" + secilen);
			}
			System.out.println("Toplam " + toplam);
			System.out.println("Kalan Sayılar: ");
			for (int i = 0; i < 10; i++) {
				if (!kullanildi[i]) {
					System.out.print(sayilar[i] + " ");
				}
			}
			System.out.println();
			if (sira%2 == 0) {
				System.out.println("Sayı Girin: ");
				sira ++;
				Scanner s = new Scanner(System.in);
				int okunan = s.nextInt();
				toplam += okunan;
				kullanildi[okunan] = true;
			}
			else {
				sira ++;
				max(toplam, 0);
			}
		}
		System.out.println("Toplam " + toplam);
		System.out.println("Kalan Sayılar: ");
		for (int i = 0; i < 10; i++) {
			if (!kullanildi[i]) {
				System.out.print(sayilar[i] + " ");
			}
		}
	}
	
}
