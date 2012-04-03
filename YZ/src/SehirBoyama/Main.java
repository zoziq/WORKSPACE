package SehirBoyama;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;


public class Main {
	
	static Node a = new Node("A");
	static Node b = new Node("B");
	static Node c = new Node("C");
	static Node d = new Node("D");
	static Node e = new Node("E");
	static Node f = new Node("F");
	static Node g = new Node("G");
	static Node h = new Node("H");
	static Node i = new Node("I");
	static Node j = new Node("J");
	static Node k = new Node("K");
	
	public static HashMap<String, String> renkler = new HashMap<String, String>();
	public static HashMap<Node, Integer> sinirlar = new HashMap<Node, Integer>();
	public static ArrayList<Node> kuyruk = new ArrayList<Node>();
	public static ArrayList<Node> nodes = new ArrayList<Node>();
	
	public static Node pop(){
		Node n = kuyruk.get(0);
		kuyruk.remove(0);
		return n;
	}
	
	public static void push(Node n){
		kuyruk.add(n);
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
	
	
	public static void sinirHesapla(Node a){
		
		int sayac = 0;
		
		Node simdiki = null;
		for (Iterator iterator = a.baglanti.iterator(); iterator.hasNext();) {
			simdiki = (Node)iterator;
			sayac = simdiki.baglanti.size();
			sinirlar.put(simdiki, sayac);
			 
			sinirHesapla((Node)iterator.next());
			
			
	
		}
		
		
	}
	
	public static void degreeSearch(Node koprü){
		
		koprü.renk = renkler.get("Gri");

		for (Iterator iterator = koprü.baglanti.iterator(); iterator.hasNext();) {	
			Node dal = (Node)iterator.next();
			push(dal);
			
			
		}
		
	}
	
	
	public static void main(String[] args) {
		
		renkler.put("Gri","Gri");
		renkler.put("Siyah","Siyah");
		renkler.put("Beyaz","Beyaz");
		
		
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
		
	//	sinirHesapla(a);
		
		sinirlar.put(a, 3);
		sinirlar.put(b, 3);
		sinirlar.put(c, 6);
		sinirlar.put(d, 3);
		sinirlar.put(e, 5);
		sinirlar.put(f, 5);
		sinirlar.put(g, 4);
		sinirlar.put(h, 4);
		sinirlar.put(i, 5);
		sinirlar.put(j, 3);
		sinirlar.put(k, 3);
		

		System.out.println(max().isim);
		System.out.println(max().isim);
		System.out.println(max().isim);
		System.out.println(max().isim);
		System.out.println(max().isim);
		System.out.println(max().isim);
		System.out.println(max().isim);
		
		//degreeSearch(a);
		
		
	}
}
