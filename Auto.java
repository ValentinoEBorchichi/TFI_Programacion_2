public class Auto extends Vehiculo {
    private int cantidadPuertas;
    private String tipo;

    // Constructor
    public Auto(int patente, String marca, String modelo, int anio, String estado, int cantidadPuertas, String tipo) {
        super(patente, marca, modelo, anio, estado);
        this.cantidadPuertas = cantidadPuertas;
        this.tipo = tipo;
    }
    
    // Getters y Setters
    public int getCantidadPuertas() {
        return cantidadPuertas;
    }
    public void setCantidadPuertas(int cantidadPuertas) {
        this.cantidadPuertas = cantidadPuertas;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Auto{" +
            "patente=" + getPatente() +
            ", marca='" + getMarca() + '\'' +
            ", modelo='" + getModelo() + '\'' +
            ", anio=" + getAnio() +
            ", estado='" + getEstado() + '\'' +
            ", cantidadPuertas=" + cantidadPuertas +
            ", tipo='" + tipo + '\'' +
            '}';
    }

}
