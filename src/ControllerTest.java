import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ControllerTest {
	
	Board gameBoard;

	@Before
	public void setUp() throws Exception {
		gameBoard = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testViewControllerRowColumnPlayerIsWinner() {
		fillBoardWithMark(Constants.CROSS);
		
		Player rowColumPlayer = new Player("Row Column Player", gameBoard, new MoveWithRowColumn());
		rowColumPlayer.setMark(Constants.CROSS);
		
		MockConsoleView mockConsoleView = new MockConsoleView(gameBoard);

		Controller gameController = new Controller(gameBoard);
		gameController.setupPreferredView(mockConsoleView);
		gameController.checkScore(rowColumPlayer);
		
		assertEquals(rowColumPlayer.getName(), mockConsoleView.currentWinner);
	}

	private void fillBoardWithMark(int playerMark) {
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 3; ++col) {
				gameBoard.setPlayerPosition(row, col, playerMark);
			}
		}
	}
	
	@Test
	public void testViewControllerComputerIsWinner() {
		fillBoardWithMark(Constants.NOUGHT);
		
		Player computer = new Player("Computer", gameBoard, new MoveWithAIMinimax(gameBoard));
		computer.setMark(Constants.NOUGHT);

		MockConsoleView mockConsoleView = new MockConsoleView(gameBoard);

		Controller gameController = new Controller(gameBoard);
		gameController.setupPreferredView(mockConsoleView);
		gameController.checkScore(computer);
		
		assertEquals(computer.getName(), mockConsoleView.currentWinner);
	}
	
	@Test
	public void testViewControllerTieScore() {
		populateTieBoard();
		
		Player rowColumPlayer = new Player("Row Column Player", gameBoard, new MoveWithRowColumn());
		rowColumPlayer.setMark(Constants.CROSS);

		MockConsoleView view = new MockConsoleView(gameBoard);
		
		Controller gameController = new Controller(gameBoard);
		gameController.setupPreferredView(view);
		gameController.checkScore(rowColumPlayer);
		
		assertTrue(view.isTie);
	}

	private void populateTieBoard() {
		gameBoard.setPlayerPosition(0, 0, Constants.CROSS);
		gameBoard.setPlayerPosition(1, 1, Constants.NOUGHT);
		gameBoard.setPlayerPosition(2, 0, Constants.CROSS);
		gameBoard.setPlayerPosition(0, 1, Constants.NOUGHT);
		gameBoard.setPlayerPosition(1, 2, Constants.CROSS);
		gameBoard.setPlayerPosition(0, 2, Constants.NOUGHT);
		gameBoard.setPlayerPosition(2, 1, Constants.CROSS);
		gameBoard.setPlayerPosition(2, 2, Constants.NOUGHT);
		gameBoard.setPlayerPosition(1, 0, Constants.NOUGHT);
	}
	
	@Test
	public void testViewControllerRowColumnPlayerMoveAttempts() {
		
		MockConsoleView mockConsoleView = new MockConsoleView(gameBoard);

		Controller gameController = new Controller(gameBoard);
		gameController.setupPreferredView(mockConsoleView);
		
		MoveWithRowColumn rowColBehaviorCross = new MoveWithRowColumn();
		Player rowColumnPlayerCross = new Player("Row Column Player", gameBoard, rowColBehaviorCross);
		rowColumnPlayerCross.setMark(Constants.CROSS);
		//gameController.setHumanPlayer(rowColumnPlayerCross);
		
		MoveWithRowColumn rowColBehaviorNought = new MoveWithRowColumn();
		Player rowColumnPlayerNought = new Player("Row Column Player", gameBoard, rowColBehaviorNought);
		rowColumnPlayerNought.setMark(Constants.NOUGHT);
		gameController.setComputerPlayer(rowColumnPlayerNought);
		
		rowColBehaviorCross.setRow(0);
		rowColBehaviorCross.setColumn(0);
		gameController.attemptMove(rowColumnPlayerCross);
		
		rowColBehaviorNought.setRow(1);
		rowColBehaviorNought.setColumn(1);
		gameController.attemptMove(rowColumnPlayerNought);
		
		assertEquals(rowColumnPlayerCross.getMark(), gameBoard.getCellValueAt(0, 0));
		assertEquals(rowColumnPlayerNought.getMark(), gameBoard.getCellValueAt(1, 1));
	}
	
	@Test
	public void testRowColumnPlayerVsMinimaxGamePlay() {
		MockConsoleView mockConsoleView = new MockConsoleView(gameBoard);

		Controller gameController = new Controller(gameBoard);
		gameController.setupPreferredView(mockConsoleView);
		
		Player consoleRowColumnPlayer = new Player("Row ColumnPlayer", gameBoard, new MockHumanMoveBehavior(gameBoard));
		consoleRowColumnPlayer.setMark(Constants.CROSS);
		//gameController.setHumanPlayer(consoleRowColumnPlayer);
		
		Player computer = new Player("Computer", gameBoard, new MoveWithAIMinimax(gameBoard));
		computer.setMark(Constants.NOUGHT);
		gameController.setComputerPlayer(computer);
		
		gameController.attemptMove(consoleRowColumnPlayer);
		gameController.attemptMove(computer);
		gameController.attemptMove(consoleRowColumnPlayer);
		gameController.attemptMove(computer);
		gameController.attemptMove(consoleRowColumnPlayer);
		gameController.attemptMove(computer);

		assertEquals(computer.getName(), mockConsoleView.currentWinner);
	}
	
	@Test
	public void testNoamMoveAttempt() {
		NoamMessageController noamMessageController;
		
		MockConsoleView mockConsoleView = new MockConsoleView(gameBoard);

		Controller gameController = new Controller(gameBoard);
		gameController.setupPreferredView(mockConsoleView);
		
		Player computer = new Player("Computer", gameBoard, new MoveWithAIMinimax(gameBoard));
		computer.setMark(Constants.NOUGHT);
		gameController.setComputerPlayer(computer);
		
		try {
			noamMessageController = new NoamMessageController(gameBoard, gameController, new Presenter(gameBoard));
			noamMessageController.parseAndAttemptIncomingMove("1,1," + Constants.CROSS);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		assertEquals(Constants.CROSS, gameBoard.getCellValueAt(0,0));
	}
}
