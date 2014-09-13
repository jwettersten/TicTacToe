
/**
 * @author jwettersten
 *
 */
public class Player {
	
	private String name;
	private int mark;
	
	public Player(String playerName, int playerType) {
		this.name = playerName;
		this.mark = playerType;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getMark() {
		return this.mark;
	}
	
	public void setMark(int newType) {
		this.mark = newType;
	}

}
