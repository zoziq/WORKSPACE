import java.util.Scanner;


public class Odev1 {
	
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
		System.out.print("\nlambda deðeri: ");
		double lambda = okunanDeger.nextDouble();
		System.out.print("alfa deðeri: ");
		double alfa = okunanDeger.nextDouble();
		System.out.print("Hata deðeri: ");
		double hataDegeri = okunanDeger.nextDouble();
	
		
		double girisler[] = new double[girisSayisi];
		double girisArakatmanAgirliklar[][] = new double[girisSayisi][araKatmanSayisi];
		double arakatmanCikisAgirliklar[][] = new double[araKatmanSayisi][cikisSayisi];
		
		//////////////////
		//   GÝRÝÞLER   //
		//////////////////
		System.out.println("\nGiriþ Deðerleri");
		for (int i = 0; i < girisSayisi; i++) {
			System.out.print(i+1 + ". Giriþ Deðeri = ");
			girisler[i] = okunanDeger.nextDouble();
		}
		System.out.print("Giriþler için beklenen deðer: ");
		double beklenenDeger = okunanDeger.nextDouble();
		//////////////////
		// AÐIRLIKLAR   //
		//////////////////
		System.out.println("\nGiriþ-Arakatman Aðýrlýklarý");
		for (int i = 0; i < girisSayisi; i++) {
			for (int j = 0; j < araKatmanSayisi; j++) {
				girisArakatmanAgirliklar[i][j] = Math.random();
				System.out.print("[" + (i+1) + "," + (j+1) + "] = " + girisArakatmanAgirliklar[i][j] + "  \t");
			}
			System.out.println();
		}
		System.out.println("\nArakatman-Çýkýþ Aðýrlýklarý");
		for (int i = 0; i < araKatmanSayisi; i++) {
			for (int j = 0; j < cikisSayisi; j++) {
				arakatmanCikisAgirliklar[i][j] = Math.random();
				System.out.print("[" + (i+1) + "," + (j+1) + "] = " + arakatmanCikisAgirliklar[i][j] + "  \t");
			}
			System.out.println();
		}
		
		//////////////////
		//    BÝASLAR   //
		//////////////////
		double arakatmanBias[] = new double[araKatmanSayisi];
		double cikisBias[] = new double[cikisSayisi];
		
		System.out.println("\nArakatman-Bias Deðerleri");
		for (int i = 0; i < araKatmanSayisi; i++) {
			arakatmanBias[i] = Math.random();
			System.out.println((i+1) + ". Arakatman Bias Deðeri = " + arakatmanBias[i]);
		}
		System.out.println("\nÇýkýþ-Bias Deðerleri");
		for (int i = 0; i < cikisSayisi; i++) {
			cikisBias[i] = Math.random();
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
				geciciNet += girisler[j] * girisArakatmanAgirliklar[j][i]; 
			}
			araKatmanNetler[i] = geciciNet;
			geciciNet = 0;
			System.out.println("Arakatman Net" + (i+1) + " = " + araKatmanNetler[i]);
			araKatmanFNetler[i] = fnet(araKatmanNetler[i], arakatmanBias[i]);
			System.out.println("Arakatman FNet" + (i+1) + " = " + araKatmanFNetler[i]);
		}
		
		System.out.println("\nÇýkýþ Net ve FNet Deðerleri");
		for (int i = 0; i < cikisSayisi; i++) {
			for (int j = 0; j < araKatmanSayisi; j++) {
				geciciNet += araKatmanFNetler[j] * arakatmanCikisAgirliklar[j][i];
			}
			cikisNetler[i] = geciciNet;
			geciciNet = 0;
			System.out.println("Çýkýþ Net" + (i+1) + " = " + cikisNetler[i]);
			cikisFNetler[i] = fnet(cikisNetler[i], cikisBias[i]);
			System.out.println("Çýkýþ FNet" + (i+1) + " = " + cikisFNetler[i]);
		}
		
		//////////////////
		// GERÝ YAYILIM //
		//////////////////
		
		double hataMiktari = 0;

		double cikisAgirlikDegisim[][] = new double[araKatmanSayisi][cikisSayisi];
		double cikisBiasDegisim[] = new double[cikisSayisi];
		double arakatmanAgirlikDegisim[][] = new double[girisSayisi][araKatmanSayisi];
		double arakatmanBiasDegisim[] = new double[araKatmanSayisi];
		
		//////////////////
		//   GÜNCELLE   //
		//////////////////
		int a = 0;
		while(beklenenDeger-cikisFNetler[0] > hataDegeri && a<50000) {
			System.out.println("\n\n*************ITERASYON " + (++a) + "**************");
			for (int i = 0; i < cikisSayisi; i++) {
				hataMiktari = cikisFNetler[i] * (1-cikisFNetler[i]) * (beklenenDeger-cikisFNetler[i]);
				for (int j = 0; j < araKatmanSayisi; j++) {
					cikisAgirlikDegisim[j][i] = lambda*hataMiktari + alfa*cikisAgirlikDegisim[j][i];
					arakatmanCikisAgirliklar[j][i] += cikisAgirlikDegisim[j][i];
				}
			}	
			for (int i = 0; i < cikisSayisi; i++) {
				hataMiktari = cikisFNetler[i] * (1-cikisFNetler[i]) * (beklenenDeger-cikisFNetler[i]);
				cikisBiasDegisim[i] = lambda*hataMiktari + alfa*cikisBiasDegisim[i];
				cikisBias[i] += cikisBiasDegisim[i];
			}
			for (int i = 0; i < araKatmanSayisi; i++) {
				hataMiktari = araKatmanFNetler[i] * (1-araKatmanFNetler[i]) * (beklenenDeger-araKatmanFNetler[i]);
				for (int j = 0; j < girisSayisi; j++) {
					arakatmanAgirlikDegisim[j][i] = lambda*hataMiktari + alfa*arakatmanAgirlikDegisim[j][i];
					girisArakatmanAgirliklar[j][i] += arakatmanAgirlikDegisim[j][i];
				}
			}	
			for (int i = 0; i < araKatmanSayisi; i++) {
				hataMiktari = araKatmanFNetler[i] * (1-araKatmanFNetler[i]) * (beklenenDeger-araKatmanFNetler[i]);
				arakatmanBiasDegisim[i] = lambda*hataMiktari + alfa*arakatmanBiasDegisim[i];
				arakatmanBias[i] += arakatmanBiasDegisim[i];
			}
									
			//////////////////
			//   GÝRÝÞLER   //
			//////////////////
			System.out.println("\nGiriþ Deðerleri");
			for (int i = 0; i < girisSayisi; i++) {
				System.out.print(i+1 + ". Giriþ Deðeri = ");
				girisler[i] = okunanDeger.nextDouble();
			}
			System.out.print("Giriþler için beklenen deðer: ");
			beklenenDeger = okunanDeger.nextDouble();
					
			geciciNet = 0;
			System.out.println("\nArakatman Net ve FNet Deðerleri");
			for (int i = 0; i < araKatmanSayisi; i++) {
				for (int j = 0; j < girisSayisi; j++) {
					geciciNet += girisler[j] * girisArakatmanAgirliklar[j][i]; 
				}
				araKatmanNetler[i] = geciciNet;
				geciciNet = 0;
				System.out.println("Arakatman Net" + (i+1) + " = " + araKatmanNetler[i]);
				araKatmanFNetler[i] = fnet(araKatmanNetler[i], arakatmanBias[i]);
				System.out.println("Arakatman FNet" + (i+1) + " = " + araKatmanFNetler[i]);
			}
			System.out.println("\nÇýkýþ Net ve FNet Deðerleri");
			for (int i = 0; i < cikisSayisi; i++) {
				for (int j = 0; j < araKatmanSayisi; j++) {
					geciciNet += araKatmanFNetler[j] * arakatmanCikisAgirliklar[j][i];
				}
				cikisNetler[i] = geciciNet;
				geciciNet = 0;
				System.out.println("Çýkýþ Net" + (i+1) + " = " + cikisNetler[i]);
				cikisFNetler[i] = fnet(cikisNetler[i], cikisBias[i]);
				System.out.println("Çýkýþ FNet" + (i+1) + " = " + cikisFNetler[i]);
			}
			System.out.println("HATA = " + (beklenenDeger-cikisFNetler[0]));
		}

	}
	
	//////////////////
	//     FNET     //
	//////////////////
	public static double fnet(double net, double bias) {
		return 1 / (1 + (Math.pow(Math.E, -1*(net + bias))));
	}
	
}
