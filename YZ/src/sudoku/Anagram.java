package sudoku;

public class Anagram {

	public static Sayi sayilar[] = new Sayi[4];
	public static int dizi[] = new int[4];
	
	public static void anagram(int i){
		if (i==4) {
			for (int j = 0; j < dizi.length; j++) {
				System.out.print(dizi[j]+"-");
			}
			System.out.println();
			return;
		}
		
		for (int j = 0; j < 4; j++) {
			if (sayilar[j].kullanýldýmý) {
				continue;
			}
			sayilar[j].kullanýldýmý = true;
			dizi[j] = sayilar[j].deger;
			anagram(i+1);
			sayilar[j].kullanýldýmý = false;
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 4; i++) {
			sayilar[i] = new Sayi(i);
			sayilar[i].deger = i;
		}
		anagram(0);
	}
	
}
