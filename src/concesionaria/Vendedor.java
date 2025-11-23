package concesionaria;

@FunctionalInterface
public interface Vendedor {
    // ---- MÉTODO PARA REALIZAR UNA VENTA ----
    Venta vender(Auto auto, Cliente cliente, Empleado vendedor, double precioFinal);
}
