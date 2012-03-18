import java.util.Scanner;


public class Odev1 {
	
	//0 manuel, 1 random
	static int secim = 0; 
	
	static double Ai[][] = {{.123,-.546}, {-.255,.342}};
	static double Aa[][] = {{-.258,.445}, {.135,-.375}};
	static double Bi[] = {.243,-.675};
	static double Ba[] = {-.575,.425};
	
	public static void main(String[] args) {
		int girisSayisi = 0;
		int araKatmanSayisi = 0;
		int cikisSayisi = 0;
		double lambda = 0;
		double alfa = 0;
		double hataToleransi = 0;

		
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
		lambda = okunanDeger.nextDouble();
		System.out.print("alfa de�eri: ");
		alfa = okunanDeger.nextDouble();
		System.out.print("Hata de�eri: ");
		hataToleransi = okunanDeger.nextDouble();
	
		
		double girisler[] = new double[girisSayisi];
		double beklenenDegerler[] = new double[cikisSayisi];
		double girisArakatmanAgirliklar[][] = new double[girisSayisi][araKatmanSayisi];
		double arakatmanCikisAgirliklar[][] = new double[araKatmanSayisi][cikisSayisi];
		double yedekArakatmanCikisAgirliklar[][] = new double[araKatmanSayisi][cikisSayisi];
		
		
		for (int k = 0; k < 5; k++) {
			System.out.println("\n\n*************ITERASYON " + (k+1) + "**************");
			//////////////////
			//   G�R��LER   //
			//////////////////
			System.out.println("\nGiri� De�erleri");
			for (int i = 0; i < girisSayisi; i++) {
				System.out.print(i+1 + ". Giri� De�eri = ");
				girisler[i] = okunanDeger.nextDouble();
			}
			System.out.print("\nBeklenen De�erler\n");
			for (int i = 0; i < cikisSayisi; i++) {
				System.out.print(i+1 + ". Beklenen De�eri = ");
				beklenenDegerler[i] = okunanDeger.nextDouble();
			}
			
			//////////////////
			// A�IRLIKLAR   //
			//////////////////
			System.out.println("\nGiri�-Arakatman A��rl�klar�");
			for (int i = 0; i < girisSayisi; i++) {
				for (int j = 0; j < araKatmanSayisi; j++) {
					if (secim==0) 
						girisArakatmanAgirliklar[i][j] = Ai[i][j];
					else if(secim==1)
					    girisArakatmanAgirliklar[i][j] = Math.random();
					System.out.print("[" + (i+1) + "," + (j+1) + "] = " + girisArakatmanAgirliklar[i][j] + "  \t");
				}
				System.out.println();
			}
			System.out.println("\nArakatman-��k�� A��rl�klar�");
			for (int i = 0; i < araKatmanSayisi; i++) {
				for (int j = 0; j < cikisSayisi; j++) {
					if(secim==0)
						arakatmanCikisAgirliklar[i][j] = Aa[i][j];
					else if(secim==1)
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
				if(secim==0)
					arakatmanBias[i] = Bi[i];
				else if(secim==1)
					arakatmanBias[i] = Math.random();
				System.out.println((i+1) + ". Arakatman Bias De�eri = " + arakatmanBias[i]);
			}
			System.out.println("\n��k��-Bias De�erleri");
			for (int i = 0; i < cikisSayisi; i++) {
				if(secim==0)	
					cikisBias[i] = Ba[i];
				else if(secim==1)
					cikisBias[i] = Math.random();
				System.out.println((i+1) + ". ��k�� Bias De�eri = " + cikisBias[i]);
			}
			
			//////////////////
			//   NETLER     //
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
			//   HATALAR    //
			//////////////////
			double hatalar[] = new double[cikisSayisi];
			double toplamHata = 0;
			
			System.out.println("\nHata De�erleri");
			for (int i = 0; i < hatalar.length; i++) {
				hatalar[i] = beklenenDegerler[i]-cikisFNetler[i];
				System.out.println("Hata" + (i+1) + " = " + hatalar[i]);
			}
			
			System.out.println("\nToplam Hata");
			for (int i = 0; i < hatalar.length; i++) {
				toplamHata += Math.pow(hatalar[i], 2);
			}
			toplamHata = Math.pow(toplamHata, .5);
			System.out.println(toplamHata);
			
			if (toplamHata < hataToleransi) {
				System.out.println("\n�terasyonlar sona ermi�tir.");
				break;
			}
			
			//////////////////
			// GER� YAYILIM //
			//////////////////
			double sigmaCikis[] = new double[cikisSayisi];
			double sigmaArakatman[] = new double[araKatmanSayisi];
			
			double cikisAgirlikDegisim[][] = new double[araKatmanSayisi][cikisSayisi];
			double cikisBiasDegisim[] = new double[cikisSayisi];
			double arakatmanAgirlikDegisim[][] = new double[girisSayisi][araKatmanSayisi];
			double arakatmanBiasDegisim[] = new double[araKatmanSayisi];
			
			System.out.println("\nSigma ��k�� De�erleri");
			for (int i = 0; i < sigmaCikis.length; i++) {
				sigmaCikis[i] = cikisFNetler[i] * (1 - cikisFNetler[i]) * toplamHata;
				System.out.println("Sigma" + (i+1) + " = " + sigmaCikis[i]);
			}
			
			System.out.println("\nG�ncellenen Arakatman-��k�� A��rl�klar�");
			for (int i = 0; i < araKatmanSayisi; i++) {
				for (int j = 0; j < cikisSayisi; j++) {
					cikisAgirlikDegisim[i][j] = lambda*sigmaCikis[j]*araKatmanFNetler[i] + alfa*cikisAgirlikDegisim[i][j];
					yedekArakatmanCikisAgirliklar[i][j] = arakatmanCikisAgirliklar[i][j];
					arakatmanCikisAgirliklar[i][j] += cikisAgirlikDegisim[i][j];
					Aa[i][j] += cikisAgirlikDegisim[i][j];
					System.out.print("[" + (i+1) + "," + (j+1) + "] = " + arakatmanCikisAgirliklar[i][j] + "  \t");
				}
				System.out.println();
			}
			
			System.out.println("\nG�ncellenen ��k�� Bias A��rl�klar�");
			for (int i = 0; i < cikisSayisi; i++) {
				cikisBiasDegisim[i] = lambda*sigmaCikis[i] + alfa*cikisBiasDegisim[i];	
				cikisBias[i] += cikisBiasDegisim[i];
				Ba[i] += cikisBiasDegisim[i];
				System.out.println((i+1) + ". ��k�� Bias De�eri = " + cikisBias[i]);	
			}
			
			System.out.println("\nSigma Arakatman De�erleri");
			toplamHata = 0;
			for (int i = 0; i < araKatmanSayisi; i++) {
				for (int j = 0; j < sigmaCikis.length; j++) {
					toplamHata += sigmaCikis[j]*yedekArakatmanCikisAgirliklar[i][j];
				}
				sigmaArakatman[i] = araKatmanFNetler[i] * (1 - araKatmanFNetler[i]) * toplamHata;
				System.out.println("Sigma" + (i+1) + " = " + sigmaArakatman[i]);
			}
			
			System.out.println("\nG�ncellenen Giri�-Arakatman A��rl�klar�");
			for (int i = 0; i < girisSayisi; i++) {
				for (int j = 0; j < araKatmanSayisi; j++) {
					arakatmanAgirlikDegisim[i][j] = lambda*sigmaArakatman[j]*girisler[i] + alfa*arakatmanAgirlikDegisim[i][j];	
					girisArakatmanAgirliklar[i][j] += arakatmanAgirlikDegisim[i][j];
					Ai[i][j] += arakatmanAgirlikDegisim[i][j];
					System.out.print("[" + (i+1) + "," + (j+1) + "] = " + girisArakatmanAgirliklar[i][j] + "  \t");
				}
				System.out.println();
			}
			
			System.out.println("\nG�ncellenen Arakatman Bias A��rl�klar�");
			for (int i = 0; i < araKatmanSayisi; i++) {
				arakatmanBiasDegisim[i] = lambda*sigmaArakatman[i] + alfa*arakatmanBiasDegisim[i];	
				arakatmanBias[i] += arakatmanBiasDegisim[i];
				Bi[i] += arakatmanBiasDegisim[i];
				System.out.println((i+1) + ". Arakatman Bias De�eri = " + arakatmanBias[i]);	
			}
		}
	}
	
	//////////////////
	//     FNET     //
	//////////////////
	public static double fnet(double net, double bias) {
		return 1 / (1 + (Math.pow(Math.E, -1*(net + bias))));
	}
	
}
