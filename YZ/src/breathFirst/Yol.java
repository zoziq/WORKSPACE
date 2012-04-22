package breathFirst;

public class Yol {
	private Node baslangic;
	private Node bitis;
	private int km;
	public Yol(Node baslangic, Node bitis, int km) {
		super();
		this.baslangic = baslangic;
		this.bitis = bitis;
		this.km = km;
	}
	public Node getBaslangic() {
		return baslangic;
	}
	public Node getBitis() {
		return bitis;
	}
	public int getKm() {
		return km;
	}
	public void setBaslangic(Node baslangic) {
		this.baslangic = baslangic;
	}
	public void setBitis(Node bitis) {
		this.bitis = bitis;
	}
	
}
