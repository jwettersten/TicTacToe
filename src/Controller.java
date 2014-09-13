import java.util.Scanner;

/**
 * @author jwettersten
 *
 */
public class Controller {
	
	private Board board;
	private Player human;
	private AIPlayerMinimax computer;
	private View view;
	private Scanner consoleUserInput = new Scanner(System.in);
	
	public Controller(Board board) {
		
		/**
		 * Start the game:
		 * - Create a new game board + view
		 * - Create a new player with a unique name provided by the player
		 * - Prompt player for their next move
		 */
		
		this.board = board;
		view = new View(this, board);
		
		view.create();
		setupPlayers();
		nextMove();
	}
	
	public void aiPlayerMove(AIPlayerMinimax player) {
		
		boolean isMoveSuccessful = false;  
		
		int[] aiRowColumnMove = player.move();
		
		do {
		      
			int row = aiRowColumnMove[0]; 
			int column = aiRowColumnMove[1];
			
			if (attemptPlayerMove(row, column, player)) {
				isMoveSuccessful = true;  
			} else {
				view.displayMessage("The position (" + (row + 1) + "," + (column + 1) + ") is not available. Try again!");
			}
			
		} while (!isMoveSuccessful);
	}
	
	public void playerMove(Player player) {
		
		boolean isMoveSuccessful = false;  
		
		do {
			
			view.displayMessage("Enter the row and column of your next move, e.g. 1 2 (first row [space] second column): ");
		      
			int row = consoleUserInput.nextInt() - 1; 
			int column = consoleUserInput.nextInt() - 1;
			
			if (attemptPlayerMove(row, column, player)) {
				isMoveSuccessful = true;  
			} else {
				view.displayMessage("The position (" + (row + 1) + "," + (column + 1) + ") is not available. Try again!");
			}
			
			
		} while (!isMoveSuccessful);
	}

	public boolean attemptPlayerMove(int rowPosition, int colPosition, Player player) {
		
		boolean returnValue = false;
		
		if (board.isBoardPositionAvailable(rowPosition, colPosition)) {
			// set the position at the positionIndex for the player type
			this.board.setPlayerBoardPosition(rowPosition, colPosition, player.getMark());
			
			returnValue = true;
			
		}
		
		return returnValue;
		
	}
	
	public boolean hasPlayerWon(Player player) {
		return board.hasPlayerWon(player.getMark());
	}
	
	public void nextMove() {
		boolean gameNotOver = true;
		
		/**
		 *  Accept and process player moves (row and column) until there is a winner
		 */
		do {
			playerMove(human);
			
			// check score
			if (hasPlayerWon(human)) {
				gameNotOver = false;
				
				view.displayMessage(human.getName() + " has won!");
			} else if (computer.evaluate() == 0) {
				gameNotOver = false;
				
				view.displayMessage("It's a tie!");
				
				break;
			}
			
			aiPlayerMove(computer);
			// check score
			if (hasPlayerWon(computer)) {
				gameNotOver = false;
				
				view.displayMessage(computer.getName() + " has won!");
			}
			
		} while (gameNotOver); // repeat until the game is over
	}
	
	public void setupPlayers() {
		// Create player instances
		view.displayMessage("What is your name Player X?");
		String newPlayerName = consoleUserInput.next(); 
		human = new Player(newPlayerName, 1);
		computer = new AIPlayerMinimax(board);
		view.displayMessage("Welcome to Tic Tac Toe " + human.getName() + "!");
	}
	
}
