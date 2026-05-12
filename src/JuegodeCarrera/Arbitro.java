package JuegodeCarrera;

public class Arbitro {
    private String nombreGanador = null;
    private boolean hayGanador = false;
    private final int totalJugadores;
    private int finalizados = 0;
    private String[] nombres;
    private int[] puntosFinales;

    public Arbitro(int totalJugadores) {
        this.totalJugadores = totalJugadores;
        this.nombres = new String[totalJugadores];
        this.puntosFinales = new int[totalJugadores];
    }

    public synchronized void registrarLlegada(String nombre, int puntos) {
        guardarResultado(nombre, puntos);
        finalizados++;
        decidirSiHayGanador();
    }

    public synchronized void registrarFinalizacion(String nombre, int puntos) {
        guardarResultado(nombre, puntos);
        finalizados++;
        decidirSiHayGanador();
    }

    private void guardarResultado(String nombre, int puntos) {
        for (int i = 0; i < totalJugadores; i++) {
            if (nombres[i] == null) {
                nombres[i] = nombre;
                puntosFinales[i] = puntos;
                return;
            } else if (nombres[i].equals(nombre)) {
                puntosFinales[i] = puntos;
                return;
            }
        }
    }

    private void decidirSiHayGanador() {
        if (finalizados >= totalJugadores && !hayGanador) {
            int max = Integer.MIN_VALUE;
            String ganador = null;
            boolean empate = false;
            for (int i = 0; i < totalJugadores; i++) {
                if (nombres[i] == null) continue;
                int p = puntosFinales[i];
                if (p > max) {
                    max = p;
                    ganador = nombres[i];
                    empate = false;
                } else if (p == max) {
                    empate = true;
                }
            }
            if (empate) nombreGanador = "EMPATE";
            else nombreGanador = ganador;
            hayGanador = true;
        }
    }

    public synchronized boolean yaHayGanador() {
        return hayGanador;
    }

    public synchronized String getNombreGanador() {
        return nombreGanador;
    }
}