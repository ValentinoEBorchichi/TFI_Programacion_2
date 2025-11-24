package concesionaria;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Auto extends Vehiculo {
private static final ArrayList<Auto> listaAutos = new ArrayList<>();
public static ArrayList<Auto> getListaAutos() {
    return listaAutos;
}
    private int cantidadPuertas;
    private String tipo;

    // Constructor
    public Auto(String patente, String marca, String modelo, int anio, String estado, double precio, int cantidadPuertas, String tipo) {
        super(patente, marca, modelo, anio, estado, precio);
        this.cantidadPuertas = cantidadPuertas;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getCantidadPuertas() {
        return cantidadPuertas;
    }
    public void setCantidadPuertas(int cantidadPuertas) {
        this.cantidadPuertas = cantidadPuertas;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public static void setEstado(String patente, String nuevoEstado) {

        for (Auto auto : Concesionaria.listaAutos) {
            if (auto.getPatente().equals(patente)) {
                auto.setEstado(nuevoEstado);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Auto{" +
            "patente=" + getPatente() +
            ", marca='" + getMarca() + "'" +
            ", modelo='" + getModelo() + "'" +
            ", anio=" + getAnio() +
            ", estado='" + getEstado() + "'" +
            ", precio=" + getPrecio() +
            ", cantidadPuertas=" + cantidadPuertas +
            ", tipo='" + tipo + "'" +
            '}';
    }
<<<<<<< HEAD


public static void guardarAutosEnArchivo(String nombreArchivo) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {

        for (Auto auto : listaAutos) {
            String linea =
                    auto.getPatente() + "," +
                    auto.getMarca() + "," +
                    auto.getModelo() + "," +
                    auto.getAnio() + "," +
                    auto.getEstado() + "," +
                    auto.getPrecio() + "," +
                    auto.getCantidadPuertas() + "," +
                    auto.getTipo();

            writer.write(linea);
            writer.newLine();
        }

        System.out.println("Autos guardados correctamente.");
    } catch (IOException e) {
        System.out.println("Error al guardar los autos: " + e.getMessage());
    }
}


public static void cargarAutosDesdeArchivo(String nombreArchivo) {
    try (Scanner fileScanner = new Scanner(new java.io.File(nombreArchivo))) {

        listaAutos.clear(); // Limpia la lista antes de cargar

        while (fileScanner.hasNextLine()) {
            String linea = fileScanner.nextLine();
            String[] datos = linea.split(",");

            if (datos.length == 8) {

                String patente = datos[0];
                String marca = datos[1];
                String modelo = datos[2];
                int anio = Integer.parseInt(datos[3]);
                String estado = datos[4];
                double precio = Double.parseDouble(datos[5]);
                int cantidadPuertas = Integer.parseInt(datos[6]);
                String tipo = datos[7];

                Auto auto = new Auto(patente, marca, modelo, anio, estado, precio, cantidadPuertas, tipo);
                listaAutos.add(auto);
            }
        }

        System.out.println("Autos cargados correctamente desde archivo.");
    } catch (IOException e) {
        System.out.println("Error al cargar los autos: " + e.getMessage());
    }
}


}

=======
}
>>>>>>> 00b9d98f56122b2ae397bd3d470bf8c9350f867c
