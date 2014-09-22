import java.util.Scanner;

public class MoveWithRowColumn implements MoveBehavior {
	
	private Scanner consoleUserInput = new Scanner(System.in);
	
	public int[] move() {

		int row = consoleUserInput.nextInt() - 1; 
		int column = consoleUserInput.nextInt() - 1;
		
		return new int[] {row, column};
	}

}
