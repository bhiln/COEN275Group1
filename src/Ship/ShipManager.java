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
	
	public void updateShip(Graphics g, int width, int height) {
		// check for boundaries
		if (myShip.x < myShip.radius) myShip.dx = Math.abs(myShip.dx);
		if (myShip.x > width - myShip.radius) myShip.dx = -Math.abs(myShip.dx);

		// adjust ball position
		myShip.x += myShip.dx;
		g.fillOval(myShip.x - myShip.radius, myShip.y - myShip.radius, myShip.radius*2, myShip.radius*2);
	}
}
