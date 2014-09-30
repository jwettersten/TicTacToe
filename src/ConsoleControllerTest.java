import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ConsoleControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Board gameBoard = new Board();
		Controller gameController = new Controller(gameBoard);
		ConsoleView consoleView = new ConsoleView(gameBoard);
		Player rowColumnPlayer = new Player("Row Column Player", gameBoard, new MoveWithRowColumn());
		rowColumnPlayer.setMark(Constants.CROSS);	
		ConsoleController consoleController = new ConsoleController(gameController, consoleView, rowColumnPlayer);
		consoleController.waitForMove();
		
		// type row value of 1 and column value of 1 on the console
		int row = 1; int col = 1;
		while (gameBoard.getCellValueAt(row - 1, col - 1) != Constants.CROSS) {
			
			try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
		
		assertEquals(Constants.CROSS, gameBoard.getCellValueAt(row - 1, col - 1));

	}
}
