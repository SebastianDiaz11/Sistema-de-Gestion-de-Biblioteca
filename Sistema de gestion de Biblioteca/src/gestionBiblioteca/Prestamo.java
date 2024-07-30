package gestionBiblioteca;

import java.util.Date;
import java.util.Calendar;

public class Prestamo {
    private Usuario usuario;
    private Libro libro;
    private Date fechaPrestamo;
    private Date fechaDevolucionEstimada;

    public Prestamo(Usuario usuario, Libro libro) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 14); // 2 semanas de préstamo
        this.fechaDevolucionEstimada = calendar.getTime();
    }

    public Usuario getUsuario() { return usuario; }
    public Libro getLibro() { return libro; }
    public Date getFechaPrestamo() { return fechaPrestamo; }
    public Date getFechaDevolucionEstimada() { return fechaDevolucionEstimada; }
}
