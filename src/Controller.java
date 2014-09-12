/**
 * 
 */

/**
 * @author jwettersten
 *
 */
public class Controller {
	
	Board board;
	View view;
	
	public Controller(Board board) {
		this.board = board;
		view = new View(this, board);
	}
	
	public void startGame() {
		// setup the game
		// setup score?
	}
	
	public void endGame() {
		// end the game
		// trigger final score
	}

	public boolean attemptPlayerMove(int rowPosition, int colPosition, Player player) {
		
		boolean returnValue = false;
		
		if (board.boardPositionAvailable(rowPosition, colPosition)) {
			// set the position at the positionIndex for the player type
			this.board.setPlayerBoardPosition(rowPosition, colPosition, player.getType());
			
			returnValue = true;
			
		} else {
			// board position already occupied
			this.view.displayMessage("That position is already occupied. Try again.");
			
			returnValue = false;
		}
		
		// Update display - consider refactoring view as an observer of the TTTBoard model
		this.view.displayBoard();
		
		return returnValue;
		
	}
	
}
