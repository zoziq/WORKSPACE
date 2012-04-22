/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sehir_mesafe_algoritma_test;

/**
 *
 * @author BMB
 */
public class Kuyruk {

    KNode head, tail;

    public void insert(Node node) {
        if (tail == null) {
            head = new KNode(node);
            tail = head;
            return;
        }
        tail.next = new KNode(node);
    }

    public Node remove() {
        if (head == null) {
            return null;
        }
        Node n = head.data;
        head = head.next;
        if(head==null) tail=null;
        return n;
    }
}
