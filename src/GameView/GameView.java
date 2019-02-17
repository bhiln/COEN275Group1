package GameView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Asteroids.Asteroid;

public class GameView extends JPanel implements ActionListener{
	
	private int delay = 16;
	protected Timer timer;
	
	private ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
	Random rand = new Random();
	
	public GameView()
	{
		timer = new Timer(delay, this);
		timer.start(); // start the timer
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
		super.paintComponent( g ); // call superclass's paintComponent 
		g.setColor(Color.red);

		ArrayList<Asteroid> toRemove = new ArrayList<Asteroid>();
		for (Asteroid myAsteroid : asteroidList) {
			// check for boundaries
			if (myAsteroid.x < myAsteroid.radius) myAsteroid.dx = Math.abs(myAsteroid.dx);
			if (myAsteroid.x > getWidth() - myAsteroid.radius) myAsteroid.dx = -Math.abs(myAsteroid.dx);
//			if (myAsteroid.y < myAsteroid.radius) myAsteroid.dy = Math.abs(myAsteroid.dy);
			if (myAsteroid.y > getHeight() + myAsteroid.radius) {// - myAsteroid.radius) myAsteroid.dy = -Math.abs(myAsteroid.dy);
				toRemove.add(myAsteroid);
			}
			// adjust ball position
//			myAsteroid.x += myAsteroid.dx;
			myAsteroid.y += myAsteroid.dy;
			g.fillOval((int)((myAsteroid.x - myAsteroid.radius) * myAsteroid.scale), (int)((myAsteroid.y - myAsteroid.radius) * myAsteroid.scale), myAsteroid.radius*2, myAsteroid.radius*2);
	
		}
		for (Asteroid removeAsteroid : toRemove) {
			asteroidList.remove(removeAsteroid);
		}
	}
}
