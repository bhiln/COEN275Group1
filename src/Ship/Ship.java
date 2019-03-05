package Ship;

import java.awt.*;
import java.awt.geom.Path2D;

import Game.SpaceObject;

public class Ship extends SpaceObject {

	private Color drawColor = Color.CYAN;
	private double maxSpeed = 5;
	private double acceleration = .5;
	public Ship(Point.Double pose, double speed) {

		super(pose);
		// create ship shape
		Path2D.Double shipShape = new Path2D.Double();
		shipShape.moveTo(0, 20);
		shipShape.lineTo(10, 0);
		shipShape.lineTo(20, 20);
		shipShape.lineTo(10, 10);
		shipShape.lineTo(0, 20);
		setShape(shipShape);

		width = 20; // ship widths




		dx = speed;

		setHealth(10);
	}
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

	public Ship(Point.Double pose, double speed, int initialHealth) {
		this(pose, speed);
		setHealth(initialHealth);
	}

	public Color getDrawColor() {
		return drawColor;
	}
}
