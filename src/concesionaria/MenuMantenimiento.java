package concesionaria;

import java.util.Scanner;

public class MenuMantenimiento {

    public void mostrarMenu(Scanner sc) {

        while (true) {

            System.out.println("\n--- Opciones de Mantenimiento ---");
            System.out.println("1. Agregar mantenimiento");
            System.out.println("2. Listar mantenimientos");
            System.out.println("3. Eliminar mantenimiento");
            System.out.println("4. Volver al menú principal");


            System.out.print("Seleccione opción: ");
            String op = sc.nextLine().trim();

            switch (op) {

        case "1":
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
                    String pat = sc.nextLine();

                    Vehiculo veh = null;
                    for (Auto a : Concesionaria.listaAutos)
                        if (a.getPatente().equals(pat)) veh = a;
                    if (veh == null) {
                        System.out.println("Vehículo inexistente.");
                        return;
                    }

                    Mantenimiento m = new Mantenimiento(id, desc, fecha, costo, veh);
                    Concesionaria.listaMantenimientos.add(m);

                    System.out.println("Mantenimiento registrado correctamente.");

                case "2":
                    if (Concesionaria.listaMantenimientos.isEmpty()) {
                        System.out.println("No hay mantenimientos registrados.");
                        }
                    else {
                        System.out.println("\n--- Listado de Mantenimientos ---");
                        int i = 1;
                        for (Mantenimiento a : Concesionaria.listaMantenimientos) {
                            System.out.println(i + ". ID Mantenimiento: " + a.getIdMantenimiento() + ", Descripición: " + a.getDescripcion() + ", Fecha: " + a.getFecha() + ", Costo: " + a.getCosto() + "\nVehículo:\n Patente: " + a.getVehiculo().getPatente() + ", Marca: " + a.getVehiculo().getMarca() + ", Modelo: " + a.getVehiculo().getModelo() + ", Año: " + a.getVehiculo().getAnio() + ", Estado: " + a.getVehiculo().getEstado() + ", Precio: $" + a.getVehiculo().getPrecio() + ", Puertas: " + ((Auto)a.getVehiculo()).getCantidadPuertas() + ", Tipo: " + ((Auto)a.getVehiculo()).getTipo());
                            i++;
                        }
                    }
                    break;
                    

                case "3":
                    System.out.print("Ingrese el ID del mantenimiento a eliminar: ");
                    int idEliminar = Integer.parseInt(sc.nextLine());

                    boolean eliminado = Concesionaria.listaMantenimientos.removeIf(mant -> mant.getIdMantenimiento() == idEliminar);
                    System.out.println(eliminado ? "Mantenimiento eliminado." : "No existe.");
                    break;
                case "4":
                    return;

                default:
                    System.out.println("Opción inválida.");
                break;
    }
        }
    }  }