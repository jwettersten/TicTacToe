
/**
 * @author jwettersten
 *
 */
public class Player {
	
	private String name;
	private int mark;
	
	MoveBehavior moveBehavior;
	
	public Player(String playerName, int playerType, MoveBehavior behavior) {
		this.name = playerName;
		this.mark = playerType;
		this.moveBehavior = behavior;
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
	

	public boolean performMove(Board board) {
		
		int[] result = moveBehavior.move();
		int row = result[0];
		int column = result[1];
		
		if (board.positionIsAvailable(row, column)) {

			board.setPlayerPosition(row, column, this.mark);
			
			return true;
			
		} else {
			return false;
		}
		
	}

}
