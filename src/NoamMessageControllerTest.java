import static org.junit.Assert.*;

import java.util.Arrays;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lemma.library.Event;
import lemma.library.EventHandler;
import lemma.library.Lemma;

public class NoamMessageControllerTest {
	
	NoamMessageGateway noamGate = NoamMessageGateway.getMessageGateway();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
/*	@Test // Learning test
	public void hearLemma() throws Exception {
        
		//Create and configure lemma
        Lemma lemma = new Lemma(this, "TicTacToeLemma", "TicTacToe");
        
        //listen for event and define callback
        lemma.hear("TicTacToeEvent",new EventHandler() {
            @Override
            public void callback(Event event) {
            	assertEquals("TicTacToeEvent", event.name);
                System.out.println("I just heard the '"+ event.name + "' event with a value of '" + event.stringValue);
            }
        });

        //optionally wait for connection to noam so you don't miss sending any messages
        while (!lemma.connected())   {
            lemma.run();
            Thread.sleep(10);
        }

        //publish events and listen in loop
        int count = 1;
        while(true){
            lemma.run();
            lemma.sendEvent("TicTacToeEvent", "Event #" + count);
            count++;
            Thread.sleep(200);
        }
		
	}*/
	
/*	@Test // Learning test
	public void testLemmaObserver() throws Exception {

		Board board = new Board();
		
		MockNoamMessageController lemma = new MockNoamMessageController(new Board());
		
		board.setPlayerPosition(0, 0, Constants.CROSS);
		
		assertEquals("GameBoardUpdated", lemma.getMessageSentToNoam());
		
		lemma.disconnectLemmaFromNoam();
	}*/
	
//	@Test 
//	public void testNoamMessageGatewayInitAndSendJSON() throws Exception {
//
//		noamGate.setupLemmaAndMessageHandling();
//		
//		JSONObject helloNoam = new JSONObject().put("JSON", "Hello Noam!");
//		
//		assertTrue(noamGate.sendMessage(helloNoam));
//		
//		noamGate.stop();
//	}
//	
//	@Test 
//	public void testNoamMessageGatewayInitAndSendSting() throws Exception {
//
//		noamGate.setupLemmaAndMessageHandling();
//		
//		String helloNoam = "Hello Noam!";
//		
//		assertTrue(noamGate.sendMessage(helloNoam));
//		
//		noamGate.stop();	
//	}
	
	@Test 
	public void testNoamMessageGatewayReceiveMoveFromNoam() throws Exception {
		
		Board gameBoard = new Board();
		NoamMessageController noamController = new NoamMessageController(gameBoard, new Presenter(gameBoard));
		
		int row = 0; int col = 0; int playerMark = Constants.CROSS;
		
		noamGate.setupLemmaAndMessageHandling();
		
		String moveFromNoam = "0,0," + String.valueOf(playerMark); // row, col, Constants.CROSS
		
		noamController.parseAndAttemptIncomingMove(moveFromNoam);
		
		assertEquals(1, gameBoard.getCellValueAt(row, col));
		
		noamGate.stop();	
	}
}
