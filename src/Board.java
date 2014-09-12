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
		
		if (this.board[row][column] == 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
