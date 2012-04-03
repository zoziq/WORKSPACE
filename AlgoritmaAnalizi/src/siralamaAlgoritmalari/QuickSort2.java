package siralamaAlgoritmalari;

import java.util.ArrayList;

public class QuickSort2 {

	public static void main(String[] args) {
		
		int dizi[] = {2,6,4,9,0,1,5,3,7,8};
		
		ArrayList<Integer> sol = new ArrayList<Integer>();
		ArrayList<Integer> sag = new ArrayList<Integer>();
		
		for (int i = 0; i < 1; i++) {
			for (int j = i+1; j < dizi.length; j++) {
				if (dizi[i] > dizi[j]) {
					sol.add(dizi[j]);
				}
				else {
					sag.add(dizi[j]);
				}
			}
			for (int j = 0; j < sol.size(); j++) {
				dizi[j] = sol.get(j);
			}
			dizi[sol.size()] = i;
			for (int j = dizi.length-sag.size()+1; j < dizi.length; j++) {
				dizi[j] = sag.get(j-sol.size());
			}
			
		}
		
		
		for (int i = 0; i < dizi.length; i++) {
			System.out.println(dizi[i]);
		}
	}
	
}
