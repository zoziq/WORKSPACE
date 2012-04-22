package breathFirst;

import java.util.ArrayList;

public class BreathFirst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node a = new Node("a");
		Node b = new Node("b");
		Node c = new Node("c");
		Node d = new Node("d");
		Node e = new Node("e");
		Node f = new Node("f");
		Node g = new Node("g");
		Node h = new Node("h");

		Yol ab = new Yol(a, b, 50);
		a.getYollar().add(ab);
		b.getYollar().add(ab);

		Yol ae = new Yol(a, e, 20);
		a.getYollar().add(ae);
		e.getYollar().add(ae);

		Yol bc = new Yol(b, c, 30);
		b.getYollar().add(bc);
		c.getYollar().add(bc);

		Yol ed = new Yol(e, d, 40);
		e.getYollar().add(ed);
		d.getYollar().add(ed);

		Yol dc = new Yol(d, c, 25);
		d.getYollar().add(dc);
		c.getYollar().add(dc);

		Yol df = new Yol(d, f, 60);
		d.getYollar().add(df);
		f.getYollar().add(df);

		Yol dg = new Yol(d, g, 35);
		d.getYollar().add(dg);
		g.getYollar().add(dg);

		Yol dh = new Yol(d, h, 30);
		d.getYollar().add(dh);
		h.getYollar().add(dh);
		
		bfs(a,h);
	}
	
	public static void bfs( Node start, Node target ){
		Kuyruk kuyruk = new Kuyruk();
		kuyruk.insert( start );
		Node n;
		while (( n=kuyruk.remove( ))!=null) {
			if (n == target) {
				System.out.println(n.getIsim()+" Bulundu ");
				return;
			}
			else {
				n.setVisited(true);
				System.out.println(n.getIsim());
				ArrayList<Yol> yollar = n.getYollar();
				for (int i = 0; i < yollar.size(); i++) {
					Yol yol= yollar.get(i);
					Node nod = yol.getBitis();
					if (nod.isVisited()==false) {
						kuyruk.insert(nod);
					}
					
				}
			}
		}
			
	}

}
