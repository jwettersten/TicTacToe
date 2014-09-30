public class Controller {
	
	private Board board;
	private Player computer;
	private View view;
	private int currentPlayerTurn = Constants.CROSS;
	
	private boolean gameNotOver = true;
	
	public Controller(Board board) {
		this.board = board;
	}
	
	public void setupPreferredView(View preferredView) {
		view = preferredView;
		view.create();
	}
	
	public void setComputerPlayer(Player newComputerPlayer) {
		computer = newComputerPlayer;
	}
	
	public void playGame() {
		while (gameNotOver) {

			if (isCurrentTurn(computer)) {
				attemptMove(computer);
			}
			
			try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
	}

	private boolean isCurrentTurn(Player player) {
		return currentPlayerTurn == player.getMark();
	}
	
	public void attemptMove(Player player) {
		if (isCurrentTurn(player)) {
			if (player.performMove()) {
				
				checkScore(player);
				changeCurrentPlayer(player);
	
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
	
	private void changeCurrentPlayer(Player currentPlayer) {
		if (currentPlayer.getMark() == Constants.CROSS) {
			currentPlayerTurn = Constants.NOUGHT;
		} else {
			currentPlayerTurn = Constants.CROSS;
		}
	}

	private void endGame() {
		gameNotOver = false;
	}
}
