import java.util.Observable;
import java.util.Observer;

public class ConsoleView implements Observer, View {
	
	private Board gameBoard;
	
	public ConsoleView(Board board) {
		this.gameBoard = board;
		
		board.addObserver(this);
	}
	
	@Override
	public void create() {
		displayMessage("Welcome to the game of Tic Tac Toe!");
		displayGameBoard();
	}

	@Override
	public void requestPlayerName() {
		displayMessage("What is your name Player X?");
	}
	
	@Override
	public void requestPlayerMove(String name) {
		displayMessage(name + " please enter the row and column of your next move, e.g. 1 2 (first row [space] second column): ");
	}
	
	@Override
	public void informPlayerMoveIsNotAvailable() {
		displayMessage("That move is not available. Try again!");
	}
	
	@Override
	public void informPlayerNotTheirTurn() {
		displayMessage("Sorry, you need to wait your turn.");
	}
	
	@Override
	public void displayWinner(String name) {
		displayMessage(name + " has won!");
	}

	@Override
	public void displayTie() {
		displayMessage("It's a tie!");
	}
	
	@Override
	public void welcomePlayerName(String name) {
		displayMessage("Welcome to Tic Tac Toe " + name + "!");
	}
   
	private void displayFormattedIndexValue(int content) {
		switch (content) {
			case Constants.UNASSIGNED:  System.out.print("   "); break;
			case Constants.CROSS:  		System.out.print(" X "); break;
			case Constants.NOUGHT:  	System.out.print(" O "); break;
		}
	}
	
	@Override
	public void displayGameBoard() {
	   
	   for (int row = 0; row < gameBoard.getNumRows(); ++row) {
         
		   for (int column = 0; column < gameBoard.getNumColumns(); ++column) {
			   
			   displayFormattedIndexValue(gameBoard.getCellValueAt(row, column)); // print each of the cells
	       
			   if (column != gameBoard.getNumColumns() - 1) {
				   System.out.print("|");   // print vertical partition
			   }
		   }
		   
         System.out.println();
         
         if (row != gameBoard.getNumRows() - 1) {
            System.out.println("-----------"); // print horizontal partition
         }
	   }
	   
      System.out.println();
   }
	
	private void displayMessage(String msg) {
		System.out.println(msg);
	}
	
	public void update(Observable obs, Object x) {
		displayGameBoard();
	}

}
