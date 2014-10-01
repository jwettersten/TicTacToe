public class Player {
	
	private String name;
	private int mark;
	private MoveBehavior moveBehavior;
	private Board gameBoard;
	
	public Player(String name, Board board, MoveBehavior behavior) {
		this.name = name;
		this.mark = Constants.UNASSIGNED;
		gameBoard = board;
		this.moveBehavior = behavior;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMark() {
		return mark;
	}
	
	public MoveBehavior getMoveBehavior() {
		return moveBehavior;
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

	// could use template pattern instead - move to base class
	public boolean performMove() {
		
		int[] result = moveBehavior.move();
		int row = result[0];
		int column = result[1];
		
		if (gameBoard.positionIsAvailable(row, column)) {

			gameBoard.setPlayerPosition(row, column, this.mark);
			
			return true;
		} 
		
		return false;
	}
}
