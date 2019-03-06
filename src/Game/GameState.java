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

	public GameState(Game game) {
		this.game = game;
		gameState = State.MENU;
		resetState();
	}

	private void resetState() {
		level = 0;
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
		gameState = State.EXIT;
	}
	
	public void addBullet() {
		long curTime = System.currentTimeMillis();
		if (curTime-lastBulletTime >= Bullet.RELOAD_TIME_MS) {
			ship.laserSound();
			Point.Double shipPose = (Double) ship.getPosition().clone();
			shipPose.x += ship.width/2;
			bullets.add(new Bullet(shipPose, -10, ship.dx));
			System.out.println("ADDED BULLET: " + ship.getPosition().x + "," + ship.getPosition().y);
			System.out.println(bullets.size());
			lastBulletTime = curTime;
		}
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
}
