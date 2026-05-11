package JuegodeCarrera;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Limite {
        
        int ancho = 20;
        int alto =75;
        int x = 600;
        int y = 200;
        int velocidad = -5;
        Ventana ventana;
        
        public Limite (Ventana ventana){
            this.ventana = ventana;
        
        }
        
        public void moverLimite(){
        if(x<=0){
            ventana.puntos++;
            x=900;
            
            
            }else {
            if (colision()){
                ventana.Juego();
            
            
            }else{
            x=x + velocidad;
            
            
            }
            
            }
        }
        
        public boolean colision(){
            return ventana.pelota1.getBounds().intersects(getBounds());
        
        }
        
        public void paint (Graphics2D g){
            g.fillRect(x,y,ancho,alto);
        
        }
        
        public Rectangle getBounds(){
            return new Rectangle (x,y,ancho,alto);
        
        }
        
    }

