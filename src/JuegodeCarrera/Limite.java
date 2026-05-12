package JuegodeCarrera;

import java.awt.*;

public class Limite {
    int ancho = 20;
    int alto = 75;
    int x = 600;
    int y = 200;
    int velocidad = -5;
    Ventana ventana;

    public Limite(Ventana ventana) {
        this.ventana = ventana;
    }

    public void moverLimite() {
        if (x <= -ancho) {
            ventana.incrementarPuntos();
            if (velocidad > -15) {
                velocidad = velocidad - 1;
            }
            x = Math.max(ventana.getWidth(), 900);
        } else {
            if (ventana.pelota1 != null && colision()) {
                ventana.reportarChoque();
            } else {
                x = x + velocidad;
            }
        }
    }

    public boolean colision() {
        return ventana.pelota1 != null && ventana.pelota1.getBounds().intersects(getBounds());
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, y, ancho, alto);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}