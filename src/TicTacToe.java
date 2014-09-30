import java.util.Scanner;

public class TicTacToe {
	
	public static void main(String[] args) throws Exception {
		
		Scanner consoleUserInput = new Scanner(System.in);
		
		Board gameBoard = new Board();
		Controller gameController = new Controller(gameBoard);
		NoamMessageController noamMessageController = new NoamMessageController(gameBoard, gameController, new Presenter(gameBoard));
		
		View consoleView = new ConsoleView(gameBoard);
		gameController.setupPreferredView(consoleView);
		
		consoleView.requestPlayerName();
		String consolePlayerName = consoleUserInput.next();
		Player consolePlayer = new Player(consolePlayerName, gameBoard, new MoveWithRowColumn());
		consolePlayer.setMark(Constants.CROSS);
		ConsoleController consoleController = new ConsoleController(gameController, (ConsoleView)consoleView, consolePlayer);
		consoleView.welcomePlayerName(consolePlayer.getName());
		
		Player computer = new Player("Computer", gameBoard, new MoveWithAIMinimax(gameBoard));
		computer.setMark(Constants.NOUGHT);
		gameController.setComputerPlayer(computer);
		
		consoleView.displayGameBoard();
		consoleController.waitForMove();
		gameController.playGame();
		
		noamMessageController.disconnectFromNoam();	
		consoleController.stop();
		consoleUserInput.close();
	}

}
