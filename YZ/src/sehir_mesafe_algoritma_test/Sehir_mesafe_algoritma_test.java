/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sehir_mesafe_algoritma_test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

/**
 *
 * @author BMB
 */
public class Sehir_mesafe_algoritma_test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Node n1 = new Node("1", 80);
        Node n2 = new Node("2", 75);
        Node n3 = new Node("3", 50);
        Node n4 = new Node("4", 27);
        Node n5 = new Node("5", 0);
        Node n6 = new Node("6", 60);
        Node n7 = new Node("7", 37);
        Node n8 = new Node("8", 40);

        Yol y12 = new Yol(n1, n2, 10);
        n1.yollar.add(y12);
        n2.yollar.add(y12);
//        Yol y23 = new Yol(n2, n3, 20);
//        n3.yollar.add(y23);
//        n2.yollar.add(y23);
        Yol y34 = new Yol(n3, n4, 30);
        n3.yollar.add(y34);
        n4.yollar.add(y34);
        Yol y45 = new Yol(n4, n5, 28);
        n4.yollar.add(y45);
        n5.yollar.add(y45);
        Yol y16 = new Yol(n1, n6, 25);
        n1.yollar.add(y16);
        n6.yollar.add(y16);
        Yol y67 = new Yol(n6, n7, 35);
        n6.yollar.add(y67);
        n7.yollar.add(y67);
        Yol y57 = new Yol(n5, n7, 40);
        n5.yollar.add(y57);
        n7.yollar.add(y57);
        Yol y18 = new Yol(n1, n8, 50);
        n1.yollar.add(y18);
        n8.yollar.add(y18);
        Yol y58 = new Yol(n5, n8, 45);
        n5.yollar.add(y58);
        n8.yollar.add(y58);
        // e.visited = true;
//        BFS(e, c);
        //Greedy(n1, n5);
        AStar(n1, n5, 0);
    }

//    public static boolean Greedy(Node current, Node target) {
//
//        int minSTL = 1000;
//
//        Node gidilecek = null;
//        for (Iterator it = current.yollar.iterator(); it.hasNext();) {
//            Yol child = (Yol) it.next();
//            Node node;
//            if (child.n1 == current) {
//                node = child.n2;
//            } else {
//                node = child.n1;
//            }
//            if (minSTL > node.kus) {
//                gidilecek = node;
//                minSTL = node.kus;
//            }
//
//        }
//
//        if (gidilecek != null) {
//            if (minSTL == 0) {
//                System.out.println(gidilecek.isim);
//                return true;
//            }
//            if (Greedy(gidilecek, target)) {
//                System.out.println(gidilecek.isim);
//                return true;
//            }
//        }
//        return false;
//    }
    public static boolean Greedy(Node current, Node target) {

        Node gidilecek = null;
        gidilecek = minBul(current);
        if (gidilecek != null) {
            int minSTL = gidilecek.kus;
            if (minSTL == 0) {
                System.out.println(gidilecek.isim);
                return true;
            }
            if (Greedy(gidilecek, target)) {
                System.out.println(gidilecek.isim);
                return true;
            }
        }
        return false;
    }

    public static Node minBul(Node current) {
        Node gidilecek = null;
        int minSTL = 1000;
        for (Iterator it = current.yollar.iterator(); it.hasNext();) {
            Yol child = (Yol) it.next();
            Node node;
            if (child.n1 == current) {
                node = child.n2;
            } else {
                node = child.n1;
            }

            if (minSTL > node.kus) {
                gidilecek = node;
                minSTL = node.kus;
            }

        }
        return gidilecek;

    }

    public static boolean AStar(Node current, Node target, int toplamYol) {
        boolean test = true;
        while (test) {
            Node gidilecek = null;
            Yol yol = null;
            int km = 0;
            int minSTL = 1000;
            gidilecek = null;
            minSTL = 1000;
            for (Iterator it = current.yollar.iterator(); it.hasNext();) {
                Yol child = (Yol) it.next();
                Node node;
                if (child.n1 == current) {
                    node = child.n2;
                } else {
                    node = child.n1;
                }
                if (minSTL > node.kus + child.km && child.acik == true) {
                    gidilecek = node;
                    minSTL = node.kus;
                    km = child.km;
                    yol = child;
                }
            }
            if (gidilecek != null) {
                if (minSTL == 0) {
                    System.out.println(gidilecek.isim + " " + (toplamYol + km));
                    return true;
                }
                   yol.acik = false;
                if (AStar(gidilecek, target, (toplamYol + km))) {
                    System.out.println(gidilecek.isim);
                    return true;
                } 
            } else {
                test = false;
            }
        }
        return false;
    }

    public static void BFS(Node baslangic, Node hedef) {
        Kuyruk kuyruk = new Kuyruk();
        kuyruk.insert(baslangic);
        baslangic.visited = true;
        Node n;
        while ((n = kuyruk.remove()) != null) {
            System.out.println(n.isim);
            if (n == hedef) {
                System.out.println("Hedef bulundu");
                return;
            }
            n.visited = true;
            for (Iterator it = n.yollar.iterator(); it.hasNext();) {
                Yol child = (Yol) it.next();
                Node node;
                if (child.n1 == n) {
                    node = child.n2;
                } else {
                    node = child.n1;
                }
                if (node.visited == true) {
                    continue;
                }
                kuyruk.insert(node);
            }
        }



    }
}
