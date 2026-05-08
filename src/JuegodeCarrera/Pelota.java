package JuegodeCarrera;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Pelota {
    
    int ancho = 50;
    int alto = 50;
    int x = 100;
    int y = 200;
    int velocidadY;
    Ventana ventana;
    boolean saltando = false;
    boolean sube = false;
    boolean baja = false;
    
    
    public Pelota (Ventana ventana){
        this.ventana = ventana;
        
   
    }
    
    public void moverPelota(){
        if (saltando){
        
            if(y == 200){
                sube = true;
                velocidadY = -5;
                baja = false;
            
            }
            if(y==80){
            baja=true;
            velocidadY = 5;
            sube = false;
            
            
            }
            
            if(sube){
                y = y + velocidadY;
            
            }
            
            if (baja){
                y = y + velocidadY;
                if(y==200){
                saltando = false;
            
            
            }
            
            
            }
        
        }
    
    
    }
    
    public void paint (Graphics2D g){
        g.fillOval (x,y,ancho,alto);
    
    }
    
    public void KeyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            saltando = true;
        
        }
    
    }
    
    public Rectangle getBounds(){
    
    return new Rectangle (x,y,ancho,alto);
    
    }
    
}
    
