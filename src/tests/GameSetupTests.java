package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

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
		
		
		int counter = 0;
		Card nc;
		for(Card c: board.getDeck()){		
			
			nc = new Card("Bouncy Castle",CardType.ROOM);
			if( c.equals(nc)) counter++;
			
			nc = new Card("House of Mirrors",CardType.ROOM);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Corn Maze",CardType.ROOM);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Haunted House",CardType.ROOM);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Ring Toss",CardType.ROOM);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Hay Ride",CardType.ROOM);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Petting Zoo",CardType.ROOM);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Ticket Booth",CardType.ROOM);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Circus Tent",CardType.ROOM);
			if( c.equals(nc)) counter++;
			
			//Checks to see if deck contains every weapon
			nc = new Card("Balloon Animal",CardType.WEAPON);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Goat",CardType.WEAPON);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Broken Mirror",CardType.WEAPON);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Cash Register",CardType.WEAPON);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Brass Knuckles",CardType.WEAPON);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Elephant Hook",CardType.WEAPON);
			if( c.equals(nc)) counter++;
			
			//Checks for player cards
			nc = new Card("Chad Bricky",CardType.PERSON);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Mary M.",CardType.PERSON);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Bozo the Bafoon",CardType.PERSON);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Milton Dundershire",CardType.PERSON);
			if( c.equals(nc)) counter++;
			
			nc = new Card("Crystal Sunshine",CardType.PERSON);
			if( c.equals(nc)) counter++;
			
			nc = new Card("LtCdr Dan",CardType.PERSON);
			if( c.equals(nc)) counter++;
			
		}
		
		//Checks for total amount of cards (6 people + 6 weapons + 9 rooms)
		assertEquals(21,counter);
	}
	
	@Test
	public void dealingCardsTest() {
		
		//Checks that each player has either 3 or 4 cards (3 players have 3, 3 players have 4)
		for (Player p: board.getPlayerList()){
			assertTrue(p.getCards().size() == 4 || p.getCards().size() == 3); 
		}
		
		//Checks to make sure number of cards equals total cards
		int counter = 0;
		for (Player p: board.getPlayerList()){
			counter += p.getCards().size(); 
		}
		assertEquals(21,counter);
		
		//Checks to see if every card is dealt once
		counter = 0;
		Card nc;
		for (Player p: board.getPlayerList()){
			for(Card c: p.getCards()){		
				
				nc = new Card("Bouncy Castle",CardType.ROOM);
				if( c.equals(nc)) counter++;
				
				nc = new Card("House of Mirrors",CardType.ROOM);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Corn Maze",CardType.ROOM);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Haunted House",CardType.ROOM);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Ring Toss",CardType.ROOM);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Hay Ride",CardType.ROOM);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Petting Zoo",CardType.ROOM);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Ticket Booth",CardType.ROOM);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Circus Tent",CardType.ROOM);
				if( c.equals(nc)) counter++;
				
				//Checks to see if deck contains every weapon
				nc = new Card("Balloon Animal",CardType.WEAPON);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Goat",CardType.WEAPON);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Broken Mirror",CardType.WEAPON);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Cash Register",CardType.WEAPON);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Brass Knuckles",CardType.WEAPON);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Elephant Hook",CardType.WEAPON);
				if( c.equals(nc)) counter++;
				
				//Checks for player cards
				nc = new Card("Chad Bricky",CardType.PERSON);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Mary M.",CardType.PERSON);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Bozo the Bafoon",CardType.PERSON);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Milton Dundershire",CardType.PERSON);
				if( c.equals(nc)) counter++;
				
				nc = new Card("Crystal Sunshine",CardType.PERSON);
				if( c.equals(nc)) counter++;
				
				nc = new Card("LtCdr Dan",CardType.PERSON);
				if( c.equals(nc)) counter++;
				
			}
		}
		assertEquals(21,counter);
		
	}
	

}
