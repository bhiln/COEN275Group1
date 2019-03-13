package Game;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class Sound implements Runnable {

	private String uriString;
	private boolean loop;
	
	public Sound (String filename) {
		this(filename, false);
	}
	
	public Sound (String filename, boolean loop) {
		new javafx.embed.swing.JFXPanel();
	    uriString = new File(filename).toURI().toString();
	    this.loop = loop;
	}
	
	public void play() {
		MediaPlayer soundPlayer = new MediaPlayer(new Media(uriString));
		do {
			if (loop) {
				soundPlayer.setVolume(0.1);
			}
			soundPlayer.play();
			if (loop) {			
				try {
					Thread.sleep(45000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (loop);
	}

	public void run() {
		play();	
	}
}
