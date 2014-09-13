import java.util.Observable;
import java.util.Observer;

/**
 * @author jwettersten
 *
 */
public class View implements Observer {
	
	private Board gameBoard;
	private Controller gameController;
	
	public View(Controller controller, Board board) {
		this.gameController = controller;
		this.gameBoard = board;
		
		board.addObserver(this);

	}
	
	public void create() {
		displayMessage("Welcome to the game of Tic Tac Toe!");
		displayGameBoard();
	}
   
	private void displayFormattedIndexValue(int content) {
		switch (content) {
			case 0:  System.out.print("   "); break;
			case 2:  System.out.print(" O "); break;
			case 1:  System.out.print(" X "); break;
		}
	}
	
	public void displayGameBoard() {
	      
	   for (int row = 0; row < gameBoard.getBoard().length; ++row) {
         
		   for (int col = 0; col < gameBoard.getBoard().length; ++col) {
			   
			   displayFormattedIndexValue(gameBoard.getBoard()[row][col]); // print each of the cells
	       
			   if (col != gameBoard.getBoard().length - 1) {
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
	
	public void displayMessage(String msg) {
		System.out.println(msg);
	}
	
	public void update(Observable obs, Object x) {
		displayGameBoard();
	}

}
