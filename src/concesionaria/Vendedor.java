package concesionaria;

@FunctionalInterface
public interface Vendedor {
    // Implementación funcional: vende un auto a un cliente (devuelve la Venta creada)
    Venta vender(Auto auto, Cliente cliente, Empleado vendedor, double precioFinal);
}
