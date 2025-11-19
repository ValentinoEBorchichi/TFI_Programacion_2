abstract public class Vehiculo {
    public  int Patente;
    public String Marca;
    public String Modelo;
    public int Anio;   
    public String Estado;

    // Constructor
    public Vehiculo(int patente, String marca, String modelo, int anio, String estado) {
        this.Patente = patente;
        this.Marca = marca;
        this.Modelo = modelo;
        this.Anio = anio;
        this.Estado = estado;
    }
}
