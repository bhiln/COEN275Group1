package Ship;

public class Ship {

	public int x = 0; // x position
	public int y = 500; // y position
	public int radius = 15; // ball radius

	public int dx = 2; // increment amount (x coord)
	public int dy = 2; // increment amount (y coord)
	
	public Ship(int xLoc, int speed) {
		System.out.println("INITIALIZING " + xLoc);
		x = xLoc;
		dx = speed;
	}
}
