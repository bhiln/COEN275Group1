package Asteroids;

import java.awt.*;
import java.awt.geom.Path2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Game.SpaceObject;

public class Asteroid extends SpaceObject {

	public double scale = 1;
	public boolean wall = false;
	private Random rand = new Random();
	private Image texture;
	
	private String[] soundFiles = {"boomb","boome","boomr","boomj","boomt","boom_word","boom_big","adios","bubye","bye","seeya"};
	
	public Color getDrawColor() {
		return drawColor;
	}

	private Color drawColor = Color.RED;

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
		
		
		width = 15 * 2;
		
		setSound("assets/asteroid/"+soundFiles[rand.nextInt(11)]+".wav", 1);
	}
	
	public Image getTexture() {
		return texture;
	}

}
