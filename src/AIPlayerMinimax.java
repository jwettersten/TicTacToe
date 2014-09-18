import java.util.*;

/**
 * @author jwettersten
 * 
 */
/** AIPlayer using Minimax algorithm */
public class AIPlayerMinimax extends Player {
	
	public AIPlayerMinimax(String playerName, Board board) {
		
		super(playerName, new MoveWithAIMinimax(board));
		
	}
	 
}
