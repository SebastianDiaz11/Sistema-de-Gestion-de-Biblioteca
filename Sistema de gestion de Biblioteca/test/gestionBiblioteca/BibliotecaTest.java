package gestionBiblioteca;

import org.junit.*;
import static org.junit.Assert.*;

public class BibliotecaTest {
    private Biblioteca biblioteca;
    private Libro libro1, libro2;
    private Usuario estudiante, profesor;

    @Before
    public void setUp() {
        biblioteca = new Biblioteca();
        libro1 = new Libro("Título 1", "Autor 1", "ISBN001");
        libro2 = new Libro("Título 2", "Autor 2", "ISBN002");
        estudiante = new Estudiante("Estudiante 1", 1001);
        profesor = new Profesor("Profesor 1", 2001);
    }

    @Test
    public void testAgregarLibro() throws Exception {
        biblioteca.agregarLibro(libro1);
        assertEquals(libro1, biblioteca.consultarLibro("ISBN001"));
    }

    @Test(expected = LibroDuplicadoException.class)
    public void testAgregarLibroDuplicado() throws Exception {
        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro1); // Debe lanzar excepción
    }

    @Test
    public void testPrestarLibroAEstudiante() throws Exception {
        biblioteca.agregarLibro(libro1);
        biblioteca.prestarLibro(estudiante, libro1);
        assertFalse(libro1.isDisponible());
    }

    @Test(expected = PrestamoExcedidoException.class)
    public void testPrestarLibroExcediendoLimiteEstudiante() throws Exception {
        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        biblioteca.prestarLibro(estudiante, libro1);
        biblioteca.prestarLibro(estudiante, libro2); // Debe lanzar excepción
    }

    @Test
    public void testDevolverLibro() throws Exception {
        biblioteca.agregarLibro(libro1);
        biblioteca.prestarLibro(estudiante, libro1);
        biblioteca.devolverLibro(estudiante, libro1);
        assertTrue(libro1.isDisponible());
    }

    @Test(expected = LibroNoPrestadoException.class)
    public void testDevolverLibroNoPrestado() throws Exception {
        biblioteca.agregarLibro(libro1);
        biblioteca.devolverLibro(estudiante, libro1); // Debe lanzar excepción
    }

    @Test
    public void testConsultarHistorial() throws Exception {
        biblioteca.agregarLibro(libro1);
        biblioteca.prestarLibro(estudiante, libro1);
        assertEquals(1, biblioteca.consultarHistorial().size());
    }

    @Test(expected = LibroNoDisponibleException.class)
    public void testPrestarLibroNoDisponible() throws Exception {
        biblioteca.agregarLibro(libro1);
        biblioteca.prestarLibro(estudiante, libro1);
        biblioteca.prestarLibro(profesor, libro1); // Debe lanzar excepción
    }
}
