/**
 * 
 */

/**
 * @author jwettersten
 *
 */
public class Player {
	
	private String name = "no name";
	private char type = 'X';
	
	public Player(String playerName, char playerType) {
		this.name = playerName;
		this.type = playerType;
	}
	
	public String getName() {
		return this.name;
	}
	
	public char getType() {
		return this.type;
	}
	
	public void setType(char newType){
		this.type = newType;
	}

}
