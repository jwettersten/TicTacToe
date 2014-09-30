import java.util.Observable;
import java.util.Observer;

public class MockNoamMessageController extends NoamMessageController implements Observer {
	private Presenter modelMessageDataProvider;
	private Board gameBoard;
	private Controller gameController;

	public String outboundJSONMessage;
	
	public MockNoamMessageController(Board board, Controller controller, Presenter presenter) throws Exception {
		super(board, controller, presenter);
	}
	
	public void parseAndAttemptIncomingMove(String incomingMove) {
		String delimiter = "[,]";
		String[] tokens = incomingMove.split(delimiter);
		MoveWithRowColumn mvrc = new MoveWithRowColumn();
		Player noamPlayer = new Player("Noam", gameBoard, mvrc);
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
		outboundJSONMessage = modelMessageDataProvider.getBoardStateJSON().toString();
	}

}
