package concesionaria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Mantenimiento {

    int idMantenimiento;
    String descripcion;
    String fecha;
    double costo;
    private Vehiculo vehiculo;

    public Mantenimiento(int idMantenimiento, String descripcion, String fecha, double costo) {
        this.idMantenimiento = idMantenimiento;
        this.descripcion = descripcion;
       	this.fecha = fecha;
        this.costo = costo;
    }

    public Mantenimiento(int idMantenimiento, String descripcion, String fecha, double costo, Vehiculo vehiculo) {
        this(idMantenimiento, descripcion, fecha, costo);
        this.vehiculo = vehiculo;
    }

    public int getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public double calcularCostoTotal() {
        double total = costo;
        return total;
    }

    @Override
    public String toString() {
        return "Mantenimiento{" +
                "idMantenimiento=" + idMantenimiento +
                ", descripcion='" + descripcion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", costo base=" + costo +
                ", costo total=" + calcularCostoTotal() +
                '}';
    }

    //Guardar lista de mantenimientos en archivo
    public static void guardarMantenimientosEnArchivo(String nombreArchivo) {
        // Implementación similar a la de Concesionaria.guardarAutosEnArchivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Mantenimiento m : Concesionaria.listaMantenimientos) {
                String linea = m.getIdMantenimiento() + "," +
                               m.getDescripcion() + "," +
                               m.getFecha() + "," +
                               m.getCosto() + "," +
                               m.getVehiculo().getPatente();
                writer.write(linea);
                writer.newLine();
            }
            System.out.println("Mantenimientos guardados en el archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los mantenimientos: " + e.getMessage());
        }
    }
    public static void cargarMantenimientosDesdeArchivo(String nombreArchivo) {
        // Poblar la lista global de mantenimientos de la concesionaria
        Concesionaria.listaMantenimientos.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                int idMantenimiento = Integer.parseInt(datos[0]);
                String descripcion  = datos[1];
                String fecha        = datos[2];
                double costo        = Double.parseDouble(datos[3]);
                String patenteVeh   = datos[4];

                Vehiculo vehiculo = null;
                for (Auto a : Concesionaria.listaAutos) {
                    if (a.getPatente().equals(patenteVeh)) {
                        vehiculo = a;
                        break;
                    }
                }

                if (vehiculo != null) {
                    // Marcar el vehículo como en mantenimiento al cargar el registro
                    vehiculo.setEstado("En mantenimiento");
                    Mantenimiento mantenimiento = new Mantenimiento(idMantenimiento, descripcion, fecha, costo, vehiculo);
                    Concesionaria.listaMantenimientos.add(mantenimiento);
                }
            }
            System.out.println("Mantenimientos cargados desde el archivo correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de mantenimientos. Se inicia con lista vacía.");
        } catch (IOException e) {
            System.out.println("Error al cargar los mantenimientos: " + e.getMessage());
        }
    }
}