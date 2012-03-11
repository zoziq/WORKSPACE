import java.util.ArrayList;
import java.util.Scanner;

public class MyModel {
	
	public static ArrayList<Double> girisler = new ArrayList<Double>();
	public static ArrayList<Double> agirliklar = new ArrayList<Double>();
	public static ArrayList<Double> araKatmanNetler = new ArrayList<Double>();
	public static ArrayList<Double> cikisNetler = new ArrayList<Double>();
	public static ArrayList<Double> araKatmanFNetler = new ArrayList<Double>();
	public static ArrayList<Double> cikisFNetler = new ArrayList<Double>();
	
	public static void main(String[] args) {
				
		int girisSayisi = 0;
		int araKatmanSayisi = 0;
		int cikisSayisi = 0;
		
		
		Scanner okunanDeger = new Scanner(System.in);
		
		System.out.print("Giriþ Sayýsý: ");
		girisSayisi = okunanDeger.nextInt();
		
		System.out.print("Ara Katman Sayýsý: ");
		araKatmanSayisi = okunanDeger.nextInt();
		
		System.out.print("Çýkýþ Sayýsý: ");
		cikisSayisi = okunanDeger.nextInt();
		
		for (int i = 0; i < girisSayisi; i++) {
			System.out.print(i+1 + ". Giriþ Deðeri = ");
			girisler.add(okunanDeger.nextDouble());
		}
		
		int agirlikSayisi = girisSayisi * araKatmanSayisi + araKatmanSayisi * cikisSayisi;
		double rastgeleAgirlik;
		for (int i = 0; i < agirlikSayisi; i++) {
			rastgeleAgirlik = Math.random();
			agirliklar.add(rastgeleAgirlik);
			System.out.println(i+1 + ". Aðýrlýk = " + rastgeleAgirlik);
		}
		
		double netDeger = 0.0;
		int agirlikIndeksi = 0;
		for (int i = 0; i < araKatmanSayisi; i++) {
			for (int j = 0; j < girisSayisi; j++) {
				netDeger += girisler.get(j) * agirliklar.get(agirlikIndeksi);
				agirlikIndeksi ++;
			}
			araKatmanNetler.add(netDeger);
			System.out.println(i+1 + ". Ara Katman Net Deðer = " + netDeger);
			netDeger = 0.0;
		}
		
		for (int i = 0; i < araKatmanNetler.size(); i++) {
			araKatmanFNetler.add(1 / (1 + (Math.pow(Math.E, (-1*araKatmanNetler.get(i))))));
			System.out.println(i+1 + ". Ara Katman FNet Deðer = " + araKatmanFNetler.get(i));
		}
		
		for (int i = 0; i < cikisSayisi; i++) {
			for (int j = 0; j < araKatmanSayisi; j++) {
				netDeger += araKatmanNetler.get(j) * agirliklar.get(agirlikIndeksi);
				agirlikIndeksi ++;
			}
			cikisNetler.add(netDeger);
			System.out.println(i+1 + ". Çýkýþ Net Deðer = " + netDeger);
			netDeger = 0.0;
		}
		
		for (int i = 0; i < cikisNetler.size(); i++) {
			cikisFNetler.add(1 / (1 + (Math.pow(Math.E, (-1*cikisNetler.get(i))))));
			System.out.println(i+1 + ". Çýkýþ FNet Deðer = " + cikisFNetler.get(i));
		}
		
		
	}	
		
}

