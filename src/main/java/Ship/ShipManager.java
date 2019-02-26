package Ship;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Area;
import java.util.ArrayList;

import Asteroids.Asteroid;
import Game.SpaceManager;

public class ShipManager implements SpaceManager {

	private Ship myShip;
	
	final Color drawColor = Color.CYAN;
	
	public void Create(Point pose) {
		int speed = 1;
		int initialHealth = 10;
		myShip = new Ship(pose, speed, initialHealth);
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
		g.setColor(drawColor);
		g.fillPolygon(myShip.getShape());
	}
	
	
	//needed to get ship health for the top bar
	public int getShipHealth() {
		return myShip.health;
	}
	
	// collision detector
	public boolean detectCollisions(ArrayList<Asteroid> asteroids){
		for (Asteroid a : asteroids) {
			Area asteroidArea = new Area(a.getShape());
			Area intersectionArea = new Area(myShip.getShape());
			intersectionArea.intersect(asteroidArea);
			
			//collision detected
			if (!intersectionArea.isEmpty()) {
			
				//how much damage each asteroid does
				myShip.decreaseHealth(5);
				
				//check if ship is destroyed
				if (myShip.health <= 0){
					return true;
				}
				else {
					asteroids.remove(a);
					return false;
				}
				
			}
		}
		return false;
	}
}
