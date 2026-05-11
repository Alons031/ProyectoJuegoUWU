
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
    
    public static boolean fin = false;
            
    public static void main(String[] args){
        //todo lo que cambio joselo
        JFrame ventana = new JFrame("Carrera de Carreras");
        
        
        ventana.setLayout(new GridLayout(3, 1, 0, 5)); 

      
        Ventana panel1 = new Ventana(KeyEvent.VK_SPACE, Color.BLUE, "industrial.png");
        Ventana panel2 = new Ventana(KeyEvent.VK_W, Color.DARK_GRAY, "Meca.png");
        Ventana panel3 = new Ventana(KeyEvent.VK_UP, Color.MAGENTA, "Programacion.png");

        ventana.add(panel1);
        ventana.add(panel2);
        ventana.add(panel3);

        ventana.setSize(900, 800); 
        ventana.setVisible(true);
        ventana.requestFocus();
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       Thread hilo1 = new Thread (()-> {
           while (!fin){
               panel1.repaint();
               
               if(panel1.obtenerPuntos()==10){
                   fin = true;
                   javax.swing.JOptionPane.showMessageDialog(null, "Industrial ha Ganado!");
                   
                   System.exit(0);
               }
               try {
                   Thread.sleep(15);
               
               } catch (Exception e){
                   e.printStackTrace();
               
               }            
               }        
       
       });
     
        Thread hilo2 = new Thread (()-> {
           while (!fin){
               panel2.repaint();
               
               if(panel2.obtenerPuntos()==10){
                   fin = true;
                   javax.swing.JOptionPane.showMessageDialog(null, "Mecatronica ha Ganado!");
                   
                   System.exit(0);
               }
               try {
                   Thread.sleep(15);
               
               } catch (Exception e){
                   e.printStackTrace();
                   
               }         
               }
 
       });
        
         Thread hilo3 = new Thread (()-> {
           while (!fin){
               panel3.repaint();
               
               if(panel3.obtenerPuntos()==10){
                   fin = true;
                   javax.swing.JOptionPane.showMessageDialog(null, "Sistemas ha Ganado!");
                   
                   System.exit(0);
               }
               try {
                   Thread.sleep(15);
               
               } catch (Exception e){
                   e.printStackTrace();
               
               }     
           
           }
       });
         
         hilo1.start();
         hilo2.start();
         hilo3.start();
       
    }
}
