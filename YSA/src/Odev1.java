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
		System.out.print("Giri� Say�s�: ");
		girisSayisi = okunanDeger.nextInt();
		System.out.print("Ara Katman Say�s�: ");
		araKatmanSayisi = okunanDeger.nextInt();
		System.out.print("��k�� Say�s�: ");
		cikisSayisi = okunanDeger.nextInt();
		System.out.print("\nlambda de�eri: ");
		double lambda = okunanDeger.nextDouble();
		System.out.print("alfa de�eri: ");
		double alfa = okunanDeger.nextDouble();
		System.out.print("Hata de�eri: ");
		double hataDegeri = okunanDeger.nextDouble();
	
		
		double girisler[] = new double[girisSayisi];
		double girisArakatmanAgirliklar[][] = new double[girisSayisi][araKatmanSayisi];
		double arakatmanCikisAgirliklar[][] = new double[araKatmanSayisi][cikisSayisi];
		
		//////////////////
		//   G�R��LER   //
		//////////////////
		System.out.println("\nGiri� De�erleri");
		for (int i = 0; i < girisSayisi; i++) {
			System.out.print(i+1 + ". Giri� De�eri = ");
			girisler[i] = okunanDeger.nextDouble();
		}
		System.out.print("Giri�ler i�in beklenen de�er: ");
		double beklenenDeger = okunanDeger.nextDouble();
		//////////////////
		// A�IRLIKLAR   //
		//////////////////
		System.out.println("\nGiri�-Arakatman A��rl�klar�");
		for (int i = 0; i < girisSayisi; i++) {
			for (int j = 0; j < araKatmanSayisi; j++) {
				girisArakatmanAgirliklar[i][j] = Math.random();
				System.out.print("[" + (i+1) + "," + (j+1) + "] = " + girisArakatmanAgirliklar[i][j] + "  \t");
			}
			System.out.println();
		}
		System.out.println("\nArakatman-��k�� A��rl�klar�");
		for (int i = 0; i < araKatmanSayisi; i++) {
			for (int j = 0; j < cikisSayisi; j++) {
				arakatmanCikisAgirliklar[i][j] = Math.random();
				System.out.print("[" + (i+1) + "," + (j+1) + "] = " + arakatmanCikisAgirliklar[i][j] + "  \t");
			}
			System.out.println();
		}
		
		//////////////////
		//    B�ASLAR   //
		//////////////////
		double arakatmanBias[] = new double[araKatmanSayisi];
		double cikisBias[] = new double[cikisSayisi];
		
		System.out.println("\nArakatman-Bias De�erleri");
		for (int i = 0; i < araKatmanSayisi; i++) {
			arakatmanBias[i] = Math.random();
			System.out.println((i+1) + ". Arakatman Bias De�eri = " + arakatmanBias[i]);
		}
		System.out.println("\n��k��-Bias De�erleri");
		for (int i = 0; i < cikisSayisi; i++) {
			cikisBias[i] = Math.random();
			System.out.println((i+1) + ". ��k�� Bias De�eri = " + cikisBias[i]);
		}
		
		//////////////////
		//   NETLER   //
		//////////////////
		double araKatmanNetler[] = new double[araKatmanSayisi];
		double araKatmanFNetler[] = new double[araKatmanSayisi];
		double cikisNetler[] = new double[cikisSayisi];
		double cikisFNetler[] = new double[cikisSayisi];
		double geciciNet = 0;
		
		System.out.println("\nArakatman Net ve FNet De�erleri");
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
		
		System.out.println("\n��k�� Net ve FNet De�erleri");
		for (int i = 0; i < cikisSayisi; i++) {
			for (int j = 0; j < araKatmanSayisi; j++) {
				geciciNet += araKatmanFNetler[j] * arakatmanCikisAgirliklar[j][i];
			}
			cikisNetler[i] = geciciNet;
			geciciNet = 0;
			System.out.println("��k�� Net" + (i+1) + " = " + cikisNetler[i]);
			cikisFNetler[i] = fnet(cikisNetler[i], cikisBias[i]);
			System.out.println("��k�� FNet" + (i+1) + " = " + cikisFNetler[i]);
		}
		
		//////////////////
		// GER� YAYILIM //
		//////////////////
		
		double hataMiktari = 0;

		double cikisAgirlikDegisim[][] = new double[araKatmanSayisi][cikisSayisi];
		double cikisBiasDegisim[] = new double[cikisSayisi];
		double arakatmanAgirlikDegisim[][] = new double[girisSayisi][araKatmanSayisi];
		double arakatmanBiasDegisim[] = new double[araKatmanSayisi];
		
		//////////////////
		//   G�NCELLE   //
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
			//   G�R��LER   //
			//////////////////
			System.out.println("\nGiri� De�erleri");
			for (int i = 0; i < girisSayisi; i++) {
				System.out.print(i+1 + ". Giri� De�eri = ");
				girisler[i] = okunanDeger.nextDouble();
			}
			System.out.print("Giri�ler i�in beklenen de�er: ");
			beklenenDeger = okunanDeger.nextDouble();
					
			geciciNet = 0;
			System.out.println("\nArakatman Net ve FNet De�erleri");
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
			System.out.println("\n��k�� Net ve FNet De�erleri");
			for (int i = 0; i < cikisSayisi; i++) {
				for (int j = 0; j < araKatmanSayisi; j++) {
					geciciNet += araKatmanFNetler[j] * arakatmanCikisAgirliklar[j][i];
				}
				cikisNetler[i] = geciciNet;
				geciciNet = 0;
				System.out.println("��k�� Net" + (i+1) + " = " + cikisNetler[i]);
				cikisFNetler[i] = fnet(cikisNetler[i], cikisBias[i]);
				System.out.println("��k�� FNet" + (i+1) + " = " + cikisFNetler[i]);
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
