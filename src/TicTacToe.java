import java.util.Scanner;

public class TicTacToe {
	
	public static void main(String[] args) throws Exception {
		
		Scanner consoleUserInput = new Scanner(System.in);
		
		Board gameBoard = new Board();
		Controller gameController = new Controller(gameBoard);
		LemmaController lemmaController = new LemmaController(gameBoard);
		View view = new ConsoleView(gameBoard);
		gameController.setupPreferredView(view);
		
		view.requestPlayerName();
		String newPlayerName = consoleUserInput.next(); 
		Player human = new HumanPlayer(newPlayerName, gameBoard);
		human.setMark(Constants.CROSS);
		gameController.setHumanPlayer(human);
		view.welcomePlayerName(human.getName());
		
		Player computer = new AIPlayerMinimax("Computer", gameBoard);
		computer.setMark(Constants.NOUGHT);
		gameController.setComputerPlayer(computer);
		
		gameController.playGame();
		
		lemmaController.disconnectLemmaFromNoam();	
		consoleUserInput.close();

	}

}
