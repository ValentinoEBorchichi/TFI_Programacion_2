package concesionaria;

import java.util.Scanner;

public class MenuMantenimiento {

    public void mostrarMenu(Scanner sc) {

        System.out.println("\n--- REGISTRAR MANTENIMIENTO ---");

        System.out.print("ID mantenimiento: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Descripción: ");
        String desc = sc.nextLine();

        System.out.print("Fecha: ");
        String fecha = sc.nextLine();

        System.out.print("Costo base: ");
        double costo = Double.parseDouble(sc.nextLine());

        if (Concesionaria.listaAutos.isEmpty()) {
            System.out.println("No hay autos cargados.");
            return;
        }

        System.out.println("Autos disponibles:");
        for (Auto a : Concesionaria.listaAutos)
            System.out.println(a);

        System.out.print("Patente del vehículo: ");
        int pat = Integer.parseInt(sc.nextLine());

        Vehiculo veh = null;
        for (Auto a : Concesionaria.listaAutos)
            if (a.getPatente() == pat) veh = a;

        Mantenimiento m = new Mantenimiento(id, desc, fecha, costo, veh);
        Concesionaria.listaMantenimientos.add(m);

        System.out.println("Mantenimiento registrado correctamente.");
    }
}
