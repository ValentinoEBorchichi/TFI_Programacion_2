package concesionaria;

import java.util.Iterator;

public class HistorialVentas implements Iterable<Venta> {

    public static HistorialVentas historialVentas = new HistorialVentas();



    private static class Node {
        Venta venta;
        Node next;

        Node(Venta venta) {
            this.venta = venta;
        }
    }

    private Node head;  
    private Node tail;  
    private int size;    


    public void registrarVenta(Venta venta) {
        if (venta == null) return;

        Node nuevo = new Node(venta);

        if (head == null) {   
            head = nuevo;
            tail = nuevo;
        } else {
            tail.next = nuevo;
            tail = nuevo;
        }
        size++;
    }

    public boolean eliminarVenta(Venta venta) {
        if (head == null || venta == null) return false;

        if (head.venta == venta) {
            head = head.next;
            if (head == null) {   
                tail = null;
            }
            size--;
            return true;
        }

        Node actual = head;
        while (actual.next != null && actual.next.venta != venta) {
            actual = actual.next;
        }

        if (actual.next == null) {
            return false; 
        }

        if (actual.next == tail) {
            tail = actual;
        }
        actual.next = actual.next.next;
        size--;
        return true;
    }


    public boolean mostrarHistorial() {
    System.out.println("\n--- Historial de ventas ---");

    if (estaVacio()) {
        System.out.println("No hay ventas registradas.");
        return false;   // ← señal para volver al menú principal
    }

    int i = 1;
    for (Venta v : this) {
        System.out.println(i + ") " + v);
        i++;
    }

    return true; // hay ventas
}


public static HistorialVentas getHistorialVentas() {
    return historialVentas;
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