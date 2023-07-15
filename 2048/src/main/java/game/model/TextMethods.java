package game.model;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;

public class TextMethods {

	
	//Methods
	/**
	 * This method returns the text width
	 * We first set the font and then calculate the textWidth by using
	 * other library methods and then return the textWidth as an int
	 * 
	 * @param text the text we are calculating the width of
	 * @param font the font of the text
	 * @param g the Graphics2D
	 * @return textWidth
	 */
	public static int getTextWidth(String text, Font font, Graphics2D g) {
		g.setFont(font);
		int textWidth = g.getFontMetrics().stringWidth(text);
		return textWidth;
	}
	
	/**
	 * This method returns the text height
	 * We first set the font and then create a TextLayout object,
	 * and then use this object to get the height of the text
	 * and return textHeight as an int
	 * 
	 * @param text the text we are calculating the height of
	 * @param font the font of the text
	 * @param g the Graphics2D
	 * @return textHeigth
	 */
	public static int getTextHeight(String text, Font font, Graphics2D g) {
		g.setFont(font);
		TextLayout tl = new TextLayout(text, font, g.getFontRenderContext());
		int textHeigth = (int) tl.getBounds().getHeight();
		return textHeigth;
	}
}
