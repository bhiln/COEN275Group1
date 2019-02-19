package Asteroids;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import Game.Position;
import Game.SpaceManager;

public class AsteroidManager implements SpaceManager {

	private ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
	Random rand = new Random();
		
	// create an asteroid with random x location
	public void Create(Position bounds) {
		int speed = 1;//rand.nextInt(2);
		
		Position pose = new Position(rand.nextInt(bounds.x), bounds.y);
		
		// add asteroid to tracked asteroid list
		asteroidList.add(new Asteroid(pose, speed));
	}
	
	// update location of all tracked asteroids
	public int Update(int width, int height) {
		ArrayList<Asteroid> toRemove = new ArrayList<Asteroid>();
		for (Asteroid myAsteroid : asteroidList) {
			// check for boundaries
			// TODO: dx is set to 0 for now, asteroids fall straight down
			if (myAsteroid.getPosition().x < myAsteroid.width/2) myAsteroid.dx = Math.abs(myAsteroid.dx);
			if (myAsteroid.getPosition().x > width - myAsteroid.width/2) myAsteroid.dx = -Math.abs(myAsteroid.dx);
			
			// if asteroid is below bottom of frame, prepare to remove from tracked list
			if (myAsteroid.getPosition().y > height + myAsteroid.width/2) {
				toRemove.add(myAsteroid);
			}
			
			// adjust asteroid position
			myAsteroid.moveX(myAsteroid.dx);
			myAsteroid.moveY(myAsteroid.dy);
		}
		
		// remove asteroid from tracked list
		for (Asteroid removeAsteroid : toRemove) {
			asteroidList.remove(removeAsteroid);
		}
		
		int removeSize = toRemove.size();
		toRemove.clear();
		return removeSize;
	}
	
	public void Draw(Graphics g) {
		for (Asteroid myAsteroid : asteroidList) {
			g.fillPolygon(myAsteroid.xVerts, myAsteroid.yVerts, myAsteroid.xVerts.length);
		}
	}
}
