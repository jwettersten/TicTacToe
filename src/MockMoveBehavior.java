public class MockMoveBehavior implements MoveBehavior {

	public int row = 0;
	public int column = 0;
	
	public void setRow(int rowValue) {
		row = rowValue;
	}
	
	public void setColumn(int colValue) {
		column = colValue;
	}
	
	@Override
	public int[] move() {
		return new int[] {row, column};
	}

}
