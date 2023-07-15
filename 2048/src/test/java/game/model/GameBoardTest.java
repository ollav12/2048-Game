package game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GameBoardTest {
	
	@Test
	void testStart() {
		GameBoard gameBoard = new GameBoard(4, 4);
		Tile[][] board = new Tile[4][4];
		int amountOfTiles = 0;
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 4; col++) {
				if(board[row][col] == null)
					continue;
				amountOfTiles += 1;
				
				}
			}
		assertEquals(2, amountOfTiles);
	}
	
	@Test
	void testCreateRandomTile() {	
	}
}
