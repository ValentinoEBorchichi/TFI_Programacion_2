package src.concesionaria;

import java.util.ArrayList;
import java.util.Scanner;

public class Cliente extends Persona {

    // Clientes (hereda de Persona)
    // constructor
    public Cliente(int DNI, String Nombre, String Apellido, String Direccion, String Telefono, String Email) {
        super(DNI, Nombre, Apellido, Direccion, Telefono, Email);
    }

    ArrayList<Cliente> listaClientes = new ArrayList<>();

    // override de equals y hashcode para comparar clientes por DNI
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Cliente cliente = (Cliente) obj;
        return this.DNI == cliente.DNI;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.DNI);
    }

    // funcion para limpiar pantalla
    public final static void limpiarPantalla() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (final Exception e) {
            // Handle any exceptions, e.g., print stack trace or log the error
            e.printStackTrace();
        }
    }


    // ------------- METODOS DE CLIENTE --------------------

    public void ListarClientes() {
        
        if (listaClientes.isEmpty()) {
            System.out.println("\nNo hay clientes registrados.\n");
            return;
        } else {
            System.out.println("\n----------- Lista de Clientes -------------\n");
            for (Cliente cliente : listaClientes) {
                System.out.println("DNI: " + cliente.DNI + ", Nombre: " + cliente.Nombre + ", Apellido: " + cliente.Apellido
                        + ", Direccion: " + cliente.Direccion + ", Telefono: " + cliente.Telefono + ", Email: "
                        + cliente.Email);
            }
        }
    }

    public Cliente buscarCliente(int DNI_Buscado) {
        for (Cliente cliente : listaClientes) {
            if (cliente.DNI == DNI_Buscado) {
                // System.out.println("DNI: " + cliente.DNI + ", Nombre: " + cliente.Nombre + ",
                // Apellido: " + cliente.Apellido + ", Direccion: " + cliente.Direccion + ",
                // Telefono: " + cliente.Telefono + ", Email: " + cliente.Email);
                return cliente;
            }
        }
        System.out.println("Cliente no encontrado.");
        return null;
    }

    public void modificarCliente(Scanner scanner) {
        
        System.out.print("Ingrese el DNI del cliente a modificar: ");
        int dniModificar = Integer.parseInt(scanner.nextLine().trim());
        Cliente encontrado = buscarCliente(dniModificar);

        if (encontrado != null) {
            System.out.print("Ingrese la nueva direccion: ");
            encontrado.Direccion = scanner.nextLine().trim();
            System.out.print("Ingrese el nuevo telefono: ");
            encontrado.Telefono = scanner.nextLine().trim();
            System.out.print("Ingrese el nuevo email: ");
            encontrado.Email = scanner.nextLine().trim();
            System.out.println("Cliente modificado con éxito.");
            return;
        }
        System.out.println("Cliente con DNI " + dniModificar + " no encontrado.");
    }

    public void agregarCliente(Scanner scanner) {
        System.out.print("Ingrese DNI: ");
        int dni = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Ingrese Nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Ingrese Apellido: ");
        String apellido = scanner.nextLine().trim();
        System.out.print("Ingrese Direccion: ");
        String direccion = scanner.nextLine().trim();
        System.out.print("Ingrese Telefono: ");
        String telefono = scanner.nextLine().trim();
        System.out.print("Ingrese Email: ");
        String email = scanner.nextLine().trim();

        Cliente nuevoCliente = new Cliente(dni, nombre, apellido, direccion, telefono, email);
        listaClientes.add(nuevoCliente);
        System.out.println("Cliente agregado con éxito.");
    }

    public void eliminarCliente(Scanner scanner) {
        System.out.print("Ingrese el DNI del cliente a eliminar: ");
        int dniEliminar = Integer.parseInt(scanner.nextLine().trim());

        boolean eliminado = listaClientes.removeIf(cliente -> cliente.DNI == dniEliminar);
        if (eliminado) { // Si se eliminó algún cliente
            System.out.println("Cliente eliminado con éxito.");
        } else { // Si no se encontró ningún cliente con ese DNI
            System.out.println("Cliente con DNI " + dniEliminar + " no encontrado.");
        }
    }

    // Metodo de Opciones de cliente
    public void opcionesCliente(Scanner scanner) {
        while (true) {
            System.out.println("Opciones de cliente:");
            System.out.println("1. Listar clientes");
            System.out.println("2. Buscar cliente por DNI");
            System.out.println("3. Agregar cliente");
            System.out.println("4. Modificar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Volver al menú principal");
            System.out.print("Elija una opción: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    limpiarPantalla();
                    ListarClientes();
                    break;
                case "2":
                    limpiarPantalla();
                    System.out.print("Ingrese el DNI a buscar: ");
                    int dniBuscar = Integer.parseInt(scanner.nextLine().trim());
                    Cliente c = buscarCliente(dniBuscar);
                    if (c != null) {
                        System.out.println("Cliente encontrado:\n");
                        System.out.println("DNI: " + c.DNI + ", Nombre: " + c.Nombre + ", Apellido: " + c.Apellido + ", Direccion: " + c.Direccion + ", Telefono: " + c.Telefono + ", Email: " + c.Email);
                    }
                    break;
                case "3":
                    limpiarPantalla();
                    agregarCliente(scanner);
                    break;
                case "4":
                    limpiarPantalla();
                    modificarCliente(scanner);
                    break;
                case "5":
                    limpiarPantalla();
                    eliminarCliente(scanner);
                    break;
                case "6":
                    limpiarPantalla();
                    return; // Salir del método y volver al menú principal
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            } // fin switch
        } // fin while

    } // fin opcionesCliente

}
