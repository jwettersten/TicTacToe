import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PlayerTest {
	
	// break out static values in separate class - make public
	private static final int CROSS = 1;
	private static final int NOUGHT = 2;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPlayer() {
		
		assertNotNull(new Player("jw", new MoveWithRowColumn()));
		
	}
	
	@Test
	public void testCreateHumanPlayerType() {
		
		Player human = new HumanPlayer("jw");
		
		assertEquals("HumanPlayer", human.getClass().getName());
		
	}
	
	@Test
	public void testCreateComputerPlayerType() {
		
		Board gameBoard = new Board();
		
		Player computer = new AIPlayerMinimax("Computer", gameBoard);
		
		assertEquals("AIPlayerMinimax", computer.getClass().getName());
		
	}
	
	@Test
	public void testPlayerName() {
		
		Player player = new Player("jw", new MoveWithRowColumn());
		
		assertEquals("jw", player.getName());
		
	}
	
	@Test
	public void testPlayerMark() {
		
		// setMark() wasn't used - mark was always set in constructor
		// however, there are fixed valid marks and the constructor cannot fail
		// want to use setMark to check for valid mark options
		Player player = new Player("jw", new MoveWithRowColumn());
		player.setMark(CROSS);
		
		assertEquals(CROSS, player.getMark());
		
	}
	
	@Test
	public void testPlayerInValidMark() {
		
		Player player = new Player("jw", new MoveWithRowColumn());
		player.setMark(4);
		
		assertEquals(0, player.getMark());
		
	}
	
	@Test
	public void testPlayerValidMark() {
		
		Player player = new Player("jw", new MoveWithRowColumn());
		player.setMark(NOUGHT);
		
		assertEquals(2, player.getMark());
		
	}
	
	class MockMoveBehavior implements MoveBehavior{

		public int row = 0;
		public int column = 0;
		
		@Override
		public int[] move() {
			return new int[] {row, column};
		}
	
	}
	
	@Test
	public void testPerformMove() {
		Board gameBoard = new Board();
		
		MockMoveBehavior behavior = new MockMoveBehavior();
		Player human = new Player("jw", behavior);
		human.setMark(NOUGHT);
		
		// test setting the position
		assertTrue(human.performMove(gameBoard));
		
		// test that the position was actually set
		assertEquals(NOUGHT, gameBoard.getCellValueAt(behavior.row, behavior.column));
	}
	
	@Test
	public void testPerformMoveUnsuccessful() {
		Board gameBoard = new Board();
		
		MockMoveBehavior behavior = new MockMoveBehavior();
		Player human = new Player("jw", behavior);
		human.setMark(NOUGHT);
		
		behavior.row = 4;
		behavior.column = 4;

		// test setting the position
		assertFalse(human.performMove(gameBoard));
	
	}
	
	@Test
	public void testPerformInvalidMove() {
		Board gameBoard = new Board();
		
		MockMoveBehavior behavior = new MockMoveBehavior();
		Player human = new Player("jw", behavior);
		human.setMark(NOUGHT);
		
		behavior.row = 1;
		behavior.column = 1;
		
		// make this row and col unavailable
		gameBoard.setPlayerPosition(1, 1, CROSS);
		
		human.performMove(gameBoard);
		
		// test that the position was actually set
		assertEquals(CROSS, gameBoard.getCellValueAt(behavior.row, behavior.column));
	}

}
