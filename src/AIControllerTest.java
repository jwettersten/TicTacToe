import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class AIControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAIControllerSetup() {
		Board gameBoard = new Board();
		
		MockConsoleView mockConsoleView = new MockConsoleView(gameBoard);
		
		Controller gameController = new Controller(gameBoard);
		gameController.setupPreferredView(mockConsoleView);
		
		Player aiPlayer = new Player("Computer", gameBoard, new MoveWithAIMinimax(gameBoard));
		aiPlayer.setMark(Constants.NOUGHT);
		
		new AIController(gameController, aiPlayer);
		
		MoveWithRowColumn rowColBehaviorCross = new MoveWithRowColumn();
		Player rowColumnPlayerCross = new Player("Row Column Player", gameBoard, rowColBehaviorCross);
		rowColumnPlayerCross.setMark(Constants.CROSS);
		
		rowColBehaviorCross.setRow(0);
		rowColBehaviorCross.setColumn(0);
		gameController.attemptMove(rowColumnPlayerCross);
		
		// Need to add assert
		// - pass in mock player for assertion
		// - mock controller to check board
	}

}
