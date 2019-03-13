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
	
	private int delay = 20;
	protected Timer timer;

	JLabel lblLevel, lblScore, lblHealth, lblAmmo;
	JPanel pnlStats;
	
	KeyInput input;

	public Renderer(Game game, KeyInput input) {
		this.game = game;
		this.input = input;

		timer = new Timer(delay, this);
		restartTimer();

		Font statFont = new Font("SanSerif", Font.BOLD, 16);
		lblLevel = new JLabel("Level " + game.getState().getLevel());
		lblLevel.setForeground(Color.WHITE);
		lblLevel.setFont(statFont);
		lblLevel.setHorizontalAlignment(JLabel.CENTER);
		lblScore = new JLabel("Score: " + game.getState().getScore());
		lblScore.setForeground(Color.WHITE);
		lblScore.setFont(statFont);
		lblScore.setHorizontalAlignment(JLabel.CENTER);
		lblHealth = new JLabel("Health: " + game.getState().getShip().getHealth());
		lblHealth.setForeground(Color.GREEN);
		lblHealth.setFont(statFont);
		lblHealth.setHorizontalAlignment(JLabel.CENTER);
		lblAmmo = new JLabel("Ammo: " + game.getState().getShip().getAmmo());
		lblAmmo.setForeground(Color.WHITE);
		lblAmmo.setFont(statFont);
		lblAmmo.setHorizontalAlignment(JLabel.CENTER);
		
		pnlStats = new JPanel();
		pnlStats.setLayout(new GridLayout(1,4));
		pnlStats.setPreferredSize(new Dimension(this.game.getSize().width,20));
		pnlStats.setOpaque(false);
		
		// add level and score labels to frame
		pnlStats.add(lblLevel, BorderLayout.CENTER);//, BorderLayout.NORTH);
		pnlStats.add(lblScore, BorderLayout.CENTER);//, BorderLayout.NORTH);
		pnlStats.add(lblHealth, BorderLayout.CENTER);//, BorderLayout.NORTH);
		pnlStats.add(lblAmmo, BorderLayout.CENTER);//, BorderLayout.NORTH);
		
		add(pnlStats, BorderLayout.NORTH);

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
		game.getState().setTimeAlive(System.currentTimeMillis() - game.getState().getStartTime());

		Ship ship = game.getState().getShip();
		
		g2d.drawImage(ship.getTexture(), (int)ship.getPosition().x, (int)ship.getPosition().y, null);
		//standard methods to draw solid color in shape
		//g2d.setColor(ship.getDrawColor());
		//g2d.fill(ship.getShape());

		ArrayList<Star> stars = game.getState().getStars();
		for (Star s : stars) {
			
			g2d.setColor(s.getDrawColor());
			
			g2d.fill(s.getShape());
		}

		ArrayList<Asteroid> asteroids = game.getState().getAsteroids();
		for (Asteroid a : asteroids) {
			g2d.rotate(a.getRotation(),((int)a.getPosition().x + a.width/2), (int)a.getPosition().y + a.width/2);
			g2d.drawImage(a.getTexture(), (int)a.getPosition().x, (int)a.getPosition().y, null);
			//have to undo rotation for x/y translation otherwise velocity vector will be rotated too
			g2d.rotate(-a.getRotation(),((int)a.getPosition().x + a.width/2), (int)a.getPosition().y + a.width/2);
		}
		
		ArrayList<Bullet> bullets = game.getState().getBullets();
		for (Bullet b : bullets) {
			g2d.setColor(b.getDrawColor());
			g2d.fill(b.getShape());
		}
		
		game.getState().setScore((long) (game.getState().dodgeCount*(0.9+game.getState().getLevel()/10.)*game.getState().getDifficulty()));

		lblScore.setText("Score " + game.getState().getScore());
		lblLevel.setText("Level " + game.getState().getLevel());

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
