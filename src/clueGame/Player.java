package clueGame;

import java.awt.Color;
import java.util.Set;
import java.lang.reflect.Field;

public class Player {
	private String playerName;
	private int row;
	private int column;
	private Color color;
	
	private Set<Card> myCards;
	private Set<Card> seenCards;
	
	public Player(String name, int row, int col, String color){
		this.playerName = name;
		this.row = row;
		this.column = col;
		this.color = convertColor(color);
	}
	
	public Card disproveSuggestion(Solution suggestion) {
		return null;
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
}
