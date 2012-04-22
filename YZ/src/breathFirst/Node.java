package breathFirst;

import java.util.ArrayList;

public class Node {
	private String isim;
	private ArrayList<Yol> yollar = new ArrayList<Yol>();
	private boolean visited;
	private int hedefeUzaklik = 0;
	private int tempYol = 0;
	boolean marked = false;
	public boolean isMarked() {
		return marked;
	}
	public void setMarked(boolean marked) {
		this.marked = marked;
	}
	public int getHedefeUzaklik() {
		return hedefeUzaklik;
	}
	public Node(String isim) {
		super();
		this.isim = isim;
		visited = false;
	}
	public Node(String isim, int hedefeUzaklik) {
		super();
		this.isim = isim;
		visited = false;
		this.hedefeUzaklik = hedefeUzaklik;
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
	public ArrayList<Yol> getYollar() {
		return yollar;
	}
	public void setTempYol(int tempYol) {
		this.tempYol = tempYol;
	}
	public int getTempYol() {
		return tempYol;
	}
	
}
