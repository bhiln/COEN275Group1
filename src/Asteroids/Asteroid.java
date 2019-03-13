package Asteroids;

import java.awt.*;
import java.awt.geom.Path2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Game.SpaceObject;

// TODO: Auto-generated Javadoc
/**
 * The Class Asteroid.
 */
public class Asteroid extends SpaceObject {

	/** The scale. */
	public double scale = 1;
	
	/** The wall. */
	public boolean wall = false;
	
	/** The width. */
	public static int width = 30; //asteroid width
	
	/** The rand. */
	private Random rand = new Random();

	
	/** The sound files. */
	private String[] soundFiles = {"boomb","boome","boomr","boomj","boomt","boom_word","boom_big","adios","bubye","bye","seeya"};
	
	/**
	 * Gets the draw color.
	 *
	 * @return the draw color
	 */
	public Color getDrawColor() {
		return drawColor;
	}

	/** The draw color. */
	private Color drawColor = Color.RED;

	/**
	 * Instantiates a new asteroid.
	 *
	 * @param pose the pose
	 * @param speed the speed
	 */
	public Asteroid(Point.Double pose, int speed) {
		super(pose);
		
		try {
		    File pathToFile = new File("assets/asteroid/asteroid.png");
		    texture = ImageIO.read(pathToFile);
		} catch (IOException ex) {
		    ex.printStackTrace();
		}

		// create ship shape
		Path2D.Double asteroidShape = new Path2D.Double();
		asteroidShape.moveTo(0, 10);
		asteroidShape.lineTo(10, 0);
		asteroidShape.lineTo(20, 0);
		asteroidShape.lineTo(30, 10);
		asteroidShape.lineTo(30, 20);
		asteroidShape.lineTo(20, 30);
		asteroidShape.lineTo(10, 30);
		asteroidShape.lineTo(0, 20);
		setShape(asteroidShape);

		dy = speed;
		
		//randomly set rotation direction, rotation speed is relative to dy
		if (rand.nextInt(2)%2 == 0) {
			dr = -0.015 * dy;
		}
		else {
			dr = 0.015* dy;
		}
		
		
		
		setSound("assets/asteroid/"+soundFiles[rand.nextInt(11)]+".wav", 1);
	}
	

}
