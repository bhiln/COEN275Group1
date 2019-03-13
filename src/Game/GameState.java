package Game;

import Asteroids.Asteroid;
import Projectiles.Bullet;
import Ship.Ship;
import Stars.Star;
import java.awt.Point;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class GameState {

	public enum State {
		MENU, GAME, PAUSED, DEATH, WIN, EXIT
	}

	private State gameState;
	private Game game;

	private int level;

	private ArrayList<Asteroid> asteroids;
	private ArrayList<Star> stars;
	private ArrayList<Bullet> bullets;
	private Ship ship;

	public int lastAsteroidIter, dodgeCount;

	private long startTime;
	private long timeAlive = 0L;
	private long lastBulletTime = 0L;
	private long score = 0;
	private int difficulty = 100;

	public GameState(Game game) {
		this.game = game;
		gameState = State.MENU;
		resetState();
	}

	private void resetState() {
		level = 1;
		lastAsteroidIter = 0;
		startTime = 0L;
		dodgeCount = 0;
		asteroids = new ArrayList<Asteroid>();
		stars = new ArrayList<Star>();
		bullets = new ArrayList<Bullet>();

		ship = new Ship(new Point.Double(game.getSize().width / 2, (int) (game.getSize().height * 0.8)), 2);
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

	public void setLevel(int level) {
		this.level = level;
	}

	public long getTimeAlive() {
		return timeAlive;
	}

	public void setTimeAlive(long time) {
		timeAlive = time;
	}

	public long getStartTime() {
		return startTime;
	}

	// state manager, mirrors the one in game

	// starts a new game
	public void startGame() {
		resetState();
		startTime = System.currentTimeMillis();
		gameState = State.GAME;
	}
	
	public void pauseGame() {
		gameState = State.PAUSED;
	}

	// if game is paused, resume game
	public void resumeGame() {
		startTime = System.currentTimeMillis()-timeAlive;
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
		gameState = State.MENU;
	}
	
	public void proDifficulty() {
		ship.setHealth(100);
		ship.setCollisionDamage(25);
		ship.setLevelIncreaseAmmo(10);
		Bullet.RELOAD_TIME_MS = 500;
		ship.setAmmo(10);
		Physics.randomDyMax = 6;
		Physics.randomDyMin = 2;
		Physics.difficultyMult = 15;
		difficulty = 50;
	}
	
	public void regularDifficulty() {
		ship.setHealth(100);
		ship.setCollisionDamage(10);
		ship.setLevelIncreaseAmmo(25);
		Bullet.RELOAD_TIME_MS = 250;
		ship.setAmmo(25);
		Physics.randomDyMax = 4;
		Physics.randomDyMin = 1;
		Physics.difficultyMult = 10;
		difficulty = 75;
	}
	
	public void beginnerDifficulty() {
		ship.setHealth(100);
		ship.setCollisionDamage(5);
		ship.setLevelIncreaseAmmo(50);
		Bullet.RELOAD_TIME_MS = 100;
		ship.setAmmo(100);
		Physics.randomDyMax = 3;
		Physics.randomDyMin = 1;
		Physics.difficultyMult = 5;
		difficulty = 100;
	}
	
	
	public void addBullet() {
		long curTime = System.currentTimeMillis();
		if (curTime-lastBulletTime >= Bullet.RELOAD_TIME_MS) {
			Point.Double shipPose = (Double) ship.getPosition().clone();
			shipPose.x += ship.width/2;
			Bullet bullet = new Bullet(shipPose, -10, ship.dx);
			bullets.add(bullet);
			lastBulletTime = curTime;
			bullet.playSound();
		}
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	
	public long getScore() {
		return score;
	}
	
	public void setScore(long score) {
		this.score = score;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
