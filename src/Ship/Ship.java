package Ship;

public class Ship {

	protected int y = 500; // y position
	protected int[] xVerts = {0,10,20,10,0};
	protected int[] yVerts = {20+y,0+y,20+y,10+y,20+y};
	protected int width = 20; // ship widths

	protected int dx = 2; // increment amount (x coord)
	protected int dy = 2; // increment amount (y coord)
	
	public Ship(int xLoc, int speed) {
		for (int i = 0; i < xVerts.length; ++i) {
			xVerts[i] += xLoc;
		}
		dx = speed;
	}
}
