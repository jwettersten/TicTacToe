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
	public void testViewHumanIsWinner() {

		// load up the board with winning human marks
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 3; ++col) {
				gameBoard.setPlayerPosition(row, col, Constants.CROSS);
			}
		}
		
		// Player
		Player human = new HumanPlayer("jw", gameBoard);
		human.setMark(Constants.CROSS);
		
		// View
		MockView view = new MockView();
		view.gameBoard = gameBoard;
		
		// Controller
		Controller controller = new Controller(gameBoard);
		controller.setupPreferredView(view);
		controller.checkScore(human);
		
		// confirm human is the winner
		assertEquals(human.getName(), view.currentWinner);
	}
	
	@Test
	public void testViewCheckScoreComputerIsWinner() {

		// load up the board with winning human marks
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 3; ++col) {
				gameBoard.setPlayerPosition(row, col, Constants.NOUGHT);
			}
		}
		
		// Player
		Player computer = new AIPlayerMinimax("Computer", gameBoard);
		computer.setMark(Constants.NOUGHT);
		
		// View
		MockView view = new MockView();
		view.gameBoard = gameBoard;
		
		// Controller
		Controller controller = new Controller(gameBoard);
		controller.setupPreferredView(view);
		controller.checkScore(computer);
		
		// confirm human is the winner
		assertEquals(computer.getName(), view.currentWinner);
	}
	
	@Test
	public void testViewCheckScoreTie() {

		// load up the board to a tie
		populateTieBoard();
		assertTrue(gameBoard.isFull());
		
		// Player
		Player computer = new AIPlayerMinimax("Computer", gameBoard);
		computer.setMark(Constants.NOUGHT);
		Player human = new HumanPlayer("jw", gameBoard);
		human.setMark(Constants.CROSS);
		
		// View
		MockView view = new MockView();
		view.gameBoard = gameBoard;
		
		// Controller
		Controller controller = new Controller(gameBoard);
		controller.setupPreferredView(view);
		
		controller.checkScore(human);
		
		// check for tie
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
	public void testViewCheckScoreHumanPlayerMoves() {
		
		MockView view = new MockView();
		view.gameBoard = gameBoard;

		Controller controller = new Controller(gameBoard);
		controller.setupPreferredView(view);
		
		MockMoveBehavior behavior = new MockMoveBehavior();
		Player human = new Player("jw", gameBoard, behavior);
		human.setMark(Constants.NOUGHT);
		
		int rows[] = new int[9];
		int cols[] = new int[9];
		rows[0] = 0; cols[0] = 0;
		rows[1] = 0; cols[1] = 1;
		rows[2] = 0; cols[2] = 2;
		rows[3] = 1; cols[3] = 0;
		rows[4] = 1; cols[4] = 1;
		rows[5] = 1; cols[5] = 2;
		rows[6] = 2; cols[6] = 0;
		rows[7] = 2; cols[7] = 1;
		rows[8] = 2; cols[8] = 2;
		// test each move
		for (int i=0; i<rows.length; i++) {
			behavior.row = rows[i];
			behavior.column = cols[i];
			
			human.performMove();
		}
		
		view.displayGameBoard();
		controller.checkScore(human);
		
		// test setting the position
		assertEquals(human.getName(), view.currentWinner);
		
	}
	
	// create simpler scripted MockBehavior - to test expected moves 
	
	// mocking out a player will test the integration with controller
	// and how it handles unexpected behavior from the player - testing dependencies
	// the controller has on the Player.(as if built controller and not have built the player)
	
	@Test
	public void testGamePlay() {
		gameBoard = new Board();
		
		MockView view = new MockView();
		view.gameBoard = gameBoard;

		Controller controller = new Controller(gameBoard);
		controller.setupPreferredView(view);
		
		Player mockHuman = new MockPlayer("jw", gameBoard);
		mockHuman.setMark(Constants.CROSS);
		controller.setHumanPlayer(mockHuman);
		
		Player computer = new AIPlayerMinimax("Computer", gameBoard);
		computer.setMark(Constants.NOUGHT);
		controller.setComputerPlayer(computer);
		controller.playGame();
		
		view.displayGameBoard();
		
		// test setting the position
		assertEquals(computer.getName(), view.currentWinner);
		
	}

	

}
