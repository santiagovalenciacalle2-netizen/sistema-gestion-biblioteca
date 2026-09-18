import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class Biblioteca {

    private ArrayList<Libro> libros;

    private HashMap<String, ArrayList<Libro>> indicePorAutor;

    private HashSet<String> codigosRegistrados;

    public Biblioteca() {

        libros = new ArrayList<>();

        indicePorAutor = new HashMap<>();

        codigosRegistrados = new HashSet<>();
    }

    public void agregarLibro(Libro libro) {

        if (codigosRegistrados.contains(libro.getCodigo())) {

            throw new IllegalArgumentException(
                    "Ya existe un libro con ese ISBN/código."
            );
        }

        libros.add(libro);

        codigosRegistrados.add(
                libro.getCodigo()
        );

        reconstruirIndiceAutores();
    }

    public void eliminarLibro(Libro libro) {

        if (libro != null) {

            libros.remove(libro);

            codigosRegistrados.remove(
                    libro.getCodigo()
            );

            reconstruirIndiceAutores();
        }
    }

    public ArrayList<Libro> filtrarPorAutor(String autor) {

        ArrayList<Libro> resultado =
                new ArrayList<>();

        String busqueda =
                autor.trim().toLowerCase();

        if (busqueda.isEmpty()) {

            return new ArrayList<>(libros);
        }

        for (Libro libro : libros) {

            if (
                    libro.getAutor()
                            .toLowerCase()
                            .contains(busqueda)
            ) {

                resultado.add(libro);
            }
        }

        return resultado;
    }

    public ArrayList<Libro> obtenerTodos() {

        return new ArrayList<>(libros);
    }

    public void ordenarPorTitulo() {

        libros.sort(
                Comparator.comparing(
                        Libro::getTitulo,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        reconstruirIndiceAutores();
    }

    private void reconstruirIndiceAutores() {

        indicePorAutor.clear();

        for (Libro libro : libros) {

            String autor =
                    libro.getAutor().toLowerCase();

            indicePorAutor.putIfAbsent(
                    autor,
                    new ArrayList<>()
            );

            indicePorAutor
                    .get(autor)
                    .add(libro);
        }
    }

    public int contarLibros() {

        return libros.size();
    }

    public HashMap<String, ArrayList<Libro>> getIndicePorAutor() {

        return new HashMap<>(
                indicePorAutor
        );
    }
}