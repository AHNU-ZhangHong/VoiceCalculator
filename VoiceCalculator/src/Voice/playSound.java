package Voice;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class playSound {
	public static void run(String path) {
		try {
			URL url=playSound.class.getResource(path);
			AudioInputStream stream=AudioSystem.getAudioInputStream(url);
			Clip clip=AudioSystem.getClip();
			clip.open(stream);
			clip.start();
			Thread.sleep(1);
			stream.close();
		}
		catch(Exception e) {}
	}
}
