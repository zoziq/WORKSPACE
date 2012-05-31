package kripto;

public class Vigenere {
	
	public static void main(String[] args) {
		
		String anahtar = "top";		
		String metin = "mustafa";
		String �ifreliMetin = "HJ�OO�T";
		
		System.out.println(de�ifrele(anahtar, �ifrele(anahtar, metin)));
	}
	
	static char c[] = {'A','B','C','�','D','E','F','G','�','H','I','�','J','K','L','M','N','O','�','P','R','S','�','T','U','�','V','Y','Z',}; 

	static String �ifrele(String anahtar, String metin) {
		metin = metin.toUpperCase();
		anahtar = anahtar.toUpperCase();
		
		char metindekiHarfler[] = metin.toCharArray();
		char �ifreliMetindekiHarfler[] = new char[metindekiHarfler.length];
		char anahtardakiHarfler[] = anahtar.toCharArray();
		
		for (int i = 0; i < metindekiHarfler.length; i++) {
			int ii = i % anahtardakiHarfler.length;
	
			int indeks1 = indeksBul(metindekiHarfler[i]);
			int indeks2 = indeksBul(anahtardakiHarfler[ii]);
			
			if (indeks1 == -1) {
				�ifreliMetindekiHarfler[i] = '#';
				continue;
			}
			
			if (indeks1 + indeks2 >= 29) 
				�ifreliMetindekiHarfler[i] = c[indeks1 + indeks2 - 29];
			else
				�ifreliMetindekiHarfler[i] = c[indeks1 + indeks2];
		}
		
		String s = "";
		for (int i = 0; i < �ifreliMetindekiHarfler.length; i++) {
			s += �ifreliMetindekiHarfler[i];
		}
		
		return s;
	}
	
	static String de�ifrele(String anahtar, String �ifreliMetin) {
		�ifreliMetin = �ifreliMetin.toUpperCase();
		anahtar = anahtar.toUpperCase();
		
		char �ifreliMetindekiHarfler[] = �ifreliMetin.toCharArray();
		char metindekiHarfler[] = new char[�ifreliMetindekiHarfler.length];
		char anahtardakiHarfler[] = anahtar.toCharArray();
		
		for (int i = 0; i < �ifreliMetindekiHarfler.length; i++) {
			int ii = i % anahtardakiHarfler.length;
	
			int indeks1 = indeksBul(�ifreliMetindekiHarfler[i]);
			int indeks2 = indeksBul(anahtardakiHarfler[ii]);
			
			if (indeks1 == -1) {
				�ifreliMetindekiHarfler[i] = '#';
				continue;
			}
			
			if (indeks1 - indeks2 <= -1) 
				metindekiHarfler[i] = c[indeks1 - indeks2 + 29];
			else
				metindekiHarfler[i] = c[indeks1 - indeks2];
		}
		
		String s = "";
		for (int i = 0; i < metindekiHarfler.length; i++) {
			s += metindekiHarfler[i];
		}
		
		return s;
	}
	static int indeksBul(char harf) {
		for (int i = 0; i < c.length; i++) {
			if (c[i] == harf) {
				return i;
			}
		}
		return -1;
	}


}
