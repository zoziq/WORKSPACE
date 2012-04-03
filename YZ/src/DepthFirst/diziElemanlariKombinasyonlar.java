package DepthFirst;

public class diziElemanlariKombinasyonlar {

	public static int dizi[] = {3,4,5,6};
	public static int sayac = 0;
	
	public static void bul(){
		if(sayac==24) {
			return;
		}
		sayac++;
		for (int i = 0; i < dizi.length; i++) {
			System.out.print(dizi[i]+ " ");
		}	
		System.out.println();
		bul();
		
	}
	
	public static void main(String[] args) {
		
		bul();
		
		
	}
	
}
