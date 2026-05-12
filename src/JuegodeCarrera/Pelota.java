package JuegodeCarrera;

import java.awt.*;
import javax.swing.*;

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
    Image imagen;

    final int Y_SUELO = 200;
    final int Y_TOPE = 80;

    public Pelota(Ventana ventana, String nombreImagen) {
        this.ventana = ventana;
        this.y = Y_SUELO;
        this.imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
    }

    public void moverPelota() {
        if (!saltando) return;

        if (sube) {
            y = y + velocidadY;
            if (y <= Y_TOPE) {
                y = Y_TOPE;
                sube = false;
                baja = true;
                velocidadY = 7; // caída más rápida que antes
            }
        } else if (baja) {
            y = y + velocidadY;
            if (y >= Y_SUELO) {
                y = Y_SUELO;
                baja = false;
                saltando = false;
                velocidadY = 0;
            }
        }
    }

    public void paint(Graphics2D g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
        } else {
            Color prev = g.getColor();
            g.setColor(Color.WHITE);
            g.fillOval(x, y, ancho, alto);
            g.setColor(prev);
        }
    }

    public void requestJump() {
        if (!saltando) {
            saltando = true;
            sube = true;
            baja = false;
            velocidadY = -7; 
        }
    }

    public void KeyPressed(java.awt.event.KeyEvent e) {
        if (e.getKeyCode() == ventana.teclaSalto) {
            saltando = true;
            sube = true;
            baja = false;
            velocidadY = -7;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}