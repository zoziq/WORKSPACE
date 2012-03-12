import java.util.Scanner;


public class Odev2 {
	
	
	public static void main(String[] args) {
		int girisSayisi = 0;
		int araKatmanSayisi = 0;
		int cikisSayisi = 0;
		//////////////////
		//    SCANNER   //
		//////////////////
		Scanner okunanDeger = new Scanner(System.in);
		System.out.print("Giriþ Sayýsý: ");
		girisSayisi = okunanDeger.nextInt();
		System.out.print("Ara Katman Sayýsý: ");
		araKatmanSayisi = okunanDeger.nextInt();
		System.out.print("Çýkýþ Sayýsý: ");
		cikisSayisi = okunanDeger.nextInt();
		
		double girisler[] = new double[girisSayisi];
		double girisAgirliklar[][] = new double[girisSayisi][araKatmanSayisi];
		double cikisAgirliklar[][] = new double[araKatmanSayisi][cikisSayisi];
		
		//////////////////
		//   GÝRÝÞLER   //
		//////////////////
		System.out.println("\nGiriþ Deðerleri");
		for (int i = 0; i < girisSayisi; i++) {
			System.out.print(i+1 + ". Giriþ Deðeri = ");
			girisler[i] = okunanDeger.nextDouble();
		}
		//////////////////
		// AÐIRLIKLAR   //
		//////////////////
		System.out.println("\nGiriþ-Arakatman Aðýrlýklarý");
		for (int i = 0; i < girisSayisi; i++) {
			for (int j = 0; j < araKatmanSayisi; j++) {
				System.out.println("gir");
				girisAgirliklar[i][j] = okunanDeger.nextDouble();
				System.out.print("[" + (i+1) + "," + (j+1) + "] = " + girisAgirliklar[i][j] + "  \t");
			}
			System.out.println();
		}
		System.out.println("\nArakatman-Çýkýþ Aðýrlýklarý");
		for (int i = 0; i < araKatmanSayisi; i++) {
			for (int j = 0; j < cikisSayisi; j++) {
				System.out.println("gir");
				cikisAgirliklar[i][j] = okunanDeger.nextDouble();
				System.out.print("[" + (i+1) + "," + (j+1) + "] = " + cikisAgirliklar[i][j] + "  \t");
			}
			System.out.println();
		}
		
		//////////////////
		//    BÝASLAR   //
		//////////////////
		double araKatmanBias[] = new double[araKatmanSayisi];
		double cikisBias[] = new double[cikisSayisi];
		
		System.out.println("\nArakatman-Bias Deðerleri");
		for (int i = 0; i < araKatmanSayisi; i++) {
			System.out.println("gir");
			araKatmanBias[i] = okunanDeger.nextDouble();
			System.out.println((i+1) + ". Arakatman Bias Deðeri = " + araKatmanBias[i]);
		}
		System.out.println("\nÇýkýþ-Bias Deðerleri");
		for (int i = 0; i < cikisSayisi; i++) {
			System.out.println("gir");
			cikisBias[i] = okunanDeger.nextDouble();
			System.out.println((i+1) + ". Çýkýþ Bias Deðeri = " + cikisBias[i]);
		}
		
		//////////////////
		//   NETLER   //
		//////////////////
		double araKatmanNetler[] = new double[araKatmanSayisi];
		double araKatmanFNetler[] = new double[araKatmanSayisi];
		double cikisNetler[] = new double[cikisSayisi];
		double cikisFNetler[] = new double[cikisSayisi];
		double geciciNet = 0;
		
		System.out.println("\nArakatman Net ve FNet Deðerleri");
		for (int i = 0; i < araKatmanSayisi; i++) {
			for (int j = 0; j < girisSayisi; j++) {
				geciciNet += girisler[j] * girisAgirliklar[j][i]; 
			}
			araKatmanNetler[i] = geciciNet;
			geciciNet = 0;
			System.out.println("Arakatman Net" + (i+1) + " = " + araKatmanNetler[i]);
			araKatmanFNetler[i] = fnet(araKatmanNetler[i], araKatmanBias[i]);
			System.out.println("Arakatman FNet" + (i+1) + " = " + araKatmanFNetler[i]);
		}
		
		System.out.println("\nÇýkýþ Net ve FNet Deðerleri");
		for (int i = 0; i < cikisSayisi; i++) {
			for (int j = 0; j < araKatmanSayisi; j++) {
				geciciNet += araKatmanFNetler[j] * cikisAgirliklar[j][i];
			}
			cikisNetler[i] = geciciNet;
			geciciNet = 0;
			System.out.println("Çýkýþ Net" + (i+1) + " = " + cikisNetler[i]);
			cikisFNetler[i] = fnet(cikisNetler[i], cikisBias[i]);
			System.out.println("Çýkýþ FNet" + (i+1) + " = " + cikisFNetler[i]);
		}
		
	}
	
	//////////////////
	//     FNET     //
	//////////////////
	public static double fnet(double net, double bias) {
		return 1 / (1 + (Math.pow(Math.E, -1*(net + bias))));
	}
	
}
