package Game;

import Asteroids.Asteroid;
import Asteroids.AsteroidWall;
import Game.GameState.State;
import Ship.Ship;
import Stars.Star;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Random;

public class Physics implements Runnable, ActionListener {
	private Game game;
	private Timer timer;
	private final int delay = 20;
	private Random rand = new Random();
	private KeyInput input;
	public Physics(Game game, KeyInput input) {
		this.game = game;
		this.input = input;
	}

	public void run() {
		timer = new Timer(delay, this);
		timer.start(); // start the timer
	}

	public void actionPerformed(ActionEvent e) {
		if (this.game.getState().getState() == State.GAME) {
			update();
		}
	}

	private void checkBoundaries() {

	}

	private void update() {
		int width = game.getSize().width;
		int height = game.getSize().height;

		Ship ship = this.game.getState().getShip();

		int forceX = 0;
		int forceY = 0;
		if(input.getKey("Left")){

			forceX = -1;
		}
		else if(input.getKey("Right")){

			forceX = 1;
		}
		if(input.getKey("Up")){

			forceY = -1;
		}
		else if(input.getKey("Down")){

			forceY = 1;
		}

		ship.applyForce(forceX,forceY);

		if (ship.getPosition().x + ship.dx < 0)
			ship.dx = Math.abs(ship.dx);
		else if (ship.getPosition().x + ship.width + ship.dx > game.getSize().width)
			ship.dx = -Math.abs(ship.dx);
		if (ship.getPosition().y + ship.dy < game.getSize().height /2)
			ship.dy = Math.abs(ship.dy)/10;
		else if (ship.getPosition().y + ship.width*2 + ship.dy > game.getSize().height)
			ship.dy = -Math.abs(ship.dy)/10;

		// adjust ship position
		ship.moveX(ship.dx);
		ship.moveY(ship.dy);

		ArrayList<Star> stars = this.game.getState().getStars();
		ArrayList<Star> toRemove = new ArrayList<Star>();

		for (Star s : stars) {
			// check for boundaries
			// TODO: dx is set to 0 for now, asteroids fall straight down
			if (s.getPosition().x < s.width / 2)
				s.dx = Math.abs(s.dx);
			if (s.getPosition().x > width - s.width / 2)
				s.dx = -Math.abs(s.dx);

			// if asteroid is below bottom of frame, prepare to remove from tracked list
			if (s.getPosition().y > height + s.width / 2) {
				toRemove.add(s);
			}

			// adjust asteroid position
			s.moveX(s.dx);
			s.moveY(s.dy);
		}

		// remove asteroid from tracked list
		for (Star removeAsteroid : toRemove) {
			stars.remove(removeAsteroid);
		}

		if (rand.nextInt(1000) > 950) {
			int speed = rand.nextInt(5) + 1;
			Point.Double pose = new Point.Double(rand.nextInt(game.getSize().width), 0);
			stars.add(new Star(pose, speed));
		}

		ArrayList<Asteroid> asteroids = this.game.getState().getAsteroids();

		if (rand.nextInt(1000) > 1000 - this.game.getState().getLevel() * 5 || this.game.getState().lastAsteroidIter > 200 - this.game.getState().getLevel() * 5) {
			Point asteroidBounds = new Point(width, 0);
			int speed = rand.nextInt(3) + 1;
			Point.Double pose = new Point.Double(rand.nextInt(width), 0);
			asteroids.add(new Asteroid(pose, speed));
			this.game.getState().lastAsteroidIter = 0;
		}
		this.game.getState().lastAsteroidIter++;

		ArrayList<Asteroid> AsteroidsToRemove = new ArrayList<Asteroid>();
		for (Asteroid myAsteroid : asteroids) {
			// check for boundaries
			// TODO: dx is set to 0 for now, asteroids fall straight down
			if (myAsteroid.getPosition().x < myAsteroid.width / 2)
				myAsteroid.dx = Math.abs(myAsteroid.dx);
			if (myAsteroid.getPosition().x > width - myAsteroid.width / 2)
				myAsteroid.dx = -Math.abs(myAsteroid.dx);

			// if asteroid is below bottom of frame, prepare to remove from tracked list
			if (myAsteroid.getPosition().y > height + myAsteroid.width / 2) {
				AsteroidsToRemove.add(myAsteroid);
			}

			// adjust asteroid position
			myAsteroid.moveX((int)myAsteroid.dx);
			myAsteroid.moveY((int)myAsteroid.dy);
		}

		// remove asteroid from tracked list
		boolean passedWall = false;
		for (Asteroid removeAsteroid : AsteroidsToRemove) {
			asteroids.remove(removeAsteroid);
			if (!removeAsteroid.wall) {
				this.game.getState().dodgeCount++;
				game.evaluateWall();
			}
			else {
				passedWall = true;
			}
		}
		
		if (passedWall) {
			game.passLevel();
		}

		AsteroidsToRemove.clear();
		AsteroidsToRemove = detectCollisions(ship, asteroids);
		if (ship.getHealth() <= 0) {
			
//            game.setBackground(Color.ORANGE);
			game.endGame();
		} else {
			// remove asteroid from tracked list
			for (Asteroid removeAsteroid : AsteroidsToRemove) {
				asteroids.remove(removeAsteroid);
			}
		}
	}

	private ArrayList<Asteroid> detectCollisions(Ship ship, ArrayList<Asteroid> asteroids) {
		ArrayList<Asteroid> collisions = new ArrayList<Asteroid>();
		ArrayList<Asteroid> AsteroidsToRemove = new ArrayList<Asteroid>();
		for (Asteroid a : asteroids) {
			Area asteroidArea = new Area(a.getShape());
			Area intersectionArea = new Area(ship.getShape());
			intersectionArea.intersect(asteroidArea);

			if (!intersectionArea.isEmpty()) {
				collisions.add(a);
				ship.setHealth(ship.getHealth() - 5);
				AsteroidsToRemove.add(a);
			}
		}

		return AsteroidsToRemove;
	}

	public void restartTimer() {
		timer.restart();
	}

	public void stopTimer() {
		timer.stop();
	}
}
