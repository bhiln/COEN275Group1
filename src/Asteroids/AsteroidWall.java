package Asteroids;

import java.awt.Point;
import java.util.ArrayList;

public class AsteroidWall extends ArrayList<Asteroid>{

	Asteroid model = new Asteroid(new Point(0,0),0);
	int gap = 2;
	
	public AsteroidWall(int missingPosition, int level) {
		
		for (int i = 0; i < missingPosition; ++i) {
			Asteroid tempAsteroid = new Asteroid(new Point (i*model.width+i*gap,0),1);
			tempAsteroid.wall = true;
			add(tempAsteroid);
		}
		for (int i = missingPosition+10-level; i < 40; ++i) {
			Asteroid tempAsteroid = new Asteroid(new Point (i*model.width+i*gap,0),1);
			tempAsteroid.wall = true;
			add(tempAsteroid);
		}
	}
}
