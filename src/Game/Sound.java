package Game;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * The Class Sound.
 */
public class Sound implements Runnable {

	/** The uri string. */
	private String uriString;
	
	/** The loop. */
	private boolean loop;
	
	/**
	 * Instantiates a new sound.
	 *
	 * @param filename the filename
	 */
	public Sound (String filename) {
		this(filename, false);
	}
	
	/**
	 * Instantiates a new sound.
	 *
	 * @param filename the filename
	 * @param loop the loop
	 */
	public Sound (String filename, boolean loop) {
		new javafx.embed.swing.JFXPanel();
	    uriString = new File(filename).toURI().toString();
	    this.loop = loop;
	}
	
	/**
	 * Play.
	 */
	public void play() {
		MediaPlayer soundPlayer = new MediaPlayer(new Media(uriString));
		soundPlayer.setOnEndOfMedia(new Runnable() {

			@Override
			public void run() {
				soundPlayer.stop();
				soundPlayer.seek(new Duration(0));
			}

		});

		do {
			if (loop) {
				soundPlayer.setVolume(0.1);
			}
			soundPlayer.play();
			if (loop) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} while (loop);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		play();	
	}
}
