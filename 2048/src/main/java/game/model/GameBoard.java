package game.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import game.controller.GameController;

public class GameBoard {
	// Constants
	public static final int ROWS = 4;
	public static final int COLS = 4;
	public static final int AMOUNT_OF_STARTING_TILES = 2;
	private static final int SPACE_BETWEEN_TILES = 15;
	
	
	// Field Variables
	private Tile[][] board;
	private boolean gameWon;
	private boolean gameLost;
	private BufferedImage gameBoard;
	private BufferedImage finalBoard;
	private int x;
	private int y;
	
	public static final int BOARD_WIDTH = (COLS + 1) * SPACE_BETWEEN_TILES + COLS * Tile.TILE_WIDTH; // X amount of SBT * SBT + cols * width of tiles
	public static final int BOARD_HEIGHT = (ROWS + 1) * SPACE_BETWEEN_TILES + ROWS * Tile.TILE_HEIGHT; // X amount of SBT * SBT + rows * height of tiles
	
	
	// Constructor
	public GameBoard(int x, int y) {
		this.x = x;
		this.y = y;
		board = new Tile[ROWS][COLS];
		gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		drawBoard(); // Draw board
		start(); // Start game
	}
	
	
	// Methods	
	/**
	 * This method sets the colors for the board and draws it.
	 * After the board is draw we then draw the tiles on top of the board. (The tiles we draw here are stuck to the board and not movable, the movable tiles are drawn on top of these tiles)
	 * We use a nested for loop to draw each individual tile according to the
	 * variables deltaX, deltaY, TILE_WIDTH and TILE_HEIGTH.
	 */
	public void drawBoard() {
		Graphics2D g = (Graphics2D) gameBoard.getGraphics();
		g.setColor(new Color(17, 47, 75));
		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		g.setColor(new Color(76, 94, 135));
		
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				int deltaX = SPACE_BETWEEN_TILES + SPACE_BETWEEN_TILES * col + Tile.TILE_WIDTH * col;
				int deltaY = SPACE_BETWEEN_TILES + SPACE_BETWEEN_TILES * row + Tile.TILE_HEIGHT * row;
				g.fillRect(deltaX, deltaY, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
			}
		}
	}
	
	// This method starts the game by spawning x amount of random tiles (depending on what the value "AMOUNT_OF_STARTING_TILES" is)
	private void start() {
		for(int i = 0; i < AMOUNT_OF_STARTING_TILES; i++) {
			createRandomTile(); // Calls createRandomTile() method
		}
	}
	
	/**
	 * This method creates a random tile on the board.
	 * We do this by using a while true loop.
	 * First we find a randomNumber and then use this to find randomRow and randomCol.
	 * Then we check if the tile with the randomRow and randomCol coordinates is empty or not.
	 * If randomTile is empty we generate a random tile value and assign it to a newTile and break out of the while loop.
	 * If randomTile is not empty we continue the while loop from the start until we find an empty tile on the board.
	 */
	private void createRandomTile() {
		Random random = new Random();
		
		while(true) {
			int randomNumber = random.nextInt(ROWS * COLS);
			int randomRow = randomNumber / ROWS;
			int randomCol = randomNumber % COLS;
			
			Tile randomTile = board[randomRow][randomCol];
			
			if(randomTile == null) {
				int tileValue = random.nextInt(100) < 90 ? 2 : 4; // This says that we get the value 2 90% of the time and 10% of the time we get the value 4
				Tile newTile = new Tile(tileValue, getTileX(randomCol), getTileY(randomRow));
				board[randomRow][randomCol] = newTile;
				break;
			}
		}
	}	
	
	/**
	 * This method draws the tiles that are movable.
	 * We use nested for loop to go through each individual tile
	 * and check if the tile is empty or not. If the tile is empty we skip the iteration
	 * and if the tile is not empty we use the render(g2d) method from the class Tile.
	 * Once the nested for loop is done we draw all of the tiles on the board.
	 * After the tiles are drawn we check if game is lost or won, if either is true we
	 * print a win or loss message on the screen.
	 * 
	 * @param g
	 */
	public void render(Graphics2D g) {
		Graphics2D g2d = (Graphics2D)finalBoard.getGraphics();
		g2d.drawImage(gameBoard, 0, 0, null);
		
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				Tile current = board[row][col];
				
				if(current == null) 
					continue; // This skips the current iteration if "current" == null, which means the tile is empty
				
				current.render(g2d); // Calls render method in Tile class which draws the tile on the board
			}
		}
		g.drawImage(finalBoard, x, y, null);
		g2d.dispose();
		
		// Checks if game is lost
		if(gameLost) {
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", 0, 100));
			g.drawString("" + "You Lost", 65, 350);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", 0, 30));
			g.drawString("" + "Press 'r' to restart or 'esc' to exit game", 25, 40);
		}
		
		// Checks if game is won
		if(gameWon) {
			g.setColor(Color.GREEN);
			g.setFont(new Font("Arial", 0, 100));
			g.drawString("" + "You Won", 65, 350);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", 0, 30));
			g.drawString("" + "Press 'r' to restart or 'esc' to exit game", 25, 40);
		}
	}
	
	/**
	 * This method first calls the checkKeys() method which checks if any keys has been clicked and act on it (for example if arrow up key is clicked the tiles moves up etc).
	 * After we have checked keys we go in to a nested for loop where we check 
	 * each individual tile if its empty or not. If it's empty we skip the iteration, and if
	 * it's not empty we call the method resetPosition, and after
	 * that we check if game is won by checking if any tiles has the value 2048.
	 */
	public void update(){
		checkKeys();
		
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				Tile current = board[row][col];
				if(current == null) 
					continue; // // If current == null then we skip this iteration and move on to the next
				resetPosition(current, row, col);
				if(current.getValue() == 2048) {
					gameWon = true;
				}
			}
		}
	}
	
	
	/**
	 * This method first checks if the currentTile is empty and if so we return and exit the method.
	 * If currentTile is not empty continue down the if statements and set new x and y values (depending on which statements are true)
	 * 
	 * @param currentTile current tile
	 * @param row the current row we are checking
	 * @param col the current col we are checking
	 */
	private void resetPosition(Tile currentTile, int row, int col) {
		if(currentTile == null) 
			return; // if current == null by any chance the method will then return to end the execution of this method
		
		int x = getTileX(col);
		int y = getTileY(row);
		
		int distX = currentTile.getX() - x;
		int distY = currentTile.getY() - y;
		
		if(Math.abs(distX) < Tile.TILE_MOVE_SPEED) {
			currentTile.setX(currentTile.getX() - distX);
		}
		
		if(Math.abs(distY) < Tile.TILE_MOVE_SPEED) {
			currentTile.setY(currentTile.getY() - distY);
		}
		
		if(distX < 0) {
			currentTile.setX(currentTile.getX() + Tile.TILE_MOVE_SPEED);	
		}
		if(distY < 0) {
			currentTile.setY(currentTile.getY() + Tile.TILE_MOVE_SPEED);
		}
		if(distX > 0) {
			currentTile.setX(currentTile.getX() - Tile.TILE_MOVE_SPEED);
		}
		if(distY > 0) {
			currentTile.setY(currentTile.getY() - Tile.TILE_MOVE_SPEED);
		}
	}
	
	/**
	 * First we check if game is won and if so we return false. Next we check if current board is empty and if so return false.
	 * If none of the first if statements are true we go in to the while loop where we futher figure out if the tile can move or not.
	 * 
	 * @param row the tile row
 	 * @param col the tile col
	 * @param horizontalDirection
	 * @param verticalDirection
	 * @param dirction the direction the tile wants to move
	 * @return true if we move the tile and false if we don't
	 */
	private boolean move(int row, int col, int horizontalDirection, int verticalDirection, Direction direction) {
		if(gameWon) {
			return false;
		}
		boolean canMove = false;
		Tile current = board[row][col];
		
		if(current == null) 
			return false;
		
		boolean move = true;
		int newCol = col;
		int newRow = row;
		while(move) {
			newCol += horizontalDirection;
			newRow += verticalDirection;
			if(checkOutOfBounds(direction, newRow, newCol)) 
				break;
			if(board[newRow][newCol] == null) {
				board[newRow][newCol] = current;
				board[newRow - verticalDirection][newCol - horizontalDirection] = null;
				board[newRow][newCol].setSlideTo(new Point(newRow, newCol));
				canMove = true;
			}
			else if(board[newRow][newCol].getValue() == current.getValue() && board[newRow][newCol].canCombine()) {
				board[newRow][newCol].setCanCombine(false);
				board[newRow][newCol].setValue(board[newRow][newCol].getValue() * 2);
				canMove = true;
				board[newRow - verticalDirection][newCol - horizontalDirection] = null;
				board[newRow][newCol].setSlideTo(new Point(newRow, newCol));
			}
			else {
				move = false;
			}
		}
		return canMove;
	}
	
	/**
	 * This method checks if a move is out of bounds or not by
	 * going through each if statements of all 4 moving directions.
	 * 
	 * @param direction
	 * @param row
	 * @param col
	 * @return boolean true if out of bounds or false if not out of bounds
	 */
	private boolean checkOutOfBounds(Direction direction, int row, int col) {
		if(direction == Direction.LEFT) {
			return col < 0;
		}
		else if(direction == Direction.RIGHT) {
			return col > COLS - 1;
		}
		else if(direction == Direction.UP) {
			return row < 0;
		}
		else if(direction == Direction.DOWN) {
			return row > ROWS - 1;
		}
		return false;
	}

	/**
	 * This method moves the tiles in the given direction
	 * 
	 * @param direction the direction which the tiles are moving
	 */
	private void moveTiles(Direction direction) {
		boolean canMove = false;
		int horizontalDirection = 0;
		int verticalDirection = 0;
		
		if(direction == Direction.LEFT) {
			horizontalDirection = -1;
			for(int row = 0; row < ROWS; row++) {
				for(int col = 0; col < COLS; col++) {
					if(!canMove) {
						canMove = move(row, col, horizontalDirection, verticalDirection, direction);
					}
					else move(row, col, horizontalDirection, verticalDirection, direction);
				}
			}
		}
		
		else if(direction == Direction.RIGHT) {
			horizontalDirection = 1;
			for(int row = 0; row < ROWS; row++) {
				for(int col = COLS - 1; col >= 0; col--) {
					if(!canMove) {
						canMove = move(row, col, horizontalDirection, verticalDirection, direction);
					}
					else move(row, col, horizontalDirection, verticalDirection, direction);
				}
			}
		}
		
		else if(direction == Direction.UP) {
			verticalDirection = -1;
			for(int row = 0; row < ROWS; row++) {
				for(int col = 0; col < COLS; col++) {
					if(!canMove) {
						canMove = move(row, col, horizontalDirection, verticalDirection, direction);
					}
					else move(row, col, horizontalDirection, verticalDirection, direction);
				}
			}
		}
		
		else if(direction == Direction.DOWN) {
			verticalDirection = 1;
			for(int row = ROWS - 1; row >= 0; row--) {
				for(int col = 0; col < COLS; col++) {
					if(!canMove) {
						canMove = move(row, col, horizontalDirection, verticalDirection, direction);
					}
					else move(row, col, horizontalDirection, verticalDirection, direction);
				}
			}
		}
		
		else {
			System.out.println(direction + "is not a valid direction to move.");
		}
		
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				Tile current = board[row][col];
				if(current == null) 
					continue;
				current.setCanCombine(true);
			}
		}
		if(canMove) {
			createRandomTile();
			checkIfGameLost();
		}
	}
	
	/** 
	 * This method checks if the game is lost or not by using a 
	 * nested for loop to check if board has empty tiles or if checkSurroundingTiles.
	 * If the nested for loop dosn't exit the method, then gameLost = true and we print "You Lost" in console.
	 */
	private void checkIfGameLost() {
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				if(board[row][col] == null) 
					return;
				if(checkSurroundingTiles(row, col, board[row][col])) {
					return;
				}
			}
		}
		gameLost = true;
		System.out.println("You Lost");
	}
	
	/** 
	 * Checks for surrounding tiles
	 * 
	 * @param row to check
	 * @param col to check
	 * @param current the tile to check
	 * @return boolean
	 */
	private boolean checkSurroundingTiles(int row, int col, Tile current) {
		if(row > 0) {
			Tile check = board[row - 1][col];
			if(check == null) 
				return true;
			if(current.getValue() == check.getValue()) 
				return true;
		}
		if(row < ROWS - 1) {
			Tile check = board[row + 1][col];
			if(check == null) 
				return true;
			if(current.getValue() == check.getValue()) 
				return true;
		}
		if(col > 0) {
			Tile check = board[row][col - 1];
			if(check == null) 
				return true;
			if(current.getValue() == check.getValue()) 
				return true;
		}
		if(col < COLS - 1) {
			Tile check = board[row][col + 1];
			if(check == null) 
				return true;
			if(current.getValue() == check.getValue()) 
				return true;
		}
		return false;
	}
	
	//This method checks what keys you click and acts on it	
	private void checkKeys() {
		if(GameController.typed(KeyEvent.VK_LEFT))
			moveTiles(Direction.LEFT); // Move tiles LEFT
		
		if(GameController.typed(KeyEvent.VK_RIGHT))
			moveTiles(Direction.RIGHT); // Move tiles RIGTH
		
		if(GameController.typed(KeyEvent.VK_UP))
			moveTiles(Direction.UP); // Move tiles UP
		
		if(GameController.typed(KeyEvent.VK_DOWN))
			moveTiles(Direction.DOWN); // Move tiles DOWN
		
		if(GameController.typed(KeyEvent.VK_ESCAPE))
			System.exit(0); // Exit game
		
		if(GameController.typed(KeyEvent.VK_R)) {
			board = new Tile[ROWS][COLS];  // Restart game
			gameWon = false;
			gameLost = false;
			start();
		}
	}
	
	
	// Getters and setters
	public int getTileX(int col) {
		return SPACE_BETWEEN_TILES + col * Tile.TILE_WIDTH + col * SPACE_BETWEEN_TILES;
	}
	
	public int getTileY(int row) {
		return SPACE_BETWEEN_TILES + row * Tile.TILE_HEIGHT + row * SPACE_BETWEEN_TILES;
	}
	
	public boolean getGameWon() {
		return this.gameWon;
	}
	
	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}
	
	public boolean getGameLost() {
		return this.gameLost;
	}
	
	public void setGameLost(boolean gameLost) {
		this.gameLost = gameLost;
	}
}


