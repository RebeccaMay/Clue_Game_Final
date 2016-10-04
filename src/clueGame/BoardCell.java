package clueGame;

public class BoardCell {
private int row;
private int column;
private char initial;

public BoardCell(int row, int column, char initial) {
	super();
	this.row = row;
	this.column = column;
	this.initial = initial;
}

public boolean isWalkway(){
	return false;
}

public boolean isRoom(){
	return false;
}

public boolean isDoorway(){
	return false;	
}
}
