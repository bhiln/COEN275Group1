package Game;

import java.awt.Graphics;
import java.awt.Point;

public interface SpaceManager {

	public void Create(Point pose);
	public int Update(int width, int height);
	public void Draw(Graphics g);
}
