package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;

public class GameSetupTests {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance and initialize it		
		board = Board.getInstance();
		board.setConfigFiles("Layout.csv", "legend.txt", "playerData.txt", "weapons.txt");		
		board.initialize();
	}

	
	
	@Test
	public void loadCardsTest() {
		//Checks for total amount of cards (6 people + 6 weapons + 9 rooms)
		assertEquals(board.getDeck().size(), 21);
		
		//Checks to see if deck contains every room card
		Card nc = new Card("Bouncy Castle",CardType.ROOM);
		board.getDeck().contains(nc);
		
		nc = new Card("House of Mirrors",CardType.ROOM);
		board.getDeck().contains(nc);
		
		nc = new Card("Corn Maze",CardType.ROOM);
		board.getDeck().contains(nc);
		
		nc = new Card("Haunted House",CardType.ROOM);
		board.getDeck().contains(nc);
		
		nc = new Card("Ring Toss",CardType.ROOM);
		board.getDeck().contains(nc);
		
		nc = new Card("Hay Ride",CardType.ROOM);
		board.getDeck().contains(nc);
		
		nc = new Card("Petting Zoo",CardType.ROOM);
		board.getDeck().contains(nc);
		
		nc = new Card("Ticket Booth",CardType.ROOM);
		board.getDeck().contains(nc);
		
		nc = new Card("Circus Tent",CardType.ROOM);
		board.getDeck().contains(nc);
		
		//Checks to see if deck contains every weapon
		nc = new Card("Balloon Animal",CardType.WEAPON);
		board.getDeck().contains(nc);
		
		nc = new Card("Goat",CardType.WEAPON);
		board.getDeck().contains(nc);
		
		nc = new Card("Broken Mirror",CardType.WEAPON);
		board.getDeck().contains(nc);
		
		nc = new Card("Cash Register",CardType.WEAPON);
		board.getDeck().contains(nc);
		
		nc = new Card("Brass Knuckles",CardType.WEAPON);
		board.getDeck().contains(nc);
		
		nc = new Card("Elephant Hook",CardType.WEAPON);
		board.getDeck().contains(nc);
		
		//Checks for player cards
	}
	
	//Load the Person from the personData.txt. Format should be 
	// Name, Color, Row, Column 
	// Color will be a String with the color's full name
	// Name can be in the form of "First Last" or Nickname
	// Row and Column are integers
	@Test
	public void loadPersonStates() {
		fail("Not yet implemented");
	}
	
	

}
