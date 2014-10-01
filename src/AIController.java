import java.util.Observable;
import java.util.Observer;


public class AIController implements Observer {
	private Controller gameController;
	private Player player;
	
	public AIController(Controller controller, Player aiPlayer) {
		gameController = controller;
		player = aiPlayer;
		gameController.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (gameController.isCurrentTurn(player)) {
			gameController.attemptMove(player);
		}
	}

}
