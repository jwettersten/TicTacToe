import java.util.Observable;
import java.util.Observer;

import lemma.library.Event;
import lemma.library.EventHandler;
import lemma.library.Lemma;

public class LemmaController implements Observer {
	private Lemma lemma;
	private Board gameBoard;
	
	public LemmaController(Board board) throws Exception {
		setupLemma(board);
		
		this.gameBoard = board;
		
		board.addObserver(this);

	}

	private void setupLemma(Board board) throws InterruptedException {
		
		lemma = new Lemma(this, "TicTacToeLemma", "TicTacToe");
		while (!lemma.connected()) {
			lemma.run();
	    	lemma.sendEvent("TicTacToeEvent", "noam, want to play a game?");
		}
		
	}
	
	public void disconnectLemmaFromNoam() {
		lemma.stop();
	}

	@Override
	public void update(Observable o, Object arg) {
		try {
			speakToNoam();
		} catch (InterruptedException e) {
			System.out.println("Error attempting to send message to noam!");
		}
	}

	private void speakToNoam() throws InterruptedException {
       lemma.run();
       lemma.sendEvent("TicTacToeEvent", gameBoard.getJSON());
	}

}
