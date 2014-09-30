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
		
		consoleThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (threadIsRunning) {
					consolePlayer.moveBehavior.setRow(consoleUserInput.nextInt() - 1);
					consolePlayer.moveBehavior.setColumn(consoleUserInput.nextInt() - 1);
					gameController.attemptMove(consolePlayer);
			    	try {
		                Thread.sleep(100);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
				}
			}
		});
	}
	
	public void waitForMove() {
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
