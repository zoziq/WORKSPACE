package kripto;

public class Cesar {

	public static void main(String[] args) {
		
		int anahtar = 12;		
		String metin = "mustafa";
		String şifreliMetin = "YGDFJÖJ";
		
		System.out.println(deşifrele(anahtar, şifrele(anahtar, metin)));
	}
	
	static char c[] = {'A','B','C','Ç','D','E','F','G','Ğ','H','I','İ','J','K','L','M','N','O','Ö','P','R','S','Ş','T','U','Ü','V','Y','Z',}; 

	static String şifrele(int anahtar, String metin) {
		metin = metin.toUpperCase(); 
		char metindekiHarfler[] = metin.toCharArray();
		char şifreliMetindekiHarfler[] = new char[metindekiHarfler.length];
		
		for (int i = 0; i < metindekiHarfler.length; i++) {
			int indeks = indeksBul(metindekiHarfler[i]);
			
			if (indeks == -1) {
				şifreliMetindekiHarfler[i] = '#';
				continue;
			}
			
			if (indeks + anahtar >= 29) 
				şifreliMetindekiHarfler[i] = c[indeks + anahtar - 29];
			else
				şifreliMetindekiHarfler[i] = c[indeks + anahtar];
		}
		
		String s = "";
		for (int i = 0; i < şifreliMetindekiHarfler.length; i++) {
			s += şifreliMetindekiHarfler[i];
		}
		
		return s;
	}
	
	static String deşifrele(int anahtar, String şifreliMetin) {
		şifreliMetin = şifreliMetin.toUpperCase(); 
		char şifreliMetindekiHarfler[] = şifreliMetin.toCharArray();
		char metindekiHarfler[] = new char[şifreliMetindekiHarfler.length];
		
		for (int i = 0; i < şifreliMetindekiHarfler.length; i++) {
			int indeks = indeksBul(şifreliMetindekiHarfler[i]);
			
			if (indeks == -1) {
				metindekiHarfler[i] = '#';
				continue;
			}
			
			if (indeks - anahtar <= -1) 
				metindekiHarfler[i] = c[indeks - anahtar + 29];
			else
				metindekiHarfler[i] = c[indeks - anahtar];
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
