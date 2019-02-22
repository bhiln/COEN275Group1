package Stars;

import java.awt.Point;
import java.awt.Polygon;
import java.util.Random;

import Game.SpaceObject;

public class Star extends SpaceObject {
	
	Random rand = new Random();

	public Star(Point pose, int speed) {
		super(pose);
		
		// create ship shape
		Polygon starShape = new Polygon();
		starShape.addPoint(0, 0);
		starShape.addPoint(0, 2);
		starShape.addPoint(2, 2);
		starShape.addPoint(2, 0);
		starShape.addPoint(0, 0);
		setShape(starShape);
		
		moveX(pose.x);
		moveY(pose.y);
				
		dy = speed;
		width = 2;
	}

}