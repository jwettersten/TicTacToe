import java.util.ArrayList;
import java.util.List;

/**
 * @author jwettersten
 * I swapped in this AI example from https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html
 * and modified it slightly to work within my app design. 
 * This was an intentional design decision so that I can create different AI player algorithms as appropriate. 
 */
public class MoveWithAIMinimax implements MoveBehavior {
	
	Board gameBoard;
	
	private int ROWS = 3;  // number of rows
	private int COLS = 3;  // number of columns
	private int[][] cells; // the board's ROWS-by-COLS array of Cells
	
	public MoveWithAIMinimax(Board board) {
		
		gameBoard = board;
		
		cells = gameBoard.getBoard();
	}
	
	public int[] move() {
		int[] result = minimax(2, Constants.NOUGHT); // depth, max turn
		return new int[] {result[1], result[2]};   // row, col
	}
	 
	/** Recursive minimax at level of depth for either maximizing or minimizing player. 
	 * Return int[3] of {score, row, col}  
	 * */
	private int[] minimax(int depth, int player) {
		// Generate possible next moves in a List of int[2] of {row, col}.
		List<int[]> nextMoves = generateMoves();
	 
		// mySeed is maximizing; while oppSeed is minimizing
		int bestScore = (player == Constants.NOUGHT) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentScore;
		int bestRow = -1;
		int bestCol = -1;
	 
		if (nextMoves.isEmpty() || depth == 0) {
			// Gameover or depth reached, evaluate score
			bestScore = evaluate();
			
		} else {
			for (int[] move : nextMoves) {
				// Try this move for the current "player"
				cells[move[0]][move[1]] = player;
				
				if (player == Constants.NOUGHT) {  // mySeed (computer) is maximizing player
					currentScore = minimax(depth - 1, Constants.CROSS)[0];
					
					if (currentScore > bestScore) {
						bestScore = currentScore;
						bestRow = move[0];
						bestCol = move[1];
					}
				} else {  // oppSeed is minimizing player
					currentScore = minimax(depth - 1, Constants.NOUGHT)[0];
					if (currentScore < bestScore) {
						bestScore = currentScore;
						bestRow = move[0];
						bestCol = move[1];
					}
				}
				
				// Undo move
				cells[move[0]][move[1]] = Constants.UNASSIGNED;
			}
		}
			return new int[] {bestScore, bestRow, bestCol};
	}
	 
	   /** Find all valid next moves.
	       Return List of moves in int[2] of {row, col} or empty list if gameover */
	   private List<int[]> generateMoves() {
	      List<int[]> nextMoves = new ArrayList<int[]>(); // allocate List
	 
	      // If gameover, i.e., no next move
	      if (gameBoard.hasPlayerWon(Constants.NOUGHT) || gameBoard.hasPlayerWon(Constants.CROSS)) {
	         return nextMoves;   // return empty list
	      }
	 
	      // Search for empty cells and add to the List
	      for (int row = 0; row < ROWS; ++row) {
	         for (int col = 0; col < COLS; ++col) {
	            if (cells[row][col] == Constants.UNASSIGNED) {
	               nextMoves.add(new int[] {row, col});
	            }
	         }
	      }
	      return nextMoves;
	   }
	 
	   /** The heuristic evaluation function for the current board
	       @Return +100, +10, +1 for EACH 3-, 2-, 1-in-a-line for computer.
	               -100, -10, -1 for EACH 3-, 2-, 1-in-a-line for opponent.
	               0 otherwise   */
	   public int evaluate() {
	      int score = 0;
	      // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
	      score += evaluateLine(0, 0, 0, 1, 0, 2);  // row 0
	      score += evaluateLine(1, 0, 1, 1, 1, 2);  // row 1
	      score += evaluateLine(2, 0, 2, 1, 2, 2);  // row 2
	      score += evaluateLine(0, 0, 1, 0, 2, 0);  // col 0
	      score += evaluateLine(0, 1, 1, 1, 2, 1);  // col 1
	      score += evaluateLine(0, 2, 1, 2, 2, 2);  // col 2
	      score += evaluateLine(0, 0, 1, 1, 2, 2);  // diagonal
	      score += evaluateLine(0, 2, 1, 1, 2, 0);  // alternate diagonal
	      return score;
	   }
	 
	   /** The heuristic evaluation function for the given line of 3 cells
	       @Return +100, +10, +1 for 3-, 2-, 1-in-a-line for computer.
	               -100, -10, -1 for 3-, 2-, 1-in-a-line for opponent.
	               0 otherwise */
	   private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
	      int score = 0;
	 
	      // First cell
	      if (cells[row1][col1] == Constants.NOUGHT) {
	         score = 1;
	      } else if (cells[row1][col1] == Constants.CROSS) {
	         score = -1;
	      }
	 
	      // Second cell
	      if (cells[row2][col2] == Constants.NOUGHT) {
	         if (score == 1) {   // cell1 is mySeed
	            score = 10;
	         } else if (score == -1) {  // cell1 is oppSeed
	            return 0;
	         } else {  // cell1 is empty
	            score = 1;
	         }
	      } else if (cells[row2][col2] == Constants.CROSS) {
	         if (score == -1) { // cell1 is oppSeed
	            score = -10;
	         } else if (score == 1) { // cell1 is mySeed
	            return 0;
	         } else {  // cell1 is empty
	            score = -1;
	         }
	      }
	 
	      // Third cell
	      if (cells[row3][col3] == Constants.NOUGHT) {
	         if (score > 0) {  // cell1 and/or cell2 is mySeed
	            score *= 10;
	         } else if (score < 0) {  // cell1 and/or cell2 is oppSeed
	            return 0;
	         } else {  // cell1 and cell2 are empty
	            score = 1;
	         }
	      } else if (cells[row3][col3] == Constants.CROSS) {
	         if (score < 0) {  // cell1 and/or cell2 is oppSeed
	            score *= 10;
	         } else if (score > 1) {  // cell1 and/or cell2 is mySeed
	            return 0;
	         } else {  // cell1 and cell2 are empty
	            score = -1;
	         }
	      }
	      return score;
	   }
	
}
