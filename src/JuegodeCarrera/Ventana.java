
package JuegodeCarrera;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Ventana extends JPanel{ 
    Pelota pelota1 ;
    Limite tope= new Limite(this);
    int teclaSalto;
    boolean juego =false;
    int puntos;
    Image fondo;
    
    //cambio joselo todo el public ventana 
    public Ventana(int tecla, Color colorFondo,String nombreImagen){
        this.teclaSalto = tecla;
       
        this.teclaSalto = tecla;
       
        
        this.fondo = new ImageIcon(getClass().getResource("salon.jpg")).getImage();
        
        pelota1 = new Pelota(this, nombreImagen);

    // Configuramos la entrada (InputMap)
    InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap am = getActionMap();

    // Registramos la tecla (ej. Espacio, W, o Flecha)
    im.put(KeyStroke.getKeyStroke(tecla, 0), "saltar");
    
    // Definimos la acción que hará esa tecla
    am.put("saltar", new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pelota1.saltando = true;
        }
    }); 
        
    }
    public void moverPanel(){
        pelota1.moverPelota(); 
        tope.moverLimite();
        
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        puntaje(g2);
        
        pelota1.paint(g2);
        moverPanel();
        
        g.setColor(Color.RED); 
        tope.paint(g2);
        
        moverPanel();
    }
    public void puntaje (Graphics2D g){
      Font score = new Font("Arial", Font.BOLD,30);
      g.setFont(score);
      g.setColor(Color.WHITE);
      g.drawString("PUNTOS: "+puntos, 20,30);
    }
    public void Juego(){
        puntos=0;
    }
}
    

