package concesionaria;

//import java.util.ArrayList;
import java.util.Scanner;


public class Main { 
    public static void main(String[] args) {

        Cliente cliente = new Cliente(12345678, "Juan", "Perez", "Calle Falsa 123", "123456789", "juan.perez@example.com");
        // menú principal con opciones para gestionar clientes por ahora
        
        // pasar la instancia de cliente al Menú principal
        new Main().new Menus(cliente).mostrarMenuPrincipal();

        System.out.println("Programa finalizado.");


    }

    class Menus {
        private final Cliente cliente;
       // private ArrayList<Mantenimiento> listaMantenimientos = new ArrayList<>();

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

                opcion = Integer.parseInt(sc.nextLine().trim());
                                switch (opcion) {
                    case 1:
                        cliente.opcionesCliente(sc);
                        break;
                    /* 
                    case 2:
                    
                        break;
                    case 3:
                        
                        break;
                    */
                    case 4:
                        opcionesMantenimiento(sc);
                        break;
                    case 5:
                        break;
                    
                    default:
                        System.out.println("Opción inválida.");

            
                } // fin switch
        
            } // fin while
        }


        private void opcionesMantenimiento(Scanner sc) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'opcionesMantenimiento'");
        }

    }
}











/* 
public class Main {
    public static void main(String[] args) {
        Concesionaria concesionaria = new Concesionaria("Concesionaria Ejemplo");
        concesionaria.agregarAuto(new Auto("Toyota", "Corolla", 2019, 15000));
        concesionaria.agregarAuto(new Auto("Ford", "Focus", 2018, 12000));
        concesionaria.agregarAuto(new Auto("Renault", "Clio", 2020, 13000));

        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- " + concesionaria.getNombre() + " ---");
            System.out.println("1. Agregar auto");
            System.out.println("2. Listar autos");
            System.out.println("3. Buscar auto por ID");
            System.out.println("4. Vender auto por ID");
            System.out.println("5. Eliminar auto por ID");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");

            String opcion = sc.nextLine().trim();
            switch (opcion) {
                case "1":
                    System.out.print("Marca: ");
                    String marca = sc.nextLine().trim();
                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine().trim();
                    System.out.print("Año: ");
                    int anio = leerEntero(sc);
                    System.out.print("Precio: ");
                    double precio = leerDouble(sc);
                    Auto nuevo = new Auto(marca, modelo, anio, precio);
                    concesionaria.agregarAuto(nuevo);
                    System.out.println("Auto agregado: " + nuevo);
                    break;
                case "2":
                    concesionaria.listarAutos();
                    break;
                case "3":
                    System.out.print("ID a buscar: ");
                    int idBuscar = leerEntero(sc);
                    Optional<Auto> encontrado = concesionaria.buscarPorId(idBuscar);
                    System.out.println(encontrado.map(Object::toString).orElse("No se encontró auto con ese ID."));
                    break;
                case "4":
                    System.out.print("ID a vender: ");
                    int idVender = leerEntero(sc);
                    boolean vendido = concesionaria.venderAuto(idVender);
                    System.out.println(vendido ? "Auto vendido." : "No se pudo vender (no existe o ya vendido).");
                    break;
                case "5":
                    System.out.print("ID a eliminar: ");
                    int idEliminar = leerEntero(sc);
                    boolean eliminado = concesionaria.eliminarAuto(idEliminar);
                    System.out.println(eliminado ? "Auto eliminado." : "No se pudo eliminar (no existe).");
                    break;
                case "6":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
        System.out.println("Programa finalizado.");
    }

    private static int leerEntero(Scanner sc) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número entero: ");
            }
        }
    }

    private static double leerDouble(Scanner sc) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número (decimales con .): ");
            }
        }
    }
}

class Concesionaria {
    private final String nombre;
    private final List<Auto> autos = new ArrayList<>();
    private int siguienteId = 1;

    public Concesionaria(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarAuto(Auto a) {
        a.setId(siguienteId++);
        autos.add(a);
    }

    public void listarAutos() {
        if (autos.isEmpty()) {
            System.out.println("No hay autos en la concesionaria.");
            return;
        }
        for (Auto a : autos) {
            System.out.println(a);
        }
    }

    public Optional<Auto> buscarPorId(int id) {
        return autos.stream().filter(a -> a.getId() == id).findFirst();
    }

    public boolean venderAuto(int id) {
        Optional<Auto> o = buscarPorId(id);
        if (o.isPresent()) {
            Auto a = o.get();
            if (!a.isVendido()) {
                a.setVendido(true);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarAuto(int id) {
        return autos.removeIf(a -> a.getId() == id);
    }
}

class Auto {
    private int id;
    private final String marca;
    private final String modelo;
    private final int anio;
    private final double precio;
    private boolean vendido = false;

    public Auto(String marca, String modelo, int anio, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    @Override
    public String toString() {
        return String.format("ID:%d | %s %s (%d) - $%.2f %s",
                id, marca, modelo, anio, precio, vendido ? "[VENDIDO]" : "");
    }
}

------------------------------------------------------------------------------
Opcion mantenimiento

        private void opcionesMantenimiento(Scanner sc) {
            int opcionMant = 0;

            while (opcionMant != 5) {
                System.out.println("\n--- Opciones de mantenimiento ---");
                System.out.println("1. Registrar mantenimiento");
                System.out.println("2. Mostrar todos los mantenimientos");
                System.out.println("3. Buscar mantenimiento por ID");
                System.out.println("4. Eliminar mantenimiento");
                System.out.println("5. Volver");
                System.out.print("Elija una opción: ");

                opcionMant = Integer.parseInt(sc.nextLine().trim());

                switch (opcionMant) {
                    case 1: {
                        System.out.println("\n--- Registrar mantenimiento ---");
                        System.out.print("ID del mantenimiento: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Descripción: ");
                        String descripcion = sc.nextLine();

                        System.out.print("Fecha (dd/mm/aaaa): ");
                        String fecha = sc.nextLine();

                        System.out.print("Costo base: ");
                        double costo = Double.parseDouble(sc.nextLine().trim());

                        Mantenimiento m = new Mantenimiento(id, descripcion, fecha, costo);
                        listaMantenimientos.add(m);

                        System.out.println("Mantenimiento registrado correctamente.");
                        break;
                    }
                    case 2: {
                        System.out.println("\n--- Lista de mantenimientos ---");
                        if (listaMantenimientos.isEmpty()) {
                            System.out.println("No hay mantenimientos registrados.");
                        } else {
                            for (Mantenimiento m : listaMantenimientos) {
                                System.out.println(m);
                                System.out.println("-------------------------");
                            }
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("\n--- Buscar mantenimiento por ID ---");
                        System.out.print("Ingrese ID: ");
                        int idBuscado = Integer.parseInt(sc.nextLine().trim());

                        Mantenimiento encontrado = null;
                        for (Mantenimiento m : listaMantenimientos) {
                            if (m.getIdMantenimiento() == idBuscado) {
                                encontrado = m;
                                break;
                            }
                        }

                        if (encontrado != null) {
                            System.out.println("Mantenimiento encontrado:");
                            System.out.println(encontrado);
                        } else {
                            System.out.println("No existe un mantenimiento con ese ID.");
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("\n--- Eliminar mantenimiento ---");
                        System.out.print("Ingrese ID: ");
                        int idEliminar = Integer.parseInt(sc.nextLine().trim());

                        boolean eliminado = false;
                        for (int i = 0; i < listaMantenimientos.size(); i++) {
                            if (listaMantenimientos.get(i).getIdMantenimiento() == idEliminar) {
                                listaMantenimientos.remove(i);
                                eliminado = true;
                                break;
                            }
                        }

                        if (eliminado) {
                            System.out.println("Mantenimiento eliminado correctamente.");
                        } else {
                            System.out.println("No se encontró un mantenimiento con ese ID.");
                        }
                        break;
                    }
                    case 5:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            }
        }




    */