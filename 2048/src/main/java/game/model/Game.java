package game.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import game.controller.GameController;

/**
 * Panel that draws a game of 2048
 */
public class Game extends JPanel implements KeyListener, Runnable {
	// Constants
	private static final long serialVersionUID = 1L;
	public static final int WINDOW_WIDTH = 555;
	public static final int WINDOW_HEIGHT = 600;
	
	
	// Field variables
	private Thread game;
	private boolean gameIsRunning;
	private BufferedImage image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
	private GameBoard board;
	
	
	// Constructor
	public Game() {
		setFocusable(true);
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	
		addKeyListener(this);
		
		board = new GameBoard(WINDOW_WIDTH / 2 - GameBoard.BOARD_WIDTH / 2, WINDOW_HEIGHT - GameBoard.BOARD_HEIGHT - 10);
	}
	
	
	// Methods
	/**
	 * This method is called once we create a thread.
	 * Since we implement runnable this run method is called instead.
	 * This method runs while gameIsRunning == true. This is
	 * where we draw, update and render the board.
	 */
	public void run() {
		double nsPerUpdate = 1000000000.0 / 60;
		
		// last update time in nanoseconds
		double then = System.nanoTime();
		double unprocessed = 0;
		
		while(gameIsRunning) {
			boolean shouldRender = false;
			double now = System.nanoTime();
			unprocessed += (now - then) / nsPerUpdate;
			then = now;
			
			// update queue
			while(unprocessed >= 1) {
				unprocessed--;
				update();
				shouldRender = true;
			}
			
			// Checks if shouldRender
			if(shouldRender) {
				render();
				shouldRender = false;
			}
		}
	}
	
	// This method calls update method in GameBoard and GameController to update any changes made on the board.
	private void update() {
		board.update();
		GameController.update();
	}
	
	/**
	 * This method renders the changes to the board
	 * and draws the board
	 */
	private void render() {
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		board.render(g);
		g.dispose();
		
		Graphics2D g2d = (Graphics2D) getGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
	}
	
	// This method creates and starts a thread
	public void startGame() {
		gameIsRunning = true;
		game = new Thread(this, "game");
		game.start();
	}
	
	
	// KeyListener methods
	public void keyTyped(KeyEvent e) {
		// Ignore
	}

	// Key pressed
	public void keyPressed(KeyEvent e) {
		GameController.keyPressed(e);
	}
	
	// Key released
	public void keyReleased(KeyEvent e) {
		GameController.keyReleased(e);
	}
}