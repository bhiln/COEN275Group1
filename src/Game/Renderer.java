package Game;

import Asteroids.Asteroid;
import Projectiles.Bullet;
import Ship.Ship;
import Stars.Star;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Renderer extends JPanel implements ActionListener {
	private Game game;
	private GameState state;
	
	private int delay = 20;
	protected Timer timer;

	JLabel lblLevel, lblDodge, lblTimeAlive, lblHealth, lblAmmo;
	
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
		lblHealth = new JLabel("Health: " + state.getShip().getHealth());
		lblHealth.setForeground(Color.GREEN);
		lblAmmo = new JLabel("Ammo: " + state.getShip().getAmmo());
		lblAmmo.setForeground(Color.WHITE);

		// add level and score labels to frame
		add(lblLevel, BorderLayout.NORTH);
		add(lblDodge, BorderLayout.NORTH);
		add(lblTimeAlive, BorderLayout.NORTH);
		add(lblHealth, BorderLayout.NORTH);
		add(lblAmmo, BorderLayout.NORTH);

		// set background to dark gray
		setBackground(Color.BLACK);
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
		
		g2d.drawImage(ship.getTexture(), (int)ship.getPosition().x, (int)ship.getPosition().y, null);
		//standard methods to draw solid color in shape
		//g2d.setColor(ship.getDrawColor());
		//g2d.fill(ship.getShape());

		ArrayList<Star> stars = state.getStars();
		for (Star s : stars) {
			
			g2d.setColor(s.getDrawColor());
			
			g2d.fill(s.getShape());
		}

		ArrayList<Asteroid> asteroids = state.getAsteroids();
		for (Asteroid a : asteroids) {
			g2d.rotate(a.getRotation(),((int)a.getPosition().x + a.width/2), (int)a.getPosition().y + a.width/2);
			g2d.drawImage(a.getTexture(), (int)a.getPosition().x, (int)a.getPosition().y, null);
			//have to undo rotation for x/y translation otherwise velocity vector will be rotated too
			g2d.rotate(-a.getRotation(),((int)a.getPosition().x + a.width/2), (int)a.getPosition().y + a.width/2);
			

			
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
		if (shipHealth > 50) {
			lblHealth.setForeground(Color.GREEN);
		} else if (shipHealth <= 25) {
			lblHealth.setForeground(Color.RED);
		} else if (shipHealth <= 50) {
			lblHealth.setForeground(Color.ORANGE);
		}
		
		lblAmmo.setText("Ammo: " + ship.getAmmo());
		if(ship.getAmmo() < 1) {
			lblAmmo.setForeground(Color.RED);
		}
		else if(ship.getAmmo() < 10) {
			lblAmmo.setForeground(Color.ORANGE);
		}
		else {
			lblAmmo.setForeground(Color.WHITE);
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
