package Asteroids;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Asteroid{

	public int x = 0; // x position
	public int y = 0; // y position
	public int radius = 15; // ball radius

	public int dx = 2; // increment amount (x coord)
	public int dy = 2; // increment amount (y coord)
	
	public double scale = 1;
	
	public Asteroid(int xLoc, int speed) {
		x = xLoc;
//		dx = scale;
		dy = speed;
	}
	
//	public void actionPerformed(ActionEvent e)
//	// will run when the timer fires
//	{
//		repaint();
//	}
	
	// draw rectangles and arcs
//	public void paintComponent( Graphics g )
//	{
//		super.paintComponent( g ); // call superclass's paintComponent 
//		g.setColor(Color.red);
//
//		// check for boundaries
//		if (x < radius) dx = Math.abs(dx);
//		if (x > getWidth() - radius) dx = -Math.abs(dx);
//		if (y < radius) dy = Math.abs(dy);
//		if (y > getHeight() - radius) dy = -Math.abs(dy);
//
//		// adjust ball position
//		x += dx;
//		y += dy;
//		g.fillOval(x - radius, y - radius, radius*2, radius*2);
//	}
}
