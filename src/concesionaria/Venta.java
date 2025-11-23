package concesionaria;

import java.util.ArrayList;

public class Venta {

    private EmpleadoVenta vendedor;
    private Cliente cliente;
    private ArrayList<DetalleVenta> detalles;
    private double totalFinal;

    
    public Venta(EmpleadoVenta vendedor, Cliente cliente, ArrayList<DetalleVenta> detalles) {
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.detalles = detalles;
        calcularTotalFinal();
    }

    // ---- CALCULA EL TOTAL DE LA VENTA ----
    private void calcularTotalFinal() {
        double total = 0;

        for (DetalleVenta dv : detalles) {
            total += dv.getSubtotal();
        }

        this.totalFinal = total;
    }

    // ---- GETTERS ----
    public EmpleadoVenta getVendedor() {
        return vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<DetalleVenta> getDetalles() {
        return detalles;
    }

    
    public double getTotalFinal() {
        return totalFinal;
    }

    @Override
    public String toString() {
        return "Venta realizada a " + cliente.getNombre() +
               " por el vendedor " + vendedor.getNombre() +
               ". Total: $" + totalFinal;
    }
}
