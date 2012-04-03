package SehirBoyama;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DegreeConstraint {

	public static ArrayList<Node> nodes = new ArrayList<Node>();
	public static HashMap<Node, Integer> sinirlar = new HashMap<Node, Integer>();
	public static ArrayList<String> renkler = new ArrayList<String>();

	
	public static void push(String n){
		for (int i = 0; i < renkler.size(); i++) {
			if(renkler.get(i).equals(n)){
				return;
			}
		}
		renkler.add(n);
	}
	
	public static Node max(){
		int buyuk = 0;
		Node  n = null;	
		for (int i = 0; i < nodes.size(); i++) {
			if (buyuk<sinirlar.get(nodes.get(i))) {
				buyuk = sinirlar.get(nodes.get(i));
				n = nodes.get(i);
			}
		}	
		nodes.remove(n);
		return n;	
	}
	
	public static void sinirHesapla(Node n){
		sinirlar.put(n, n.baglanti.size());
	}
	
	public static void degreeSearch(){
		
		for (int i = 0; i < 12; i++) {
			Node n = max();
			for (int j = 0; j < n.baglanti.size(); j++) {			
				renkler.remove(n.baglanti.get(j).renk);	
			}
			if (renkler.size()!=0) {
				n.renk = renkler.get(0);
				System.out.println(n.isim+ " --- " + n.renk);
			}
			push("Mavi");
			push("Sarý");
			push("Kýrmýzý");
		}
	
	}
	
	public static void main(String[] args) {

		renkler.add("Mavi");
		renkler.add("Sarý");
		renkler.add("Kýrmýzý");
		
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		Node e = new Node("E");
		Node f = new Node("F");
		Node g = new Node("G");
		Node h = new Node("H");
		Node i = new Node("I");
		Node j = new Node("J");
		Node k = new Node("K");
		
		nodes.add(a);
		nodes.add(b);
		nodes.add(c);
		nodes.add(d);
		nodes.add(e);
		nodes.add(f);
		nodes.add(g);
		nodes.add(h);
		nodes.add(i);
		nodes.add(j);
		nodes.add(k);

		a.baglanti.add(b);
		b.baglanti.add(a);
		a.baglanti.add(c);
		c.baglanti.add(a);
		a.baglanti.add(d);
		d.baglanti.add(a);

		b.baglanti.add(c);
		c.baglanti.add(b);
		b.baglanti.add(g);
		g.baglanti.add(b);

		c.baglanti.add(d);
		d.baglanti.add(c);
		c.baglanti.add(e);
		e.baglanti.add(c);
		c.baglanti.add(f);
		f.baglanti.add(c);
		c.baglanti.add(g);
		g.baglanti.add(c);

		d.baglanti.add(e);
		e.baglanti.add(d);

		e.baglanti.add(f);
		f.baglanti.add(e);
		e.baglanti.add(i);
		i.baglanti.add(e);
		e.baglanti.add(k);
		k.baglanti.add(e);

		f.baglanti.add(i);
		i.baglanti.add(f);
		f.baglanti.add(h);
		h.baglanti.add(f);
		f.baglanti.add(g);
		g.baglanti.add(f);

		g.baglanti.add(h);
		h.baglanti.add(g);

		h.baglanti.add(i);
		i.baglanti.add(h);
		h.baglanti.add(j);
		j.baglanti.add(h);

		i.baglanti.add(k);
		k.baglanti.add(i);
		i.baglanti.add(j);
		j.baglanti.add(i);

		j.baglanti.add(k);
		k.baglanti.add(j);
		
		for (int m = 0; m < nodes.size(); m++) {
			sinirHesapla(nodes.get(m));
		}
		
		degreeSearch();

	
	

	}

}
