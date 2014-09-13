import java.util.Observable;

/**
 * @author jwettersten
 *
 */
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
	
	public boolean isPositionAvailable(int row, int column) {
		
		if (this.isFull() || board[row][column] > 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public void setPlayerPosition(int row, int column, int playerMark) {
		
		board[row][column] = playerMark;
		
		setChanged();
		notifyObservers();
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
