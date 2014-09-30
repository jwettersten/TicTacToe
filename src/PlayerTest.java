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
		player.setMark(Constants.CROSS);
		
		assertEquals(1, player.getMark());
	}
	
	@Test
	public void testPerformMove() {
		Board gameBoard = new Board();
		
		MoveWithRowColumn behavior = new MoveWithRowColumn();
		Player rowColumnPlayer = new Player("jw", gameBoard, behavior);
		rowColumnPlayer.setMark(Constants.CROSS);
		
		int row = 0; int column = 0;
		behavior.setRow(row);
		behavior.setColumn(column);
		
		assertTrue(rowColumnPlayer.performMove());

		assertEquals(Constants.CROSS, gameBoard.getCellValueAt(row, column));
	}
	
	@Test
	public void testPerformMoveUnsuccessful() {
		Board gameBoard = new Board();
		
		MoveWithRowColumn behavior = new MoveWithRowColumn();
		Player rowColumnPlayer = new Player("jw", gameBoard, behavior);
		rowColumnPlayer.setMark(Constants.NOUGHT);
		
		int row = 4; int column = 4;
		behavior.setRow(row);
		behavior.setColumn(column);

		assertFalse(rowColumnPlayer.performMove());
	}

}
