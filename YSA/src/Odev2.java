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
		System.out.print("Giri� Say�s�: ");
		girisSayisi = okunanDeger.nextInt();
		System.out.print("Ara Katman Say�s�: ");
		araKatmanSayisi = okunanDeger.nextInt();
		System.out.print("��k�� Say�s�: ");
		cikisSayisi = okunanDeger.nextInt();
		
		double girisler[] = new double[girisSayisi];
		double girisAgirliklar[][] = new double[girisSayisi][araKatmanSayisi];
		double cikisAgirliklar[][] = new double[araKatmanSayisi][cikisSayisi];
		
		//////////////////
		//   G�R��LER   //
		//////////////////
		System.out.println("\nGiri� De�erleri");
		for (int i = 0; i < girisSayisi; i++) {
			System.out.print(i+1 + ". Giri� De�eri = ");
			girisler[i] = okunanDeger.nextDouble();
		}
		//////////////////
		// A�IRLIKLAR   //
		//////////////////
		System.out.println("\nGiri�-Arakatman A��rl�klar�");
		for (int i = 0; i < girisSayisi; i++) {
			for (int j = 0; j < araKatmanSayisi; j++) {
				System.out.println("gir");
				girisAgirliklar[i][j] = okunanDeger.nextDouble();
				System.out.print("[" + (i+1) + "," + (j+1) + "] = " + girisAgirliklar[i][j] + "  \t");
			}
			System.out.println();
		}
		System.out.println("\nArakatman-��k�� A��rl�klar�");
		for (int i = 0; i < araKatmanSayisi; i++) {
			for (int j = 0; j < cikisSayisi; j++) {
				System.out.println("gir");
				cikisAgirliklar[i][j] = okunanDeger.nextDouble();
				System.out.print("[" + (i+1) + "," + (j+1) + "] = " + cikisAgirliklar[i][j] + "  \t");
			}
			System.out.println();
		}
		
		//////////////////
		//    B�ASLAR   //
		//////////////////
		double araKatmanBias[] = new double[araKatmanSayisi];
		double cikisBias[] = new double[cikisSayisi];
		
		System.out.println("\nArakatman-Bias De�erleri");
		for (int i = 0; i < araKatmanSayisi; i++) {
			System.out.println("gir");
			araKatmanBias[i] = okunanDeger.nextDouble();
			System.out.println((i+1) + ". Arakatman Bias De�eri = " + araKatmanBias[i]);
		}
		System.out.println("\n��k��-Bias De�erleri");
		for (int i = 0; i < cikisSayisi; i++) {
			System.out.println("gir");
			cikisBias[i] = okunanDeger.nextDouble();
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
				geciciNet += girisler[j] * girisAgirliklar[j][i]; 
			}
			araKatmanNetler[i] = geciciNet;
			geciciNet = 0;
			System.out.println("Arakatman Net" + (i+1) + " = " + araKatmanNetler[i]);
			araKatmanFNetler[i] = fnet(araKatmanNetler[i], araKatmanBias[i]);
			System.out.println("Arakatman FNet" + (i+1) + " = " + araKatmanFNetler[i]);
		}
		
		System.out.println("\n��k�� Net ve FNet De�erleri");
		for (int i = 0; i < cikisSayisi; i++) {
			for (int j = 0; j < araKatmanSayisi; j++) {
				geciciNet += araKatmanFNetler[j] * cikisAgirliklar[j][i];
			}
			cikisNetler[i] = geciciNet;
			geciciNet = 0;
			System.out.println("��k�� Net" + (i+1) + " = " + cikisNetler[i]);
			cikisFNetler[i] = fnet(cikisNetler[i], cikisBias[i]);
			System.out.println("��k�� FNet" + (i+1) + " = " + cikisFNetler[i]);
		}
		
	}
	
	//////////////////
	//     FNET     //
	//////////////////
	public static double fnet(double net, double bias) {
		return 1 / (1 + (Math.pow(Math.E, -1*(net + bias))));
	}
	
}
