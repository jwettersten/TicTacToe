public interface View {

	public abstract void create();

	public abstract void requestPlayerName();

	public abstract void requestPlayerMove(String name);

	public abstract void informPlayerMoveIsNotAvailable();

	public abstract void displayWinner(String name);

	public abstract void displayTie();

	public abstract void welcomePlayerName(String name);

	public abstract void displayGameBoard();

}