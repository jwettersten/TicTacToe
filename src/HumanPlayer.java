public class HumanPlayer extends Player {
	
	public HumanPlayer(String playerName, Board board) {
		
		super(playerName, board, new MoveWithRowColumn());
		
	}
	
}
