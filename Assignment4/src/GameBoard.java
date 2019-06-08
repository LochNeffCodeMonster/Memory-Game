
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * Class GameBoard is a class that creates the game user interface (GUI) in which the user will interact with.
 * @author JonathanNeff
 *
 */
public class GameBoard extends JFrame{
	public static void main(String [] args) {
		System.out.println("Please enter the number of pairs of cards: ");
		Scanner input = new Scanner(System.in);
		int userInput = Integer.parseInt(input.nextLine());
		GameBoard gameBoard = new GameBoard(userInput);
		gameBoard.setVisible(true);
		input.close();
	}
	
	/**
	 * Class properties:
	 * numberOfPairs     total number of pairs of Card objects in the current state of GUI.
	 * cardPanel     JPanel for holding the Card objects.
	 * buttonPanel     JPanel for holding the JButtons. 
	 * messagePanel     JPanel for holding the JLabel. 
	 * statusLabel     JLabel for displaying messages. 
	 * integers     an ArrayList of integers used for assigning a Color to a Card object. 
	 * cards     an ArrayList of Card objects.  
	 */
	private Container container = getContentPane();
	private int numberOfPairs;
	private JPanel cardPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel messagePanel = new JPanel();
	private JLabel statusLabel;
	private ArrayList<Integer> integers = new ArrayList<Integer>();
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	/**
	 * Class constructor GameBoard creates an instance of Concentration Game.
	 * Creates the necessary components of the GUI; cardPanel, gameController, Card objects, buttonPanel and messagePanel. 
	 * These necessary components are then added to the JFrame. 
	 * @param pairs     total number of pairs of Card objects to be displayed in GUI. 
	 */
	public GameBoard(int pairs) {
		int numberOfPairs = pairs;
		
		setSize(600, 600);
		setTitle("Concentration Game");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Determine grid layout based on number of pairs.
		int[] grid = getLayout(numberOfPairs);
		int x = grid[0]; int y = grid[1];
		
		//Create, then add JPanel containing Card objects to Content Pane.
		cardPanel.setLayout(new GridLayout(x, y, 10, 10));
		cardPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		getContentPane().add(cardPanel, BorderLayout.PAGE_START);
		
		//Create gameController. 
		GameController gc = new GameController(numberOfPairs);
		
		//Create ArrayList of integers for assigning the faceColor to a Card object.  
		for(int i = 0; i < numberOfPairs; i++) {
			integers.add(i); integers.add(i);
		}
		
		//Shuffle integers for card randomization. 
		Collections.shuffle(integers);
		Collections.shuffle(integers);
		
		//Create Card object, then add to JPanel and ArrayList cards.
		for(Integer integer: integers) {
			Card card = new Card(getColor(integer));
			cards.add(card);
			cardPanel.add(card);
			card.addActionListener(gc);
		}
		
		//Create, then add JPanel to ContentPane.
		buttonPanel = createButtonPanel(gc);
		add(buttonPanel, BorderLayout.CENTER);
		
		//Create, then add JPanel to ContentPane.
		messagePanel = createMessagePanel();
		add(messagePanel, BorderLayout.PAGE_END);
		
		pack();
	}

	/**
	 * The getColor method assigns a faceColor to a Card object. Colors to choose from include red, yellow, green, blue, magenta and orange. 
	 * @param colorValue    integer value used for assigning a faceColor to a Card object. 
	 * @return
	 */
	private Color getColor(int colorValue) {
		int value = colorValue % 6;
		
		if (value == 1) {
			return Color.RED;
		}
		else if (value == 2) {
			return Color.YELLOW;
		}
		else if (value == 3) {
			return Color.GREEN;
		}
		else if (value == 4) {
			return Color.BLUE;
		}
		else if (value == 5) {
			return Color.MAGENTA;
		}
		else if (value == 0) {
			return Color.ORANGE;
		}
		return null;
	}
	
	/**
	 * The getLayout method calculates the largest prime factor of the number of Card objects.
	 * The number of Card objects is divided by the largest prime factor, continuously reducing by 1, until the operation yields no remainder.
	 * Used for determining the most fit gridLayout for holding the Card objects.  
	 * @param numPairs     number of pairs of Card objects. 
	 * @return array     an array containing two integers that represent the number of rows and columns of the gridLayout. 
	 */
	private int[] getLayout(int numPairs) {
		int numberOfCards = numPairs*2;
		int[] array = new int[2];
		int i = 1, result = 1;
		
		if(numberOfCards == 0) {
			System.out.println("Invalid number of pairs!");
			System.exit(0);
		}
		else if(numberOfCards == 2) {
			array[0] = 2; array[1] = 1;
            return array;
		}
		else if(numberOfCards > 3) {
			while (result < numberOfCards) {
				if (result == numberOfCards) {
					array[0] = i; array[1] = i;
					return array;
				}
				i++;
				result = i * i;
			}
        int value =  i - 1;
        while (value > 0) {
        		if(numberOfCards % value == 0) {
        			int y = numberOfCards / value;
        			int min = Math.min(value, y);
        			int max = Math.max(value, y);
        			array[0] = min; array[1] = max;
        			return array;
        		}
        		value--;
        		}
		}
		return null;
	}
	
	/**
	 * The createButtonPanel method creates a JPanel containing a JButton, which allows the user to exit the GUI.
	 * An actionListener is assigned to the JButton to handle an ActionEvent; being clicked on by the user.   
	 * @param gc     GameController for handling an actionEvent.
	 * @return buttonPanel    JPanel containing the JButton. 
	 */
	private JPanel createButtonPanel(GameController gc) {
		buttonPanel.setLayout(new FlowLayout()); 
		buttonPanel.setPreferredSize(new Dimension(40, 40));
		
		JButton exitButton = new JButton("EXIT");
		exitButton.addActionListener(gc);
	    buttonPanel.add(exitButton);
	 
	    return buttonPanel;
	}
	
	/**
	 * The createMessagePanel method creates a JPanel containing a JLabel, which displays messages to the user. 
	 * @return messagePanel     JPanel containing the JLabel. 
	 */
	private JPanel createMessagePanel() {
		messagePanel.setLayout(new BorderLayout());
		messagePanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		statusLabel = new JLabel("No message");
		messagePanel.add(statusLabel, BorderLayout.CENTER);

		return messagePanel;
	}
}

