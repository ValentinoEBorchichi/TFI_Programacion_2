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
                    Concesionaria.listaAutos.stream()
                            .filter(a -> "Disponible".equalsIgnoreCase(a.getEstado()))
                            .forEach(System.out::println);

                    System.out.print("Patente del vehículo: ");
                    String pat = sc.nextLine();

                    Vehiculo veh = null;
                    for (Auto a : Concesionaria.listaAutos) {
                        if (a.getPatente().equals(pat) && "Disponible".equalsIgnoreCase(a.getEstado())) {
                            veh = a;
                            break;
                        }
                    }

                    if (veh == null) {
                        System.out.println("Vehículo inexistente o no disponible.");
                        return;
                    }

                    veh.setEstado("En mantenimiento");

                    Mantenimiento m = new Mantenimiento(id, desc, fecha, costo, veh);
                    Concesionaria.listaMantenimientos.add(m);

                    System.out.println("Mantenimiento registrado correctamente.");
                    // Guardar tanto los mantenimientos como los cambios en los autos
                    Mantenimiento.guardarMantenimientosEnArchivo("mantenimientos.txt");
                    Concesionaria.guardarAutosEnArchivo("autos.txt");
                    break;

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
                        
                        // El vehículo volverá a "Disponible" cuando se elimine o finalice el mantenimiento.
                    }
                    break;
                    

                case "3":
                    System.out.print("Ingrese el ID del mantenimiento a eliminar: ");
                    int idEliminar = Integer.parseInt(sc.nextLine());

                    // Buscar mantenimiento(s) a eliminar y devolver el vehículo a Disponible
                    Mantenimiento encontrado = null;
                    for (Mantenimiento mnt : Concesionaria.listaMantenimientos) {
                        if (mnt.getIdMantenimiento() == idEliminar) {
                            encontrado = mnt;
                            break;
                        }
                    }

                    if (encontrado != null) {
                        // Restaurar estado del vehículo asociado
                        if (encontrado.getVehiculo() != null) {
                            encontrado.getVehiculo().setEstado("Disponible");
                        }
                        Concesionaria.listaMantenimientos.remove(encontrado);
                        System.out.println("Mantenimiento eliminado.");
                        // Guardar cambios
                        Mantenimiento.guardarMantenimientosEnArchivo("mantenimientos.txt");
                        Concesionaria.guardarAutosEnArchivo("autos.txt");
                    } else {
                        System.out.println("No existe.");
                    }
                    break;

                case "4":
                    return;

                default:
                    System.out.println("Opción inválida.");
                break;
    }
        }
    }  }