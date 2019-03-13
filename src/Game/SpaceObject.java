package Game;

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

/**
 * The Class SpaceObject.
 */
public abstract class SpaceObject {

	/** The pose. */
	private Point.Double pose; // object location
	
	/** The rotation. */
	private Double rotation = 0.0; //objects rotation
	
	/** The shape. */
	private Path2D.Double shape; // object shape
	
	/** The width. */
	public int width = 0; // object width
	
	
	/** The texture. */
	protected Image texture;
	
	/** The sound. */
	private Sound sound;

	/** The dr. */
	public double dr = 0; //increment amount of rotation
	
	/** The dx. */
	public double dx = 0; // increment amount (x coord)
	
	/** The dy. */
	public double dy = 0; // increment amount (y cord)

	/** The health. */
	private int health = 0; // health of object, left here in superclass in case we want to make asteroids
	
	/** The collision damage. */
	private int collisionDamage = 0; //health removed everytime the ship is hit
	
	/** The ammo. */
	private int ammo = 0; // ammo the ship has
	
	/** The level increase ammo. */
	private int levelIncreaseAmmo = 0; // ammo given per level increase

	/**
	 * Instantiates a new space object.
	 *
	 * @param pose the pose
	 */
	public SpaceObject(Point.Double pose) {
		this.pose = pose;
	}

	/**
	 * Sets the rotation.
	 *
	 * @param theta the new rotation
	 */
	public void setRotation(Double theta) {
		this.rotation = theta;
	}
	
	/**
	 * Gets the rotation.
	 *
	 * @return the rotation
	 */
	public Double getRotation() {
		return rotation;
	}
	
	/**
	 * Sets the shape.
	 *
	 * @param shape the new shape
	 */
	public void setShape(Path2D.Double shape) {
		this.shape = shape;
		AffineTransform translation = new AffineTransform();
		translation.translate(pose.x,pose.y);
		shape.transform(translation);
	}

	/**
	 * Gets the shape.
	 *
	 * @return the shape
	 */
	public Path2D getShape() {
		return shape;
	}

	/**
	 * Sets the position.
	 *
	 * @param pose the new position
	 */
	public void setPosition(Point.Double pose) {
		this.pose = pose;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Point.Double getPosition() {
		return pose;
	}

	/**
	 * Sets the health.
	 *
	 * @param newHealth the new health
	 */
	public void setHealth(int newHealth) {
		health = newHealth;
	}
	
	/**
	 * Gets the texture.
	 *
	 * @return the texture
	 */
	public Image getTexture() {
		return texture;
	}

	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Gets the ammo.
	 *
	 * @return the ammo
	 */
	public int getAmmo() {
		return ammo;
	}
	
	/**
	 * Sets the ammo.
	 *
	 * @param newAmmo the new ammo
	 */
	public void setAmmo(int newAmmo) {
		ammo = newAmmo;
	}
	
	/**
	 * Use ammo.
	 */
	public void useAmmo() {
		ammo -= 1;
	}
	
	/**
	 * Sets the collision damage.
	 *
	 * @param damageDone the new collision damage
	 */
	public void setCollisionDamage(int damageDone) {
		collisionDamage = damageDone;
	}
	
	/**
	 * Gets the collision damage.
	 *
	 * @return the collision damage
	 */
	public int getCollisionDamage() {
		return collisionDamage;
	}
	
	/**
	 * Gets the level increase ammo.
	 *
	 * @return the level increase ammo
	 */
	public int getLevelIncreaseAmmo() {
		return levelIncreaseAmmo;
	}
	
	/**
	 * Sets the level increase ammo.
	 *
	 * @param ammoIncrease the new level increase ammo
	 */
	public void setLevelIncreaseAmmo(int ammoIncrease) {
		levelIncreaseAmmo = ammoIncrease;
	}


	
	/**
	 * Rotate.
	 *
	 * @param angle the angle
	 */
	public void rotate(double angle) {
		rotation += angle;
	}

	/**
	 * Move X.
	 *
	 * @param pixels the pixels
	 */
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

	/**
	 * Move Y.
	 *
	 * @param pixels the pixels
	 */
	public void moveY(double pixels) {
		pose.y += pixels;
//		for (int i = 0; i < shape.npoints; ++i) {
//			shape.ypoints[i] += (int)pixels;
//		}
		AffineTransform translation = new AffineTransform();
		translation.translate(0,pixels);
		shape.transform(translation);
	}
	
	/**
	 * Sets the sound.
	 *
	 * @param filename the filename
	 * @param rate the rate
	 */
	public void setSound(String filename, double rate) {
		 // initialize a new Thread using Counter as the task
        sound = new Sound(filename);
	}
	
	/**
	 * Play sound.
	 */
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
