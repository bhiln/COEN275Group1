package Game;

public abstract class SpaceObject {

	private Position pose; // object location
	public int[] xVerts, yVerts = {}; // object shape
	public int width = 0; // object width

	public int dx = 0; // increment amount (x coord)
	public int dy = 0; // increment amount (y coord)
	
	public SpaceObject(Position pose) {
		this.pose = pose;
	}
	
	public void setPosition(Position pose) {
		this.pose = pose;
	}
	
	public Position getPosition() {
		return pose;
	}
	
	public void moveX(int pixels) {
		pose.x += pixels;
		for (int i = 0; i < xVerts.length; ++i) {
			xVerts[i] += pixels;
		}
	}
	
	public void moveY(int pixels) {
		pose.y += pixels;
		for (int i = 0; i < yVerts.length; ++i) {
			yVerts[i] += pixels;
		}
	}
}
