package Asteroids;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class AsteroidManager {

	private ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
	Random rand = new Random();
	
	public void createAsteroid(int width) {
		int xLoc = rand.nextInt(width);
		int speed = 1;//rand.nextInt(2);
		asteroidList.add(new Asteroid(xLoc, speed));
	}
	
	public int updateAsteroids(int width, int height) {
		ArrayList<Asteroid> toRemove = new ArrayList<Asteroid>();
		for (Asteroid myAsteroid : asteroidList) {
			// check for boundaries
			if (myAsteroid.x < myAsteroid.radius) myAsteroid.dx = Math.abs(myAsteroid.dx);
			if (myAsteroid.x > width - myAsteroid.radius) myAsteroid.dx = -Math.abs(myAsteroid.dx);
			if (myAsteroid.y > height + myAsteroid.radius) {// - myAsteroid.radius) myAsteroid.dy = -Math.abs(myAsteroid.dy);
				toRemove.add(myAsteroid);
			}
			// adjust ball position
			myAsteroid.y += myAsteroid.dy;
		}
		
		for (Asteroid removeAsteroid : toRemove) {
			asteroidList.remove(removeAsteroid);
		}
		
		int removeSize = toRemove.size();
		toRemove.clear();
		return removeSize;
	}
	
	public void drawAsteroids(Graphics g) {
		for (Asteroid myAsteroid : asteroidList) {
			g.fillOval((int)((myAsteroid.x - myAsteroid.radius) * myAsteroid.scale), (int)((myAsteroid.y - myAsteroid.radius) * myAsteroid.scale), myAsteroid.radius*2, myAsteroid.radius*2);
		}
	}
}
