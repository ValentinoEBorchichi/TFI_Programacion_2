public class Persona {
    int DNI;
    String Nombre;
    String Apellido;
    String Direccion;
    String Telefono;
    String Email;

    public Persona(int DNI, String nombre, String apellido,
                   String direccion, String telefono, String email) {
        this.DNI = DNI;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Direccion = direccion;
        this.Telefono = telefono;
        this.Email = email;
    }
}
