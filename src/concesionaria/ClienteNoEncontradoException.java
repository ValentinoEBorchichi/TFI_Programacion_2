package concesionaria;

public class ClienteNoEncontradoException extends Exception {

    public ClienteNoEncontradoException() {
        super("No se encontró un cliente con el DNI especificado.");
    }

    public ClienteNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
