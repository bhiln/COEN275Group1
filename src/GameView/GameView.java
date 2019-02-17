package GameView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Asteroids.Asteroid;
import Asteroids.AsteroidManager;
import Ship.ShipManager;

public class GameView extends JPanel implements ActionListener{
	
	private int delay = 16;
	protected Timer timer;
	
	private int level = 1;
	private int lastAsteroidIter = 0;
	private int dodgeCount = 0;
	
	JLabel lblLevel = new JLabel("Level " + level);
	JLabel lblDodge = new JLabel("Dodged " + dodgeCount + " Asteroids!");
	
	ShipManager m_ship = new ShipManager();
	AsteroidManager m_asteroid = new AsteroidManager();
	Random rand = new Random();
	
	public GameView()
	{
		timer = new Timer(delay, this);
		timer.start(); // start the timer
		add(lblLevel, BorderLayout.NORTH);
		add(lblDodge, BorderLayout.NORTH);
		setBackground(Color.DARK_GRAY);
		m_ship.createShip(100);
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
		
		m_ship.updateShip(g, getWidth(), getHeight());
		if (rand.nextInt(1000) > 1000-level*5 || lastAsteroidIter > 200-level*5) {
			m_asteroid.createAsteroid(getWidth());
			lastAsteroidIter = 0;
		}
		lastAsteroidIter++;

		int removed = m_asteroid.updateAsteroids(g, getWidth(), getHeight());
		dodgeCount += removed;
		
		lblDodge.setText("Dodged " + dodgeCount + " Asteroids!");
		if (removed > 0 && dodgeCount > 0 && dodgeCount % 10 == 0) {
			level++;
			lblLevel.setText("Level " + level);
		}
	}
}
