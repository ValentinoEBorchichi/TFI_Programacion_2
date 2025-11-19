
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

    // ------------- METODOS DE CLIENTE --------------------

    public void ListarClientes() {
        for (Cliente cliente : listaClientes) {
            System.out.println("DNI: " + cliente.DNI + ", Nombre: " + cliente.Nombre + ", Apellido: " + cliente.Apellido
                    + ", Direccion: " + cliente.Direccion + ", Telefono: " + cliente.Telefono + ", Email: "
                    + cliente.Email);
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

    public void modificarCliente(int DNI_AModificar) {
        Cliente encontrado = buscarCliente(DNI_AModificar);

        if (encontrado != null) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Ingrese la nueva direccion: ");
            encontrado.Direccion = scanner.nextLine().trim();
            System.out.print("Ingrese el nuevo telefono: ");
            encontrado.Telefono = scanner.nextLine().trim();
            System.out.print("Ingrese el nuevo email: ");
            encontrado.Email = scanner.nextLine().trim();
            System.out.println("Cliente modificado con éxito.");
            scanner.close();
            return;
        }
        System.out.println("Cliente con DNI " + DNI_AModificar + " no encontrado.");
    }

    public void agregarCliente() {
        Scanner scanner = new Scanner(System.in);

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

        // cerrar scanner
        scanner.close();

    }

    public void eliminarCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el DNI del cliente a eliminar: ");
        int dniEliminar = Integer.parseInt(scanner.nextLine().trim());

        boolean eliminado = listaClientes.removeIf(cliente -> cliente.DNI == dniEliminar);
        if (eliminado) { // Si se eliminó algún cliente
            System.out.println("Cliente eliminado con éxito.");
        } else { // Si no se encontró ningún cliente con ese DNI
            System.out.println("Cliente con DNI " + dniEliminar + " no encontrado.");
        }
        scanner.close();
    }

    // Metodo de Opciones de cliente
    public void opcionesCliente() {
        Scanner scanner = new Scanner(System.in);
        int opcion = Integer.parseInt(scanner.nextLine().trim());

        while (opcion != 6) {
            System.out.println("Opciones de cliente:");
            System.out.println("1. Listar clientes");
            System.out.println("2. Buscar cliente por DNI");
            System.out.println("3. Agregar cliente");
            System.out.println("4. Modificar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Volver al menú principal");
            System.out.print("Elija una opción: ");

            switch (opcion) {
                case 1:
                    ListarClientes();
                    break;
                case 2:
                    System.out.print("Ingrese el DNI a buscar: ");
                    int dniBuscar = Integer.parseInt(scanner.nextLine().trim());
                    buscarCliente(dniBuscar);
                    break;
                case 3:
                    agregarCliente();
                    break;
                case 4:
                    System.out.print("Ingrese el DNI del cliente a modificar: ");
                    int dniModificar = Integer.parseInt(scanner.nextLine().trim());
                    modificarCliente(dniModificar);
                    break;
                case 5:
                    eliminarCliente();
                    break;
                case 6:
                    return; // Salir del método y volver al menú principal
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            } // fin switch
        } // fin while

    } // fin opcionesCliente

}
