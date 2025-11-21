package concesionaria;

import java.util.ArrayList;

public class Mantenimiento {

    int idMantenimiento;
    String descripcion;
    String fecha;
    double costo;

    ArrayList<Repuesto> repuestosUsados = new ArrayList<>();
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

    public ArrayList<Repuesto> getRepuestosUsados() {
        return repuestosUsados;
    }

    public void agregarRepuesto(Repuesto r) {
        repuestosUsados.add(r);
    }

    public double calcularCostoTotal() {
        double total = costo;
        for (Repuesto r : repuestosUsados) {
            total += r.getPrecio();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Mantenimiento{" +
                "idMantenimiento=" + idMantenimiento +
                ", descripcion='" + descripcion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", costo base=" + costo +
                ", repuestos=" + repuestosUsados +
                ", costo total=" + calcularCostoTotal() +
                '}';
    }
}