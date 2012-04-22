package rekursif;

public class kokleriBul {

	public static void cozumKumesi(int x, int y){
		if (x==5 && y==5) {
			return;
		}
		if (x*x+y*y==25) {
			System.out.println(x+" , " +y);
		}
		if (x==5) {
			x=-5;
			y++;
		}
		cozumKumesi(++x, y);
	}
	
	public static void main(String[] args) {
		cozumKumesi(-5,-5);
	}
	
}
