package game;

import javax.swing.JFrame;

import game.model.Game;

public class GameMain {

	public static void main(String[] args) {
		// Creates necessary objects
		Game game = new Game(); // Creates Game object
		
		
		// Creates JFrame and sets necessary settings
		JFrame window = new JFrame("2048"); // Creates JFrame object window with title "2048"
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false); // Makes the window size final so users can't change window size
		window.add(game); // Adds game (JPanel) to window (JFrame)
		window.pack(); // Initializes the prefferedSize and sizes the window
		window.setLocationRelativeTo(null);
		window.setVisible(true); // Makes the window visible if true or not visible if false
		
		
		// Starts Game
		game.startGame();
	}
}
