
package JuegodeCarrera;

import javax.swing.JFrame;
/**
 * EQUIPO 2:
 *      Cruz Paz Imanol
 *      Espino Espino Lisandro
 *      Gonzalez Garcial Michelle
 *      Ortega Zarate Alonso
 *      Reyes Perez Jose Eduardo
 */

public class Main {
    public static void main(String[] args){
        JFrame ventana = new JFrame();
        Ventana panel = new Ventana();
        ventana.add(panel);
        ventana.setSize(600,300);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        while(!panel.juego){
            panel.repaint();
            
            try{
                Thread.sleep(15);
            }catch(Exception e){
            }
        }
    }
}
