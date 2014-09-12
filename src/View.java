/**
 * 
 */


/**
 * @author jwettersten
 *
 */
public class View {
	
	Board gameBoard;
	Controller gameController;
	
	public View(Controller controller, Board board) {
		this.gameController = controller;
		this.gameBoard = board;
		
		// register this view as an observer as a model if necessary
		//this.gameBoard.re
		
		displayMessage("Welcome to the game of Tic Tac Toe!");
		
		displayBoard();
	}
	
	/** Display the game board */
	public void displayBoard() {
      
	   for (int row = 0; row < gameBoard.getBoard().length; ++row) {
         
		   for (int col = 0; col < gameBoard.getBoard().length; ++col) {
			   
			   outputValue(gameBoard.getBoard()[row][col]); // print each of the cells
	       
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
   
   	/** Output the value of the index */
	private void outputValue(char content) {
      switch (content) {
         case '\u0000':  System.out.print("   "); break;
         case 'O': System.out.print(" O "); break;
         case 'X':  System.out.print(" X "); break;
      }
   }

}
