package game.model;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TileTest {
	
	@Test
	void testTile() {
		Tile tile = new Tile(2, 3, 3);
		
		assertEquals(2, tile.getValue());
		assertEquals(3, tile.getX());
		assertEquals(3, tile.getY());
	}
	
	@Test
	void testAssignTileColorAndTextColor() {
		Tile tile1 = new Tile(2, 5, 5);
		assertEquals(new Color(225, 227, 228), tile1.getTileColor());
		assertEquals(Color.BLACK, tile1.getTextColor());
		
		Tile tile2 = new Tile(4, 5, 5);
		assertEquals(new Color(251, 250, 217), tile2.getTileColor());
		assertEquals(Color.BLACK, tile2.getTextColor());
		
		Tile tile3 = new Tile(8, 5, 5);
		assertEquals(new Color(237, 148, 77), tile3.getTileColor());
		assertEquals(Color.WHITE, tile3.getTextColor());
		
		Tile tile4 = new Tile(15, 5, 5);
		assertEquals(Color.RED, tile4.getTileColor());
		assertEquals(Color.RED, tile4.getTextColor());
	}
}
