package gestionBiblioteca;

public class Estudiante extends Usuario {
    public Estudiante(String nombre, int numeroUsuario) {
        super(nombre, numeroUsuario);
    }

    @Override
    public int getMaxLibros() {
        return 1; // Los estudiantes pueden solicitar un libro a la vez
    }
}
