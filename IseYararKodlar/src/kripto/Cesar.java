package kripto;

public class Cesar {

	public static void main(String[] args) {
		
		int anahtar = 12;		
		String metin = "mustafa";
		String þifreliMetin = "YGDFJÖJ";
		
		System.out.println(deþifrele(anahtar, þifrele(anahtar, metin)));
	}
	
	static char c[] = {'A','B','C','Ç','D','E','F','G','Ð','H','I','Ý','J','K','L','M','N','O','Ö','P','R','S','Þ','T','U','Ü','V','Y','Z',}; 

	static String þifrele(int anahtar, String metin) {
		metin = metin.toUpperCase(); 
		char metindekiHarfler[] = metin.toCharArray();
		char þifreliMetindekiHarfler[] = new char[metindekiHarfler.length];
		
		for (int i = 0; i < metindekiHarfler.length; i++) {
			int indeks = indeksBul(metindekiHarfler[i]);
			
			if (indeks == -1) {
				þifreliMetindekiHarfler[i] = '#';
				continue;
			}
			
			if (indeks + anahtar >= 29) 
				þifreliMetindekiHarfler[i] = c[indeks + anahtar - 29];
			else
				þifreliMetindekiHarfler[i] = c[indeks + anahtar];
		}
		
		String s = "";
		for (int i = 0; i < þifreliMetindekiHarfler.length; i++) {
			s += þifreliMetindekiHarfler[i];
		}
		
		return s;
	}
	
	static String deþifrele(int anahtar, String þifreliMetin) {
		þifreliMetin = þifreliMetin.toUpperCase(); 
		char þifreliMetindekiHarfler[] = þifreliMetin.toCharArray();
		char metindekiHarfler[] = new char[þifreliMetindekiHarfler.length];
		
		for (int i = 0; i < þifreliMetindekiHarfler.length; i++) {
			int indeks = indeksBul(þifreliMetindekiHarfler[i]);
			
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
