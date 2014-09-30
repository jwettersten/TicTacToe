import java.util.Scanner;

public class TicTacToe {
	
	public static void main(String[] args) throws Exception {
		
		Scanner consoleUserInput = new Scanner(System.in);
		
		Board gameBoard = new Board();
		Controller gameController = new Controller(gameBoard);
		NoamMessageController messageController = new NoamMessageController(gameBoard, gameController, new Presenter(gameBoard));
		
		View view = new ConsoleView(gameBoard);
		gameController.setupPreferredView(view);
		
		view.requestPlayerName();
		String newPlayerName = consoleUserInput.next(); 
		Player human = new Player(newPlayerName, gameBoard, new MoveWithRowColumn());
		human.setMark(Constants.CROSS);
		gameController.setHumanPlayer(human);
		ConsoleController consoleController = new ConsoleController(gameController, (ConsoleView)view, human);
		view.welcomePlayerName(human.getName());
		consoleController.waitForMove();
		
		Player computer = new Player("Computer", gameBoard, new MoveWithAIMinimax(gameBoard));
		computer.setMark(Constants.NOUGHT);
		gameController.setComputerPlayer(computer);
		
		view.displayGameBoard();
		
		gameController.playGame();
		
		messageController.disconnectFromNoam();	
		consoleController.stop();
		consoleUserInput.close();

		// use factory singleton pattern for players
		
	}

}
