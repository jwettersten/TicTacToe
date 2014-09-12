
/**
 * @author jwettersten
 *
 */
public class TicTacToe {
	
	public static void main(String[] args) {
		
		/**
		 * Start the game:
		 * - Create a new game board
		 * - Create a new player with a unique name provided by the player
		 * - Prompt player for their next move
		 */
		Board gameBoard = new Board();
		Controller gameController = new Controller(gameBoard);

	}

}
