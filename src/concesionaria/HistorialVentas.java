package concesionaria;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class HistorialVentas implements Iterable<Venta> {

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
        guardarVentasEnArchivo("ventas.txt");
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
        guardarVentasEnArchivo("ventas.txt");
        return true;
    }


    public boolean mostrarHistorial() {
    System.out.println("\n--- Historial de ventas ---");

    if (estaVacio()) {
        System.out.println("No hay ventas registradas.");
        return false;  
    }

    int i = 1;
    for (Venta v : this) {
        System.out.println(i + ") " + v);
        i++;
    }

    return true; 
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

    // ------------------ Metodos para guardar y cargar desde archivo -------------------
    public void guardarVentasEnArchivo(String nombreArchivo) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {

        for (Venta v : this) {

            Cliente cliente = v.getCliente();
            DetalleVenta detalle = v.getDetalles().get(0);
            Vehiculo vehiculo = detalle.getVehiculo();

            String linea = cliente.getDNI() + "," +
                           vehiculo.getPatente() + "," +
                           v.getTotalFinal();

            writer.write(linea);
            writer.newLine();
        }

        System.out.println("Ventas guardadas correctamente.");

    } catch (IOException e) {
        System.out.println("Error al guardar las ventas: " + e.getMessage());
    }
}

    public void cargarVentasDesdeArchivo(String nombreArchivo) {
    java.io.File archivo = new java.io.File(nombreArchivo);
    if (!archivo.exists()) {
        return;
    }

    try (Scanner sc = new Scanner(archivo)) {

        while (sc.hasNextLine()) {
            String linea = sc.nextLine().trim();
            if (linea.isEmpty()) continue;

            String[] datos = linea.split(",");
            if (datos.length != 3) continue;

            int dniCliente   = Integer.parseInt(datos[0].trim());
            String patenteAuto  = datos[1].trim();

            Cliente cliente = Cliente.getListaClientes().stream()
                    .filter(c -> c.getDNI() == dniCliente)
                    .findFirst()
                    .orElse(null);

            Vehiculo vehiculo = Concesionaria.listaAutos.stream()
                    .filter(a -> a.getPatente().equals(patenteAuto))
                    .findFirst()
                    .orElse(null);

            if (cliente == null || vehiculo == null) {
                continue;
            }

            ArrayList<DetalleVenta> detalles = new ArrayList<>();
            detalles.add(new DetalleVenta(vehiculo, 1));

            Venta venta = new Venta(cliente, detalles);

            this.registrarVenta(venta);
        }

        System.out.println("Ventas cargadas desde el archivo correctamente.");

    } catch (Exception e) {
        System.out.println("Error al cargar las ventas desde el archivo: " + e.getMessage());
    }
}
}
