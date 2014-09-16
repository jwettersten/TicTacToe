import java.util.Scanner;

/**
 * @author jwettersten
 *
 */
public class Controller {
	
	private Board board;
	private Player human;
	private Player computer;
	private View view;
	private Scanner consoleUserInput = new Scanner(System.in);
	private boolean gameNotOver = true;
	
	public Controller(Board board) {

		// Create a new game board + view
		this.board = board;
		view = new View(this, board);
		view.create();
	}
	
	
	public void createHumanPlayerWithName() {
		view.requestPlayerName();
		String newPlayerName = consoleUserInput.next(); 
		human = new HumanPlayer(newPlayerName, 1);
		view.welcomePlayerName("Welcome to Tic Tac Toe " + human.getName() + "!");
	}
	
	public void createComputerPlayer() {
		computer = new AIPlayerMinimax("Copmuter", board);
	}
	
	public void aiPlayerMove(Player player) {
		
		boolean isMoveSuccessful = true;  
		
		do {
			
			int[] aiRowColumnMove = player.performMove();  
			
			int row = aiRowColumnMove[0]; 
			int column = aiRowColumnMove[1];
			
			isMoveSuccessful = attemptMove(player, board, row, column);
			
		} while (!isMoveSuccessful);
	}
	
	public void playerMove(Player player) {
		
		boolean isMoveSuccessful = false;  
		
		do {
			
			view.requestPlayerMove();
		     
			int[] result = player.performMove();
			int row = result[0]; 
			int column = result[1];
			
			isMoveSuccessful = attemptMove(player, board, row, column);
			
		} while (!isMoveSuccessful);
	}
	
	private boolean attemptMove(Player player, Board board, int row, int column) {
		
		boolean returnValue = false;
		
		if (player.attemptToMakeBoardMove(board, row, column)) {
			returnValue = true;  
		} else {
			
			view.displayMessage("The position (" + (row + 1) + "," + (column + 1) + ") is not available. Try again!");
		}
		
		return returnValue;
	}
	
	public void checkScore(Player player) {
		if (board.hasPlayerWon(player.getMark())) {
			
			view.displayMessage(player.getName() + " has won!");
			gameNotOver = false;
			
		} else if (board.isFull()) {
			
			view.displayMessage("It's a tie!");
			gameNotOver = false;
			
		} 

	}
	
	// could the game loop be something like while game not over take next move
	
	// Check Fowler Refactoring book on removing efficiency for the sake of design
	// consider passing in players
	public void playGame() {

		while (gameNotOver) {
			
			playerMove(human);
			// is game over
			
			checkScore(human);
			
			if (gameNotOver) {
				aiPlayerMove(computer);
				checkScore(computer);
			} else {
				break;
			}
			
		}
	}
	
	
	
}
