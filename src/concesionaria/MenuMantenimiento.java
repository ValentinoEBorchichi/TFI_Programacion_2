package concesionaria;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuVentas {

    public void mostrarMenu(Scanner sc) {

        if (Concesionaria.listaAutos.isEmpty()) {
            System.out.println("No hay autos para vender.");
            return;
        }

        if (Cliente.getListaClientes().isEmpty()) {
            System.out.println("No hay clientes cargados.");
            return;
        }

        System.out.println("\n--- NUEVA VENTA ---");

        System.out.println("Clientes disponibles:");
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
        int pat = Integer.parseInt(sc.nextLine());

        Auto auto = null;
        for (Auto a : Concesionaria.listaAutos)
            if (a.getPatente() == pat) auto = a;

        if (auto == null) {
            System.out.println("Auto inexistente.");
            return;
        }

        // Crear detalle de venta
        DetalleVenta dv = new DetalleVenta(auto, 1);
        ArrayList<DetalleVenta> detalles = new ArrayList<>();
        detalles.add(dv);

        // Vendedor ficticio por ahora
        EmpleadoVenta vendedor = new EmpleadoVenta(
            55555555, "Carlos", "Gómez", "Calle", "111", "mail@mail.com",
            1, 250000, 0.06
        );

        Venta venta = new Venta(vendedor, cliente, detalles);

        System.out.println("\nVenta registrada exitosamente.");
        System.out.println("Total Final: $" + venta.getTotalFinal());
    }
}
