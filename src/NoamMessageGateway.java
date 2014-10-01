import org.json.JSONObject;

import lemma.library.Lemma;
import lemma.library.Event;
import lemma.library.EventHandler;

public class NoamMessageGateway {
	private static NoamMessageGateway uniqueInstance = new NoamMessageGateway();
	private static Lemma lemma;
	private boolean threadIsRunning = true;
	private Thread lemmaThread;
	private NoamMessageController messageController;
	
	private NoamMessageGateway() { 
		setupLemmaAndMessageHandling();
	}
	
	public static NoamMessageGateway getMessageGateway() {
		return uniqueInstance;
	}
	
	public void setMessageController(NoamMessageController msgController) {
		messageController = msgController;
	}
	
	public void setupLemmaAndMessageHandling() {
		lemma = new Lemma(this, "TicTacToeLemma", "TicTacToe");
		setupLemmaHearEventHandlers();
		lemmaThread.start();
		waitForConnection();
				
	}

	private void setupLemmaHearEventHandlers() {
		lemma.hear("TicTacToeEventInput",new EventHandler() {
		      @Override
		      public void callback(Event event) {
		          System.out.println("I just heard the '"+ event.name + "' event with a value of '" + event.stringValue);
		          messageController.parseAndAttemptIncomingMove(event.stringValue);
		      }
		});
		
		// lemma.run() should block instead of requiring this thread
		// pull request for Lemma.java?
		lemmaThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (threadIsRunning) {
					lemma.run();
			    	try {
		                Thread.sleep(100);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
				}
		
			}
		});
	}
	
	private void waitForConnection() {
		while (!lemma.connected()) {
			lemma.run();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		threadIsRunning = false;
		lemma.stop();
		try {
			lemmaThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean sendMessage(String message) throws InterruptedException {
		return lemma.sendEvent("TicTacToeEvent", message);
	}
	
	public boolean sendMessage(JSONObject message) throws InterruptedException {
		return lemma.sendEvent("TicTacToeEvent", message);
	}
	
}
