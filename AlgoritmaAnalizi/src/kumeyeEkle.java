import java.util.ArrayList;


public class kumeyeEkle {

	
	public static ArrayList<Integer> listeyeEkle (ArrayList<Integer> a, int sayi){
		int s = 0;
		if (a.isEmpty()) {
			a.add(sayi);
		}
		else {
			for (int i = 0; i < a.size(); i++) {
				if (a.get(i)==sayi) {
					return a;
				}
				else{
					s = 1;
				}
			}
			if (s==1) {
				s=0;
				a.add(sayi);
				return a;
			}
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		ArrayList<Integer> liste = new ArrayList<Integer>();
		
		listeyeEkle(liste, 2);
		listeyeEkle(liste, 2);
		listeyeEkle(liste, 3);
		listeyeEkle(liste, 4);
		listeyeEkle(liste, 3);
		listeyeEkle(liste, 1);
		System.out.println(liste);
	}
}
