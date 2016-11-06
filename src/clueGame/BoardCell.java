package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Field;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	private DoorDirection door;

	private boolean walkway = false;
	private boolean room = false;
	private boolean doorway = false;

	//GUI related variables
	Color c;
	int sideLength = 20;
	String roomName = "";

	public BoardCell(int row, int column, char initial,DoorDirection door) {
		super();
		this.row = row;
		this.column = column;
		this.initial = initial;
		this.door = door;
		if(initial == 'W') {
			walkway = true;
			c = convertColor("yellow");
		}
		else if(door != DoorDirection.NONE){
			doorway = true;
			room = true;
			c = convertColor("lightGray");
		}
		else{
			room = true;
			c = convertColor("lightGray");
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

	//setter for cell roomName string. Room name is a label drawn on the board for the given room
	public void setRoomName(String s){
		this.roomName = s;
	}

	// Private method for converting string representation of color to actual color object
	private Color convertColor(String strColor) {
		strColor = strColor.toLowerCase();
		Color color; 
		try {     
			// We can use reflection to convert the string to a color
			Field field = Class.forName("java.awt.Color").getField(strColor.trim());     
			color = (Color)field.get(null); 
		} catch (Exception e) {  
			color = null; // Not defined  
		}
		return color;
	}

	public void draw(Graphics g){
		g.setColor(c);
		g.drawRect(column*sideLength, row*sideLength, sideLength, sideLength);
	}
}
