import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.Year;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {

    private Biblioteca biblioteca;

    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtCodigo;
    private JTextField txtGenero;
    private JTextField txtAnio;
    private JTextField txtCopias;
    private JTextField txtBuscarAutor;

    private JTable tablaLibros;

    private DefaultTableModel modeloTabla;

    private final String[] columnas = {
            "Título",
            "Autor",
            "ISBN/Código",
            "Género",
            "Año",
            "Copias"
    };

    public VentanaPrincipal(Biblioteca biblioteca) {

        this.biblioteca = biblioteca;

        setTitle(
                "Biblioteca Municipal San Rafael"
        );

        setSize(950, 600);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        construirInterfaz();

        actualizarTabla(
                biblioteca.obtenerTodos()
        );
    }

    private void construirInterfaz() {

        setLayout(
                new BorderLayout(10, 10)
        );

        JPanel panelFormulario =
                new JPanel(
                        new GridLayout(
                                3,
                                4,
                                8,
                                8
                        )
                );

        panelFormulario.setBorder(
                BorderFactory.createTitledBorder(
                        "Registrar libro"
                )
        );

        txtTitulo =
                new JTextField();

        txtAutor =
                new JTextField();

        txtCodigo =
                new JTextField();

        txtGenero =
                new JTextField();

        txtAnio =
                new JTextField();

        txtCopias =
                new JTextField();

        panelFormulario.add(
                new JLabel("Título:")
        );

        panelFormulario.add(
                txtTitulo
        );

        panelFormulario.add(
                new JLabel("Autor:")
        );

        panelFormulario.add(
                txtAutor
        );

        panelFormulario.add(
                new JLabel("ISBN/Código:")
        );

        panelFormulario.add(
                txtCodigo
        );

        panelFormulario.add(
                new JLabel("Género:")
        );

        panelFormulario.add(
                txtGenero
        );

        panelFormulario.add(
                new JLabel("Año publicación:")
        );

        panelFormulario.add(
                txtAnio
        );

        panelFormulario.add(
                new JLabel("Copias disponibles:")
        );

        panelFormulario.add(
                txtCopias
        );

        JPanel panelSuperior =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        panelSuperior.add(
                panelFormulario,
                BorderLayout.CENTER
        );

        JButton btnAgregar =
                new JButton(
                        "Agregar libro"
                );

        btnAgregar.addActionListener(
                e -> agregarLibro()
        );

        JPanel panelBotonAgregar =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        panelBotonAgregar.add(
                btnAgregar
        );

        panelSuperior.add(
                panelBotonAgregar,
                BorderLayout.SOUTH
        );

        add(
                panelSuperior,
                BorderLayout.NORTH
        );

        modeloTabla =
                new DefaultTableModel(
                        columnas,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        tablaLibros =
                new JTable(
                        modeloTabla
                );

        tablaLibros.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tablaLibros.setAutoCreateRowSorter(
                true
        );

        add(
                new JScrollPane(
                        tablaLibros
                ),
                BorderLayout.CENTER
        );

        txtBuscarAutor =
                new JTextField(20);

        JButton btnFiltrar =
                new JButton(
                        "Filtrar por autor"
                );

        btnFiltrar.addActionListener(
                e -> filtrarPorAutor()
        );

        JButton btnMostrarTodos =
                new JButton(
                        "Mostrar todos"
                );

        btnMostrarTodos.addActionListener(
                e -> mostrarTodos()
        );

        JButton btnEliminar =
                new JButton(
                        "Eliminar seleccionado"
                );

        btnEliminar.addActionListener(
                e -> eliminarSeleccionado()
        );

        JButton btnOrdenar =
                new JButton(
                        "Ordenar por título"
                );

        btnOrdenar.addActionListener(
                e -> {

                    biblioteca.ordenarPorTitulo();

                    actualizarTabla(
                            biblioteca.obtenerTodos()
                    );
                }
        );

        JButton btnContar =
                new JButton(
                        "Contar libros"
                );

        btnContar.addActionListener(
                e ->
                        JOptionPane.showMessageDialog(
                                this,
                                "Libros registrados: "
                                        + biblioteca.contarLibros(),
                                "Cantidad",
                                JOptionPane.INFORMATION_MESSAGE
                        )
        );

        JPanel panelInferior =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                8,
                                8
                        )
                );

        panelInferior.add(
                new JLabel("Autor:")
        );

        panelInferior.add(
                txtBuscarAutor
        );

        panelInferior.add(
                btnFiltrar
        );

        panelInferior.add(
                btnMostrarTodos
        );

        panelInferior.add(
                btnEliminar
        );

        panelInferior.add(
                btnOrdenar
        );

        panelInferior.add(
                btnContar
        );

        add(
                panelInferior,
                BorderLayout.SOUTH
        );
    }

    private void agregarLibro() {

        String titulo =
                txtTitulo.getText().trim();

        String autor =
                txtAutor.getText().trim();

        String codigo =
                txtCodigo.getText().trim();

        String genero =
                txtGenero.getText().trim();

        String anioTexto =
                txtAnio.getText().trim();

        String copiasTexto =
                txtCopias.getText().trim();

        if (
                titulo.isEmpty()
                        || autor.isEmpty()
                        || codigo.isEmpty()
                        || genero.isEmpty()
                        || anioTexto.isEmpty()
                        || copiasTexto.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Todos los campos son obligatorios.",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        int anio;

        int copias;

        try {

            anio =
                    Integer.parseInt(
                            anioTexto
                    );

            copias =
                    Integer.parseInt(
                            copiasTexto
                    );

        } catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "El año y las copias deben ser números enteros.",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        int anioActual =
                Year.now().getValue();

        if (anio > anioActual) {

            JOptionPane.showMessageDialog(
                    this,
                    "El año de publicación no puede ser mayor a "
                            + anioActual
                            + ".",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (copias < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Las copias disponibles deben ser mayores o iguales a 0.",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Libro libro =
                new Libro(
                        titulo,
                        autor,
                        codigo,
                        genero,
                        anio,
                        copias
                );

        try {

            biblioteca.agregarLibro(
                    libro
            );

            limpiarFormulario();

            actualizarTabla(
                    biblioteca.obtenerTodos()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Libro registrado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (
                IllegalArgumentException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Libro duplicado",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void filtrarPorAutor() {

        String autor =
                txtBuscarAutor
                        .getText()
                        .trim();

        if (autor.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Escribe un autor para realizar el filtro.",
                    "Búsqueda",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        ArrayList<Libro> resultado =
                biblioteca.filtrarPorAutor(
                        autor
                );

        actualizarTabla(
                resultado
        );

        if (resultado.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se encontraron libros de ese autor.",
                    "Resultado",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void mostrarTodos() {

        txtBuscarAutor.setText("");

        actualizarTabla(
                biblioteca.obtenerTodos()
        );
    }

    private void eliminarSeleccionado() {

        int filaVista =
                tablaLibros.getSelectedRow();

        if (filaVista == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un libro de la tabla.",
                    "Eliminar",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int filaModelo =
                tablaLibros.convertRowIndexToModel(
                        filaVista
                );

        String codigo =
                modeloTabla
                        .getValueAt(
                                filaModelo,
                                2
                        )
                        .toString();

        Libro libroSeleccionado =
                null;

        for (
                Libro libro :
                biblioteca.obtenerTodos()
        ) {

            if (
                    libro.getCodigo()
                            .equalsIgnoreCase(
                                    codigo
                            )
            ) {

                libroSeleccionado =
                        libro;

                break;
            }
        }

        if (
                libroSeleccionado == null
        ) {
            return;
        }

        int confirmacion =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Seguro que deseas eliminar el libro \""
                                + libroSeleccionado.getTitulo()
                                + "\"?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                confirmacion
                        == JOptionPane.YES_OPTION
        ) {

            biblioteca.eliminarLibro(
                    libroSeleccionado
            );

            actualizarTabla(
                    biblioteca.obtenerTodos()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Libro eliminado correctamente.",
                    "Eliminación",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void actualizarTabla(
            ArrayList<Libro> libros
    ) {

        modeloTabla.setRowCount(0);

        for (
                Libro libro :
                libros
        ) {

            modeloTabla.addRow(
                    new Object[]{
                            libro.getTitulo(),
                            libro.getAutor(),
                            libro.getCodigo(),
                            libro.getGenero(),
                            libro.getAnioPublicacion(),
                            libro.getCopiasDisponibles()
                    }
            );
        }
    }

    private void limpiarFormulario() {

        txtTitulo.setText("");
        txtAutor.setText("");
        txtCodigo.setText("");
        txtGenero.setText("");
        txtAnio.setText("");
        txtCopias.setText("");

        txtTitulo.requestFocus();
    }
}
