import java.util.Scanner;

public class ConsoleController {
	private Scanner consoleUserInput = new Scanner(System.in);
	private Controller gameController;
	private ConsoleView consoleView;
	private Player consolePlayer;
	private boolean threadIsRunning = true;
	private Thread consoleThread;
	
	public ConsoleController(Controller controller, ConsoleView view, Player player) {
		gameController = controller;
		consoleView = view;
		consolePlayer = player;
	}
	
	public void waitForMove() {
		consoleThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (threadIsRunning) {
					parseAndAttemptConsoleMove();
			    	try {
		                Thread.sleep(1000);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
				}
			}

			private void parseAndAttemptConsoleMove() {
				MoveWithRowColumn mvrc = (MoveWithRowColumn)consolePlayer.getMoveBehavior();
				mvrc.setRow(consoleUserInput.nextInt() - 1);
				mvrc.setColumn(consoleUserInput.nextInt() - 1);
				gameController.attemptMove(consolePlayer);
			}
		});
		
		consoleThread.start();
		consoleView.requestPlayerMove(consolePlayer.getName());
	}
	
	public void stop() {
		threadIsRunning = false;
		try {
			consoleThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
