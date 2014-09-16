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
	private boolean gameOver = false;
	
	public Controller(Board board) {

		// Create a new game board + view
		this.board = board;
		view = new View(this, board);
		view.create();
	}
	
	
	public void createHumanPlayerWithName() {
		view.requestPlayerName();
		String newPlayerName = consoleUserInput.next(); 
		human = new Player(newPlayerName, 1);
		view.welcomePlayerName("Welcome to Tic Tac Toe " + human.getName() + "!");
	}
	
	public void createComputerPlayer() {
		computer = new AIPlayerMinimax(board);
	}
	
	public void aiPlayerMove(AIPlayerMinimax player) {
		
		boolean isMoveSuccessful = true;  
		
		do {
			
			int[] aiRowColumnMove = player.move();  
			
			int row = aiRowColumnMove[0]; 
			int column = aiRowColumnMove[1];
			
			isMoveSuccessful = attemptMove(player, board, row, column);
			
		} while (!isMoveSuccessful);
	}
	
	public void playerMove(Player player) {
		
		boolean isMoveSuccessful = false;  
		
		do {
			
			view.displayMessage("Enter the row and column of your next move, e.g. 1 2 (first row [space] second column): ");
		      
			int row = consoleUserInput.nextInt() - 1; 
			int column = consoleUserInput.nextInt() - 1;
			
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
			gameOver = true;
			
		} else if (board.isFull()) {
			
			view.displayMessage("It's a tie!");
			gameOver = true;
			
		} 

	}
	
	// could the game loop be something like while game not over take next move
	
	// Check Fowler Refactoring book on removing efficiency for the sake of design
	
	public void playGame() {

		do {
			
			playerMove(human);
			// is game over
			
			checkScore(human);
			
			if (!gameOver) {
				aiPlayerMove(computer);
				checkScore(computer);
			} else {
				break;
			}
			
		} while (!gameOver); // repeat until the game is over
	}
	
	
	
}
