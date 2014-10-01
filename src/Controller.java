import java.util.Observable;

public class Controller extends Observable {
	private Board board;
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
	
	public boolean isCurrentTurn(Player player) {
		return currentPlayerTurn == player.getMark();
	}
	
	public void playGame() {
		while (gameNotOver) {
			// loop keeping requests open for moves
			try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
	}
	
	public synchronized void attemptMove(Player player) {
		if (gameNotOver && isCurrentTurn(player)) {
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
		
		if (gameNotOver) {
			setChanged();
			notifyObservers();
		}
	}

	private void endGame() {
		gameNotOver = false;
	}
}
