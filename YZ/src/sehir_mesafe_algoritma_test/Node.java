/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sehir_mesafe_algoritma_test;

import java.util.LinkedList;

/**
 *
 * @author BMB
 */
public class Node {

    String isim;
    boolean visited;
    LinkedList<Yol> yollar = new LinkedList<Yol>();
    int kus;

    public Node(String isim,int kus) {
        this.isim = isim;
        this.kus= kus;
    }
}
