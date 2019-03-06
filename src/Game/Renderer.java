package Game;

import Asteroids.Asteroid;
import Projectiles.Bullet;
import Ship.Ship;
import Stars.Star;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Renderer extends JPanel implements ActionListener {
	private Game game;
	private GameState state;

	private int delay = 20;
	protected Timer timer;

	JLabel lblLevel, lblDodge, lblTimeAlive, lblHealth;
	
	KeyInput input;

	public Renderer(Game game, GameState state, KeyInput input) {
		this.game = game;
		this.state = state;
		this.input = input;

		timer = new Timer(delay, this);
		restartTimer();

		lblLevel = new JLabel("Level " + state.getLevel());
		lblLevel.setForeground(Color.WHITE);
		lblDodge = new JLabel("Dodged " + state.dodgeCount + " Asteroids!");
		lblDodge.setForeground(Color.WHITE);
		lblTimeAlive = new JLabel("Time alive: " + state.getTimeAlive());
		lblTimeAlive.setForeground(Color.WHITE);
		lblHealth = new JLabel("Health: ");
		lblHealth.setForeground(Color.GREEN);

		// add level and score labels to frame
		add(lblLevel, BorderLayout.NORTH);
		add(lblDodge, BorderLayout.NORTH);
		add(lblTimeAlive, BorderLayout.NORTH);
		add(lblHealth, BorderLayout.NORTH);

		// set background to dark gray
		setBackground(Color.DARK_GRAY);
	}

	public void actionPerformed(ActionEvent e) {
		// will run when the timer fires
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); // call superclass's paintComponent
		Graphics2D g2d = (Graphics2D) g.create();

		// calculate time alive. Only update label if it's a new second
		state.setTimeAlive(System.currentTimeMillis() - state.getStartTime());
		lblTimeAlive.setText("Time alive: " + state.getTimeAlive()/1000L);

		Ship ship = state.getShip();
		g2d.setColor(ship.getDrawColor());

		g2d.fill(ship.getShape());

		ArrayList<Star> stars = state.getStars();
		for (Star s : stars) {
			g2d.setColor(s.getDrawColor());
			g2d.fill(s.getShape());
		}

		ArrayList<Asteroid> asteroids = state.getAsteroids();
		for (Asteroid a : asteroids) {
			g2d.setColor(a.getDrawColor());
			g2d.fill(a.getShape());
		}
		
		ArrayList<Bullet> bullets = state.getBullets();
		for (Bullet b : bullets) {
			g2d.setColor(b.getDrawColor());
			g2d.fill(b.getShape());
		}

		lblDodge.setText("Dodged " + state.dodgeCount + " Asteroids!");
		lblLevel.setText("Level " + state.getLevel());

		// updates health label and changes color if low health
		int shipHealth = ship.getHealth();
		lblHealth.setText("Health: " + shipHealth);
		if (shipHealth >= 10) {
			lblHealth.setForeground(Color.GREEN);
		} else if (ship.getHealth() < 10) {
			lblHealth.setForeground(Color.RED);
		} else if (ship.getHealth() < 20) {
			lblHealth.setForeground(Color.ORANGE);
		}
		
		if(input.getKey("Escape")){
			game.pauseGame();
			game.getState().pauseGame();
		}
		else if (input.getKey("Space")) {
			game.getState().addBullet();
		}
	}

	public void stopTimer() {
		timer.stop();
	}

	public void restartTimer() {
		timer.restart();
	}
}
