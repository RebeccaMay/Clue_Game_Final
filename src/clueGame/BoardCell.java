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
	private Color c;
	private final int sideLength = 26;
	private final int doorWidth = 3;
	private String roomName = "";
	

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
		g.fillRect(column*sideLength, row*sideLength, sideLength, sideLength);		
		if(walkway == true){
			g.setColor(Color.black);
			g.drawRect(column*sideLength, row*sideLength, sideLength, sideLength);
		}
		if(doorway == true){
			g.setColor(Color.blue);
			switch(door){
				case UP:
					g.fillRect(column*sideLength, row*sideLength,sideLength,doorWidth);
					break;
				case DOWN:
					g.fillRect(column*sideLength, (row+1)*sideLength-doorWidth,sideLength,doorWidth);
					break;
				case LEFT:
					g.fillRect(column*sideLength, row*sideLength,doorWidth,sideLength);
					break;
				case RIGHT:
					g.fillRect((column+1)*sideLength-doorWidth, row*sideLength,doorWidth,sideLength);
					break;
			}
			
		}
		
		if(roomName != ""){
			g.setColor(Color.black);
			g.drawString(roomName, column*sideLength, row*sideLength);
		}
		
	}
}
