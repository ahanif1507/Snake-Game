package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import list.MyList;
import list.SimpleList;

/**
 * Pellets, to be eaten by a Snake in the Snakes Game.
 *
 * @see SnakesGame
 */
public class Pellets {
	// pellet color
	private Color color;

	// the pellets, stored as a List of Points
	private SimpleList<Point> pellets = new MyList<Point>();

	// random number generator for initial Pellet locations
	private Random gen = new Random();

	/**
	 * Create the new pellets, with the given color at randomly chosen positions.
	 *
	 * @param c   the pellet color
	 * @param num the number of pellets
	 */
	public Pellets(Color c, int num) {
		color = c;

		for (int i = 0; i < num; i++) {
			Point p = new Point(gen.nextInt(SnakesGame.BOARD_WIDTH - 1) + 1,
					gen.nextInt(SnakesGame.BOARD_HEIGHT - 1) + 1);
			pellets.add(p);
		}
	}

	/**
	 * Determine whether a pellet lies on the given position.
	 *
	 * @param p the position to test
	 * @return <code>true</code> if a pellet lies on p; <code>false</code> otherwise
	 */
	public boolean onPosition(Point p) {
		// iterate over all the pellets
		for (Point pellet : pellets) {
			if (pellet.equals(p)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove the pellet at Point p from the collection of pellets.
	 *
	 * @param p the Point that has the pellet to remove
	 */
	public void remove(Point p) {
		pellets.remove(p);
	}

	/**
	 * Determine whether all of the pellets have been consumed.
	 *
	 * @return <code>true</code> if there are no more pellets; <code>false</code>
	 *         otherwise
	 */
	public boolean allGone() {
		return pellets.isEmpty();
	}

	/**
	 * Draw the pellets on the given pane
	 *
	 * @param g the Graphics pane on which to draw the pellet
	 */
	public void paint(Graphics g) {
		g.setColor(color);
		for (Point pellet : pellets) {
			g.fillOval(5 + SnakesGame.SEGMENT_SIZE * pellet.x + 2, 
					15 + SnakesGame.SEGMENT_SIZE * pellet.y + 2,
					SnakesGame.SEGMENT_SIZE - 4, SnakesGame.SEGMENT_SIZE - 4);
		}
	}
}