package Asteroids;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Game.SpaceObject;

public class Asteroid extends SpaceObject {
	
	public double scale = 1;
	
	public Asteroid(int xLoc, int speed) {
		x = xLoc;
		dy = speed;
		width = 15*2;
	}
}
