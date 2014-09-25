import java.util.Arrays;
import java.util.Observable;

import org.json.JSONObject;

public class Board extends Observable {

	private int[][] board;
	private static final int ROWS = 3;
	private static final int COLUMNS = 3;
	
	public Board() {
		this.board = new int[ROWS][COLUMNS];
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public int getCellValueAt(int row, int column) throws IndexOutOfBoundsException {
		
		return board[row][column];
	}
	
	public int getNumRows() {
		return ROWS;
	}
	
	public int getNumColumns() {
		return COLUMNS;
	}
	
	
	public boolean isFull() {
		boolean returnValue = true;
		
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLUMNS; ++col) {
				if (board[row][col] == 0) {
					return false;
				}
			}
		}
		
		return returnValue;
	}
	
	public boolean positionIsAvailable(int row, int column) {

		return (positionIsWithinBounds(row, column) && board[row][column] == 0);
		
	}

	private boolean positionIsWithinBounds(int row, int column) {
		return row < ROWS && column < COLUMNS;
	}
	
	public void setPlayerPosition(int row, int column, int playerMark) {
		
		if (row < ROWS && column < COLUMNS) {
			board[row][column] = playerMark;
			
			setChanged();
			notifyObservers();
		}
	}
	
	public String getJSON() {
		JSONObject boardValues = new JSONObject();
	    for (int row = 0; row < ROWS; ++row) {
	    	boardValues.put(Integer.toString(row), Arrays.toString(board[row]));
	    }
	    return boardValues.toString();
	}
	
	
	/**
	 * The following 2 winner scoring pattern + hasPlayerWon functions came from the AI example from:
	 * https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html
	 */
	/** Returns true if thePlayer wins */
	public boolean hasPlayerWon(int playerMark) {
		int pattern = 0b000000000;  // 9-bit pattern for the 9 cells
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLUMNS; ++col) {
				if (board[row][col] == playerMark) {
					pattern |= (1 << (row * ROWS + col));
				}
			}
		}
		   
		for (int winningPattern : winningPatterns) {
			if ((pattern & winningPattern) == winningPattern) return true;
		}
		   
		return false;
	}
	
	private int[] winningPatterns = {
			0b111000000, 0b000111000, 0b000000111, // rows
			0b100100100, 0b010010010, 0b001001001, // cols
			0b100010001, 0b001010100               // diagonals
	};
 
   
	
}
