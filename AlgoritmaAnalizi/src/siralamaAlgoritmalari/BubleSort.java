package siralamaAlgoritmalari;

public class BubleSort {

	public static void main(String[] args) {
		
		long t = System.nanoTime();
		
		int n = 25000;
		
		int dizi[] = new int[n];
		
		for (int i = 0; i < dizi.length; i++) {
			dizi[i] = (int)(Math.random()*n);
		}
		
		for (int i = 0; i < dizi.length; i++) {
			System.out.print(dizi[i] + ", ");
		}
		
		int kontrol = 0;
		while(kontrol == 0) {
			kontrol = 1;
			int diziBoyutu = dizi.length;
			for (int i = 0; i < diziBoyutu-1; i++) {
				if (dizi[i] > dizi[i+1]) {
					int gecici = dizi[i+1];
					dizi[i+1] = dizi[i];
					dizi[i] = gecici;
					kontrol = 0;
				}
			}
			diziBoyutu --;
		}
		
		System.out.println();
		for (int i = 0; i < dizi.length; i++) {
			System.out.print(dizi[i] + ", ");
		}
		
		System.out.println("\nSüre: " + (double)(System.nanoTime()-t)/1000000 + " milisaniye");
	}	
}
