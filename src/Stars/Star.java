package Stars;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Random;

import Game.SpaceObject;

/**
 * The Class Star.
 */
public class Star extends SpaceObject {

	/** The rand. */
	Random rand = new Random();

	/**
	 * Gets the draw color.
	 *
	 * @return the draw color
	 */
	public Color getDrawColor() {
		return drawColor;
	}

	/** The draw color. */
	private Color drawColor = Color.LIGHT_GRAY;

	/**
	 * Instantiates a new star.
	 *
	 * @param pose the pose
	 * @param speed the speed
	 */
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
