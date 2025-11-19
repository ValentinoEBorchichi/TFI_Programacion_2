public class Auto extends Vehiculo {
    private int CantidadPuertas;
    private String tipo;



    //Constructor
    public Auto(int patente, String marca, String modelo, int anio, String estado, int cantidadPuertas, String tipo) {
        super(patente, marca, modelo, anio, estado); 
        this.CantidadPuertas = cantidadPuertas;
        this.tipo = tipo;
    }
    
    //Getters y Setters
    public int getCantidadPuertas() {
        return CantidadPuertas;
    }
    public void setCantidadPuertas(int cantidadPuertas) {
        CantidadPuertas = cantidadPuertas;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
