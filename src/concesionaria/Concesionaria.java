package concesionaria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Concesionaria {

    public static ArrayList<Auto> listaAutos = new ArrayList<>();
    public static ArrayList<Mantenimiento> listaMantenimientos = new ArrayList<>();
        // Historial de ventas usando lista enlazada propia
    public static HistorialVentas historialVentas = new HistorialVentas();


    public static void guardarAutosEnArchivo(String nombreArchivo) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {

        java.io.File f = new java.io.File(nombreArchivo);
        System.out.println("GUARDANDO AUTOS EN: " + f.getAbsolutePath());
        System.out.println("Cantidad de autos en memoria al guardar: " + listaAutos.size());

        for (Auto a : listaAutos) {
            String linea = a.getPatente() + "," +
                           a.getMarca() + "," +
                           a.getModelo() + "," +
                           a.getAnio() + "," +
                           a.getEstado() + "," +
                           a.getPrecio() + "," +
                           a.getCantidadPuertas() + "," +
                           a.getTipo();
            writer.write(linea);
            writer.newLine();
        }
        System.out.println("Autos guardados en el archivo correctamente.");
    } catch (IOException e) {
        System.out.println("Error al guardar los autos: " + e.getMessage());
    }
}
    
public static void cargarAutosDesdeArchivo(String nombreArchivo) {
    listaAutos.clear();
    java.io.File f = new java.io.File(nombreArchivo);
    System.out.println("CARGANDO AUTOS DESDE: " + f.getAbsolutePath());

    try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] datos = linea.split(",");
            String patente = datos[0];
            String marca   = datos[1];
            String modelo  = datos[2];
            int anio       = Integer.parseInt(datos[3]);
            String estado  = datos[4];
            double precio  = Double.parseDouble(datos[5]);
            int puertas    = Integer.parseInt(datos[6]);
            String tipo    = datos[7];

            Auto auto = new Auto(patente, marca, modelo, anio, estado, precio, puertas, tipo);
            listaAutos.add(auto);
        }
        System.out.println("Autos cargados desde el archivo correctamente.");
        System.out.println("Cantidad de autos en memoria luego de cargar: " + listaAutos.size());
    } catch (FileNotFoundException e) {
        System.out.println("No se encontró el archivo de autos. Se inicia con lista vacía.");
    } catch (IOException e) {
        System.out.println("Error al cargar los autos: " + e.getMessage());
    }
}
}