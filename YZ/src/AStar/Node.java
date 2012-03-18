package AStar;

import java.util.LinkedList;


public class Node {

	public String ad;
	public LinkedList<Yol> yollar = new LinkedList<Yol>();
	public Node parent;
	public boolean visited;
	
	public Node(String ad) {
		this.ad=ad;
	}
	
}
