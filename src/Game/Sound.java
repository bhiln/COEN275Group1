package Game;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Sound implements Runnable {

	private String uriString;
	
	public Sound (String filename) {
		new javafx.embed.swing.JFXPanel();
	    uriString = new File(filename).toURI().toString();
	}
	
	public void play() {
		MediaPlayer soundPlayer = new MediaPlayer(new Media(uriString));
		soundPlayer.play();
	}

	public void run() {
		play();	
	}
}
