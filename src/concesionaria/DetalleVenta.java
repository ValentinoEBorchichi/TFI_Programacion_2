package concesionaria;

public class DetalleVenta {

    private Vehiculo vehiculo;
    private int cantidad;
    private double subtotal;

    public DetalleVenta(Vehiculo vehiculo, int cantidad) {
        this.vehiculo = vehiculo;
        this.cantidad = cantidad;
        this.subtotal = vehiculo.getPrecio() * cantidad;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    // Método requerido por Venta para calcular el subtotal neto
    public double getSubtotalNeto() {
        return subtotal;
    }

    @Override
    public String toString() {
        return vehiculo.getMarca() + " " + vehiculo.getModelo() +
               " | Cant: " + cantidad + " | Subtotal: $" + subtotal;
    }
}
