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

public class GameView extends JPanel implements ActionListener{
	
	private int delay = 16;
	protected Timer timer;
	
	private int level = 1;
	private int lastAsteroidIter = 0;
	private int dodgeCount = 0;
	
	JLabel lblDodge = new JLabel("Dodged " + dodgeCount + " Asteroids!");
	
	private ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
	Random rand = new Random();
	
	public GameView()
	{
		timer = new Timer(delay, this);
		timer.start(); // start the timer
		super.add(lblDodge, BorderLayout.NORTH);
	}
	
	public void createAsteroid(int num) {
		for (int i = 0; i < num; i++) {
			System.out.println(getWidth());
			int xLoc = rand.nextInt(getWidth());
			int speed = 1;//rand.nextInt(2);
			asteroidList.add(new Asteroid(xLoc, speed));
		}
	}

	public void actionPerformed(ActionEvent e)
	// will run when the timer fires
	{
		repaint();
	}

	// draw rectangles and arcs
	public void paintComponent( Graphics g )
	{
		if (rand.nextInt(1000) > 1000-level*5 | lastAsteroidIter > 200-level*5) {
			createAsteroid(1);
			lastAsteroidIter = 0;
		}
		lastAsteroidIter++;
		super.paintComponent( g ); // call superclass's paintComponent 
		g.setColor(Color.red);

		ArrayList<Asteroid> toRemove = new ArrayList<Asteroid>();
		for (Asteroid myAsteroid : asteroidList) {
			// check for boundaries
			if (myAsteroid.x < myAsteroid.radius) myAsteroid.dx = Math.abs(myAsteroid.dx);
			if (myAsteroid.x > getWidth() - myAsteroid.radius) myAsteroid.dx = -Math.abs(myAsteroid.dx);
			if (myAsteroid.y > getHeight() + myAsteroid.radius) {// - myAsteroid.radius) myAsteroid.dy = -Math.abs(myAsteroid.dy);
				toRemove.add(myAsteroid);
			}
			// adjust ball position
			myAsteroid.y += myAsteroid.dy;
			g.fillOval((int)((myAsteroid.x - myAsteroid.radius) * myAsteroid.scale), (int)((myAsteroid.y - myAsteroid.radius) * myAsteroid.scale), myAsteroid.radius*2, myAsteroid.radius*2);
	
		}
		for (Asteroid removeAsteroid : toRemove) {
			asteroidList.remove(removeAsteroid);
			dodgeCount++;
			lblDodge.setText("Dodged " + dodgeCount + " Asteroids!");
		}
	}
}
