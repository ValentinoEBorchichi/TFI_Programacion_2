package concesionaria;

import java.util.Iterator;

/**
 * Lista enlazada simple para llevar un historial de ventas.
 */
public class HistorialVentas implements Iterable<Venta> {

    // Nodo interno de la lista
    private static class Node {
        Venta venta;
        Node next;

        Node(Venta venta) {
            this.venta = venta;
        }
    }

    private Node head;   // primer nodo
    private Node tail;   // último nodo (para agregar al final más rápido)
    private int size;    // cantidad de ventas registradas

    /**
     * Agrega una venta al final del historial.
     */
    public void registrarVenta(Venta venta) {
        if (venta == null) return;

        Node nuevo = new Node(venta);

        if (head == null) {   // lista vacía
            head = nuevo;
            tail = nuevo;
        } else {
            tail.next = nuevo;
            tail = nuevo;
        }
        size++;
    }

    /**
     * Elimina la primera aparición de esa venta (por referencia).
     * Devuelve true si se eliminó, false si no se encontró.
     */
    public boolean eliminarVenta(Venta venta) {
        if (head == null || venta == null) return false;

        // Caso: está en el primero
        if (head.venta == venta) {
            head = head.next;
            if (head == null) {   // si la lista quedó vacía
                tail = null;
            }
            size--;
            return true;
        }

        // Caso: está en el medio/final
        Node actual = head;
        while (actual.next != null && actual.next.venta != venta) {
            actual = actual.next;
        }

        if (actual.next == null) {
            return false; // no se encontró
        }

        // "salteo" el nodo a eliminar
        if (actual.next == tail) {
            tail = actual;
        }
        actual.next = actual.next.next;
        size--;
        return true;
    }

    /**
     * Muestra por consola todo el historial de ventas.
     */
    public void mostrarHistorial() {
        if (estaVacio()) {
            System.out.println("\n--- Historial de ventas ---");
            System.out.println("No hay ventas registradas.");
            return;
        }

        System.out.println("\n--- Historial de ventas ---");
        int i = 1;
        for (Venta v : this) {
            System.out.println(i + ") " + v);
            i++;
        }
    }

    public int size() {
        return size;
    }

    public boolean estaVacio() {
        return size == 0;
    }

    @Override
    public Iterator<Venta> iterator() {
        return new Iterator<Venta>() {
            private Node actual = head;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public Venta next() {
                Venta v = actual.venta;
                actual = actual.next;
                return v;
            }
        };
    }
}
