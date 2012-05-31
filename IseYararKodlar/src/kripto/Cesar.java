package kripto;

public class Cesar {

	public static void main(String[] args) {
		
		int anahtar = 12;		
		String metin = "mustafa";
		String �ifreliMetin = "YGDFJ�J";
		
		System.out.println(de�ifrele(anahtar, �ifrele(anahtar, metin)));
	}
	
	static char c[] = {'A','B','C','�','D','E','F','G','�','H','I','�','J','K','L','M','N','O','�','P','R','S','�','T','U','�','V','Y','Z',}; 

	static String �ifrele(int anahtar, String metin) {
		metin = metin.toUpperCase(); 
		char metindekiHarfler[] = metin.toCharArray();
		char �ifreliMetindekiHarfler[] = new char[metindekiHarfler.length];
		
		for (int i = 0; i < metindekiHarfler.length; i++) {
			int indeks = indeksBul(metindekiHarfler[i]);
			
			if (indeks == -1) {
				�ifreliMetindekiHarfler[i] = '#';
				continue;
			}
			
			if (indeks + anahtar >= 29) 
				�ifreliMetindekiHarfler[i] = c[indeks + anahtar - 29];
			else
				�ifreliMetindekiHarfler[i] = c[indeks + anahtar];
		}
		
		String s = "";
		for (int i = 0; i < �ifreliMetindekiHarfler.length; i++) {
			s += �ifreliMetindekiHarfler[i];
		}
		
		return s;
	}
	
	static String de�ifrele(int anahtar, String �ifreliMetin) {
		�ifreliMetin = �ifreliMetin.toUpperCase(); 
		char �ifreliMetindekiHarfler[] = �ifreliMetin.toCharArray();
		char metindekiHarfler[] = new char[�ifreliMetindekiHarfler.length];
		
		for (int i = 0; i < �ifreliMetindekiHarfler.length; i++) {
			int indeks = indeksBul(�ifreliMetindekiHarfler[i]);
			
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
