package greedySearch;

import java.util.ArrayList;



public class Greedy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GNode a = new GNode("a",100);
		GNode b = new GNode("b",200);
		GNode c = new GNode("c",300);
		GNode d = new GNode("d",400);
		GNode e = new GNode("e",500);
		GNode f = new GNode("f",600);
		GNode g = new GNode("g",700);
		GNode h = new GNode("h",800);

		GYol ab = new GYol(a,b);
		a.getYollar().add(ab);
		b.getYollar().add(ab);

		GYol ae = new GYol(a,e);
		a.getYollar().add(ae);
		e.getYollar().add(ae);

		GYol bc = new GYol(b, c);
		b.getYollar().add(bc);
		c.getYollar().add(bc);

		GYol ed = new GYol(e, d);
		e.getYollar().add(ed);
		d.getYollar().add(ed);

		GYol dc = new GYol(d, c);
		d.getYollar().add(dc);
		c.getYollar().add(dc);

		GYol df = new GYol(d, f);
		d.getYollar().add(df);
		f.getYollar().add(df);

		GYol dg = new GYol(d, g);
		d.getYollar().add(dg);
		g.getYollar().add(dg);

		GYol dh = new GYol(d, h);
		d.getYollar().add(dh);
		h.getYollar().add(dh);
		
		
	}
	
	public static void Greedy(GNode start, GNode target) {
		
	} 
	

}
