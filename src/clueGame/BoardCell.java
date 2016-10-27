package clueGame;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	private DoorDirection door;

	private boolean walkway = false;
	private boolean room = false;
	private boolean doorway = false;

	public BoardCell(int row, int column, char initial,DoorDirection door) {
		super();
		this.row = row;
		this.column = column;
		this.initial = initial;
		this.door = door;
		if(initial == 'W') {
			walkway = true;
		}
		else if(door != DoorDirection.NONE){
			doorway = true;
			room = true;
		}
		else{
			room = true;
		}		
	}

	public boolean isWalkway() {
		return walkway;
	}

	public boolean isRoom() {
		return room;
	}

	public boolean isDoorway() {
		return doorway;
	}

	public DoorDirection getDoorDirection() {
		return door;
	}

	public char getInitial() {
		return initial;
	}

}
