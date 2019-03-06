package Asteroids;

import java.awt.*;
import java.awt.geom.Path2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.SpaceObject;

public class Asteroid extends SpaceObject {

	public double scale = 1;
	public boolean wall = false;
	private Image texture;
	
	public Color getDrawColor() {
		return drawColor;
	}

	private Color drawColor = Color.RED;

	public Asteroid(Point.Double pose, int speed) {
		super(pose);
		
		try {
		    File pathToFile = new File("assets/asteroid.png");
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
		width = 15 * 2;
	}
	
	public Image getTexture() {
		return texture;
	}

}
