package Game;

import Asteroids.Asteroid;
import Ship.Ship;
import Stars.Star;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Renderer extends JPanel implements ActionListener {
	private Game game;
	private GameState state;
	
	private BufferedImage asteroidTexture;
	private BufferedImage in;
	private int delay = 20;
	protected Timer timer;

	JLabel lblLevel, lblDodge, lblTimeAlive, lblHealth;

	public Renderer(Game game, GameState state) {
		this.game = game;
		this.state = state;
		
		try {
		    File pathToFile = new File("assets/asteroid.png");
		    in = ImageIO.read(pathToFile);
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		
		asteroidTexture = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		

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
		state.setTimeAlive((System.currentTimeMillis() - state.getStartTime()) / 1000L);
		lblTimeAlive.setText("Time alive: " + state.getTimeAlive());

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
			//g2d.setColor(a.getDrawColor());
			//g2d.fill(a.getShape());
			
			TexturePaint tp = new TexturePaint(asteroidTexture, new Rectangle(0,0,16,16));
			g2d.setPaint(tp);
			//BufferedImage img1 = in.filter(in,null);
			g2d.drawRenderedImage(in, null);
			
			//g2d.setPaint(new Color(0, 0, 0));
			//g2d = asteroidTexture.createGraphics();
			//g2d.fillRect(0,0,asteroidTexture.getWidth(), asteroidTexture.getHeight());
			
		}

		lblDodge.setText("Dodged " + state.dodgeCount + " Asteroids!");
		lblLevel.setText("Level " + state.getLevel());

		// updates health label and changes color if low health
		int shipHealth = ship.getHealth();
		lblHealth.setText("Health: " + shipHealth);
		if (shipHealth >= 20) {
			lblHealth.setForeground(Color.GREEN);
		} else if (ship.getHealth() < 10) {
			lblHealth.setForeground(Color.RED);
		} else if (ship.getHealth() < 20) {
			lblHealth.setForeground(Color.ORANGE);
		}
	}

	public void stopTimer() {
		timer.stop();
	}

	public void restartTimer() {
		timer.restart();
	}
}
