
package JuegodeCarrera;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Ventana extends JPanel{ 
    Pelota pelota1 = new Pelota(this);
    Limite tope= new Limite(this);
    boolean juego =false;
    int puntos;
    
    public Ventana(){
        setBackground(Color.BLUE);
        
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pelota1.KeyPressed(e);
            }
        });
        setFocusable(true);
        
    }
    public void moverPanel(){
        pelota1.moverPelota(); 
        tope.moverLimite();
        
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        puntaje(g2);
        g.setColor(Color.GREEN);
        pelota1.paint(g2);
        moverPanel();
        
        g.setColor(Color.RED); 
        tope.paint(g2);
        
        moverPanel();
    }
    public void puntaje (Graphics2D g){
      Font score = new Font("Arial", Font.BOLD,30);
      g.setFont(score);
      g.setColor(Color.BLACK);
      g.drawString("PUNTOS"+puntos, 20,30);
    }
    public void Juego(){
        puntos=0;
    }
}
    

