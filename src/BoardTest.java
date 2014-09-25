import static org.junit.Assert.*;

import java.util.Arrays;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	
	Board board;
	private static final int ROWS = 3;
	private static final int COLUMNS = 3;

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
				board.setPlayerPosition(row, col, Constants.CROSS);
			}
		}
		
		assertTrue(board.isFull());
	}
	
	@Test
	public void testGetCellValueAtRowColumn() {
		
		int row = 0;
		int column = 0;
	
		board.setPlayerPosition(row, column, Constants.CROSS);
		
		assertEquals(1, board.getCellValueAt(row, column));
		
	}
	
	@Test
	public void testNewCellValueAtRowColumn() {
		
		int row = 0;
		int column = 0;
	
		board.setPlayerPosition(row, column, Constants.NOUGHT);
		
		assertEquals(2, board.getCellValueAt(row, column));
		
	}
	
	@Test
	public void testPositionIsAvailable() {
		
		int row = 0;
		int column = 0;

		assertTrue(board.positionIsAvailable(row, column));
		
	}
	
	@Test
	public void testPositionIsNotAvailable() {
		
		int row = 0;
		int column = 0;
	
		board.setPlayerPosition(row, column, Constants.NOUGHT);
		
		assertFalse(board.positionIsAvailable(row, column));
		
	}
	
	@Test
	public void testValidPosition() {
		
		int row = 3;
		int column = 3;
	
		board.setPlayerPosition(row, column, Constants.NOUGHT);
		
		assertFalse(board.positionIsAvailable(row, column));
		
	}
	
	@Test(expected=IndexOutOfBoundsException.class) 
	public void raisesExceptionWhenGettingOutOfBounds() {
		 board.getCellValueAt(3, 3);
	}
	
	@Test
	public void convertBoardToJSON() {
		int[][] gameBoard = board.getBoard();
		
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 3; ++col) {
				gameBoard[row][col] = Constants.CROSS;
			}
		}
	    
		JSONObject boardValues = new JSONObject();
	    for (int row = 0; row < ROWS; ++row) {
	    	//boardValues.put(Integer.toString(row), getSecondDimensionValues(boardValues, gameBoard, row));
	    	boardValues.put("Row: " + Integer.toString(row), Arrays.toString(gameBoard[row]));
	    }
	    
	    //assertEquals()
	    System.out.println(boardValues.toString());

	}

	private String getSecondDimensionValues(JSONObject boardValues, int[][] gameBoard, int row) {
		
		for (int col = 0; col < COLUMNS; ++col) {
			boardValues.put(Integer.toString(col), Integer.toString(gameBoard[row][col]));
		}
		
		return boardValues.toString();
	}

}
