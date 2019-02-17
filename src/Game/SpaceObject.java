package Game;

public abstract class SpaceObject {

	public int x, y = 0; // y position
	public int[] xVerts, yVerts = {};
	public int width = 20; // ship widths

	public int dx = 0; // increment amount (x coord)
	public int dy = 0; // increment amount (y coord)
}
