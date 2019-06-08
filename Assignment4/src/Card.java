
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * Class Card is a class that generates a Card object to displayed in the game user interface (GUI). 
 * @author JonathanNeff
 *
 */

public class Card extends JButton {
	
	/**
	 * Class properties:
	 * width	     length (width) in pixels of Card object.
	 * height     length (height) in pixels of Card object.
	 * faceColor	     color of front of Card object.
	 * backgroundColor     color of back of Card object. 
	 * isFaceDown     true if Card object is face down, or false if Card object is face up.  
	 */
	private static int width = 75;
	private static int height = 125;
	private Color faceColor;
	private static Color backgroundColor = Color.LIGHT_GRAY;
	private boolean isFaceDown = true; 
	
	/**
	 * Class constructor instantiates a Card of a specified faceColor.
	 * @param faceColor     color of front of Card.
	 */
	public Card(Color faceColor) {
		this.faceColor = faceColor;
		setPreferredSize(new Dimension(width, height));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		setBackground(backgroundColor);
		setOpaque(true);
		faceDown();
	}
	
	/**
	 * The faceUp method visually turns the card over by setting the background color to its faceColor. 
	 */
	public void faceUp() {
		isFaceDown = false;
		changeColor(faceColor);
	}
	
	/**
	 * The faceDown method visually turns the card over by setting the background color to its backgroundColor.  
	 */
	public void faceDown() {
		isFaceDown = true;
		changeColor(backgroundColor);
	}
	
	/**
	 * The equals method test the equality of two Card objects, by comparing their corresponding faceColor. 
	 */
	@Override
	public boolean equals(Object other) {
		Card otherCard = (Card) other;
		return faceColor.equals(otherCard.faceColor);
	}
	
	/**
	 * The changeColor method visually changes the background of the Card object to a specified Color c. 
	 * @param c     the Color to change the background too.  
	 */
	public void changeColor(Color c) {
		setBackground(c);
		paintImmediately(0, 0, width, height);
	}
	
	/**
	 * The isFaceDown method returns true or false depending on whether the Card object is face up or face down. 
	 * @return
	 */
	public boolean isFaceDown() {
		return isFaceDown;
	}
}
