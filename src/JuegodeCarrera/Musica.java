package JuegodeCarrera;

import java.io.File;
import javax.sound.sampled.*;

public class Musica {
    private Clip clip;

    public void cargarMusica(String nombreArchivo) {
        try {
            File rutaArchivo = new File("src/JuegodeCarrera/" + nombreArchivo);
            AudioInputStream audio = AudioSystem.getAudioInputStream(rutaArchivo);
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reproducirLoop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }

    public void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}