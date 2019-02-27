package Ship;

import java.awt.Point;
import java.awt.Polygon;

import Game.SpaceObject;

public class Ship extends SpaceObject{
	
	public Ship(Point pose, int speed) {
		
		super(pose);
		
		// create ship shape
		Polygon shipShape = new Polygon();
		shipShape.addPoint(0, 20);
		shipShape.addPoint(10, 0);
		shipShape.addPoint(20, 20);
		shipShape.addPoint(10, 10);
		shipShape.addPoint(0, 20);
		setShape(shipShape);
		
		width = 20; // ship widths
		
		moveX(pose.x);
		moveY(pose.y);
		
		dx = speed;
	}
}