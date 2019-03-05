package Game;

import Asteroids.Asteroid;
import Ship.Ship;
import Stars.Star;
import java.awt.Point;

import java.util.ArrayList;

public class GameState {

	private enum State{
		MENU, GAME, PAUSED, DEATH, WIN, EXIT
	}
	
	private State gameState;

	public int level;

	private ArrayList<Asteroid> asteroids;
	private ArrayList<Star> stars;
	private Ship ship;

	public int lastAsteroidIter = 0;// count how many frames passed since last asteroid was created
	public int dodgeCount = 0;

	public GameState(Game game) {
		game = game;
		gameState = State.MENU;
		level = 0;
		asteroids = new ArrayList<Asteroid>();
		stars = new ArrayList<Star>();

		ship = new Ship(new Point(game.getSize().width / 2, (int) (game.getSize().height * 0.8)), 2);
	}

	private void clearState() {
		asteroids.clear();
		stars.clear();
		ship = new Ship(new Point(0, 0), 0);
	}

	public State getState() {
		return gameState;
	}

	public Ship getShip() {
		return this.ship;
	}

	public ArrayList<Asteroid> getAsteroids() {
		return asteroids;
	}

	public ArrayList<Star> getStars() {
		return stars;
	}

	public int getLevel() {
		return level;
	}

	// state manager, mirrors the one in game

	// starts a new game
	public void startGame() {
		gameState = State.GAME;
	}

	// if game is paused, resume game
	public void resumeGame() {
		gameState = State.GAME;
	}

	// game has been won, switch to win state
	public void finishGame() {
		gameState = State.WIN;
	}

	// game has been lost, switch to lose state
	public void endGame() {
		gameState = State.DEATH;
	}

	// return to menu
	public void exitGame() {
		gameState = State.EXIT;
	}
}
