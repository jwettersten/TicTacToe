import java.util.Observable;
import java.util.Observer;

import lemma.library.Event;
import lemma.library.EventHandler;
import lemma.library.Lemma;

public class MockNoamMessageController implements Observer {
	private Lemma lemma;
	private Board gameBoard;
	
	private String currentNoamMessage = "";
	
	public MockNoamMessageController(Board board) throws Exception {
		setupLemma(board);
		
		this.gameBoard = board;
		
		board.addObserver(this);

	}
	
	public void disconnectLemmaFromNoam() {
		lemma.stop();
	}

	private void setupLemma(Board board) throws InterruptedException {
		
		lemma = new Lemma(this, "TicTacToeLemma", "TicTacToe");
		while (!lemma.connected()) {
			lemma.run();
	    	lemma.sendEvent("TicTacToeEvent", "noam, want to play a game?");
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		try {
			currentNoamMessage = "GameBoardUpdated";
			speakToNoam();
		} catch (InterruptedException e) {
			System.out.println("Error attempting to send message to noam!");
		}
	}
	
	public String getMessageSentToNoam() {
		return currentNoamMessage;
	}

	private void speakToNoam() throws InterruptedException {
		
		while (!lemma.connected())   {
          lemma.run();
          lemma.sendEvent("TicTacToeEvent", "GameBoardUpdated");
          currentNoamMessage = "GameBoardUpdated";
          Thread.sleep(10);
		}

	}

}
