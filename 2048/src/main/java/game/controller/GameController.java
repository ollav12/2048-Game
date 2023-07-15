package game.controller;

import java.awt.event.KeyEvent;

public class GameController {
	
	// Field Variables
	private static boolean[] pressedKeysArray = new boolean[100];
	private static boolean[] previouslyPressedKeysArray = new boolean[100];

	// Methods
	// Update method
	public static void update() {
		for(int i = 0; i < 6; i++) {
			if(i == 0)
				previouslyPressedKeysArray[KeyEvent.VK_LEFT] = pressedKeysArray[KeyEvent.VK_LEFT];
			if(i == 1)
				previouslyPressedKeysArray[KeyEvent.VK_RIGHT] = pressedKeysArray[KeyEvent.VK_RIGHT];
			if(i == 2) 
				previouslyPressedKeysArray[KeyEvent.VK_UP] = pressedKeysArray[KeyEvent.VK_UP];
			if(i == 3) 
				previouslyPressedKeysArray[KeyEvent.VK_DOWN] = pressedKeysArray[KeyEvent.VK_DOWN];
			if(i == 4) 
				previouslyPressedKeysArray[KeyEvent.VK_ESCAPE] = pressedKeysArray[KeyEvent.VK_ESCAPE];
			if(i == 5) 
				previouslyPressedKeysArray[KeyEvent.VK_R] = pressedKeysArray[KeyEvent.VK_R];
			}
	}
	
	// Key pressed
	public static void keyPressed(KeyEvent e) {
		pressedKeysArray[e.getKeyCode()] = true;
	}
	
	// Key released
	public static void keyReleased(KeyEvent e) {
		pressedKeysArray[e.getKeyCode()] = false;
	}
	
	// Typed
	public static boolean typed(int keyEvent) {
		return !pressedKeysArray[keyEvent] && previouslyPressedKeysArray[keyEvent];
	}
}

