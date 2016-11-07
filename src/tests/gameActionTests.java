package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

public class gameActionTests {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	
	//Note, each test holds different categories broken down like the assignment flows. (ie. in testing all targets
	// for the computer player, there are two test for each bullet. 
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance and initialize it		
		board = Board.getInstance();
		board.setConfigFiles("LayoutAPJS.csv", "legendAPJS.txt", "playerDataAPJS.txt", "weaponsAPJS.txt","RoomNameLayoutAPJS.txt");		
		board.initialize();
	}
	//This test tests the a few locations around the board for different rolls. follows the algorithm that
	//if a room that wasn't recently visited, select it no matter what. if no room, select randomly.
	// if a room just visited was in the list, randomly select between each target.
	@Test
	public void computerPlayerSelectTargetTest() {
		//Test to see random selection between all possible cells if there is no room.
		boolean[] test1 = new boolean[8];
		//Instantiate a new computer player at 10, 4
		ComputerPlayer testPlayer = new ComputerPlayer("Test Player", "White", 10, 4);
		board.calcTargets(10, 4, 2);
		//loop for a large number of times
		for(int i = 0; i < 10000; i++) {
			//tell the computer to pick a location based off its current location. Hard code row column
			BoardCell pickedCell = testPlayer.pickLocation(board.getTargets());
			//check to see if each place has been chosen at least once.
			if(pickedCell.equals(board.getCellAt(8, 4))) test1[0] = true;
			else if(pickedCell.equals(board.getCellAt(9, 5))) test1[1] = true;
			else if(pickedCell.equals(board.getCellAt(10, 6))) test1[2] = true;
			else if(pickedCell.equals(board.getCellAt(11, 5))) test1[3] = true;
			else if(pickedCell.equals(board.getCellAt(12, 4))) test1[4] = true;
			else if(pickedCell.equals(board.getCellAt(11, 3))) test1[5] = true;
			else if(pickedCell.equals(board.getCellAt(10, 2))) test1[6] = true;
			else if(pickedCell.equals(board.getCellAt(9, 3))) test1[7] = true;
		}
		//iterate through the test to assert if it is true
		for(boolean b: test1) assertTrue(b);
		
		boolean[] test2 = new boolean[13];
		//Instantiate a new computer player at 11, 4
		testPlayer = new ComputerPlayer("Test Player", "White", 11, 4);
		board.calcTargets(11, 4, 3);
		//loop for a large number of times
		for(int i = 0; i < 10000; i++) {
			//tell the computer to pick a location based off its current location. Hard code row column
			BoardCell pickedCell = testPlayer.pickLocation(board.getTargets());
			//check to see if each place has been chosen at least once.
			if(pickedCell.equals(board.getCellAt(8, 4))) test2[0] = true;
			else if(pickedCell.equals(board.getCellAt(9, 5))) test2[1] = true;
			else if(pickedCell.equals(board.getCellAt(10, 6))) test2[2] = true;
			else if(pickedCell.equals(board.getCellAt(11, 7))) test2[3] = true;
			else if(pickedCell.equals(board.getCellAt(12, 6))) test2[4] = true;
			else if(pickedCell.equals(board.getCellAt(12, 2))) test2[5] = true;
			else if(pickedCell.equals(board.getCellAt(11, 1))) test2[6] = true;
			else if(pickedCell.equals(board.getCellAt(10, 2))) test2[7] = true;
			else if(pickedCell.equals(board.getCellAt(9, 3))) test2[8] = true;
			else if(pickedCell.equals(board.getCellAt(10, 4))) test2[9] = true;
			else if(pickedCell.equals(board.getCellAt(11, 5))) test2[10] = true;
			else if(pickedCell.equals(board.getCellAt(12, 4))) test2[11] = true;
			else if(pickedCell.equals(board.getCellAt(11, 3))) test2[12] = true;
		}
		//iterate through the test to assert if it is true
		for(boolean b: test2) assertTrue(b);
		
	
	
		//Tests to see if there was a room that is in the the range of targets
		boolean test3 = true;
		//Instantiate a new computer player at 22, 4
		testPlayer = new ComputerPlayer("Test Player", "White", 22, 4);
		board.calcTargets(22, 4, 2);
		//loop for a large number of times
		for(int i = 0; i < 10000; i++) {
			//tell the computer to pick a location based off its current location. Hard code row column
			BoardCell pickedCell = testPlayer.pickLocation(board.getTargets());
			//This one should always return the house of mirrors room
			if(!pickedCell.equals(board.getCellAt(21, 5))) test3 = false; 
			testPlayer.setLastRoom(' ');
		}
		assertTrue(test3);
		
		//Tests a different room to make sure it always chooses a room
		boolean test4 = true;
		//Instantiate a new computer player at 2, 22
		testPlayer = new ComputerPlayer("Test Player", "White", 2, 22);
		board.calcTargets(2, 22, 2);
		//loop for a large number of times
		for(int i = 0; i < 10000; i++) {
			//tell the computer to pick a location based off its current location. Hard code row column
			BoardCell pickedCell = testPlayer.pickLocation(board.getTargets());
			//This one should always return the house of mirrors room
			if(!pickedCell.equals(board.getCellAt(2, 20))) test4 = false; 
			testPlayer.setLastRoom(' ');
		}
		assertTrue(test4);
		
		//Tests to see if the room was just visited, can be a random assortment, use previous tests, but extrapolate
		boolean[] test5 = new boolean[2];
		//Instantiate a new computer player at 22, 4
		testPlayer = new ComputerPlayer("Test Player", "White", 22, 4);
		//Set last room name to room in list
		testPlayer.setLastRoom('M');
		board.calcTargets(22, 4, 2);
		//loop for a large number of times
		for(int i = 0; i < 10000; i++) {
			//tell the computer to pick a location based off its current location. Hard code row column
			BoardCell pickedCell = testPlayer.pickLocation(board.getTargets());
			//This one should always return the house of mirrors room
			if(pickedCell.equals(board.getCellAt(21, 5))) test5[0] = true;
			else if(pickedCell.equals(board.getCellAt(20, 4))) test5[1] = true;
		}
		for(boolean b: test5) assertTrue(b);
		
		
		//Last test to make sure randomness if visited last room
		boolean[] test6 = new boolean[3];
		//Instantiate a new computer player at 2, 22
		testPlayer = new ComputerPlayer("Test Player", "White", 2, 22);
		//Set last room name to room in list
		testPlayer.setLastRoom('M');
		board.calcTargets(2, 22, 2);
		//loop for a large number of times
		for(int i = 0; i < 10000; i++) {
			//tell the computer to pick a location based off its current location. Hard code row column
			BoardCell pickedCell = testPlayer.pickLocation(board.getTargets());
			//This one should always return the house of mirrors room
			if(pickedCell.equals(board.getCellAt(2, 20))) test6[0] = true;
			else if(pickedCell.equals(board.getCellAt(0, 22))) test6[1] = true;
			else if(pickedCell.equals(board.getCellAt(4, 22))) test6[2] = true;
		}
		for(boolean b: test6) assertTrue(b);
	}
	
	//This test
	@Test
	public void checkingAccusationTest() {
		//For testing purposes, set the solution to a constant
		board.setSolution("Chad Bricky", "Goat", "Corn Maze");
		
		//Test the correct solution
		assertTrue(board.checkAccusation(new Solution("Chad Bricky", "Goat", "Corn Maze")));
		//Test a wrong name
		assertFalse(board.checkAccusation(new Solution("Barney", "Goat", "Corn Maze")));
		//Test a wrong weapon
		assertFalse(board.checkAccusation(new Solution("Chad Bricky", "Balloon Animal", "Corn Maze")));
		//Test a wrong room
		assertFalse(board.checkAccusation(new Solution("Chad Bricky", "Goat", "Ticket Booth")));

	}
	
	//This test checks the computer player's ability to create a suggestion (not an accusation).
	@Test
	public void createSuggestionTest(){
		//Set up a computer player in a specific room on the layout. In this case, the corn maze.
		ComputerPlayer testPlayer = new ComputerPlayer("Test Player", "White", 0, 4);
		//Artificially deal cards to this player, 
		//Create all the weapon cards 
		Card weapon1 = new Card("Balloon Animal", CardType.WEAPON);
		Card weapon2 = new Card("Goat", CardType.WEAPON);
		Card weapon3 = new Card("Broken Mirror", CardType.WEAPON);
		Card weapon4 = new Card("Cash Register", CardType.WEAPON);
		Card weapon5 = new Card("Brass Knuckles", CardType.WEAPON);
		Card weapon6 = new Card("Elephant Hook", CardType.WEAPON);
		
		//Create all the player cards
		Card person1 = new Card("Chad Bricky",CardType.PERSON);	
		Card person2 = new Card("Mary M.",CardType.PERSON);		
		Card person3 = new Card("Bozo the Bafoon",CardType.PERSON);		
		Card person4 = new Card("Milton Dundershire",CardType.PERSON);		
		Card person5 = new Card("Crystal Sunshine",CardType.PERSON);		
		Card person6 = new Card("LtCdr Dan",CardType.PERSON);
		
		//Force give the testPlayer 3 weapons and 3 people.
		board.forceGiveCard(testPlayer, weapon1);
		board.forceGiveCard(testPlayer, weapon2);
		board.forceGiveCard(testPlayer, weapon3);
		board.forceGiveCard(testPlayer, person1);
		board.forceGiveCard(testPlayer, person2);
		board.forceGiveCard(testPlayer, person3);
	
		//Test to see that suggestion always returns room
		assertEquals(testPlayer.createSuggestion().getRoom(), "Corn Maze");
		
		//Test to make a suggestion that is random between them all
		boolean[] test1 = new boolean[6];
		for (int i = 0; i < 10000; i++){
			Solution suggestion = testPlayer.createSuggestion();
			//Check to see if person is chosen
			if(suggestion.getPerson().equals(person4.getCardName())) test1[0] = true;
			if(suggestion.getPerson().equals(person5.getCardName())) test1[1] = true;
			if(suggestion.getPerson().equals(person6.getCardName())) test1[2] = true;

			//Check to see if weapon is chosen
			if(suggestion.getWeapon().equals(weapon4.getCardName())) test1[3] = true;
			if(suggestion.getWeapon().equals(weapon5.getCardName())) test1[4] = true;
			if(suggestion.getWeapon().equals(weapon6.getCardName())) test1[5] = true;
		}
	
		for(boolean b: test1) assertTrue(b);
		
		
		//Give player other two weapons and two people.
		board.forceGiveCard(testPlayer, weapon4);
		board.forceGiveCard(testPlayer, weapon5);
		board.forceGiveCard(testPlayer, person4);
		board.forceGiveCard(testPlayer, person5);
		
		//Test to make sure the suggestion is for player6 for weapon6 in the room they are in. 
		boolean test2 = false;
		for (int i = 0; i < 10000; i++){
			Solution suggestion = testPlayer.createSuggestion();
			//Check to see if person6 and weapon6 is chosen
			if(!(suggestion.getPerson().equals(person6.getCardName()) && suggestion.getWeapon().equals(weapon6.getCardName()))) test2 = true;
		}
		assertTrue(test2);		
	}
	
	@Test
	public void testDisproveSuggestion() {
		//Instantiate a test computer player at 0,0
		ComputerPlayer testPlayer = new ComputerPlayer("Test Player", "White", 0, 0);
		
		//give the player specific cards. Give 2 weapons and 1 person
		Card weapon1 = new Card("Balloon Animal", CardType.WEAPON);
		Card weapon2 = new Card("Goat", CardType.WEAPON);
		Card person1 = new Card("Chad Bricky",CardType.PERSON);
		
		//Deal specific cards
		board.forceGiveCard(testPlayer, weapon1);
		board.forceGiveCard(testPlayer, weapon2);
		board.forceGiveCard(testPlayer, person1);
		
		//If computer player gets a suggestion that matches only one card, return that one card.
		//As the function will have random components, test to make sure it doesn't return randomly
		for(int i = 0; i < 10000; i++) {
			assertEquals(person1, testPlayer.disproveSuggestion(new Solution("Chad Bricky", "Broken Mirror", "Corn Maze")));
		}
		
		//If computer has two or more cards (this case two), make sure at least all cards can be chosen
		boolean[] test2 = new boolean[2];
		Solution disprove = new Solution("Chad Bricky", "Goat", "Corn Maze");
		for(int i = 0; i < 10000; i++) {
			if(testPlayer.disproveSuggestion(disprove).equals(person1)) test2[0] = true;
			if(testPlayer.disproveSuggestion(disprove).equals(weapon2)) test2[0] = true;
			//Check to see other card in hand not returned
			if(testPlayer.disproveSuggestion(disprove).equals(weapon1)) fail("Returned Wrong Card");
		}
		
		//If no matching cards found, return null
		//As it can have random components, check to see it always is null
		//As the function will have random components, test to make sure it doesn't return randomly
		disprove = new Solution("Mary M.", "Broken Mirror", "Corn Maze");
		for(int i = 0; i < 10000; i++) {
			assertEquals(null, testPlayer.disproveSuggestion(disprove));
		}
	}
	
	@Test
	public void testHandleSuggestion() {
		//Test the board class, make some fake players and deal them some cards. These functions are only used for testing. 
		//The setup of the data structures is tested in GameSetupTests.java
		ComputerPlayer testPlayer1 = new ComputerPlayer("TestPlayer1", "White", 0,0);
		ComputerPlayer testPlayer2 = new ComputerPlayer("TestPlayer2", "White", 0,0);
		ComputerPlayer testPlayer3 = new ComputerPlayer("TestPlayer3", "White", 0,0);
		ComputerPlayer testPlayer4 = new ComputerPlayer("TestPlayer4", "White", 0,0);
		ComputerPlayer testPlayer5 = new ComputerPlayer("TestPlayer5", "White", 0,0);
		HumanPlayer testPlayer6 = new HumanPlayer("TestPlayer6", "White", 0,0);
		
		//Deal specific cards to the players to make sure order is kept, deal one card for testing, 
		// No need to deal the whole deck.
		Card weapon1 = new Card("Balloon Animal", CardType.WEAPON);
		Card weapon2 = new Card("Goat", CardType.WEAPON);
		
		Card person1 = new Card("Chad Bricky",CardType.PERSON);	
		Card person2 = new Card("Mary M.",CardType.PERSON);	
		
		Card room1 = new Card("Bouncy Castle",CardType.ROOM);
		Card room2 = new Card("House of Mirrors",CardType.ROOM);

		//Give them the cards
		board.forceGiveCard(testPlayer1, weapon1);
		board.forceGiveCard(testPlayer2, weapon2);
		board.forceGiveCard(testPlayer3, person1);
		board.forceGiveCard(testPlayer4, person2);
		board.forceGiveCard(testPlayer5, room1);
		board.forceGiveCard(testPlayer6, room2);
		//Add them to the list
		ArrayList<Player> testPlayerList = new ArrayList<Player>();
		testPlayerList.add(testPlayer1);
		testPlayerList.add(testPlayer2);
		testPlayerList.add(testPlayer3);
		testPlayerList.add(testPlayer4);
		testPlayerList.add(testPlayer5);
		testPlayerList.add(testPlayer6);
		//Set this list of players as a "faux" player list for testing
		board.forceSetPlayerList(testPlayerList);
		
		//Test to make sure that if we are at player1, that if nobody can disprove the suggestion, then null is returned
		board.setCurrentPlayer(0);
		assertEquals(null, board.handleSuggestion(new Solution("LtCmdr Dan", "Broken Mirror", "Corn Maze")));
		
		//Test to see that this is also true for starting at player5
		board.setCurrentPlayer(4);
		assertEquals(null, board.handleSuggestion(new Solution("LtCmdr Dan", "Broken Mirror", "Corn Maze")));
		
		//Test to see if player 1 can only disprove the answer then returns null
		board.setCurrentPlayer(0);
		assertEquals(null, board.handleSuggestion(new Solution("LtCmdr Dan", "Balloon Animal", "Corn Maze")));
		
		//Test if it works at player 5
		board.setCurrentPlayer(4);
		assertEquals(null, board.handleSuggestion(new Solution("LtCmdr Dan", "Broken Mirror", "Bouncy Castle")));
		
		//Test to see if human player can only disprove it, then the return is the card. 
		//Test at player 1
		board.setCurrentPlayer(0);
		assertEquals(room2, board.handleSuggestion(new Solution("LtCmdr Dan", "Broken Mirror", "House of Mirrors")));
		
		
		//Test at player 5
		board.setCurrentPlayer(4);
		assertEquals(room2, board.handleSuggestion(new Solution("LtCmdr Dan", "Broken Mirror", "House of Mirrors")));
		
		//Now test to see if the human is the accuser, it instead returns null
		board.setCurrentPlayer(5);
		assertEquals(null, board.handleSuggestion(new Solution("LtCmdr Dan", "Broken Mirror", "House of Mirrors")));
		
		//Test to see if two people can disprove, that the correct one disproves
		//Start at player1 and players 2 and 3 can disprove, check to see player 2 disproved. 
		board.setCurrentPlayer(0);
		assertEquals(weapon2, board.handleSuggestion(new Solution("Chad Bricky", "Goat", "Corn Maze")));
		
		//Again, test to make sure human player's card doesn't take priority
		board.setCurrentPlayer(0);
		assertEquals(weapon2, board.handleSuggestion(new Solution("LtCmdr Dan", "Goat", "House of Mirrors")));
	}
}


