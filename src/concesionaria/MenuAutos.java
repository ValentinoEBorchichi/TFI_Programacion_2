package concesionaria;

import java.util.Scanner;

public class MenuAutos {

    public void mostrarMenu(Scanner sc) {

        while (true) {

            System.out.println("\n--- Opciones de Autos ---");
            System.out.println("1. Agregar auto");
            System.out.println("2. Listar autos");
            System.out.println("3. Buscar auto por patente");
            System.out.println("4. Eliminar auto");
            System.out.println("5. Volver");

            System.out.print("Seleccione opción: ");
            String op = sc.nextLine().trim();

            switch (op) {

                case "1":
                    System.out.print("Patente: ");
                    String patente = sc.nextLine();

                    System.out.print("Marca: ");
                    String marca = sc.nextLine();

                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();

                    System.out.print("Año: ");
                    int anio = Integer.parseInt(sc.nextLine());

                    System.out.print("Estado: ");
                    String estado = sc.nextLine();

                    System.out.print("Precio: ");
                    double precio = Double.parseDouble(sc.nextLine());

                    System.out.print("Cantidad de puertas: ");
                    int puertas = Integer.parseInt(sc.nextLine());

                    System.out.print("Tipo: ");
                    String tipo = sc.nextLine();

                    Auto auto = new Auto(patente, marca, modelo, anio, estado, precio, puertas, tipo);
                    Concesionaria.listaAutos.add(auto);

                    System.out.println("Auto agregado.");
                    break;

                case "2":
                    if (Concesionaria.listaAutos.isEmpty()) {
                        System.out.println("No hay autos cargados.");
                    } else {
                        System.out.println("--------------------- Listado de autos ----------------------");
                        int i = 1;
                        for (Auto a : Concesionaria.listaAutos) {
                            System.out.println(i + ". Patente: " + a.getPatente() + ", Marca: " + a.getMarca() + ", Modelo: " + a.getModelo() + ", Año: " + a.getAnio() + ", Estado: " + a.getEstado() + ", Precio: $" + a.getPrecio() + ", Puertas: " + a.getCantidadPuertas() + ", Tipo: " + a.getTipo());
                            i++;
                        }
                    }
                    break;

                case "3":
                    System.out.print("Ingrese patente: ");
                    String patBuscar = sc.nextLine();
                    Auto encontrado = null;

                    for (Auto a : Concesionaria.listaAutos) {
                        if (a.getPatente().equals(patBuscar))
                            encontrado = a;
                    }

                    System.out.println(encontrado != null ? encontrado : "No encontrado.");
                    break;

                case "4":
                    System.out.print("Patente a eliminar: ");
                    String patEliminar = sc.nextLine();

                    boolean eliminado = Concesionaria.listaAutos.removeIf(a -> a.getPatente().equals(patEliminar));
                    System.out.println(eliminado ? "Auto eliminado." : "No existe.");
                    break;

                case "5":
                    return;

                default:
                    System.out.println("Opción inválida.");
                }//fin case
            } //fin while       
        }// fin mostrarMenu
    }// fin clase
