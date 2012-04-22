package AStar;

import java.util.ArrayList;
import java.util.Iterator;


public class Main {

	static ArrayList<Integer> kusUcusu = new ArrayList<Integer>();
	
	static ArrayList<Node> kuyruk = new ArrayList<Node>();
	
	
	public static void push(Node n) {
		kuyruk.add(n);
	}
	
	public static Node pop() {
		Node n = (Node) kuyruk.get(0);
		kuyruk.remove(0);
		return n;
	}
	
	public static void greedy(Node parent, Node hedef){
		
		if (parent.ad.equals(hedef.ad)) {
			System.out.println("Bulundu");
			return;
		}
		
		for (int i = 0; i < parent.yollar.size(); i++) {
			if (parent.yollar.get(i).bitis.equals(parent)) {
				push(parent.yollar.get(i).baslangic);
				parent.yollar.get(i).baslangic.parent = parent;
				System.out.println(parent.yollar.get(i).baslangic.ad);
			}
			else {
				push(parent.yollar.get(i).bitis);
				parent.yollar.get(i).baslangic.parent = parent;
				System.out.println(parent.yollar.get(i).bitis.ad);
			}
			
		}
		
		Node gecici = pop();
		Node n = null;
		while(kuyruk.size()!=0) {
			n = pop();
			int m1 = 0,m2 = 0;
			for (int i = 0; i < n.yollar.size(); i++) {
				if (n.yollar.get(i).bitis.equals(n.parent)) {
					m1 = n.yollar.get(i).mesafe;
				}
			}
			for (int i = 0; i < gecici.yollar.size(); i++) {
				if (gecici.yollar.get(i).bitis.equals(gecici.parent)) {
					m2 = gecici.yollar.get(i).mesafe;
				}
			}
			System.out.println(m1);
			System.out.println(m2);
			if (kusUcusu.get(Integer.parseInt(n.ad)-1)+m1<kusUcusu.get(Integer.parseInt(gecici.ad)-1)+m2) {
				gecici = n;
			}	
		}
		System.out.println("gecici= " + gecici.ad + " mesafe= " + kusUcusu.get(Integer.parseInt(gecici.ad)-1));
		
		greedy(gecici, hedef);
		
	}
	
	public static void main(String[] args) {
		
		Node n1 = new Node("1");
		Node n2 = new Node("2");
		Node n3 = new Node("3");
		Node n4 = new Node("4");
		Node n5 = new Node("5");
		Node n6 = new Node("6");
		Node n7 = new Node("7");
		Node n8 = new Node("8");
		
		Yol y16 = new Yol(n1,n6,25);
		n1.yollar.add(y16);
		n6.yollar.add(y16);
		Yol y18 = new Yol(n1,n8,50);
		n1.yollar.add(y18);
		n8.yollar.add(y18);
		Yol y12 = new Yol(n1,n2,10);
		n1.yollar.add(y12);
		n2.yollar.add(y12);
		Yol y23 = new Yol(n2,n3,20);
		n2.yollar.add(y23);
		n3.yollar.add(y23);
		Yol y34 = new Yol(n3,n4,30);
		n3.yollar.add(y34);
		n4.yollar.add(y34);
		Yol y45 = new Yol(n4,n5,28);
		n4.yollar.add(y45);
		n5.yollar.add(y45);
		Yol y85 = new Yol(n8,n5,45);
		n8.yollar.add(y85);
		n5.yollar.add(y85);
		Yol y67 = new Yol(n6,n7,35);
		n6.yollar.add(y67);
		n7.yollar.add(y67);
		Yol y75 = new Yol(n7,n5,40);
		n7.yollar.add(y75);
		n5.yollar.add(y75);
		
		kusUcusu.add(80);
		kusUcusu.add(75);
		kusUcusu.add(50);
		kusUcusu.add(27);
		kusUcusu.add(0);
		kusUcusu.add(60);
		kusUcusu.add(37);
		kusUcusu.add(40);
		
		
		
		
		
		greedy(n1, n5);
	}
	
	
}
