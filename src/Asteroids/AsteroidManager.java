package Asteroids;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import Ship.Ship;

public class AsteroidManager {

	private ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
	Random rand = new Random();
	
	// create an asteroid with random x location
	public void createAsteroid(int width) {
		int xLoc = rand.nextInt(width);
		int speed = 1;//rand.nextInt(2);
		
		// add asteroid to tracked asteroid list
		asteroidList.add(new Asteroid(xLoc, speed));
	}
	
	// update location of all tracked asteroids
	public int updateAsteroids(int width, int height) {
		ArrayList<Asteroid> toRemove = new ArrayList<Asteroid>();
		for (Asteroid myAsteroid : asteroidList) {
			// check for boundaries
			// TODO: dx is set to 0 for now, asteroids fall straight down
			if (myAsteroid.x < myAsteroid.radius) myAsteroid.dx = Math.abs(myAsteroid.dx);
			if (myAsteroid.x > width - myAsteroid.radius) myAsteroid.dx = -Math.abs(myAsteroid.dx);
			
			// if asteroid is below bottom of frame, prepare to remove from tracked list
			if (myAsteroid.y > height + myAsteroid.radius) {
				toRemove.add(myAsteroid);
			}
			
			// adjust asteroid position
			myAsteroid.x += myAsteroid.dx;
			myAsteroid.y += myAsteroid.dy;
		}
		
		// remove asteroid from tracked list
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
