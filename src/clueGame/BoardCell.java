package clueGame;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	private DoorDirection door;

	private boolean walkway = false;
	private boolean room = false;
	private boolean doorway = false;

	public BoardCell(int row, int column, char initial) {
		super();
		this.row = row;
		this.column = column;
		this.initial = initial;
		
		
		
	}

	public boolean isWalkway() {
		if (initial == 'W') {
			return true;
		}
		return false;
	}

	public boolean isRoom() {

		return false;
	}

	public boolean isDoorway() {
		return false;
	}

	public DoorDirection getDoorDirection() {
		return door;
	}

	public char getInitial() {
		return initial;
	}

}
