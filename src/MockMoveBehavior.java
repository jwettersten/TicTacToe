public class MockMoveBehavior implements MoveBehavior {

	public int row = 0;
	public int column = 0;
	
	@Override
	public int[] move() {
		return new int[] {row, column};
	}

}
