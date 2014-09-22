public class Controller {
	
	private Board board;
	private Player human;
	private Player computer;
	private View view;
	
	private boolean gameNotOver = true;
	
	public Controller(Board board) {

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
	
	public void attemptMove(Player player) {
		
		boolean moveIsNotSuccessful = true;  
		
		while (moveIsNotSuccessful) {
			
			view.requestPlayerMove(player.getName());
			
			if (player.performMove()) {
				
				moveIsNotSuccessful = false;  
				
			} else {

				view.informPlayerMoveIsNotAvailable();
			}
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

	private void endGame() {
		gameNotOver = false;
	}
	
	// can a test be used to play the entire game
	// mock out sequence of moves per player as they go back and forth
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
