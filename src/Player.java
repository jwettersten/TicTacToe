
/**
 * @author jwettersten
 *
 */
public class Player {
	
	private String name;
	private int mark;
	private static final int UNASSIGNED = 0;
	private static final int CROSS = 1;
	private static final int NOUGHT = 2;
	
	MoveBehavior moveBehavior;
	
	public Player(String name, MoveBehavior behavior) {
		this.name = name;
		this.mark = UNASSIGNED;
		this.moveBehavior = behavior;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getMark() {
		return this.mark;
	}
	
	// add throw exception
	public void setMark(int newMark) {
		if (isValidMark(newMark)) {
			this.mark = newMark;
		}
	}
	
	public boolean isValidMark(int newMark) {
		return (newMark == CROSS || newMark == NOUGHT);	
	}

	public boolean performMove(Board board) {
		
		int[] result = moveBehavior.move();
		int row = result[0];
		int column = result[1];
		
		if (board.positionIsAvailable(row, column)) {

			board.setPlayerPosition(row, column, this.mark);
			
			return true;
		} 
		
		return false;
		
	}

}
