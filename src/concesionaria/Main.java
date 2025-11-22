package concesionaria;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Cliente cliente = new Cliente(12345678, "Juan", "Perez", "Calle Falsa 123", "123456789", "juan.perez@example.com");
        // Registramos el cliente inicial en la lista central de clientes
        Cliente.getListaClientes().add(cliente);

        // menú principal con opciones para gestionar clientes por ahora
        // pasar la instancia de cliente al Menú principal
        new Main().new Menus(cliente).mostrarMenuPrincipal();

        System.out.println("Programa finalizado.");
    }

    class Menus {
        private final Cliente cliente;

        Menus(Cliente cliente) {
            this.cliente = cliente;
        }

        public void mostrarMenuPrincipal() {
            Scanner sc = new Scanner(System.in);
            int opcion = 0;

            while (opcion != 5) { // inicio del bucle while
                System.out.println("\n--- Concesionaria El Pepe ---");
                System.out.println("1. Opciones de cliente");
                System.out.println("2. Opciones de autos");
                System.out.println("3. Opciones de ventas");
                System.out.println("4. Opciones de mantenimiento");
                System.out.println("5. Salir");
                System.out.print("Elija una opción: ");
                System.out.println("\n");

                try {
                    opcion = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    opcion = -1;
                }

                switch (opcion) {
                    case 1:
                        cliente.opcionesCliente(sc);
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
                        break;

                    default:
                        System.out.println("Opción inválida.");
                } // fin switch

            } // fin while

            sc.close();
        }
    }
}
