package game.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Tile {
	// Constants
	public static final int TILE_WIDTH = 115;
	public static final int TILE_HEIGHT = 115;
	public static final int TILE_MOVE_SPEED = 25;
	private final Font tile_text_font = new Font("Arial", 0, 40);
	
	
	// Field Variables
	private BufferedImage tileImage;
	private int tile_value;
	private Color tile_color;
	private Color text_color;
	private Point slideTo;
	private int x;
	private int y;
	private boolean canCombine = true;
	
	
	// Constructor
 	public Tile(int value, int x, int y) {
 		this.tile_value = value;
 		this.x = x;
 		this.y = y;
 		slideTo = new Point(x, y);
 		tileImage = new BufferedImage(TILE_WIDTH, TILE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
 		drawTileOnBoard(); // Calls drawTileOnBoard()
 	}
 	
 	
 	// Methods
 	/**
 	 * This method draws a tile on a board
 	 * We use if statements to check the tile_value,
 	 * and then save tile_color and text_color in variables.
 	 * These variables are used after the if statements
 	 * to draw the tile on the board.
 	 */
	private void drawTileOnBoard() {
		Graphics2D g = (Graphics2D) tileImage.getGraphics();
		
		// Here we call a method that assigns tile_color and text_color
		assignTileColorAndTextColor();
		
		// Set tile color and draw tile on board
		g.setColor(tile_color);
		g.fillRect(0, 0, TILE_WIDTH, TILE_HEIGHT);
		
		// Set text color and font style
		g.setColor(text_color);
		g.setFont(tile_text_font);
		
		// Find center x and y coordinates for the tile
		int center_x = (TILE_WIDTH / 2) - TextMethods.getTextWidth("" + tile_value, tile_text_font, g) / 2;
		int center_y = (TILE_HEIGHT / 2) + TextMethods.getTextHeight("" + tile_value, tile_text_font, g) / 2;
		
		// Draw tile_value in the center of tile
		g.drawString("" + tile_value, center_x, center_y);
		g.dispose();
	}
	
	/**
	 * This method finds the tile color and text according to the tile value
	 * The method goes through a bunch of if and if else statements
	 * and checks if the tile_value is a match or not. If a statement succeeds then
	 * tile_coloor and text_color gets assigned color values according to the tile_value.
	 */
	private void assignTileColorAndTextColor() {
		if(tile_value == 2) {
			tile_color = new Color(225, 227, 228); // Light Gray
			text_color = Color.BLACK;
		}
		else if (tile_value == 4) {
			tile_color = new Color(251, 250, 217); // Light Yellow
			text_color = Color.BLACK;
		}
		else if (tile_value == 8) {
			tile_color = new Color(237, 148, 77); // Orange
			text_color = Color.WHITE;
		}
		else if (tile_value == 16) {
			tile_color = new Color(204, 62, 62); // Light Red
			text_color = Color.WHITE;
		}
		else if (tile_value == 32) {
			tile_color = new Color(179, 63, 64); // Red
			text_color = Color.WHITE;
		}	
		else if (tile_value == 64) {
			tile_color = new Color(118, 31, 23); // Dark Red
			text_color = Color.WHITE;
		}	
		else if (tile_value == 128) {
			tile_color = new Color(241, 217, 0); //Yellow
			text_color = Color.WHITE;
		}	
		else if (tile_value == 256) {
			tile_color = new Color(241, 217, 0); // Yellow
			text_color = Color.WHITE;
		}	
		else if (tile_value == 512) {
			tile_color = new Color(241, 217, 0); // Yellow
			text_color = Color.WHITE;
		}	
		else if (tile_value == 1024) {
			tile_color = new Color(241, 217, 0); // Yellow
			text_color = Color.WHITE;
		}	
		else if (tile_value == 2048) {
			tile_color = new Color(241, 217, 0); // Yellow
			text_color = Color.WHITE;
		}
		
		// If for some reason none of the if statements work we then use this else statement and print an error message to console
		else {
			tile_color = Color.RED;
			text_color = Color.RED;
			System.out.println("Error, value does not exist");
		}
	}
	
	// Draw everything together
	public void render(Graphics2D g) {
		g.drawImage(tileImage, x, y, null);
	}
	
	
	// Getters and setters
	public Color getTileColor() {
		return this.tile_color;
	}
	
	public Color getTextColor() {
		return this.text_color;
	}
	
	public int getValue() {
		return this.tile_value;
	}
	
	public void setValue(int value) {
		this.tile_value = value;
		drawTileOnBoard();
	}

	public boolean canCombine() {
		return this.canCombine;
	}

	public void setCanCombine(boolean canCombine) {
		this.canCombine = canCombine;
	}

	public Point getSlideTo() {
		return this.slideTo;
	}

	public void setSlideTo(Point slideTo) {
		this.slideTo = slideTo;
	}
	
	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}
}