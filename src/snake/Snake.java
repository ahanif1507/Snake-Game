package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import list.MyList;
import list.SimpleQueue;

/**
 * Snake, for use in the Snakes Game
 *
 * @see SnakesGame
 */
public class Snake {
	// its color
	private Color color;
	// the position of its head
	private Point position;
	// the direction of its head
	private char direction = 'E';
	// its entire body
	private SimpleQueue<Point> body = new MyList<Point>();
	// its current score
	private int score = 0;
	// x-location to print score on the board
	private int scoreLoc;
	// the pending commands to be applied
	private SimpleQueue<Character> commands = new MyList<Character>();

	private boolean blocked = false;
	private boolean b1 = false;
	private boolean b2 = false;
	private boolean b3 = false;
	private boolean b4 = false;

	/**
	 * Construct a new snake with the given color and starting position.
	 *
	 * @param c   this snake's color
	 * @param sp  this snake's starting position (a point on the grid)
	 * @param len the snake's length (number of body segments)
	 * @param loc the x coordinate of this snake's score
	 */
	public Snake(Color c, Point sp, int len, int loc) {
		color = c;
		scoreLoc = loc;
		for (int i = 0; i < len; i++) {
			position = new Point(sp.x + i, sp.y);
			body.enqueue(position);
		}

	}

	/**
	 * Set the direction of the head of the snake.
	 *
	 * @param d the direction (N, S, E, or W) or pause command (Z)
	 */
	public void setDirection(char d) {
		commands.enqueue(Character.valueOf(d));
	}

	/**
	 * Get the snake's score.
	 *
	 * @return the current score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Move the snake one step.
	 *
	 * @param game the current state of the snake game
	 */
	public void move(SnakesGame game) {
		// first see if our direction is changing
		if (!commands.isEmpty()) {
			Character c = commands.dequeue();
			direction = c.charValue();
			if (direction == 'Z') {
				return; // we're not moving
			}
		}
		// then find new position along that direction
		Point newPos = newPosition();
		if (game.canMove(newPos)) {
			// check if we will eat a pellet
			if (game.pellets.onPosition(newPos)) {
				game.pellets.remove(newPos);
				score++;
				body.enqueue(newPos);
			}
			// erase one segment, add another
			body.dequeue();
			body.enqueue(newPos);
			position = newPos;
		}
	}

	/**
	 * Calculate the next position for the snake, based on its current direction of
	 * movement.
	 * 
	 * @return a Point representing the next position in the chosen direction
	 */
	private Point newPosition() {
		int x = position.x;
		int y = position.y;
		switch (direction) {
		case 'E':
			x++;
			break;
		case 'W':
			x--;
			break;
		case 'N':
			y--;
			break;
		case 'S':
			y++;
			break;
		default:
			break;
		}

		if (x < 0) {
			x = SnakesGame.BOARD_WIDTH;
		}

		if (x > SnakesGame.BOARD_WIDTH) {
			x = 0;
		}

		if (y < 0) {
			y = SnakesGame.BOARD_HEIGHT;
		}

		if (y > SnakesGame.BOARD_HEIGHT) {
			y = 0;
		}

		Point p1 = new Point(x, y + 1);
		Point p2 = new Point(x, y - 1);
		Point p3 = new Point(x + 1, y);
		Point p4 = new Point(x - 1, y);

		for (Point segment : body) {
			if (segment.equals(p1)) {
				b1 = true;
			}
			if (segment.equals(p2)) {
				b2 = true;
			}
			if (segment.equals(p3)) {
				b3 = true;
			}
			if (segment.equals(p4)) {
				b4 = true;
			}
		}

		if (b1 == true && b2 == true && b3 == true && b4 == true) {
			blocked = true;
		} else {
			blocked = false;
			b1 = false;
			b2 = false;
			b3 = false;
			b4 = false;
		}

		return new Point(x, y);
	}

	/**
	 * Determine whether any part of the snake's body lies on the given position.
	 *
	 * @param newPos the position to test
	 * @return true if any part of the snake's body lies on newPos
	 */
	public boolean onPosition(Point newPos) {
		for (Point segment : body) {
			if (segment.equals(newPos)) {
				return true;
			}
		}
		return false;
	}

	public boolean getBlocked() {
		return this.blocked;
	}

	/**
	 * Draw this snake on the given graphics pane and draw its score in the
	 * appropriate location.
	 *
	 * @param g the graphics pane on which to draw the snake
	 */

	public void paint(Graphics g) {
		g.setColor(color);
		for (Point segment : body) {
			g.fillOval(5 + SnakesGame.SEGMENT_SIZE * segment.x, 
					   15 + SnakesGame.SEGMENT_SIZE * segment.y,
					   SnakesGame.SEGMENT_SIZE, SnakesGame.SEGMENT_SIZE);
		}
		g.drawString(Integer.toString(score), scoreLoc,
					 SnakesGame.SEGMENT_SIZE * (SnakesGame.BOARD_HEIGHT + 2));
	}
}