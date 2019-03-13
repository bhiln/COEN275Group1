package Game;

import Asteroids.Asteroid;
import Projectiles.Bullet;
import Ship.Ship;
import Stars.Star;
import java.awt.Point;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

/**
 * The Class GameState.
 */
public class GameState {

	/**
	 * The Enum State.
	 */
	public enum State {
		
		/** The menu. */
		MENU, 
 /** The game. */
 GAME, 
 /** The paused. */
 PAUSED, 
 /** The death. */
 DEATH, 
 /** The win. */
 WIN, 
 /** The exit. */
 EXIT
	}
	
	/**
	 * The Enum Difficulty.
	 */
	public enum Difficulty {
		
		/** The easy. */
		EASY, 
 /** The medium. */
 MEDIUM, 
 /** The hard. */
 HARD
	}
	
	/** The game difficulty. */
	public Difficulty gameDifficulty = Difficulty.EASY;
	
	/** The game state. */
	private State gameState;
	
	/** The game. */
	private Game game;

	/** The level. */
	private int level;

	/** The asteroids. */
	private ArrayList<Asteroid> asteroids;
	
	/** The stars. */
	private ArrayList<Star> stars;
	
	/** The bullets. */
	private ArrayList<Bullet> bullets;
	
	/** The ship. */
	private Ship ship;

	/** The dodge count. */
	public int lastAsteroidIter, dodgeCount;

	/** The start time. */
	private long startTime;
	
	/** The time alive. */
	private long timeAlive = 0L;
	
	/** The last bullet time. */
	private long lastBulletTime = 0L;

	/**
	 * Instantiates a new game state.
	 *
	 * @param game the game
	 */
	public GameState(Game game) {
		this.game = game;
		gameState = State.MENU;
		resetState();
	}

	/**
	 * Reset state.
	 */
	private void resetState() {
		level = 0;
		lastAsteroidIter = 0;
		startTime = 0L;
		dodgeCount = 0;
		asteroids = new ArrayList<Asteroid>();
		stars = new ArrayList<Star>();
		bullets = new ArrayList<Bullet>();

		ship = new Ship(new Point.Double(game.getSize().width / 2, (int) (game.getSize().height * 0.8)), 2);
		
		if (gameDifficulty == Difficulty.HARD) {
			proDifficulty();
		}
		else if (gameDifficulty == Difficulty.MEDIUM) {
			regularDifficulty();
		}
		else {
			beginnerDifficulty();
		}
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public State getState() {
		return gameState;
	}

	/**
	 * Gets the ship.
	 *
	 * @return the ship
	 */
	public Ship getShip() {
		return this.ship;
	}

	/**
	 * Gets the asteroids.
	 *
	 * @return the asteroids
	 */
	public ArrayList<Asteroid> getAsteroids() {
		return asteroids;
	}

	/**
	 * Gets the stars.
	 *
	 * @return the stars
	 */
	public ArrayList<Star> getStars() {
		return stars;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Gets the time alive.
	 *
	 * @return the time alive
	 */
	public long getTimeAlive() {
		return timeAlive;
	}

	/**
	 * Sets the time alive.
	 *
	 * @param time the new time alive
	 */
	public void setTimeAlive(long time) {
		timeAlive = time;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public long getStartTime() {
		return startTime;
	}

	// state manager, mirrors the one in game

	/**
	 * Start game.
	 */
	// starts a new game
	public void startGame() {
		resetState();
		startTime = System.currentTimeMillis();
		gameState = State.GAME;
	}
	
	/**
	 * Pause game.
	 */
	public void pauseGame() {
		gameState = State.PAUSED;
	}

	/**
	 * Resume game.
	 */
	// if game is paused, resume game
	public void resumeGame() {
		startTime = System.currentTimeMillis()-timeAlive;
		gameState = State.GAME;
	}

	/**
	 * Finish game.
	 */
	// game has been won, switch to win state
	public void finishGame() {
		gameState = State.WIN;
	}

	/**
	 * End game.
	 */
	// game has been lost, switch to lose state
	public void endGame() {
		gameState = State.DEATH;
	}

	/**
	 * Exit game.
	 */
	// return to menu
	public void exitGame() {
		gameState = State.MENU;
	}
	
	/**
	 * Pro difficulty.
	 */
	public void proDifficulty() {
		ship.setHealth(100);
		ship.setCollisionDamage(25);
		ship.setLevelIncreaseAmmo(10);
		Bullet.RELOAD_TIME_MS = 500;
		ship.setAmmo(10);
		Physics.randomDyMax = 6;
		Physics.randomDyMin = 2;
		Physics.difficultyMult = 15;
	}
	
	/**
	 * Regular difficulty.
	 */
	public void regularDifficulty() {
		ship.setHealth(100);
		ship.setCollisionDamage(10);
		ship.setLevelIncreaseAmmo(25);
		Bullet.RELOAD_TIME_MS = 250;
		ship.setAmmo(25);
		Physics.randomDyMax = 4;
		Physics.randomDyMin = 1;
		Physics.difficultyMult = 10;
	}
	
	/**
	 * Beginner difficulty.
	 */
	public void beginnerDifficulty() {
		ship.setHealth(100);
		ship.setCollisionDamage(5);
		ship.setLevelIncreaseAmmo(50);
		Bullet.RELOAD_TIME_MS = 100;
		ship.setAmmo(100);
		Physics.randomDyMax = 3;
		Physics.randomDyMin = 1;
		Physics.difficultyMult = 5;
	}
	
	
	/**
	 * Adds the bullet.
	 */
	public void addBullet() {
		long curTime = System.currentTimeMillis();
		if ((curTime-lastBulletTime >= Bullet.RELOAD_TIME_MS) && (ship.getAmmo() > 0)) {
			ship.useAmmo();
			Point.Double shipPose = (Double) ship.getPosition().clone();
			shipPose.x += ship.width/2;
			Bullet bullet = new Bullet(shipPose, -10, ship.dx);
			bullets.add(bullet);
			lastBulletTime = curTime;
			bullet.playSound();
		}
	}
	
	/**
	 * Gets the bullets.
	 *
	 * @return the bullets
	 */
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
}
