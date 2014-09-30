
public class MockController {
	private Board board;
	private Player human;
	private Player computer;
	private View view;
	private int currentTurn = Constants.CROSS;
	
	private boolean gameNotOver = true;
	
	public MockController(Board board) {

		this.board = board;
	}
	
	public void setupPreferredView(View preferredView) {
		view = preferredView;
		view.create();
	}
	
	public void setHumanPlayer(Player newHumanPlayer) {
		human = newHumanPlayer;
	}
	
	public void setComputerPlayer(Player newComputerPlayer) {
		computer = newComputerPlayer;
	}
	
	public void playGame() {

		while (gameNotOver) {
			// wait for next input from whatever player
			// add threaded sleep
			if (currentTurn == computer.getMark()) {
				attemptMove(computer);
			}
			try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
	}
	
	public void attemptMove(Player player) {
		
		performMove(player);
	}
	
	public void attemptRowColumnMove(int row, int col, Player requestingPlayer) {

		Player player = human;
		
		// transform playerMark into appropriate player type
		if (requestingPlayer.getMark() == Constants.CROSS) {
			player = this.human;
			player.moveBehavior.setRow(row);
			player.moveBehavior.setColumn(col);

		} else {
			player = this.computer;
		}
		
		performMove(player);
	}
	
	// need to add logic to control turns
	// need to support computer to constantly check if it's computers turn
	private void performMove(Player player) {

		if (gameNotOver && player.getMark() == currentTurn) {
			if (player.performMove()) {
				
				checkScore(player);
				player = swapPlayer(player);
	
			} else {
	
				view.informPlayerMoveIsNotAvailable();
			}
		} else {
			view.informPlayerNotTheirTurn();
		}
	}
	
	public void checkScore(Player player) {
		if (board.hasPlayerWon(player.getMark())) {
			
			view.displayWinner(player.getName());
			endGame();
			
		} else if (board.isFull()) {
			
			view.displayTie();
			endGame();
			
		} 
	}

	private Player swapPlayer(Player currentPlayer) {
		if (currentPlayer == computer) {
		  currentPlayer = human;
		} else {
		  currentPlayer = computer;
		}

		currentTurn = currentPlayer.getMark();
		
		return currentPlayer;
	}
	
	private void endGame() {
		gameNotOver = false;
	}
}
