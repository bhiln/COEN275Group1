package Asteroids;

import Game.Position;
import Game.SpaceObject;

public class Asteroid extends SpaceObject {
	
	public double scale = 1;
	
	public Asteroid(Position pose, int speed) {
		super(pose);
		
		// create ship shape
		xVerts = new int[]{0,10,20,30,30,20,10,0};
		yVerts = new int[]{10,0,0,10,20,30,30,20};
		
		moveX(pose.x);
		moveY(pose.y);
				
		dy = speed;
		width = 15*2;
	}
}
