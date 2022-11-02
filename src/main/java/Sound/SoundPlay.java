package Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundPlay {
    private Clip clip;
    private String path;
    public SoundPlay(String path) {
        this.path = path;
        try {
            File file = new File(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
    public boolean isFinish() {
        return (clip.getMicrosecondLength() == clip.getMicrosecondPosition());
    }
}
