import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	
	private Board gameBoard;

	@Before
	public void setUp() throws Exception {
		gameBoard = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRowColumnPlayer() {
		
		assertNotNull(new Player("jw", gameBoard, new MoveWithRowColumn()));
		
	}
	
	@Test
	public void testMinimaxPlayer() {
		
		assertNotNull(new Player("Computer", gameBoard, new MoveWithAIMinimax(gameBoard)));
		
	}

	
	@Test
	public void testPlayerName() {
		
		Player player = new Player("jw", gameBoard, new MoveWithRowColumn());
		
		assertEquals("jw", player.getName());
		
	}
	
	@Test
	public void testPlayerMark() {
		
		Player player = new Player("jw", gameBoard, new MoveWithRowColumn());
		player.setMark(Constants.CROSS);
		
		assertEquals(Constants.CROSS, player.getMark());
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPlayerInValidMark() {
		
		Player player = new Player("jw", gameBoard, new MoveWithRowColumn());
		player.setMark(4);
		
	}
	
	@Test
	public void testPlayerValidMark() {
		
		Player player = new Player("jw", gameBoard, new MoveWithRowColumn());
		player.setMark(Constants.NOUGHT);
		
		assertEquals(2, player.getMark());
		
	}
	
	@Test
	public void testPerformMove() {
		Board gameBoard = new Board();
		
		MockMoveBehavior behavior = new MockMoveBehavior();
		Player human = new Player("jw", gameBoard, behavior);
		human.setMark(Constants.NOUGHT);
		
		// test setting the position
		assertTrue(human.performMove());
		
		// test that the position was actually set
		assertEquals(Constants.NOUGHT, gameBoard.getCellValueAt(behavior.row, behavior.column));
	}
	
	@Test
	public void testPerformMoveUnsuccessful() {
		Board gameBoard = new Board();
		
		MockMoveBehavior behavior = new MockMoveBehavior();
		Player human = new Player("jw", gameBoard, behavior);
		human.setMark(Constants.NOUGHT);
		
		behavior.row = 4;
		behavior.column = 4;

		// test setting the position
		assertFalse(human.performMove());
	
	}
	
	@Test
	public void testPerformInvalidMove() {
		Board gameBoard = new Board();
		
		MockMoveBehavior behavior = new MockMoveBehavior();
		Player human = new Player("jw", gameBoard, behavior);
		human.setMark(Constants.NOUGHT);
		
		behavior.row = 1;
		behavior.column = 1;
		
		// make this row and col unavailable
		gameBoard.setPlayerPosition(1, 1, Constants.CROSS);
		
		human.performMove();
		
		// test that the position was actually set
		assertEquals(Constants.CROSS, gameBoard.getCellValueAt(behavior.row, behavior.column));
	}

}
