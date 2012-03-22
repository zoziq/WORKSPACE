package RakamlariFarkliEsitlik;

public class RakamlariFarkli {
	
	
	/*
	 * Problem;
	 * Tum degiskenlere farkli rakamlar atanmasi sartiyla,
	 * abc * de * f = ghij
	 * esitligini saglayacak kombinasyonlari bulmak.
	 */
	
	public static int[] rakamlar(long i){
		String s;
		if (i<1000000000) {		//Sayi 9 basamakliysa basinda 0 ekler.
			s = "0" + i;
		}
		else {
			s = "" + i;
		}
		char c[] = s.toCharArray();
		int dizi[] = new int[10];
		for (int j = 0; j < c.length; j++) {
			dizi[j] = Integer.parseInt(""+c[j]);
		}
		return dizi;	//10 basamakli bir sayinin tum elemanlarini bir diziyle geri dondurur.
	}
	
	public static void main(String[] args) {
		
		int kontrol = 0;
		for (long i = 123456789L; i < 9876543210L; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = j+1; k < 10; k++) {
					if(rakamlar(i)[j]==rakamlar(i)[k]) {
						i += Math.pow(10,(9-k));	//Icinde ayni rakamdan en az 2 tane olan 10 basamakli
						kontrol = 1;				//bir i sayisina denk gelinmistir. 123457700 sayisinda olalim.
						break;						//Bu durumda i sayisini 1 artirmak yerine 1000 artirmak gerekir.
					}
				}
				if (kontrol==1) {		//Uygun bir i sayisi bulunamadigi icin en ustteki for dongusune gidilir.
					break;			
				}
			}
			if (kontrol==0) {
				int abc = rakamlar(i)[0]*100 + rakamlar(i)[1]*10 + rakamlar(i)[2];
				int de = rakamlar(i)[3]*10 + rakamlar(i)[4];
				int f = rakamlar(i)[5];
				int ghij = rakamlar(i)[6]*1000 + rakamlar(i)[7]*100 + rakamlar(i)[8]*10 + rakamlar(i)[9];
				if(abc*de*f==ghij) {
					System.out.println(i + "---> " + abc + " * " + de + " * " + f + " = " + ghij);
				}	
			}
			kontrol = 0;
		}
	}
}
