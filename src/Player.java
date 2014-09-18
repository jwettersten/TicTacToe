
/**
 * @author jwettersten
 *
 */
public class Player {
	
	private String name;
	private int mark;
	MoveBehavior moveBehavior;
	
	public Player(String name, MoveBehavior behavior) {
		this.name = name;
		this.mark = Constants.UNASSIGNED;
		this.moveBehavior = behavior;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getMark() {
		return this.mark;
	}

	public void setMark(int newMark) {
		if (isValidMark(newMark)) {
			this.mark = newMark;
		} else {
			throw new IllegalArgumentException("Mark is not an allowable value.");
		}
	}
	
	public boolean isValidMark(int newMark) {
		return (newMark == Constants.CROSS || newMark == Constants.NOUGHT);	
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
