
package JuegodeCarrera;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Musica {
    private Clip clip;
    
    public void cargarMusica(String nombreArchivo){
        try{
            File rutaArchivo = new File("src/JuegodeCarrera/" + nombreArchivo);
            AudioInputStream audio = AudioSystem.getAudioInputStream(rutaArchivo);
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    // Método 2: se repita
    public void reproducirLoop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY); // se repite sin fin
            clip.start();
        }
    }

    // Método 3: Para detenerla cuando termine el juego
    public void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
