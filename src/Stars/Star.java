package Stars;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Random;

import Game.SpaceObject;

public class Star extends SpaceObject {

	Random rand = new Random();

	public Color getDrawColor() {
		return drawColor;
	}

	private Color drawColor = Color.LIGHT_GRAY;

	public Star(Point.Double pose, int speed) {
		super(pose);

		// create ship shape
		Path2D.Double starShape = new Path2D.Double();
		starShape.moveTo(0, 0);
		starShape.lineTo(0, 2);
		starShape.lineTo(2, 2);
		starShape.lineTo(2, 0);
		starShape.lineTo(0, 0);
		setShape(starShape);



		dy = speed;
		width = 2;
	}
}
