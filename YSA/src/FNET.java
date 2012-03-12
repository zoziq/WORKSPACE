/*
 * System.out.println("\n" + (k+1) + ". yineleme Arakatman-��k�� A��rl�klar�");
			for (int i = 0; i < araKatmanSayisi; i++) {
				for (int j = 0; j < cikisSayisi; j++) {
					arakatmanCikisAgirliklar[i][j] += cikisAgirlikDegisim[i][j];
					System.out.print("[" + (i+1) + "," + (j+1) + "] = " + arakatmanCikisAgirliklar[i][j] + "  \t");
				}
				System.out.println();
			}
			System.out.println("\n" + k + ". yineleme ��k��-Bias De�erleri");
			for (int i = 0; i < cikisSayisi; i++) {
				cikisBias[i] += cikisBiasDegisim[i];
				System.out.println((i+1) + ". ��k�� Bias De�eri = " + arakatmanBias[i]);
			}
			System.out.println("\n" + (k+1) + ". yineleme Giri�-Arakatman A��rl�klar�");
			for (int i = 0; i < girisSayisi; i++) {
				for (int j = 0; j < araKatmanSayisi; j++) {
					girisArakatmanAgirliklar[i][j] += arakatmanAgirlikDegisim[i][j];
					System.out.print("[" + (i+1) + "," + (j+1) + "] = " + girisArakatmanAgirliklar[i][j] + "  \t");
				}
				System.out.println();
			}
			System.out.println("\n" + (k+1) + ". yineleme Arakatman-Bias De�erleri");
			for (int i = 0; i < araKatmanSayisi; i++) {
				arakatmanBias[i] += arakatmanBiasDegisim[i];
				System.out.println((i+1) + ". Arakatman Bias De�eri = " + arakatmanBias[i]);
			}
 */


public class FNET {

	public static double fnet(double net, double bias) {
		return 1 / (1 + (Math.pow(Math.E, -1*(net + bias))));
	}
	
	public static void main(String[] args) {
		System.out.println(fnet(.517,-.993));
		
		int[][] a = new int[5][4];
		
		System.out.println(a[1][1]);
		System.out.println(a.length);
	}
	
	
}
