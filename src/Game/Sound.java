package Game;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The Class Sound.
 */
public class Sound implements Runnable {

	/** The uri string. */
	private String uriString;
	
	/**
	 * Instantiates a new sound.
	 *
	 * @param filename the filename
	 */
	public Sound (String filename) {
		new javafx.embed.swing.JFXPanel();
	    uriString = new File(filename).toURI().toString();
	}
	
	/**
	 * Play.
	 */
	public void play() {
		MediaPlayer soundPlayer = new MediaPlayer(new Media(uriString));
		soundPlayer.play();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		play();	
	}
}
