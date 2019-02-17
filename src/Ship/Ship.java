package Ship;

public class Ship {

	public int y = 500; // y position
	int[] xVerts = {0,10,20,10,0};
	int[] yVerts = {20+y,0+y,20+y,10+y,20+y};
	public int width = 20; // ball radius

	public int dx = 2; // increment amount (x coord)
	public int dy = 2; // increment amount (y coord)
	
	public Ship(int xLoc, int speed) {
		x = xLoc;
		dx = speed;
	}
}
