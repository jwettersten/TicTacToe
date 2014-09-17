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
		view = new View(board);
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
	
	public void attemptMove(Player player) {
		
		boolean moveIsNotSuccessful = true;  
		
		while (moveIsNotSuccessful) {
			
			view.requestPlayerMove(player.getName());
			
			if (player.performMove(board)) {
				
				moveIsNotSuccessful = false;  
				
			} else {

				view.informPlayerMoveIsNotAvailable();
			}
			
			
		}
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
	

	public void playGame() {
		Player currentPlayer = human;
		while (gameNotOver) {
			attemptMove(currentPlayer);
			checkScore(currentPlayer);
			currentPlayer = swapPlayer(currentPlayer);
		}
	}

	private Player swapPlayer(Player currentPlayer) {
		if (currentPlayer == computer) {
		  currentPlayer = human;
		} else {
		  currentPlayer = computer;
		}
		return currentPlayer;
	}
	
	
}
