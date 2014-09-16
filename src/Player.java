
/**
 * @author jwettersten
 *
 */
public class Player {
	
	private String name;
	private int mark;
	
	MoveBehavior moveBehavior;
	
	public Player(String playerName, int playerType) {
		this.name = playerName;
		this.mark = playerType;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getMark() {
		return this.mark;
	}
	
	public void setMark(int newType) {
		this.mark = newType;
	}
	
	// add move() method
	public int[] performMove() {
		return moveBehavior.move();
	}
	

	
	public boolean attemptToMakeBoardMove(Board board, int rowPosition, int colPosition) {
		
		boolean returnValue = false;
		
		if (board.isPositionAvailable(rowPosition, colPosition)) {

			board.setPlayerPosition(rowPosition, colPosition, this.mark);
			
			returnValue = true;
			
		}
		
		return returnValue;
		
	}

}
