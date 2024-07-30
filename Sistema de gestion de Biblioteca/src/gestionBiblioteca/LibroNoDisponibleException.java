package gestionBiblioteca;

public class LibroNoDisponibleException extends PrestamoException {
    public LibroNoDisponibleException(String message) {
        super(message);
    }
}
