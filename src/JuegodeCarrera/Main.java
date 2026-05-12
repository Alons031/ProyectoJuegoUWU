package JuegodeCarrera;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Carrera de carreras - Multihilo");
            ventana.setLayout(new GridLayout(3, 1, 0, 5));

            Arbitro var = new Arbitro(3);

            Ventana panel1 = new Ventana(KeyEvent.VK_SPACE, Color.BLUE, "industrial.png", "INDUSTRIAL", var, 0);
            Ventana panel2 = new Ventana(KeyEvent.VK_W, Color.DARK_GRAY, "Meca.png", "MECATRÓNICA", var, 0);
            Ventana panel3 = new Ventana(KeyEvent.VK_UP, Color.MAGENTA, "Programacion.png", "SISTEMAS", var, 0);

            ventana.add(panel1);
            ventana.add(panel2);
            ventana.add(panel3);

            ventana.setSize(900, 800);
            ventana.setLocationRelativeTo(null);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setVisible(true);

            // Música de fondo
            Musica musicaFondo = new Musica();
            musicaFondo.cargarMusica("Sonido.wav");
            musicaFondo.reproducirLoop();

            new Thread(panel1).start();
            new Thread(panel2).start();
            new Thread(panel3).start();
        });
    }
}