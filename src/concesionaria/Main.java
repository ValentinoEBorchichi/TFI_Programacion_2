package concesionaria;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // --------------- Menú Principal ---------------
        Menus.mostrarMenuPrincipal();
        System.out.println("Programa finalizado.");
    }

    static class Menus {

        public static void mostrarMenuPrincipal() {
            Cliente.cargarClientesDesdeArchivo("clientes.txt"); // Cargar clientes al iniciar el programa
            Concesionaria.cargarAutosDesdeArchivo("autos.txt");   // Cargar autos al iniciar el programa
            Mantenimiento.cargarMantenimientosDesdeArchivo("mantenimientos.txt"); // Cargar mantenimientos al iniciar el programa
            Scanner sc = new Scanner(System.in);
            int opcion = 0;

            while (opcion != 6) { 
                System.out.println("\n--- Concesionaria El Pepe ---");
                System.out.println("1. Opciones de cliente");
                System.out.println("2. Opciones de autos");
                System.out.println("3. Opciones de ventas");
                System.out.println("4. Opciones de mantenimiento");
                System.out.println("5. Ver historial de ventas");
                System.out.println("6. Salir");
                System.out.print("Elija una opción: ");
                System.out.println("\n");

                try {
                    opcion = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    opcion = -1;
                }

                switch (opcion) {
                    case 1:
                    Cliente.opcionesCliente(sc);
                    break;

                    case 2:
                    new MenuAutos().mostrarMenu(sc);
                    break;

                    case 3:
                    new MenuVentas().mostrarMenu(sc);
                    break;

                    case 4:
                    new MenuMantenimiento().mostrarMenu(sc);
                    break;
    
                    case 5:
                    boolean hayVentas = HistorialVentas.getHistorialVentas().mostrarHistorial();
                    if (hayVentas) {
                        System.out.println("Presione ENTER para volver al menú...");
                        sc.nextLine();
                    }
                    break;

                    case 6:
                    break;

                    default:
                    System.out.println("Opción inválida.");
                } 

            } 
            sc.close();
        }
    }
}