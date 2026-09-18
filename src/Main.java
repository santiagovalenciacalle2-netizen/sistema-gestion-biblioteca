import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Biblioteca biblioteca =
                    new Biblioteca();

            VentanaPrincipal ventana =
                    new VentanaPrincipal(
                            biblioteca
                    );

            ventana.setVisible(true);
        });
    }
}