package Ship;

import java.awt.Graphics;
import java.util.ArrayList;

import Asteroids.Asteroid;

public class ShipManager {

	private Ship myShip;	
	
	public void createShip(int width) {
		int xLoc = width/2;
		int speed = 1;
		myShip = new Ship(xLoc, speed);
	}
	
	// update ship location based on width and height of the frame
	// TODO: update ship location based on user input
	public void updateShip(int width, int height) {
		// check for boundaries
		if (myShip.xVerts[0] == 0) myShip.dx = Math.abs(myShip.dx);
		if (myShip.xVerts[0] + myShip.width > width) myShip.dx = -Math.abs(myShip.dx);
		
		// adjust ship position		
		for (int i = 0; i < myShip.xVerts.length; ++i) {
			myShip.xVerts[i] += myShip.dx;
		}
	}
	
	// draw ship polygon
	public void drawShip(Graphics g) {
		g.fillPolygon(myShip.xVerts, myShip.yVerts, myShip.xVerts.length);
	}
	
	// TODO: write collision detector
	public ArrayList<Asteroid> detectCollisions(ArrayList<Asteroid> asteroids){
		return new ArrayList<Asteroid>();
	}
}
