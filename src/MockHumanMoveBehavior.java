public class MockHumanMoveBehavior implements MoveBehavior {
	public int row = 0;
	public int column = 0;
	
	public Board gameBoard;
	
	public MockHumanMoveBehavior(Board board) {
		gameBoard = board;
	}
	
	public void setRow(int rowValue) {
		this.row = rowValue;
	}
	
	public void setColumn(int colValue) {
		this.column = colValue;
	}
	
	@Override
	// modify interface to pass in board 
	public int[] move() {
		return nextMove(gameBoard);
	}
	
	public int[] nextMove(Board board) {
		int rows[] = new int[9];
		int cols[] = new int[9];
		rows[0] = 0; cols[0] = 0;
		rows[1] = 0; cols[1] = 1;
		rows[2] = 0; cols[2] = 2;
		rows[3] = 1; cols[3] = 0;
		rows[4] = 1; cols[4] = 1;
		rows[5] = 1; cols[5] = 2;
		rows[6] = 2; cols[6] = 0;
		rows[7] = 2; cols[7] = 1;
		rows[8] = 2; cols[8] = 2;
		// test each move
		for (int i=0; i<rows.length; i++) {
			row = rows[i];
			column = cols[i];
			
			if (board.positionIsAvailable(row, column)) {
				return new int[] {row, column};
			}
		}
		
		return new int[] {row, column};
	}
}
