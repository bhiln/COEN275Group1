package Asteroids;

import java.awt.Point;
import java.awt.Polygon;

import Game.SpaceObject;

public class Asteroid extends SpaceObject {
	
	public double scale = 1;
	
	public Asteroid(Point pose, int speed) {
		super(pose);
		
		// create ship shape
		Polygon asteroidShape = new Polygon();
		asteroidShape.addPoint(0, 10);
		asteroidShape.addPoint(10, 0);
		asteroidShape.addPoint(20, 0);
		asteroidShape.addPoint(30, 10);
		asteroidShape.addPoint(30, 20);
		asteroidShape.addPoint(20, 30);
		asteroidShape.addPoint(10, 30);
		asteroidShape.addPoint(0, 20);
		setShape(asteroidShape);
		
		moveX(pose.x);
		moveY(pose.y);
				
		dy = speed;
		width = 15*2;
	}
}
