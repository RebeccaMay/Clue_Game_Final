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
		assertTrue(board.getPlayerList().contains(new Player("Chad Bricky","White",10,0)));
		assertTrue(board.getPlayerList().contains(new Player("Mary M.","Magenta",22,4)));
		assertTrue(board.getPlayerList().contains(new Player("Bozo the Bafoon","Orange",6,22)));
		assertTrue(board.getPlayerList().contains(new Player("Milton Dundershire","Gray", 0,15)));
		assertTrue(board.getPlayerList().contains(new Player("Crystal Sunshine","Blue", 0,0)));
		assertTrue(board.getPlayerList().contains(new Player("LtCdr Dan","Green", 11,11)));
	}
		
	@Test
	public void loadCardsTest() {		
		//Checks to see if deck contains every room card
		
		for(Card c: board.getDeck()){
			System.out.println(c);
		}
		System.out.println(board.getDeck().contains(new Card("Bouncy Castle",CardType.ROOM)));
		
		System.out.println(new Card("Bouncy Castle",CardType.ROOM));
		
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
		nc = new Card("Chad Bricky",CardType.PERSON);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Mary M.",CardType.PERSON);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Bozo the Bafoon",CardType.PERSON);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Milton Dundershire",CardType.PERSON);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("Crystal Sunshine",CardType.PERSON);
		assertTrue(board.getDeck().contains(nc));
		
		nc = new Card("LtCdr Dan, Green",CardType.PERSON);
		assertTrue(board.getDeck().contains(nc));
		
		//Checks for total amount of cards (6 people + 6 weapons + 9 rooms)
		assertEquals(21,board.getDeck().size());
	}
	
	
	

}
