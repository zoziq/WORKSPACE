package kripto;

public class Vigenere {
	
	public static void main(String[] args) {
		
		String anahtar = "top";		
		String metin = "mustafa";
		String şifreliMetin = "HJİOOÜT";
		
		System.out.println(deşifrele(anahtar, şifrele(anahtar, metin)));
	}
	
	static char c[] = {'A','B','C','Ç','D','E','F','G','Ğ','H','I','İ','J','K','L','M','N','O','Ö','P','R','S','Ş','T','U','Ü','V','Y','Z',}; 

	static String şifrele(String anahtar, String metin) {
		metin = metin.toUpperCase();
		anahtar = anahtar.toUpperCase();
		
		char metindekiHarfler[] = metin.toCharArray();
		char şifreliMetindekiHarfler[] = new char[metindekiHarfler.length];
		char anahtardakiHarfler[] = anahtar.toCharArray();
		
		for (int i = 0; i < metindekiHarfler.length; i++) {
			int ii = i % anahtardakiHarfler.length;
	
			int indeks1 = indeksBul(metindekiHarfler[i]);
			int indeks2 = indeksBul(anahtardakiHarfler[ii]);
			
			if (indeks1 == -1) {
				şifreliMetindekiHarfler[i] = '#';
				continue;
			}
			
			if (indeks1 + indeks2 >= 29) 
				şifreliMetindekiHarfler[i] = c[indeks1 + indeks2 - 29];
			else
				şifreliMetindekiHarfler[i] = c[indeks1 + indeks2];
		}
		
		String s = "";
		for (int i = 0; i < şifreliMetindekiHarfler.length; i++) {
			s += şifreliMetindekiHarfler[i];
		}
		
		return s;
	}
	
	static String deşifrele(String anahtar, String şifreliMetin) {
		şifreliMetin = şifreliMetin.toUpperCase();
		anahtar = anahtar.toUpperCase();
		
		char şifreliMetindekiHarfler[] = şifreliMetin.toCharArray();
		char metindekiHarfler[] = new char[şifreliMetindekiHarfler.length];
		char anahtardakiHarfler[] = anahtar.toCharArray();
		
		for (int i = 0; i < şifreliMetindekiHarfler.length; i++) {
			int ii = i % anahtardakiHarfler.length;
	
			int indeks1 = indeksBul(şifreliMetindekiHarfler[i]);
			int indeks2 = indeksBul(anahtardakiHarfler[ii]);
			
			if (indeks1 == -1) {
				şifreliMetindekiHarfler[i] = '#';
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
