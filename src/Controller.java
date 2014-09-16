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
	
	public void playerMove(Player player) {
		
		boolean isMoveSuccessful = false;  
		
		do {
			
			view.requestPlayerMove(player.getName());
		     
			int[] result = player.performMove();
			
			isMoveSuccessful = attemptMove(player, board, result[0], result[1]);
			
		} while (!isMoveSuccessful);
	}
	
	private boolean attemptMove(Player player, Board board, int row, int column) {
		
		boolean returnValue = false;
		
		if (player.attemptToMakeBoardMove(board, row, column)) {
			returnValue = true;  
		} else {

			view.informPlayerMoveIsNotAvailable(String.valueOf(row + 1), String.valueOf(column + 1));
		}
		
		return returnValue;
	}
	
	public void checkScore(Player player) {
		if (board.hasPlayerWon(player.getMark())) {
			
			view.displayWinner(player.getName());
			gameNotOver = false;
			
		} else if (board.isFull()) {
			
			view.displayTie();
			gameNotOver = false;
			
		} 

	}
	
	// could the game loop be something like while game not over take next move
	
	// Check Fowler Refactoring book on removing efficiency for the sake of design
	// consider passing in players
	public void playGame() {

		while (gameNotOver) {
			
			playerMove(human);
			checkScore(human);
			
			if (gameNotOver) {
				playerMove(computer);
				checkScore(computer);
			} else {
				break;
			}
			
		}
	}
	
	
}
