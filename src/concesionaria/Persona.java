package concesionaria;

public class Persona {
    int DNI;
    String Nombre;
    String Apellido;
    String Direccion;
    String Telefono;
    String Email;

    //Persona (DNI, NomYApe, FechaNacimiento, Telefono, Direccion)
    //constructor
    public Persona(int DNI, String Nombre, String Apellido, String Direccion, String Telefono, String Email) {
        this.DNI = DNI;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Email = Email;
    }

    // METODOS DE PERSONA

    public int getDNI() {
        return this.DNI;
    }
    public String getNombre() {
        return this.Nombre;
    }
    public String getApellido() {
        return this.Apellido;
    }
    public String getDireccion() {
        return this.Direccion;
    }
    public String getTelefono() {
        return this.Telefono;
    }
    public String getEmail() {
        return this.Email;
    }
}