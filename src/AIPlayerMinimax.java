import java.util.*;

/**
 * @author jwettersten
 *
 * I swapped in this AI example from https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html
 * and modified it slightly to work within my app design. 
 * This was an intentional design decision so that I can create different AI player algorithms as appropriate. 
 * I left the content body as is from the original source, with the exception of a couple refactoring decisions:
 * - Subclassed Player
 * - Moved Board scoring functions into Board model
 * - Making member variables private
 */
/** AIPlayer using Minimax algorithm */
public class AIPlayerMinimax extends Player {
	
	Board gameBoard;
	
	// Name-constants to represent the seeds and cell contents
	private static final int EMPTY = 0;
	private static final int CROSS = 1;
	private static final int NOUGHT = 2;
	
	private int ROWS = 3;  // number of rows
	private int COLS = 3;  // number of columns
	private int[][] cells; // the board's ROWS-by-COLS array of Cells
	
	public AIPlayerMinimax(Board board) {
		
		super("Computer", NOUGHT);
		
		gameBoard = board;
		
		cells = gameBoard.getBoard();
	}
	 
	/** Get next best move for computer. Return int[2] of {row, col} */
	int[] move() {
		int[] result = minimax(2, NOUGHT); // depth, max turn
		return new int[] {result[1], result[2]};   // row, col
	}
	 
	/** Recursive minimax at level of depth for either maximizing or minimizing player. 
	 * Return int[3] of {score, row, col}  
	 * */
	private int[] minimax(int depth, int player) {
		// Generate possible next moves in a List of int[2] of {row, col}.
		List<int[]> nextMoves = generateMoves();
	 
		// mySeed is maximizing; while oppSeed is minimizing
		int bestScore = (player == NOUGHT) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentScore;
		int bestRow = -1;
		int bestCol = -1;
	 
		if (nextMoves.isEmpty() || depth == 0) {
			// Gameover or depth reached, evaluate score
			bestScore = evaluate();
			
			System.out.println("Best score is: " + bestScore);
			
		} else {
			for (int[] move : nextMoves) {
				// Try this move for the current "player"
				cells[move[0]][move[1]] = player;
				
				if (player == NOUGHT) {  // mySeed (computer) is maximizing player
					currentScore = minimax(depth - 1, CROSS)[0];
					
					if (currentScore > bestScore) {
						bestScore = currentScore;
						bestRow = move[0];
						bestCol = move[1];
					}
				} else {  // oppSeed is minimizing player
					currentScore = minimax(depth - 1, NOUGHT)[0];
					if (currentScore < bestScore) {
						bestScore = currentScore;
						bestRow = move[0];
						bestCol = move[1];
					}
				}
				
				// Undo move
				cells[move[0]][move[1]] = EMPTY;
			}
		}
			return new int[] {bestScore, bestRow, bestCol};
	}
	 
	   /** Find all valid next moves.
	       Return List of moves in int[2] of {row, col} or empty list if gameover */
	   private List<int[]> generateMoves() {
	      List<int[]> nextMoves = new ArrayList<int[]>(); // allocate List
	 
	      // If gameover, i.e., no next move
	      if (gameBoard.hasPlayerWon(NOUGHT) || gameBoard.hasPlayerWon(CROSS)) {
	         return nextMoves;   // return empty list
	      }
	 
	      // Search for empty cells and add to the List
	      for (int row = 0; row < ROWS; ++row) {
	         for (int col = 0; col < COLS; ++col) {
	            if (cells[row][col] == EMPTY) {
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
	      if (cells[row1][col1] == NOUGHT) {
	         score = 1;
	      } else if (cells[row1][col1] == CROSS) {
	         score = -1;
	      }
	 
	      // Second cell
	      if (cells[row2][col2] == NOUGHT) {
	         if (score == 1) {   // cell1 is mySeed
	            score = 10;
	         } else if (score == -1) {  // cell1 is oppSeed
	            return 0;
	         } else {  // cell1 is empty
	            score = 1;
	         }
	      } else if (cells[row2][col2] == CROSS) {
	         if (score == -1) { // cell1 is oppSeed
	            score = -10;
	         } else if (score == 1) { // cell1 is mySeed
	            return 0;
	         } else {  // cell1 is empty
	            score = -1;
	         }
	      }
	 
	      // Third cell
	      if (cells[row3][col3] == NOUGHT) {
	         if (score > 0) {  // cell1 and/or cell2 is mySeed
	            score *= 10;
	         } else if (score < 0) {  // cell1 and/or cell2 is oppSeed
	            return 0;
	         } else {  // cell1 and cell2 are empty
	            score = 1;
	         }
	      } else if (cells[row3][col3] == CROSS) {
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
