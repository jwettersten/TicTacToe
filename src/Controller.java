import java.util.Scanner;

/**
 * @author jwettersten
 *
 */
public class Controller {
	
	private Board board;
	private Player human;
	private AIPlayer computer;
	private View view;
	private Scanner consoleUserInput = new Scanner(System.in);
	
	public Controller(Board board) {
		this.board = board;
		view = new View(this, board);
		
		view.create();
		setupPlayers();
		nextMove();
	}
	
	
	
	public void playerMove(Player player) {
		
		boolean isMoveSuccessful = false;  
		
		do {
			
			System.out.print("Enter the row and column of your next move, e.g. 1 2 (first row [space] second column): ");
		      
			int row = consoleUserInput.nextInt() - 1; 
			int column = consoleUserInput.nextInt() - 1;
			
			if (attemptPlayerMove(row, column, player)) {
				isMoveSuccessful = true;  
			} else {
				System.out.println("The position (" + (row + 1) + "," + (column + 1) + ") is not available. Try again!");
			}
			
		} while (!isMoveSuccessful);
	}

	public boolean attemptPlayerMove(int rowPosition, int colPosition, Player player) {
		
		boolean returnValue = false;
		
		if (board.isBoardPositionAvailable(rowPosition, colPosition)) {
			// set the position at the positionIndex for the player type
			this.board.setPlayerBoardPosition(rowPosition, colPosition, player.getMark());
			
			returnValue = true;
			
		} else {
			// board position already occupied
			this.view.displayMessage("That position is already occupied. Try again.");
			
			returnValue = false;
		}
		
		return returnValue;
		
	}
	
	public void nextMove() {
		/**
		 *  Accept and process player moves (row and column) until there is a winner
		 */
		do {
			playerMove(human);
			
			// TODO computer move calls AI method...
			
		} while (true); // repeat until the game is over
	}
	
	public void setupPlayers() {
		// Create player instances
		System.out.println("What is your name Player X?");
		String newPlayerName = consoleUserInput.next(); 
		human = new Player(newPlayerName, 1);
		computer = new AIPlayer(board);
		System.out.println("Welcome to Tic Tac Toe " + human.getName() + "!");
	}
	
}
