/**
 * 
 */
import java.util.*;
/**
 * @author jwettersten
 *
 */
public class Board {

	private char[][] board;
	//ArrayList observers = new ArrayList();
	
	public Board() {
		this.board = new char[3][3];
	}
	
	public char[][] getBoard() {
		return board;
	}
	
	public void setPlayerBoardPosition(int row, int column, char playerType) {
		// test if position is available
		this.board[row][column] = playerType;
	}
	
	public boolean boardPositionAvailable(int row, int column) {
		
		if (this.board[row][column] == '\u0000') {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	public void registerObserver(Observer obs) {
		observers.add(obs);
	}
	**/
	
	/**
	private void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			Observer obs = (Observer)observers.get(i);
			obs.update();
		}
	}
	**/
	
}
