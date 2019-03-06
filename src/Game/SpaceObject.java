package Game;

import java.awt.Point;

import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public abstract class SpaceObject {

	private Point.Double pose; // object location
	private Path2D.Double shape; // object shape
	public int width = 0; // object width



	public double dx = 0; // increment amount (x coord)
	public double dy = 0; // increment amount (y coord)

	private int health = 0; // health of object, left here in superclass in case we want to make asteroids
							// have health for shooting later

	public SpaceObject(Point.Double pose) {
		this.pose = pose;
	}

	public void setShape(Path2D.Double shape) {
		this.shape = shape;
		AffineTransform translation = new AffineTransform();
		translation.translate(pose.x,pose.y);
		shape.transform(translation);
	}

	public Path2D getShape() {
		return shape;
	}

	public void setPosition(Point.Double pose) {
		this.pose = pose;
	}

	public Point.Double getPosition() {
		return pose;
	}

	public void setHealth(int newHealth) {
		health = newHealth;
	}

	public int getHealth() {
		return health;
	}


	public void moveX(double pixels) {
		//System.out.println("pixels" + pixels);
		pose.x += pixels;
//		for (int i = 0; i < shape.npoints; ++i) {
//			shape.xpoints[i] += (int)pixels;
//		}
		AffineTransform translation = new AffineTransform();
		translation.translate(pixels,0);
		shape.transform(translation);
	}

	public void moveY(double pixels) {
		pose.y += pixels;
//		for (int i = 0; i < shape.npoints; ++i) {
//			shape.ypoints[i] += (int)pixels;
//		}
		AffineTransform translation = new AffineTransform();
		translation.translate(0,pixels);
		shape.transform(translation);
	}
}
