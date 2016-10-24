package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Player;

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

	
	//Load the Person from the personData.txt. Format should be 
	// Name, Color, Row, Column 
	// Color will be a String with the color's full name
	// Name can be in the form of "First Last" or Nickname
	// Row and Column are integers
	@Test
	public void loadPersonStates() {
		
		//Checks for proper number of players in playerlist
		assertEquals(board.getPlayerList().size(), 6);
		
		//Checks if playerlist contains players as specified in playerData.txt
		assertTrue(board.getPlayerList().contains(new Player("Chad Bricky",10,0,"White")));
		assertTrue(board.getPlayerList().contains(new Player("Mary M.",22,4,"Gold")));
		assertTrue(board.getPlayerList().contains(new Player("Bozo the Bafoon",6,22,"Orange")));
		assertTrue(board.getPlayerList().contains(new Player("Milton Dundershire",0, 15,"Gray")));
		assertTrue(board.getPlayerList().contains(new Player("Crystal Sunshine",0, 0,"Blue")));
		assertTrue(board.getPlayerList().contains(new Player("LtCdr Dan",11, 11,"Green")));
	}
		
	@Test
	public void loadCardsTest() {
		//Checks for total amount of cards (6 people + 6 weapons + 9 rooms)
		assertEquals(board.getDeck().size(), 21);
		
		//Checks to see if deck contains every room card
		Card nc = new Card("Bouncy Castle",CardType.ROOM);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("House of Mirrors",CardType.ROOM);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Corn Maze",CardType.ROOM);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Haunted House",CardType.ROOM);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Ring Toss",CardType.ROOM);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Hay Ride",CardType.ROOM);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Petting Zoo",CardType.ROOM);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Ticket Booth",CardType.ROOM);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Circus Tent",CardType.ROOM);
		assertTrue(board.getDeck().contains(nc));
		
		//Checks to see if deck contains every weapon
		nc = new Card("Balloon Animal",CardType.WEAPON);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Goat",CardType.WEAPON);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Broken Mirror",CardType.WEAPON);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Cash Register",CardType.WEAPON);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Brass Knuckles",CardType.WEAPON);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Elephant Hook",CardType.WEAPON);
		assertTrue(board.getDeck().contains(nc));
		
		//Checks for player cards
	}
	
	
	

}
