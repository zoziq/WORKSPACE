package siralamaAlgoritmalari;

import java.util.Arrays;

public class QuickSort {

	int[] arr = { 2, 17, -4, 42, 9, 26, 11, 3, 5, 28 };

	void quickSort(int[] a, int altindis, int üstindis) {
		// altindis o adýmda sýralanan altdizinin ek küçük indisidir
		// üstindis o adýmda sýralanan altdizinin ek büyük indisidir
		int i = altindis, j = üstindis, h;
		// x terimi, mukayesenin yapýlacaðý mihenk'dir (pivot)
		int x = a[(altindis + üstindis) / 2];
		// Takas eylemiyle diziyi ayrýþtýrma
		do {
			while (a[i] < x)
				i++;
			while (a[j] > x)
				j--;
			if (i <= j) {
				h = a[i];
				a[i] = a[j];
				a[j] = h;
				i++;
				j--;
			}
			System.out.println(Arrays.toString(arr));
		} while (i <= j);
		// yinelge (recursion)
		if (altindis < j)
			quickSort(a, altindis, j);
		if (i < üstindis)
			quickSort(a, i, üstindis);
	}

	public static void main(String[] args) {
		
		QuickSort qs = new QuickSort();
		
		long t = System.nanoTime();
		
		int n = 25000;
		
		int dizi[] = new int[n];
		
		for (int i = 0; i < dizi.length; i++) {
			dizi[i] = (int)(Math.random()*n);
		}
		
		
		System.out.println(Arrays.toString(qs.arr));
		qs.quickSort(qs.arr, 0, 9);

		System.out.println(Arrays.toString(qs.arr));
		
		System.out.println("\nSüre: " + (double)(System.nanoTime()-t)/1000000 + " milisaniye");
	}

}
