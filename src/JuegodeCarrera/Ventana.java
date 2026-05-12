package JuegodeCarrera;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ventana extends JPanel implements Runnable {
    Pelota pelota1;
    Limite tope = new Limite(this);
    public int teclaSalto;
    boolean juego = false;
    Image fondo;
    Arbitro arbitro;
    String nombreCarril;
    static boolean juegoIniciado = false;
    private final int metaPuntos;
    private int puntos = 0;
    private boolean terminadoLocal = false;

    public Ventana(int tecla, Color colorFondo, String nombreImagen, String nombreCarril, Arbitro arbitro, int metaPuntos) {
        this.teclaSalto = tecla;
        this.nombreCarril = nombreCarril;
        this.arbitro = arbitro;
        this.metaPuntos = metaPuntos;
        this.setFocusable(true);
        java.net.URL url = getClass().getResource("salon.jpg");
        if (url != null) this.fondo = new ImageIcon(url).getImage();
        else this.fondo = null;

        pelota1 = new Pelota(this, nombreImagen);

        InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
        im.put(KeyStroke.getKeyStroke(tecla, 0), "saltar");
        am.put("saltar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!juegoIniciado) {
                    juegoIniciado = true;
                }
                // compatibilidad con el código del equipo que usa pelota1.saltando = true
                pelota1.saltando = true;
                pelota1.sube = true;
                pelota1.baja = false;
                pelota1.velocidadY = -7;
            }
        });
    }

    @Override
    public void run() {
        while (true) {
            if (juegoIniciado && !isTerminado() && !arbitro.yaHayGanador()) {
                moverPanel();
                if (metaPuntos > 0 && getPuntos() >= metaPuntos) {
                    arbitro.registrarLlegada(nombreCarril, getPuntos());
                    marcarTerminado();
                }
            }
            repaint();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if (fondo != null) g2.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        else {
            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        if (!juegoIniciado) {
            dibujarMenu(g2);
        } else if (arbitro.yaHayGanador()) {
            dibujarFinal(g2);
        } else if (isTerminado()) {
            dibujarFinal(g2);
        } else {
            pelota1.paint(g2);
            tope.paint(g2);
            puntaje(g2);
        }
    }

    private void dibujarFinal(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFont(new Font("Arial", Font.BOLD, 45));
        if (arbitro.yaHayGanador()) {
            g.setColor(Color.GREEN);
            g.drawString("¡GANADOR: " + arbitro.getNombreGanador() + "!", 100, getHeight() / 2);
        } else {
            g.setColor(Color.YELLOW);
            g.drawString("¡CHOCASTE! - ESPERANDO...", 100, getHeight() / 2);
        }
    }

    private void dibujarMenu(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("PRESIONA TU TECLA PARA INICIAR", 150, getHeight() / 2);
    }

    public void moverPanel() {
        pelota1.moverPelota();
        tope.moverLimite();
    }

    private void puntaje(Graphics2D g) {
        Font score = new Font("Arial", Font.BOLD, 30);
        g.setFont(score);
        g.setColor(Color.WHITE);
        g.drawString("PUNTOS: " + puntos, 20, 30);
    }

    public void Juego() {
        puntos = 0;
    }

    public synchronized void incrementarPuntos() {
        puntos++;
        repaint();
    }

    public synchronized int getPuntos() {
        return puntos;
    }

    public synchronized void marcarTerminado() {
        terminadoLocal = true;
        repaint();
    }

    public synchronized boolean isTerminado() {
        return terminadoLocal;
    }

    public int obtenerPuntos() {
        return getPuntos();
    }

    public void reportarChoque() {
        synchronized (this) {
            if (isTerminado()) return;
            marcarTerminado();
        }
        arbitro.registrarFinalizacion(nombreCarril, getPuntos());
    }
}