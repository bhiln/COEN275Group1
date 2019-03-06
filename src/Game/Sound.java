package Game;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Sound implements Runnable {

	private MediaPlayer soundPlayer;
	
	public Sound (String filename) {
		new javafx.embed.swing.JFXPanel();
	    String uriString = new File(filename).toURI().toString();
	    soundPlayer = new MediaPlayer(new Media(uriString));
	}
	
	public void setRate(double rate) {
		soundPlayer.setRate(rate);
	}
	
	public void play() {
		soundPlayer.seek(Duration.ZERO);
		soundPlayer.play();
		System.out.println("sound");
	}

	public void run() {
		play();	
	}
}
