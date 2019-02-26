package Game;

import java.awt.Point;

import java.awt.Polygon;

public abstract class SpaceObject {

	private Point pose; // object location
	private Polygon shape; // object shape
	public int width = 0; // object width

	public int dx = 0; // increment amount (x coord)
	public int dy = 0; // increment amount (y coord)
	
	public int health = 0; //health of object, left here in superclass in case we want to make asteroids have health for shooting later
	
	public SpaceObject(Point pose) {
		this.pose = pose;
	}
	
	public void setShape(Polygon shape) {
		this.shape = shape;
	}
	
	public Polygon getShape() {
		return shape;
	}
	
	
	
	public void setPosition(Point pose) {
		this.pose = pose;
	}
	
	public Point getPosition() {
		return pose;
	}
	
	public void decreaseHealth(int damageInflicted) {
		health -= damageInflicted;
	}
	
	public void moveX(int pixels) {
		pose.x += pixels;
		for (int i = 0; i < shape.npoints; ++i) {
			shape.xpoints[i] += pixels;
		}
	}
	
	public void moveY(int pixels) {
		pose.y += pixels;
		for (int i = 0; i < shape.npoints; ++i) {
			shape.ypoints[i] += pixels;
		}
	}
}
