package greedySearch;


public class GYol {
	private GNode baslangic;
	private GNode bitis;
	public GYol(GNode baslangic, GNode bitis) {
		super();
		this.baslangic = baslangic;
		this.bitis = bitis;
	}
	public GNode getBaslangic() {
		return baslangic;
	}
	public GNode getBitis() {
		return bitis;
	}
	public void setBaslangic(GNode baslangic) {
		this.baslangic = baslangic;
	}
	public void setBitis(GNode bitis) {
		this.bitis = bitis;
	}
}
