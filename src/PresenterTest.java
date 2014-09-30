import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PresenterTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testModelUpdatedJSON() {
		Board gameBoard = new Board();
		Presenter presenter = new Presenter(gameBoard);
		
		int row = 0; int column = 0;
		gameBoard.setPlayerPosition(row, column, Constants.CROSS);
		
		JSONObject testObject = new JSONObject("{2:[0,0,0],1:[0,0,0],0:[1,0,0]}");
		
		assertEquals(testObject.toString(), presenter.getBoardStateJSON().toString());
		
	}

}
