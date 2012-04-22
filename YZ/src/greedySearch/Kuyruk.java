/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greedySearch;

/**
 *
 * @author BMB
 */
public class Kuyruk {

    KNode head, tail;

    public void insert(GNode node) {
        if (tail == null) {
            head = new KNode(node);
            tail = head;
            return;
        }
        tail.next = new KNode(node);
        tail = tail.next;
    }

    public GNode remove() {
        if (head == null) {
            return null;
        }
        GNode n = head.data;
        head = head.next;
        if(head==null) tail=null;
        return n;
    }
}
