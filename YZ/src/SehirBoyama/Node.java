package SehirBoyama;

import java.util.ArrayList;
import java.util.LinkedList;


public class Node {

	public String isim;
	public LinkedList<Node> baglanti = new LinkedList<Node>();
	public String renk;
	
	public Node(String isim) {
		super();
		this.isim = isim;
	}
	
	public static void main(String[] args) {
		
	
		ArrayList<String> s = new ArrayList<String>();
		
		s.add("Mustafa");
		s.add("Birgül");
		s.add("Top");
		
		s.remove("Mustafa");s.remove("Mustafa");s.remove("Top");s.remove("Top");s.remove("Birgül");
		
		System.out.println(s);
		
		if (s.size()==0) {
			System.out.println("ss");
		}
		

		
	}
	
}
