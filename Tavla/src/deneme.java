
public class deneme {

	public long globalTime = System.currentTimeMillis();
	
	public  static int zarListesi[][] = new int[20][2];
	
	public void randomZar() {
		for (int j = 0; j < 20; j++) {
			for (int i = 0; i < 2; i++) {
				zarListesi[j][i] = (int)(Math.random()*6)+1;
				
			}
		}
	}
	
	public int listedenZarSec(int sira) {
		int secilenZar = zarListesi[sira][0]*10+zarListesi[sira][1];
		int liste[][] = new int[zarListesi.length - 1][2];
		for (int j = 0; j < zarListesi.length -1; j++) {
			for (int i = 0; i < 2; i++) {
				liste[j][i] = zarListesi[j][i];
			}
		}
		for (int j = sira; j < zarListesi.length -1; j++) {
			for (int i = 0; i < 2; i++) {
				liste[j][i] = zarListesi[j+1][i];
			}
		}
		zarListesi = new int[zarListesi.length -1][2];
		for (int j = 0; j < zarListesi.length; j++) {
			for (int i = 0; i < 2; i++) {
				zarListesi[j][i] = liste[j][i];
			}
		}
		return secilenZar;
	}
	public static void yaz(){
		for (int j = 0; j < zarListesi.length; j++) {
			for (int i = 0; i < 2; i++) {
				System.out.print(zarListesi[j][i] + " ");
				
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		
		
		deneme d = new deneme();
		/*
		d.randomZar();
		yaz();
		
		System.out.println(d.listedenZarSec(1));
		yaz();
		System.out.println(d.listedenZarSec(1));
		yaz();
		*/
		
		System.out.println(d.globalTime);
		
	}
	
}
