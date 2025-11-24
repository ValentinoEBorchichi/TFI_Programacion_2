package concesionaria;

import java.util.Iterator;

/**
 * Estructura propia simple: lista enlazada para registro de operaciones (Strings)
 */
public class RegistroLinkedList implements Iterable<String> {
    private static class Node {
        String value;
        Node next;
        Node(String v) { value = v; }
    }

    private Node head;
    private int size = 0;

    public void add(String s) {
        Node n = new Node(s);
        if (head == null) head = n;
        else {
            Node cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = n;
        }
        size++;
    }

    public boolean remove(String s) {
        if (head == null) return false;
        if (head.value.equals(s)) { head = head.next; size--; return true; }
        Node prev = head;
        Node cur = head.next;
        while (cur != null) {
            if (cur.value.equals(s)) { prev.next = cur.next; size--; return true; }
            prev = cur; cur = cur.next;
        }
        return false;
    }

    public int size() { return size; }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private Node cur = head;
            @Override public boolean hasNext() { return cur != null; }
            @Override public String next() { String v = cur.value; cur = cur.next; return v; }
        };
    }
}