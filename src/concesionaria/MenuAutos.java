package concesionaria;

import java.util.Scanner;

public class MenuAutos {
    
    // Definición de la interfaz funcional
    @FunctionalInterface 
        public interface FiltroAuto {   //sirve para filtrar autos por cualquiera de sus atributos
            boolean filtrar(Auto a);
        }
    




    public void mostrarMenu(Scanner sc) {

        while (true) {

            System.out.println("\n--- Opciones de Autos ---");
            System.out.println("1. Agregar auto");
            System.out.println("2. Listar autos disponibles");
            System.out.println("3. Listado de autos en mantenimiento");
            System.out.println("4. Buscar auto por patente");
            System.out.println("5. Eliminar auto");
            System.out.println("6. Filtrar autos por Marca");
            System.out.println("7. Volver al menú principal");


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

                    System.out.print("Precio: ");
                    double precio = Double.parseDouble(sc.nextLine());

                    System.out.print("Cantidad de puertas: ");
                    int puertas = Integer.parseInt(sc.nextLine());

                    System.out.print("Tipo: ");
                    String tipo = sc.nextLine();

                    //System.out.print("Estado: ");
                    //String estado = sc.nextLine();
                    String estado = "Disponible";

                    Auto auto = new Auto(patente, marca, modelo, anio, estado, precio, puertas, tipo);
                    Concesionaria.listaAutos.add(auto);

                    System.out.println("Auto agregado.");
                    Concesionaria.guardarAutosEnArchivo("autos.txt");
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
                    if (Concesionaria.listaMantenimientos.isEmpty()) {
                        System.out.println("No hay autos en mantenimiento.");
                    } else {
                        System.out.println("--------------------- Listado de autos en mantenimiento ----------------------");
                        int i = 1;
                        for (Mantenimiento a : Concesionaria.listaMantenimientos) {
                            System.out.println(i + ". ID Mantenimiento: " + a.getIdMantenimiento() + ", Descripición: " + a.getDescripcion() + ", Fecha: " + a.getFecha() + ", Costo: " + a.getCosto() + ", Vehículo: " + a.getVehiculo());
                            //System.out.println(i + ". Patente: " + a.getPatente() + ", Marca: " + a.getMarca() + ", Modelo: " + a.getModelo() + ", Año: " + a.getAnio() + ", Estado: " + a.getEstado() + ", Precio: $" + a.getPrecio() + ", Puertas: " + a.getCantidadPuertas() + ", Tipo: " + a.getTipo());
                            i++;
                        }
                    }

                break;

                case "4":
                    System.out.print("Ingrese patente: ");
                    String patBuscar = sc.nextLine();
                    Auto encontrado = null;

                    for (Auto a : Concesionaria.listaAutos) {
                        if (a.getPatente().equals(patBuscar))
                            encontrado = a;
                    }

                    System.out.println(encontrado != null ? encontrado : "No encontrado.");
                    break;

                case "5":
                    System.out.print("Patente a eliminar: ");
                    String patEliminar = sc.nextLine();

                    boolean eliminado = Concesionaria.listaAutos.removeIf(a -> a.getPatente().equals(patEliminar));
                    System.out.println(eliminado ? "Auto eliminado." : "No existe.");
                    Concesionaria.guardarAutosEnArchivo("autos.txt");
                    break;
  
                case "6":
                    System.out.print("Ingrese la marca a filtrar: ");
                    String marcaFiltro = sc.nextLine().trim();

                    // Implementación de la interfaz funcional con expresión lambda
                    FiltroAuto filtroPorMarca = a -> a.getMarca().equalsIgnoreCase(marcaFiltro);
                    boolean alguno = false;
                    System.out.println("----- Autos filtrados por marca: " + marcaFiltro + " -----");

                    for (Auto a : Concesionaria.listaAutos) {
                        if (filtroPorMarca.filtrar(a)) {
                            System.out.println("Patente: " + a.getPatente() +
                                            ", Marca: " + a.getMarca() +
                                            ", Modelo: " + a.getModelo() +
                                            ", Año: " + a.getAnio() +
                                            ", Estado: " + a.getEstado() +
                                            ", Precio: $" + a.getPrecio() +
                                            ", Puertas: " + a.getCantidadPuertas() +
                                            ", Tipo: " + a.getTipo());
                            alguno = true;
                        }
                    }

                    if (!alguno)
                        System.out.println("No se encontraron autos con esa marca.");

                    break;

                case "7":
                    return;

                default:
                    System.out.println("Opción inválida.");
                }
            }       
        }
    }
