package src.concesionaria;

public class EmpleadoVenta extends Empleado {

    private double comisionPorVenta; 
    private int cantidadVentas;

    public EmpleadoVenta(int DNI, String nombre, String apellido,
                         String direccion, String telefono, String email,
                         int legajo, double sueldoBase,
                         double comisionPorVenta) {
        super(DNI, nombre, apellido, direccion, telefono, email, legajo, sueldoBase);
        this.comisionPorVenta = comisionPorVenta;
        this.cantidadVentas = 0;
    }

    public void registrarVenta(Venta v) {
        cantidadVentas++;
    }

    @Override
    public double calcularSueldo() {
        return sueldoBase + (comisionPorVenta * cantidadVentas);
    }

    public String mostrarDatos() {
        return mostrarDatosBasicos() +
               "\nPuesto: Vendedor" +
               "\nVentas realizadas: " + cantidadVentas +
               "\nSueldo final: " + calcularSueldo();
    }
}