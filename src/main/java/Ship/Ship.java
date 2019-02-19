package Ship;

import Game.Position;
import Game.SpaceObject;

public class Ship extends SpaceObject{
	
	public Ship(Position pose, int speed) {
		
		super(pose);
		
		// create ship shape
		xVerts = new int[]{0,10,20,10,0};
		yVerts = new int[]{20,0,20,10,20};
		width = 20; // ship widths
		
		moveX(pose.x);
		moveY(pose.y);
		
		dx = speed;
	}
}
