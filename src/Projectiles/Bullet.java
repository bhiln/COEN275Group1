package Projectiles;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Path2D;

import Game.SpaceObject;

public class Bullet extends SpaceObject {

	public Color getDrawColor() {
		return drawColor;
	}

	private Color drawColor = Color.YELLOW;
	static public final int RELOAD_TIME_MS = 250;
	
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
	}
}
