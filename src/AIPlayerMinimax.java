public class AIPlayerMinimax extends Player {
	
	public AIPlayerMinimax(String playerName, Board board) {
		
		super(playerName, board, new MoveWithAIMinimax(board));
		
	}
	 
}
