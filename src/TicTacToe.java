
/**
 * @author jwettersten
 *
 */
public class TicTacToe {
	
	public static void main(String[] args) {
		
		// Setup the game
		Board gameBoard = new Board();
		Controller gameController = new Controller(gameBoard);
		
		gameController.createHumanPlayerWithName();
		gameController.createComputerPlayer();
		
		gameController.playGame();

	}

}
