import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BoardTest {
	
	Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testNumRows() {
		assertEquals(3, board.getNumRows());
	}
	
	@Test
	public void testNumColumns() {
		assertEquals(3, board.getNumColumns());
	}

	@Test
	public void testNewBoardIsNotFull() {
		Board board = new Board();
		
		assertFalse(board.isFull());
	}
	
	@Test
	public void testPopulatedBoardIsFull() {
		
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 3; ++col) {
				board.setPlayerPosition(row, col, 1);
			}
		}
		
		assertTrue(board.isFull());
	}
	
	@Test
	public void testGetCellValueAtRowColumn() {
		
		int row = 0;
		int column = 0;
	
		board.setPlayerPosition(row, column, 1);
		
		assertEquals(1, board.getCellValueAt(row, column));
		
	}
	
	@Test
	public void testNewCellValueAtRowColumn() {
		
		int row = 0;
		int column = 0;
	
		board.setPlayerPosition(row, column, 2);
		
		assertEquals(2, board.getCellValueAt(row, column));
		
	}
	
	@Test
	public void testPositionIsAvailable() {
		
		int row = 0;
		int column = 0;

		assertTrue(board.isPositionAvailable(row, column));
		
	}
	
	@Test
	public void testPositionIsNotAvailable() {
		
		int row = 0;
		int column = 0;
	
		board.setPlayerPosition(row, column, 2);
		
		assertFalse(board.isPositionAvailable(row, column));
		
	}
	
	@Test
	public void testValidPosition() {
		
		int row = 3;
		int column = 3;
	
		board.setPlayerPosition(row, column, 2);
		
		assertFalse(board.isPositionAvailable(row, column));
		
	}
	
	@Test(expected=IndexOutOfBoundsException.class) 
	public void raisesExceptionWhenGettingOutOfBounds() {
		 board.getCellValueAt(3, 3);
	}
	

}
