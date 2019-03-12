package Game;

import java.awt.Image;
import java.awt.Point;

import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Random;

public abstract class SpaceObject {

	private Point.Double pose; // object location
	private Double rotation = 0.0; //objects rotation
	
	private Path2D.Double shape; // object shape
	public int width = 0; // object width
	
	
	protected Image texture;
	private Sound sound;

	public double dr = 0; //increment amount of rotation
	public double dx = 0; // increment amount (x coord)
	public double dy = 0; // increment amount (y coord)

	private int health = 0; // health of object, left here in superclass in case we want to make asteroids
							// have health for shooting later

	public SpaceObject(Point.Double pose) {
		this.pose = pose;
	}

	public void setRotation(Double theta) {
		this.rotation = theta;
	}
	
	public Double getRotation() {
		return rotation;
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
	
	public Image getTexture() {
		return texture;
	}

	public int getHealth() {
		return health;
	}

	
	public void rotate(double angle) {
		rotation += angle;
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
	
	public void setSound(String filename, double rate) {
		 // initialize a new Thread using Counter as the task
        sound = new Sound(filename);
	}
	
	public void playSound() {
		try {
			Thread soundThread = new Thread(sound);
			soundThread.start();
		}
		catch(Exception e) {
			System.out.println("SOUND ERROR");
		}
	}
}
