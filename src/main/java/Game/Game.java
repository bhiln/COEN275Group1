package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Asteroids.AsteroidManager;
import Ship.ShipManager;
import Stars.StarManager;

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
	StarManager m_star = new StarManager();
	
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
		Point originalPose = new Point(0,500);
		m_ship.Create(originalPose);
		
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
		m_ship.Update(getWidth(), getHeight());
		m_ship.Draw(g);
		
		// draw background stars
		if (rand.nextInt(1000) > 950) {
			Point starBounds = new Point(getWidth(), 0);
			m_star.Create(starBounds);
		}
		m_star.Update(getWidth(), getHeight());
		m_star.Draw(g);
		
		// randomly generate an asteroid
		// likelihood of asteroid generation increases with level
		// if no asteroid has been created lately, create a new asteroid (gets faster as level increases)
		if (rand.nextInt(1000) > 1000-level*5 || lastAsteroidIter > 200-level*5) {
			Point asteroidBounds = new Point(getWidth(), 0);
			m_asteroid.Create(asteroidBounds);
			lastAsteroidIter = 0;
		}
		lastAsteroidIter++;

		// update and draw all asteroids
		int removed = m_asteroid.Update(getWidth(), getHeight());
		m_asteroid.Draw(g);
		
		// update score
		dodgeCount += removed;
		lblDodge.setText("Dodged " + dodgeCount + " Asteroids!");
		
		// update level
		// 10 dodges = 1 level increase
		if (removed > 0 && dodgeCount > 0 && dodgeCount % 10 == 0) {
			level++;
			lblLevel.setText("Level " + level);
		}
		
		if (m_ship.detectCollisions(m_asteroid.getAsteroids()).size() > 0) {
			setBackground(Color.ORANGE);
			timer.stop();
			// TODO: popup menu with stats and restart
		}
		else {
			if (getBackground() == Color.ORANGE) {
				setBackground(Color.DARK_GRAY);
			}
		}
	}
}
