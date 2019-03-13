package Ship;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Game.SpaceObject;

/**
 * The Class Ship.
 */
public class Ship extends SpaceObject {

	/** The draw color. */
	private Color drawColor = Color.CYAN;
	
	/** The max speed. */
	private double maxSpeed = 5;
	
	/** The acceleration. */
	private double acceleration = .5;
	
	/** The x cord. */
	int[] xCord;
	
	/** The y cord. */
	int[] yCord;

	/**
	 * Instantiates a new ship.
	 *
	 * @param pose the pose
	 * @param speed the speed
	 */
	public Ship(Point.Double pose, double speed) {
		

		super(pose);
		// create ship shape
		xCord = new int[]{10, 10, 9, 9, 8, 8, 7, 7, 6, 6, 5, 5, 4,  4,  3,  3,  2,  2,  1,  1,  0,  0,  3,  3,  4,  4,  5,  5,  8,  8,  9,  9, 10, 10, 10, 12, 12, 13, 13, 16, 16, 17, 17, 18, 18, 21, 21, 20, 20, 19, 19, 18, 18, 17, 17, 16, 16, 15, 15, 14, 14, 13, 13, 12, 12, 11, 11, 11, 10};
		yCord = new int[]{ 0,  1, 1, 3, 3, 5, 5, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 17, 17, 16, 16, 15, 15, 14, 14, 15, 15, 18, 18, 19, 18, 18, 15, 15, 14, 14, 15, 15, 16, 16, 17, 17, 13, 13, 12, 12, 11, 11, 10, 10,  9,  9,  8,  8,  7,  7,  5,  5,  3,  3,  1,  1,  1,  0,  0} ; 
		Path2D.Double shipShape = new Path2D.Double();
		shipShape.moveTo(10, 0);
		for (int i = 0; i< xCord.length; i++) {
			shipShape.lineTo(xCord[i], yCord[i]);
		}
		setShape(shipShape);

		width = 20; // ship widths

		dx = speed;

		setHealth(100);
		

		
		
		try {
		    File pathToFile = new File("assets/ship.png");
		    texture = ImageIO.read(pathToFile);
		} catch (IOException ex) {
		    ex.printStackTrace();
		}

		
		new javafx.embed.swing.JFXPanel();
	    //String uriString = new File("assets/laser.mp3").toURI().toString();
	    //laserPlayer = new MediaPlayer(new Media(uriString));
	    //laserPlayer.setRate(2);


	}
	
	/**
	 * Apply force.
	 *
	 * @param forceX the force X
	 * @param forceY the force Y
	 */
	public void applyForce(int forceX, int forceY){
		dx += acceleration * forceX;
		dx *= .99;//slow down over time
		dy += acceleration * forceY;
		dy *= .99;//slow down over time
		if(Math.abs(dx) > maxSpeed){
			dx = (maxSpeed * Integer.signum(forceX) );
		}
		if(Math.abs(dy) > maxSpeed){
			dy = (maxSpeed * Integer.signum(forceY) );
		}

	}

	/**
	 * Instantiates a new ship.
	 *
	 * @param pose the pose
	 * @param speed the speed
	 * @param initialHealth the initial health
	 */
	public Ship(Point.Double pose, double speed, int initialHealth) {
		this(pose, speed);
		setHealth(initialHealth);
	}

	/**
	 * Gets the draw color.
	 *
	 * @return the draw color
	 */
	public Color getDrawColor() {
		return drawColor;
	}
}
