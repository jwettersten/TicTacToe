import java.util.Observable;
import java.util.Observer;

public class NoamMessageController implements Observer {
	private Presenter modelMessageDataProvider;
	private Controller gameController;
	private Player noamPlayer;
	private NoamMessageGateway noamGateway;
	
	public NoamMessageController(Board board, Controller controller, Presenter presenter, Player player) throws Exception {
		modelMessageDataProvider = presenter;
		board.addObserver(this);
		gameController = controller;
		noamPlayer = player;
		noamGateway = NoamMessageGateway.getMessageGateway();
		noamGateway.setMessageController(this);
	}
	
	public void parseAndAttemptIncomingMove(String incomingMove) {
		String delimiter = "[,]";
		String[] tokens = incomingMove.split(delimiter);

		MoveWithRowColumn mvrc = (MoveWithRowColumn)noamPlayer.getMoveBehavior();
		mvrc.setRow(Integer.parseInt(tokens[0]) - 1);
		mvrc.setColumn(Integer.parseInt(tokens[1]) - 1);
		noamPlayer.setMark(Integer.parseInt(tokens[2]));
		
		gameController.attemptMove(noamPlayer);
	}
	
	public void disconnectFromNoam() {
		NoamMessageGateway.getMessageGateway().stop();
	}

	@Override
	public void update(Observable o, Object arg) {
		try {
			noamGateway.sendMessage(modelMessageDataProvider.getBoardStateJSON());
		} catch (InterruptedException e) {
			System.out.println("Error attempting to send message to noam! " + e.getMessage());
		}
	}

}
