package concesionaria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MenuVentas {

    public void mostrarMenu(Scanner sc) {

        if (Concesionaria.listaAutos.isEmpty()) {
            System.out.println("No hay autos para vender.");
            return;
        }

<<<<<<< HEAD
        if (Cliente.getListaClientes().isEmpty()) {
            System.out.println("No hay clientes cargados.");
            return;
        }

        System.out.println("\n--- NUEVA VENTA ---");

        System.out.println("Clientes disponibles:");
        Collections.sort(Cliente.getListaClientes());

        for (Cliente c : Cliente.getListaClientes())
            System.out.println(c.getDNI() + " - " + c.getNombre());

        System.out.print("Ingrese el DNI del cliente: ");
        int dni = Integer.parseInt(sc.nextLine());

        Cliente cliente = null;
        for (Cliente c : Cliente.getListaClientes())
            if (c.getDNI() == dni) cliente = c;

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.println("\nAutos disponibles:");
        for (Auto a : Concesionaria.listaAutos)
            System.out.println(a);

        System.out.print("Ingrese patente del auto a vender: ");
        String pat = sc.nextLine();

        Auto auto = null;
        for (Auto a : Concesionaria.listaAutos) 
            if (a.getPatente().equals(pat)) auto = a;
        if (auto == null) {
            System.out.println("Auto inexistente.");
            return;
        }

        // Crear detalle de venta
        DetalleVenta dv = new DetalleVenta(auto, 1);
        ArrayList<DetalleVenta> detalles = new ArrayList<>();
        detalles.add(dv);

=======
        if (Concesionaria.listaAutos.stream().noneMatch(auto -> "Disponible".equals(auto.getEstado()))) {
            System.out.println("No hay autos disponibles para la venta.");
            return;
        }

        if (Cliente.getListaClientes().isEmpty()) {
            System.out.println("No hay clientes cargados.");
            return;
        }

        System.out.println("\n--- NUEVA VENTA ---");

        System.out.println("Clientes disponibles:");
        Collections.sort(Cliente.getListaClientes());
        Cliente.getListaClientes().forEach(System.out::println);

        for (Cliente c : Cliente.getListaClientes())
            System.out.println(c.getDNI() + " - " + c.getNombre());

        System.out.print("Ingrese el DNI del cliente: ");
        int dni = Integer.parseInt(sc.nextLine());

        Cliente cliente = null;
        for (Cliente c : Cliente.getListaClientes())
            if (c.getDNI() == dni) cliente = c;

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.println("\nAutos disponibles:");
        // Sólo autos con estado "Disponible"
        Concesionaria.listaAutos.stream()
                .filter(a -> "Disponible".equalsIgnoreCase(a.getEstado()))
                .forEach(System.out::println);

        System.out.print("Ingrese patente del auto a vender: ");
        String pat = sc.nextLine();

        
        Auto auto = null;
        for (Auto a : Concesionaria.listaAutos) {
            if (a.getPatente().equals(pat) && "Disponible".equalsIgnoreCase(a.getEstado())) {
                auto = a;
                break;
            }
        }

        if (auto == null) {
            System.out.println("Auto inexistente o no disponible para la venta.");
            return;
        }

        // Crear detalle de venta
        DetalleVenta dv = new DetalleVenta(auto, 1);
        ArrayList<DetalleVenta> detalles = new ArrayList<>();
        detalles.add(dv);

>>>>>>> 00b9d98f56122b2ae397bd3d470bf8c9350f867c
        // Vendedor ficticio por ahora
        EmpleadoVenta vendedor = new EmpleadoVenta(
            55555555, "Carlos", "Gómez", "Calle", "111", "mail@mail.com",
            1, 250000, 0.06
        );

        Venta venta = new Venta(vendedor, cliente, detalles);

<<<<<<< HEAD
=======
        Concesionaria.listaAutos.remove(auto);

>>>>>>> 00b9d98f56122b2ae397bd3d470bf8c9350f867c
        System.out.println("\nVenta registrada exitosamente.");
        System.out.println("Total Final: $" + venta.getTotalFinal());
    }
}