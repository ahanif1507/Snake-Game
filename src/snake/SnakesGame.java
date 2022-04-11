package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

/**
 * The user interface and main program for the Snakes game.
 *
 * This is a two-player game. Each player controls a snake and attempts to
 * perform maneuvers so that it eats as many pellets as possible. Whoever eats
 * more pellets is the winner.
 *
 * The snakes are controlled via keyboard commands. Snakes initially move
 * eastward, but at any moment a user can command the snake to stop moving or
 * else change directions (north, south, east, or west). The keyboard commands
 * for each player are as follows:
 *
 * Player one (blue): w=north, s=south, d=east, a=west, q=pause
 *
 * Player two (red): i=north, k=south, l=east, j=west, p=pause
 *
 * @see Snake
 * @see Pellets
 */
public class SnakesGame extends Frame {
	// The internal state of a Snakes game; constants have package visibility to be
	// used elsewhere if needed

	// initial length of each snake
	static final int LENGTH = 10;
	// board width ...
	static final int BOARD_WIDTH = 60;
	// ... and height
	static final int BOARD_HEIGHT = 40;
	// size of each snake segment
	static final int SEGMENT_SIZE = 10;
	// player one's snake
	private Snake playerOne = new Snake(Color.BLUE, new Point(20, 10), LENGTH, SEGMENT_SIZE * 4);
	// player two's snake
	private Snake playerTwo = new Snake(Color.RED, new Point(20, 30), LENGTH, 
										SEGMENT_SIZE * (BOARD_WIDTH - 4));
	// the pellets, to be initialized in the SnakesGame constructor
	Pellets pellets; // package visibility so the snakes can access and "eat" the pellets
	
	private boolean end = false;

	/**
	 * Construct a new instance of the snake game.
	 */
	public SnakesGame() {
		// I/O to acquire and set the number of pellets
		String input = JOptionPane.showInputDialog("Number of pellets? ");
		int numPellets = Integer.parseInt(input);
		pellets = new Pellets(Color.GREEN, numPellets);

		// create the board and necessary event listeners
		setSize((BOARD_WIDTH + 1) * SEGMENT_SIZE, BOARD_HEIGHT * SEGMENT_SIZE + 32);
		setTitle("Snakes Game");
		addKeyListener(new KeyReader());
		addWindowListener(new CloseAndQuit());
	}

	/**
	 * Run the game and animate the results.
	 */
	public void run() {
		while (!pellets.allGone() && end == false) {
			movePlayers();
			repaint();
			try {
				// sleep 100 milliseconds to give illusion of animation
				Thread.sleep(100);
			} catch (Exception e) {
				System.out.println("Error running sleep!");
			}
		}
	}

	/**
	 * Draw the current game state, or display splash screen with winner if done.
	 *
	 * @param g the graphics pane on which to redraw
	 */
	public void paint(Graphics g) {
		
		if (playerOne.getBlocked() == true) {
			g.setColor(Color.red);
			g.drawString("Red is the winner!", 220, 200);
			end = true;
		}

		if (playerTwo.getBlocked() == true) {
			g.setColor(Color.blue);
			g.drawString("Blue is the winner!", 220, 200);
			end = true;
		}
		
		if (!pellets.allGone()) {
			pellets.paint(g);
			playerOne.paint(g);
			playerTwo.paint(g);	
		} else {
			int playerOneScore = playerOne.getScore();
			int playerTwoScore = playerTwo.getScore();
			g.setFont(g.getFont().deriveFont(Font.BOLD, (float) 20.0));

			if (playerOneScore > playerTwoScore) {
				g.setColor(Color.blue);
				g.drawString("Blue is the winner!", 220, 200);
			} else if (playerTwoScore > playerOneScore) {
				g.setColor(Color.red);
				g.drawString("Red is the winner!", 220, 200);
			} else {
				g.setColor(Color.black);
				g.drawString("A tie game!", 250, 200);
			}	
		}
		
		
	}

	/**
	 * For each player, consume the next command and move accordingly.
	 */
	public void movePlayers() {
		playerOne.move(this);
		playerTwo.move(this);
	}

	/**
	 * Determine whether the given point is free (so that a snake can move onto it).
	 *
	 * @param newPos the point to test
	 * @return true if newPos is free
	 */
	public boolean canMove(Point newPos) {
		// get x, y coordinate
		/*
		 * int x = newPos.x;
		 * int y = newPos.y;
		*/
		// test if on the board
		/*
		 * if ((x <= 0) || (x >= BOARD_WIDTH) || (y <= 0) || (y >= BOARD_HEIGHT)) {
		 * return false; }
		 */
		// test if newPos puts one snake on another
		if (playerOne.onPosition(newPos) || playerTwo.onPosition(newPos)) {
			return false;
		}
		// safe square to move onto
		return true;
	}

	/**
	 * Convert keyboard inputs for the two players into corresponding snake
	 * commands.
	 */
	private class KeyReader extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			char c = e.getKeyChar();
			switch (c) {
			case 'q':
				playerOne.setDirection('Z');
				break; // 'Z' pauses that snake
			case 'p':
				playerTwo.setDirection('Z');
				break; // 'Z' pauses that snake
			case 'a':
				playerOne.setDirection('W');
				break;
			case 'd':
				playerOne.setDirection('E');
				break;
			case 'w':
				playerOne.setDirection('N');
				break;
			case 's':
				playerOne.setDirection('S');
				break;
			case 'j':
				playerTwo.setDirection('W');
				break;
			case 'k':
				playerTwo.setDirection('S');
				break;
			case 'l':
				playerTwo.setDirection('E');
				break;
			case 'i':
				playerTwo.setDirection('N');
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Cleanly quit if game window is closed.
	 */
	private class CloseAndQuit extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	/**
	 * The main method. This creates a new world, displays it, and then starts it
	 * running.
	 *
	 * @param args the command line arguments (all of which are ignored)
	 */
	public static void main(String[] args) {
		SnakesGame world = new SnakesGame();
		world.setVisible(true);
		world.run();
	}
}