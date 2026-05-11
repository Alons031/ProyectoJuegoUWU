
package JuegodeCarrera;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Ventana extends JPanel{ 
    Pelota pelota1 = new Pelota(this);
    Limite tope= new Limite(this);
    int teclaSalto;
    boolean juego =false;
    int puntos;
    //cambio joselo todo el public ventana 
    public Ventana(int tecla, Color colorFondo){
        this.teclaSalto = tecla;
        setBackground(colorFondo);
        this.teclaSalto = tecla;
        setBackground(colorFondo);

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
      g.drawString("PUNTOS: "+puntos, 20,30);
    }
    public void Juego(){
        puntos=0;
    }
}
    

