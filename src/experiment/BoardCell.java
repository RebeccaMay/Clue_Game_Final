package experiment;
/*This class should have/do the following:
 * 
 * DONE: Two member variables of type int to represent the row and column.
 * 
 * ToDo: Add more fields later.
 * 
 * DONE: Should be in the package experiment.
 * 
 * 
 * 
 * */
public class BoardCell {
	private int row;
	private int column;
	
	
	public BoardCell(int row, int column){
		this.row = row;
		this.column = column;
	}
	
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
}
