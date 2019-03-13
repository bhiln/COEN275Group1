package Asteroids;

import java.awt.Point;
import java.util.ArrayList;

/**
 * The Class AsteroidWall.
 */
public class AsteroidWall extends ArrayList<Asteroid>{

	/** The model. */
	Asteroid model = new Asteroid(new Point.Double(0,0),0);
	
	/** The gap. */
	int gap = 2;
	
	/**
	 * Instantiates a new asteroid wall.
	 *
	 * @param missingPosition the missing position
	 * @param level the level
	 */
	public AsteroidWall(int missingPosition, int level) {
		
		for (int i = 0; i < missingPosition; ++i) {
			Asteroid tempAsteroid = new Asteroid(new Point.Double (i*model.width+i*gap,0),1);
			tempAsteroid.wall = true;
			add(tempAsteroid);
		}
		for (int i = missingPosition+10-level; i < 40; ++i) {
			Asteroid tempAsteroid = new Asteroid(new Point.Double (i*model.width+i*gap,0),1);
			tempAsteroid.wall = true;
			add(tempAsteroid);
		}
	}
}
