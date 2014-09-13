import java.util.Observable;

/**
 * @author jwettersten
 *
 */
public class Board extends Observable {

	private int[][] board;
	
	public Board() {
		this.board = new int[3][3];
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public void setPlayerBoardPosition(int row, int column, int playerType) {
		
		this.board[row][column] = playerType;
		
		setChanged();
		notifyObservers();
	}
	
	public boolean isBoardPositionAvailable(int row, int column) {
		System.out.println("index value: " + this.board[row][column]);
		
		if (this.board[row][column] > 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	/**
	 * This 
	 */
	private int[] winningPatterns = {
			0b111000000, 0b000111000, 0b000000111, // rows
			0b100100100, 0b010010010, 0b001001001, // cols
			0b100010001, 0b001010100               // diagonals
	};
 
   /** Returns true if thePlayer wins */
   public boolean hasWon(int player) {
	   int pattern = 0b000000000;  // 9-bit pattern for the 9 cells
	   for (int row = 0; row < 3; ++row) {
         for (int col = 0; col < 3; ++col) {
            if (board[row][col] == player) {
               pattern |= (1 << (row * 3 + col));
            }
         }
      }
      for (int winningPattern : winningPatterns) {
         if ((pattern & winningPattern) == winningPattern) return true;
      }
      return false;
   }
	
}
