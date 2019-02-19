package Game;

import java.awt.Graphics;

public interface SpaceManager {

	public void Create(int width);
	public int Update(int width, int height);
	public void Draw(Graphics g);
}
