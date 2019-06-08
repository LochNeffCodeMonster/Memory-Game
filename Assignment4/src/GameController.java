
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class GameController is a class for responding to events originating from the game user interface (GUI). Actions performed include
 * exiting GUI; check equivalence of two Card objects; and print to console, the number of clicks required by the user
 * to match all pairs of Card objects. 
 * @author JonathanNeff
 *
 */

public class GameController implements ActionListener {
	
	/**
	 * Class properties:
	 * cardsSelected     number of Card objects currently selected for comparison.
	 * totalClicks     total number of clicks by user to complete game.
	 * selectedCard1     first Card object selected for comparison by user.
	 * selectedCard2     second Card object selected for comparison by user. 
	 * numberOfPairs     total number of pairs of Card objects in the current state of GUI.
	 * numberOfMatches     total number of pairs of matched Card objects in the current state of GUI.         
	 */
	private int cardsSelected = 0;
	private int totalClicks = 0; 
	private Card selectedCard1;
	private Card selectedCard2;
	private int numberOfPairs;
	private int numberOfMatches;
	
	/**
	 * Class constructor instantiates a GameController. 
	 * @param numberOfPairs     total number of pairs of Card objects in the current state of GUI.
	 */
	public GameController(int numberOfPairs) {
		this.numberOfPairs = numberOfPairs;
	}

	/**
	 * The actionPerformed method responds to action events originating from the GUI by the user. 
	 * If the JButton "EXIT" is selected, exit GUI. 
	 * If cardsSelected = 1; turn selected Card object face up. 
	 * If cardsSelected = 2; turn selected Card object face up if not already faceUp; check equivalence between selectedCard1
	 * 		and selectedCard2. If equivalent, selected cards remain face up. Otherwise they are returned to their initial state. 
	 * If numberOfPairs == numberOfMatches, exit game and print to console the number of clicks required by the user
	 * 		to match all pairs of Card objects. 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		cardsSelected ++; totalClicks ++;
		
		if (arg0.getActionCommand().equals("EXIT")) {
			System.exit(0);
		}
		
		else if(cardsSelected == 1) {
			Object card = arg0.getSource();
			Card clickedCard1 = (Card) card;
			clickedCard1.faceUp();
			selectedCard1 = clickedCard1;
		}
		
		else if(cardsSelected == 2) {
			Object card = arg0.getSource();
			Card clickedCard2 = (Card) card;	
			if(clickedCard2.isFaceDown() == true) {
				clickedCard2.faceUp();
				selectedCard2 = clickedCard2;
			
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			
				if(selectedCard1.equals(selectedCard2)) {
					cardsSelected --; cardsSelected --;
					selectedCard1.setEnabled(false);
					selectedCard2.setEnabled(false);
					selectedCard1 = null;
					selectedCard2 = null;
					numberOfMatches ++;
				
					if(numberOfMatches == numberOfPairs) {
						System.out.println("Total Moves: " + totalClicks);
						System.out.println("Congratulations you won!");
					}
				}
			
				else if(!selectedCard1.equals(selectedCard2)) {
					selectedCard1.faceDown();
					selectedCard2.faceDown();
					selectedCard1 = null;
					selectedCard2 = null;
					cardsSelected --; cardsSelected --;
					}
				}
			else {
				cardsSelected--;
			}
		}
	}
}
