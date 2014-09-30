public class MoveWithRowColumn implements MoveBehavior {
	public int row = 0;
	public int column = 0;
	
	public void setRow(int rowValue) {
		this.row = rowValue;
	}
	
	public void setColumn(int colValue) {
		this.column = colValue;
	}
	
	public int[] move() {
		return new int[] {row, column};
	}

}
