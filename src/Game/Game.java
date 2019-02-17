package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Asteroids.AsteroidManager;
import Ship.ShipManager;

public class Game extends JPanel implements ActionListener{
	
	private int delay = 16;
	protected Timer timer;
	
	private int level = 1;				// player level
	private int lastAsteroidIter = 0;	// count how many frames passed since last asteroid was created
	private int dodgeCount = 0;			// count score

	private long startTime;
	private long timeAlive = 0L;
	private long lastTimeAlive = 0L;
	
	// level and score labels
	JLabel lblLevel, lblDodge, lblTimeAlive;
	
	// create ship and asteroid managers
	ShipManager m_ship = new ShipManager();
	AsteroidManager m_asteroid = new AsteroidManager();
	
	Random rand = new Random();
	
	public Game()
	{
		timer = new Timer(delay, this);
		timer.start(); // start the timer
		
		lblLevel = new JLabel("Level " + level);
		lblLevel.setForeground(Color.WHITE);
		lblDodge = new JLabel("Dodged " + dodgeCount + " Asteroids!");
		lblDodge.setForeground(Color.WHITE);
		lblTimeAlive = new JLabel("Time alive: " + timeAlive);
		lblTimeAlive.setForeground(Color.WHITE);
		
		// add level and score labels to frame
		add(lblLevel, BorderLayout.NORTH);
		add(lblDodge, BorderLayout.NORTH);
		add(lblTimeAlive, BorderLayout.NORTH);
		
		// set background to dark gray
		setBackground(Color.DARK_GRAY);
		
		// create ship
		m_ship.createShip(0);
		
		startTime = System.currentTimeMillis();
	}

	public void actionPerformed(ActionEvent e)
	// will run when the timer fires
	{
		repaint();
	}

	// draw rectangles and arcs
	public void paintComponent( Graphics g )
	{		
		super.paintComponent( g ); // call superclass's paintComponent 
		g.setColor(Color.red);
		
		// calculate time alive. Only update label if it's a new second
		timeAlive = (System.currentTimeMillis()-startTime)/1000L;
		if (timeAlive > lastTimeAlive) {
			lblTimeAlive.setText("Time alive: " + timeAlive);
			lastTimeAlive = timeAlive;
		}
		
		// update and draw ship
		// this takes into account the current size of the frame so it can dynamically scale
		m_ship.updateShip(getWidth(), getHeight());
		m_ship.drawShip(g);
		
		// randomly generate an asteroid
		// likelihood of asteroid generation increases with level
		// if no asteroid has been created lately, create a new asteroid (gets faster as level increases)
		if (rand.nextInt(1000) > 1000-level*5 || lastAsteroidIter > 200-level*5) {
			m_asteroid.createAsteroid(getWidth());
			lastAsteroidIter = 0;
		}
		lastAsteroidIter++;

		// update and draw all asteroids
		int removed = m_asteroid.updateAsteroids(getWidth(), getHeight());
		m_asteroid.drawAsteroids(g);
		
		// update score
		dodgeCount += removed;
		lblDodge.setText("Dodged " + dodgeCount + " Asteroids!");
		
		// update level
		// 10 dodges = 1 level increase
		if (removed > 0 && dodgeCount > 0 && dodgeCount % 10 == 0) {
			level++;
			lblLevel.setText("Level " + level);
		}
	}
}
