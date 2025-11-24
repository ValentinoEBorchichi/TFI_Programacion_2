package concesionaria;

public abstract class Empleado extends Persona {

    private int legajo;
    protected double sueldoBase;

    public Empleado(int DNI, String nombre, String apellido,
                    String direccion, String telefono, String email,
                    int legajo, double sueldoBase) {
        super(DNI, nombre, apellido, direccion, telefono, email);
        this.legajo = legajo;
        this.sueldoBase = sueldoBase;
    }

    public abstract double calcularSueldo();

    public String mostrarDatosBasicos() {
        return "Legajo: " + legajo +
               "\nNombre: " + Nombre + " " + Apellido +
               "\nDNI: " + DNI +
               "\nTeléfono: " + Telefono +
               "\nEmail: " + Email;
    }

    
}