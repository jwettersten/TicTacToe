
public class HumanPlayer extends Player {
	
	public HumanPlayer(String playerName, int playerType) {
		
		super(playerName, playerType, new MoveWithRowColumn());
		
	}
	
}
