package gestionBiblioteca;

public class Profesor extends Usuario {
    public Profesor(String nombre, int numeroUsuario) {
        super(nombre, numeroUsuario);
    }

    @Override
    public int getMaxLibros() {
        return 3; // Los profesores pueden solicitar hasta tres libros simultáneamente
    }
}
