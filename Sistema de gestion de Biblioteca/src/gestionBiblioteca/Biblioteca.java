package gestionBiblioteca;

import java.util.*;

public class Biblioteca {
    private List<Libro> libros;
    private Map<Usuario, List<Prestamo>> prestamos;

    public Biblioteca() {
        libros = new ArrayList<>();
        prestamos = new HashMap<>();
    }

    public void agregarLibro(Libro libro) throws LibroDuplicadoException {
        for (Libro l : libros) {
            if (l.getIsbn().equals(libro.getIsbn())) {
                throw new LibroDuplicadoException("El libro ya existe en la biblioteca");
            }
        }
        libros.add(libro);
    }

    public Libro consultarLibro(String isbn) {
        for (Libro libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }

    public void prestarLibro(Usuario usuario, Libro libro) throws PrestamoException {
        if (!libro.isDisponible()) {
            throw new LibroNoDisponibleException("El libro no está disponible");
        }

        List<Prestamo> prestamosUsuario = prestamos.getOrDefault(usuario, new ArrayList<>());
        if (prestamosUsuario.size() >= usuario.getMaxLibros()) {
            throw new PrestamoExcedidoException("El usuario ha excedido el límite de préstamos");
        }

        libro.setDisponible(false);
        prestamosUsuario.add(new Prestamo(usuario, libro));
        prestamos.put(usuario, prestamosUsuario);
    }

    public void devolverLibro(Usuario usuario, Libro libro) throws PrestamoException {
        List<Prestamo> prestamosUsuario = prestamos.get(usuario);

        if (prestamosUsuario == null || !prestamosUsuario.removeIf(p -> p.getLibro().equals(libro))) {
            throw new LibroNoPrestadoException("El libro no ha sido prestado a este usuario");
        }

        libro.setDisponible(true);
    }

    public List<Prestamo> consultarHistorial() {
        List<Prestamo> historial = new ArrayList<>();
        for (List<Prestamo> prestamosUsuario : prestamos.values()) {
            historial.addAll(prestamosUsuario);
        }
        return historial;
    }
}


