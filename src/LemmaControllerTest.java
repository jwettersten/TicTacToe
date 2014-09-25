import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lemma.library.Event;
import lemma.library.EventHandler;
import lemma.library.Lemma;

public class LemmaControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
//	@Test 
//	public void hearLemma() throws Exception {
//        
//		//Create and configure lemma
//        Lemma lemma = new Lemma(this, "TicTacToeLemma", "TicTacToe");
//        
//        //listen for event and define callback
//        lemma.hear("TicTacToeEvent",new EventHandler() {
//            @Override
//            public void callback(Event event) {
//            	assertEquals("TicTacToeEvent", event.name);
//                System.out.println("I just heard the '"+ event.name + "' event with a value of '" + event.stringValue);
//            }
//        });
//
//        //optionally wait for connection to noam so you don't miss sending any messages
//        while (!lemma.connected())   {
//            lemma.run();
//            Thread.sleep(10);
//        }
//
//        //publish events and listen in loop
//        int count = 1;
//        while(true){
//            lemma.run();
//            lemma.sendEvent("TicTacToeEvent", "Event #" + count);
//            count++;
//            Thread.sleep(200);
//        }
//		
//	}
	
	@Test 
	public void lemmaController() throws Exception {

		Board board = new Board();
		
		MockLemmaController lemma = new MockLemmaController(new Board());
		
		board.setPlayerPosition(0, 0, Constants.CROSS);
		
		assertEquals("GameBoardUpdated", lemma.getMessageSentToNoam());
		
		lemma.disconnectLemmaFromNoam();
		
	}

}
