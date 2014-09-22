public class MockPlayer extends Player {
	
	public MockPlayer(String playerName, Board board) {
		
		super(playerName, board, new MockHumanMoveBehavior(board));
		
	}
}
