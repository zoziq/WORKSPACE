package kripto;

public class Vigenere {
	
	public static void main(String[] args) {
		
		String anahtar = "top";		
		String metin = "mustafa";
		String þifreliMetin = "HJÝOOÜT";
		
		System.out.println(deþifrele(anahtar, þifrele(anahtar, metin)));
	}
	
	static char c[] = {'A','B','C','Ç','D','E','F','G','Ð','H','I','Ý','J','K','L','M','N','O','Ö','P','R','S','Þ','T','U','Ü','V','Y','Z',}; 

	static String þifrele(String anahtar, String metin) {
		metin = metin.toUpperCase();
		anahtar = anahtar.toUpperCase();
		
		char metindekiHarfler[] = metin.toCharArray();
		char þifreliMetindekiHarfler[] = new char[metindekiHarfler.length];
		char anahtardakiHarfler[] = anahtar.toCharArray();
		
		for (int i = 0; i < metindekiHarfler.length; i++) {
			int ii = i % anahtardakiHarfler.length;
	
			int indeks1 = indeksBul(metindekiHarfler[i]);
			int indeks2 = indeksBul(anahtardakiHarfler[ii]);
			
			if (indeks1 == -1) {
				þifreliMetindekiHarfler[i] = '#';
				continue;
			}
			
			if (indeks1 + indeks2 >= 29) 
				þifreliMetindekiHarfler[i] = c[indeks1 + indeks2 - 29];
			else
				þifreliMetindekiHarfler[i] = c[indeks1 + indeks2];
		}
		
		String s = "";
		for (int i = 0; i < þifreliMetindekiHarfler.length; i++) {
			s += þifreliMetindekiHarfler[i];
		}
		
		return s;
	}
	
	static String deþifrele(String anahtar, String þifreliMetin) {
		þifreliMetin = þifreliMetin.toUpperCase();
		anahtar = anahtar.toUpperCase();
		
		char þifreliMetindekiHarfler[] = þifreliMetin.toCharArray();
		char metindekiHarfler[] = new char[þifreliMetindekiHarfler.length];
		char anahtardakiHarfler[] = anahtar.toCharArray();
		
		for (int i = 0; i < þifreliMetindekiHarfler.length; i++) {
			int ii = i % anahtardakiHarfler.length;
	
			int indeks1 = indeksBul(þifreliMetindekiHarfler[i]);
			int indeks2 = indeksBul(anahtardakiHarfler[ii]);
			
			if (indeks1 == -1) {
				þifreliMetindekiHarfler[i] = '#';
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
