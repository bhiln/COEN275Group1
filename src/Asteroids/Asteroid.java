package Asteroids;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Asteroid{

	public int x = 0; // x position
	public int y = 0; // y position
	public int radius = 15; // asteroid radius

	public int dx = 0; // increment amount (x coord)
					   // dx is set to 0 for now, this means that the asteroid will fall straight down
	public int dy = 2; // increment amount (y coord)
	
	public double scale = 1;
	
	public Asteroid(int xLoc, int speed) {
		x = xLoc;
		dy = speed;
	}
}
