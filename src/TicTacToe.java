
import java.util.Scanner;

/**
 * @author jwettersten
 *
 */
public class TicTacToe {

	public static Scanner consoleUserInput = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		/**
		 * Start the game:
		 * - Create a new game board
		 * - Create a new player with a unique name provided by the player
		 * - Prompt player for their next move
		 */
		Board gameBoard = new Board();
		Controller gameController = new Controller(gameBoard);
		
		// get player name
		System.out.println("What is your name Player X?");
		
		String name = args[0];
		
		if (name == null) {
			// give player default name, "Player X"
			name = "Player X";
		}
		
		// Create player instances
		Player human = new Player(name, 'X');
		// Computer
		Player computer = new Player("Computer", 'O');
		
		// start game
		System.out.println("Welcome " + human.getName() + "!");
		
		/**
		 *  Accept and process moves (row and column) for both players (human vs. computer)
		 *  until there is a winner
		 */
		do {
			acceptPlayerMove(args, gameController, human);
			
			// TODO computer move calls AI method...
			
		} while (true); // repeat if not game-over
		
		
	}
	
	public static void acceptPlayerMove(String[] args, Controller gameController, Player player) {
		
		boolean isMoveSuccessful = false;  
		
		do {
			
			System.out.print("Enter the row and column of your next move, e.g. 1 2 (first row [space] second column): ");
		      
			int row = consoleUserInput.nextInt() - 1; 
			int column = consoleUserInput.nextInt() - 1;
			
			if (gameController.attemptPlayerMove(row, column, player)) {
				isMoveSuccessful = true;  
			} else {
				System.out.println("This move at (" + (row + 1) + "," + (column + 1) + ") is not valid. Try again...");
			}
			
		} while (!isMoveSuccessful);
	}

}
