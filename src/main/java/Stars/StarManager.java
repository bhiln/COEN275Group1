package Stars;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import Game.SpaceManager;
import Stars.Star;

public class StarManager implements SpaceManager {

	private ArrayList<Star> starList = new ArrayList<Star>();
	Random rand = new Random();
	
	final Color drawColor = Color.LIGHT_GRAY;
		
	// create an asteroid with random x location
	public void Create(Point bounds) {
		int speed = rand.nextInt(5)+1;
		
		Point pose = new Point(rand.nextInt(bounds.x), bounds.y);
		
		// add asteroid to tracked asteroid list
		starList.add(new Star(pose, speed));
	}
	
	// update location of all tracked asteroids
	public int Update(int width, int height) {
		ArrayList<Star> toRemove = new ArrayList<Star>();
		for (Star myStar : starList) {
			// check for boundaries
			// TODO: dx is set to 0 for now, asteroids fall straight down
			if (myStar.getPosition().x < myStar.width/2) myStar.dx = Math.abs(myStar.dx);
			if (myStar.getPosition().x > width - myStar.width/2) myStar.dx = -Math.abs(myStar.dx);
			
			// if asteroid is below bottom of frame, prepare to remove from tracked list
			if (myStar.getPosition().y > height + myStar.width/2) {
				toRemove.add(myStar);
			}
			
			// adjust asteroid position
			myStar.moveX(myStar.dx);
			myStar.moveY(myStar.dy);
		}
		
		// remove asteroid from tracked list
		for (Star removeAsteroid : toRemove) {
			starList.remove(removeAsteroid);
		}
		
		int removeSize = toRemove.size();
		toRemove.clear();
		return removeSize;
	}
	
	public void Draw(Graphics g) {
		g.setColor(drawColor);
		for (Star myStar : starList) {
			g.fillPolygon(myStar.getShape());
		}
	}
	
	public ArrayList<Star> getAsteroids(){
		return starList;
	}
}
