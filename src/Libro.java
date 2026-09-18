public class Libro extends MaterialBibliografico {

    private String codigo;
    private String genero;
    private int anioPublicacion;
    private int copiasDisponibles;

    public Libro(
            String titulo,
            String autor,
            String codigo,
            String genero,
            int anioPublicacion,
            int copiasDisponibles
    ) {
        super(titulo, autor);

        this.codigo = codigo;
        this.genero = genero;
        this.anioPublicacion = anioPublicacion;
        this.copiasDisponibles = copiasDisponibles;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public void setCopiasDisponibles(int copiasDisponibles) {
        this.copiasDisponibles = copiasDisponibles;
    }

    @Override
    public String getTipo() {
        return "Libro";
    }

    @Override
    public String toString() {
        return getTitulo()
                + " - "
                + getAutor()
                + " ("
                + codigo
                + ")";
    }
}