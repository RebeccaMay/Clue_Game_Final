package clueGame;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;
import java.lang.reflect.Field;

public class Player {
	private String playerName;
	private int row;
	private int column;
	private Color color;
	
	private Set<Card> myCards;
	private Set<Card> seenCards;
	
	public Player(String name, String color, int row, int col){
		this.playerName = name;
		this.row = row;
		this.column = col;
		this.color = convertColor(color);
		
		this.myCards = new HashSet<Card>();
		this.seenCards = new HashSet<Card>();
	}
	
	public Card disproveSuggestion(Solution suggestion) {
		return null;
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

	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", row=" + row + ", column=" + column + ", color=" + color
				+"]";
	}
	
	//Had to override the equals method, as the java default failed (when is most certainly should not have)
	@Override
	public boolean equals(Object o){
		Player p = (Player)o;
		return this.playerName.equals(p.playerName) && this.row == p.row && this.column == p.column && this.color.equals(p.color);
	}
	
	public void giveCard(Card c){
		myCards.add(c);
		seenCards.add(c);
	}
	
	//This getter is for testing only
	public Set<Card> getCards(){
		return this.myCards;
	}
}