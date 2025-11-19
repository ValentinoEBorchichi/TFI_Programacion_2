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

    //getters y setters
    public int getPatente() {
        return Patente;
    }
    public void setPatente(int patente) {
        Patente = patente;
    }
    public String getMarca() {
        return Marca;
    }
    public void setMarca(String marca) {
        Marca = marca;
    }
    public String getModelo() {
        return Modelo;
    }
    public void setModelo(String modelo) {
        Modelo = modelo;
    }
    public int getAnio() {
        return Anio;
    }
    public void setAnio(int anio) {
        Anio = anio;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
    }
}
