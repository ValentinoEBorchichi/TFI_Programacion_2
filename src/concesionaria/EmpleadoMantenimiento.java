package src.concesionaria;

public class EmpleadoMantenimiento extends Empleado {

    private String especialidad; 
    private int trabajosRealizados;
    private double extraPorTrabajo;

    public EmpleadoMantenimiento(int DNI, String nombre, String apellido,
                                 String direccion, String telefono, String email,
                                 int legajo, double sueldoBase,
                                 String especialidad, double extraPorTrabajo) {
        super(DNI, nombre, apellido, direccion, telefono, email, legajo, sueldoBase);
        this.especialidad = especialidad;
        this.trabajosRealizados = 0;
        this.extraPorTrabajo = extraPorTrabajo;
    }

    public void registrarMantenimiento(Mantenimiento m) {
        trabajosRealizados++;
    }

    @Override
    public double calcularSueldo() {
        return sueldoBase + (trabajosRealizados * extraPorTrabajo);
    }

    public String mostrarDatos() {
        return mostrarDatosBasicos() +
               "\nPuesto: Técnico de mantenimiento" +
               "\nEspecialidad: " + especialidad +
               "\nTrabajos realizados: " + trabajosRealizados +
               "\nSueldo final: " + calcularSueldo();
    }
}
