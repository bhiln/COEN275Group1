package Projectiles;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.util.Random;

import Game.SpaceObject;

public class Bullet extends SpaceObject {

	public Color getDrawColor() {
		return drawColor;
	}

	private Color drawColor = Color.YELLOW;
	static public int RELOAD_TIME_MS = 200;
	
	private Random rand = new Random();
	private String[] soundFiles = {"pew","pewb","pewe","pewr","pewj","pewt","pew_word"};
	
	public Bullet(Point.Double pose, int speed, double lateralSpeed) {
		super(pose);
		
		// create ship shape
		Path2D.Double bulletShape = new Path2D.Double();
		bulletShape.moveTo(0, 0);
		bulletShape.lineTo(0, 20);
		bulletShape.lineTo(5, 20);
		bulletShape.lineTo(5, 0);
		bulletShape.lineTo(0, 0);
		setShape(bulletShape);

		dy = speed;
		dx = lateralSpeed;
		width = 5;
		
		setSound("assets/bullet/"+soundFiles[rand.nextInt(7)]+".wav", 1);
	}
}
