package Ship;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Area;
import java.util.ArrayList;

import Asteroids.Asteroid;
import Game.SpaceManager;

public class ShipManager implements SpaceManager {

	private Ship myShip;	
	
	public void Create(Point pose) {
		int speed = 1;
		myShip = new Ship(pose, speed);
	}
	
	// update ship location based on width and height of the frame
	// TODO: update ship location based on user input
	public int Update(int width, int height) {
		// check for boundaries
		if (myShip.getShape().xpoints[0] == 0) myShip.dx = Math.abs(myShip.dx);
		if (myShip.getShape().xpoints[0] + myShip.width > width) myShip.dx = -Math.abs(myShip.dx);
		
		// adjust ship position		
		myShip.moveX(myShip.dx);
		myShip.moveY(myShip.dy);
		
		return 0; // int return not applicable to Ship
	}
	
	// draw ship polygon
	public void Draw(Graphics g) {
		g.fillPolygon(myShip.getShape());
	}
	
	// collision detector
	public ArrayList<Asteroid> detectCollisions(ArrayList<Asteroid> asteroids){
		ArrayList<Asteroid> collisions = new ArrayList<Asteroid>();
		for (Asteroid a : asteroids) {
			Area asteroidArea = new Area(a.getShape());
			Area intersectionArea = new Area(myShip.getShape());
			intersectionArea.intersect(asteroidArea);
			
			if (!intersectionArea.isEmpty()) {
				collisions.add(a);
			}
		}
		return collisions;
	}
}
