package greedySearch;

import java.util.ArrayList;


public class GNode {
	private String isim;
	private ArrayList<GYol> yollar = new ArrayList<GYol>();
	private boolean visited;
	private int uzaklik;
	public GNode(String isim, int km) {
		super();
		this.isim = isim;
		visited = false;
	}
	
	public String getIsim() {
		return isim;
	}
	
	public void setIsim(String isim) {
		this.isim = isim;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public ArrayList<GYol> getYollar() {
		return yollar;
	}

	public void setUzaklik(int uzaklik) {
		this.uzaklik = uzaklik;
	}

	public int getUzaklik() {
		return uzaklik;
	}
}
