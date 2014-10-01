import java.util.Scanner;

public class TicTacToe {
	
	public static void main(String[] args) throws Exception {
		
		Scanner consoleUserInput = new Scanner(System.in);
		
		Board gameBoard = new Board();
		Controller gameController = new Controller(gameBoard);
		
		Player noamPlayer = new Player("Noam!", gameBoard, new MoveWithRowColumn());
		noamPlayer.setMark(Constants.CROSS);
		NoamMessageController noamMessageController = new NoamMessageController(gameBoard, gameController, new Presenter(gameBoard), noamPlayer);
		
		View consoleView = new ConsoleView(gameBoard);
		gameController.setupPreferredView(consoleView);
		
		consoleView.requestPlayerName();
		String consolePlayerName = consoleUserInput.next();
		Player consolePlayer = new Player(consolePlayerName, gameBoard, new MoveWithRowColumn());
		consolePlayer.setMark(Constants.CROSS);
		ConsoleController consoleController = new ConsoleController(gameController, (ConsoleView)consoleView, consolePlayer);
		consoleView.welcomePlayerName(consolePlayer.getName());
		
		Player aiPlayer = new Player("Minimax Moves Me!", gameBoard, new MoveWithAIMinimax(gameBoard));
		aiPlayer.setMark(Constants.NOUGHT);
		new AIController(gameController, aiPlayer);
		
		consoleView.displayGameBoard();
		consoleController.waitForMove();
		gameController.playGame();
		
		noamMessageController.disconnectFromNoam();	
		consoleController.stop();
		consoleUserInput.close();
	}

}
