package proyectojuegouwu;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
/**
 * EQUIPO 2:
 *      Cruz Paz Imanol
 *      Espino Espino Lisandro
 *      Gonzalez Garcial Michelle
 *      Ortega Zarate Alonso
 *      Reyes Perez Jose Eduardo
 * 
 */
public class ProyectoJuegoUwU extends JFrame {
    
    // Declaramos los 5 caballos como JLabels individuales y sencillos
    JLabel caballo1;
    JLabel caballo2;
    JLabel caballo3;
    JLabel caballo4;
    JLabel caballo5;
    
    // Elementos de la pista
    JButton btnIniciar;
    JLabel lineaMeta;

    // Variable global simple para saber si alguien ya ganó y detener a los demás
    public static boolean hayGanador = false;

    public ProyectoJuegoUwU() {
        // Configuración básica de la ventana
        setTitle("Juego de Caballos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Layout nulo para mover los labels libremente por coordenadas (X, Y)

        // --- Crear Caballo 1 ---
        caballo1 = new JLabel(); 
        caballo1.setIcon(new ImageIcon(getClass().getResource("caballo.gif")));
        caballo1.setBounds(20, 50, 200, 150); 
        add(caballo1);

        // --- Crear Caballo 2 ---
        caballo2 = new JLabel();
        caballo2.setIcon(new ImageIcon(getClass().getResource("caballo.gif")));
        caballo2.setBounds(20, 120, 200, 150); 
        add(caballo2);

        // --- Crear Caballo 3 ---
        caballo3 = new JLabel();
        caballo3.setIcon(new ImageIcon(getClass().getResource("caballo.gif")));
        caballo3.setBounds(20, 190, 200, 150); 
        add(caballo3);

        // --- Crear Caballo 4 ---
        caballo4 = new JLabel();
        caballo4.setIcon(new ImageIcon(getClass().getResource("caballo.gif")));
        caballo4.setBounds(20, 260, 200, 150); 
        add(caballo4);

        // --- Crear Caballo 5 ---
        caballo5 = new JLabel();
        caballo5.setIcon(new ImageIcon(getClass().getResource("caballo.gif")));
        caballo5.setBounds(20, 330, 200, 150); 
        add(caballo5);

        // --- Línea de meta visible ---
        lineaMeta = new JLabel();
        lineaMeta.setOpaque(true);
        lineaMeta.setBackground(Color.BLACK);
        lineaMeta.setBounds(600, 0, 10, 400); 
        add(lineaMeta);

        // --- Botón para iniciar ---
        btnIniciar = new JButton("Comenzar Carrera");
        btnIniciar.setBounds(250, 400, 150, 40);
        add(btnIniciar);

        // --- Acción de darle clic al botón ---
        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hayGanador = false; // Reiniciamos la variable

                // Regresar los caballos a la línea de salida (coordenada X = 20)
                caballo1.setLocation(20, 50);
                caballo2.setLocation(20, 120);
                caballo3.setLocation(20, 190);
                caballo4.setLocation(20, 260);
                caballo5.setLocation(20, 330);

                // Instanciar los hilos. Le pasamos al constructor solo lo esencial: el label y el nombre.
                HiloCaballo h1 = new HiloCaballo(caballo1, "Jugador 1");
                HiloCaballo h2 = new HiloCaballo(caballo2, "Jugador 2");
                HiloCaballo h3 = new HiloCaballo(caballo3, "Jugador 3");
                HiloCaballo h4 = new HiloCaballo(caballo4, "Jugador 4");
                HiloCaballo h5 = new HiloCaballo(caballo5, "Jugador 5");

                // Iniciar los hilos al mismo tiempo
                h1.start();
                h2.start();
                h3.start();
                h4.start();
                h5.start();
            }
        });
    }

    public static void main(String[] args) {
        ProyectoJuegoUwU ventana = new ProyectoJuegoUwU();
        ventana.setVisible(true);
    }
}

class HiloCaballo extends Thread {
    
    JLabel caballo;
    String nombre;

    // Constructor simple
    public HiloCaballo(JLabel caballo, String nombre) {
        this.caballo = caballo;
        this.nombre = nombre;
    }

    // Este método es el que se ejecuta cuando llamamos a .start()
    public void run() {
        int posicionX = caballo.getX();
        int meta = 600; // Coordenada X donde está la meta negra

        // Mientras no llegue a la meta y NADIE haya ganado aún
        while (posicionX < meta && ProyectoJuegoUwU.hayGanador == false) {
            try {
                // 1. Pausa de tiempo aleatoria (para que los caballos vayan a diferente ritmo)
                int tiempoPausa = (int) (Math.random() * 80) + 20;
                Thread.sleep(tiempoPausa);

                // 2. Avance de pixeles aleatorio
                int avance = (int) (Math.random() * 15) + 5;
                posicionX = posicionX + avance;

                // 3. Actualizar la posición del JLabel en la ventana
                caballo.setLocation(posicionX, caballo.getY());

                // 4. Comprobar si este hilo en particular ya cruzó la meta
                if (posicionX >= meta) {
                    ProyectoJuegoUwU.hayGanador = true; // Avisamos que ya hay ganador para que el "while" de los demás se rompa
                    JOptionPane.showMessageDialog(null, "¡" + nombre + " USTED ES EL GANADOR!");
                }

            } catch (Exception e) {
                System.out.println("Ocurrió un error en el hilo del " + nombre);
            }
        }
    }
}
