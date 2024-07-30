package gestionBiblioteca;

public abstract class Usuario {
    private String nombre;
    private int numeroUsuario;

    public Usuario(String nombre, int numeroUsuario) {
        this.nombre = nombre;
        this.numeroUsuario = numeroUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroUsuario() {
        return numeroUsuario;
    }

    public abstract int getMaxLibros();
}
