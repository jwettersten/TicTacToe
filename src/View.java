import java.util.Observable;
import java.util.Observer;

/**
 * @author jwettersten
 *
 */
public class View implements Observer {
	
	private Board gameBoard;
	
	public View(Board board) {
		this.gameBoard = board;
		
		board.addObserver(this);

	}
	
	public void create() {
		displayMessage("Welcome to the game of Tic Tac Toe!");
		displayGameBoard();
	}
	
	public void requestPlayerName() {
		displayMessage("What is your name Player X?");
	}
	
	public void requestPlayerMove(String name) {
		displayMessage(name + " please enter the row and column of your next move, e.g. 1 2 (first row [space] second column): ");
	}
	
	public void informPlayerMoveIsNotAvailable() {
		displayMessage("That move is not available. Try again!");
	}
	
	public void displayWinner(String name) {
		displayMessage(name + " has won!");
	}
	
	public void displayTie() {
		displayMessage("It's a tie!");
	}
	
	public void welcomePlayerName(String name) {
		displayMessage("Welcome to Tic Tac Toe " + name + "!");
	}
   
	private void displayFormattedIndexValue(int content) {
		switch (content) {
			case 0:  System.out.print("   "); break;
			case 2:  System.out.print(" O "); break;
			case 1:  System.out.print(" X "); break;
		}
	}
	
	public void displayGameBoard() {
	   
	   for (int row = 0; row < gameBoard.getNumRows(); ++row) {
         
		   for (int column = 0; column < gameBoard.getNumColumns(); ++column) {
			   
			   displayFormattedIndexValue(gameBoard.getCellValueAt(row, column)); // print each of the cells
	       
			   if (column != gameBoard.getNumColumns() - 1) {
				   System.out.print("|");   // print vertical partition
			   }
		   }
		   
         System.out.println();
         
         if (row != gameBoard.getBoard().length - 1) {
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
