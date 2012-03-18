package siralamaAlgoritmalari;

public class InsertionSort {

	public static void main(String[] args) {
		
		long t = System.nanoTime();
		
//		int dizi[] = {2,6,4,9,0,1,5,3,7,8};
		
		int n = 25000;
		
		int dizi[] = new int[n];
		
		for (int i = 0; i < dizi.length; i++) {
			dizi[i] = (int)(Math.random()*n);
		}
		
		for (int i = 0; i < dizi.length; i++) {
			System.out.print(dizi[i] + ", ");
		}
		
		for (int i = 1; i < dizi.length; i++) {
			while(true) {
				if(dizi[i] < dizi[i-1]){
					int a = dizi[i];
					dizi[i] = dizi[i-1];
					dizi[i-1] = a;
					i--;
					if(i==0){
						break;
					}
				}else {
					break;
				}
			}
		}
		
		System.out.println();
		for (int i = 0; i < dizi.length; i++) {
			System.out.print(dizi[i] + ", ");
		}
		
		System.out.println("\nSüre: " + (double)(System.nanoTime()-t)/1000000 + " milisaniye");
	}
	
}
