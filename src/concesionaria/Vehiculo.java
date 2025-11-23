package concesionaria;

import java.util.Objects;

abstract public class Vehiculo {
    private int patente;
    private String marca;
    private String modelo;
    private int anio;
    private String estado;
    private double precio;

    // Constructor
    public Vehiculo(int patente, String marca, String modelo, int anio, String estado, double precio) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.estado = estado;
        this.precio = precio;
    }

    // Getters y setters
    public int getPatente() { return patente; }
    public void setPatente(int patente) { this.patente = patente; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "patente=" + patente +
                ", marca='" + marca + "'" +
                ", modelo='" + modelo + "'" +
                ", anio=" + anio +
                ", estado='" + estado + "'" +
                ", precio=" + precio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return patente == vehiculo.patente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patente);
    }
}
