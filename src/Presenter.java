import org.json.JSONArray;
import org.json.JSONObject;


public class Presenter {
	Board gameBoard;
	
	public Presenter(Board board) {
		this.gameBoard = board;
	}
	
	public JSONObject getBoardStateJSON() {
		JSONObject boardValues = new JSONObject();
	    for (int row = 0; row < gameBoard.getNumRows(); ++row) {
	    	boardValues.put(Integer.toString(row), getColumnValues(boardValues, row));
	    }
	    return boardValues;
	}
	
	private JSONArray getColumnValues(JSONObject boardValues, int row) {
		int columnValues[] = new int[gameBoard.getNumColumns()];
		
		for (int col = 0; col < gameBoard.getNumColumns(); ++col) {
			columnValues[col] = gameBoard.getCellValueAt(row, col);
	    }
		
		return new JSONArray(columnValues);
	}

}
