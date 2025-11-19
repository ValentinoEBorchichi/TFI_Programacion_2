package concesionaria;

public class Mantenimiento {
    int idMantenimiento;
    String descripcion;
    String fecha;
    double costo;


    //Constructor
    public Mantenimiento(int idMantenimiento, String descripcion, String fecha, double costo) {
        this.idMantenimiento = idMantenimiento;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.costo = costo;
    }
    //Getters y Setters
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

    @Override
    public String toString() {
        return "Mantenimiento{" +
                "idMantenimiento=" + idMantenimiento +
                ", descripcion='" + descripcion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", costo=" + costo +
                '}';
    }
 

}
