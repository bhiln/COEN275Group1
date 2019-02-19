package Ship;

import Game.SpaceObject;

public class Ship extends SpaceObject{
	
	public Ship(int xLoc, int speed) {
		y = 500;
		xVerts = new int[]{0,10,20,10,0};
		yVerts = new int[]{20+y,0+y,20+y,10+y,20+y};
		width = 20; // ship widths
		for (int i = 0; i < xVerts.length; ++i) {
			xVerts[i] += xLoc;
		}
		dx = speed;
	}
}
