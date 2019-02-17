package Ship;

import java.awt.Graphics;

import Asteroids.Asteroid;

public class ShipManager {

	Ship myShip;	
	
	public void createShip(int width) {
		int xLoc = width/2;
		int speed = 1;//rand.nextInt(2);
		myShip = new Ship(xLoc, speed);
	}
	
	public void updateShip(int width, int height) {
		// check for boundaries
		if (myShip.xVerts[0] == 0) myShip.dx = Math.abs(myShip.dx);
		if (myShip.xVerts[0] + myShip.width > width) myShip.dx = -Math.abs(myShip.dx);
		
		// adjust ship position		
		for (int i = 0; i < myShip.xVerts.length; ++i) {
			myShip.xVerts[i] += myShip.dx;
		}
	}
	
	public void drawShip(Graphics g) {
		g.fillPolygon(myShip.xVerts, myShip.yVerts, myShip.xVerts.length);
	}
}
