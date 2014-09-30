import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NoamMessageControllerTest {
	
	NoamMessageGateway noamGate = NoamMessageGateway.getMessageGateway();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test 
	public void testIncomingMoveFromNoam() throws Exception {
		Board gameBoard = new Board();
		Controller gameController = new Controller(gameBoard);
		
		NoamMessageController noamMessageController = new NoamMessageController(gameBoard, gameController, new Presenter(gameBoard));
		
		int row = 1; int col = 1; int playerMark = Constants.CROSS;
		String moveFromNoam = String.valueOf(row) + "," + String.valueOf(col) + "," + String.valueOf(playerMark);
		
		noamMessageController.parseAndAttemptIncomingMove(moveFromNoam);
		
		assertEquals(Constants.CROSS, gameBoard.getCellValueAt(row - 1, col - 1));
		
		noamMessageController.disconnectFromNoam();	
	}
}
