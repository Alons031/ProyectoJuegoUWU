
package JuegodeCarrera;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
/**
 * EQUIPO 2:
 *      Cruz Paz Imanol
 *      Espino Espino Lisandro
 *      Gonzalez Garcia Michelle
 *      Ortega Zarate Alonso
 *      Reyes Perez Jose Eduardo
 */

public class Main {
    public static void main(String[] args){
        //todo lo que cambio joselo
        JFrame ventana = new JFrame("Carrera de 3 Jugadores");
        
        
        ventana.setLayout(new GridLayout(3, 1, 0, 5)); 

      
        Ventana panel1 = new Ventana(KeyEvent.VK_SPACE, Color.BLUE, "industrial.png");
        Ventana panel2 = new Ventana(KeyEvent.VK_W, Color.DARK_GRAY, "Meca.png");
        Ventana panel3 = new Ventana(KeyEvent.VK_UP, Color.MAGENTA, "Programacion.png");

        ventana.add(panel1);
        ventana.add(panel2);
        ventana.add(panel3);

        ventana.setSize(900, 1200); //
        ventana.setVisible(true);
        ventana.requestFocus();
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
        while (true) {
            panel1.repaint();
            panel2.repaint();
            panel3.repaint();
            try {
                Thread.sleep(15);
            } catch (Exception e) {}
        }
    }
}
