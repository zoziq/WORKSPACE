package AStar;


public class Yol {

	public Node baslangic;
	public Node bitis;
	public int mesafe;
	
	public Yol(Node a, Node b, int m) {
		baslangic = a;
		bitis = b;
		mesafe = m;
	}
}
