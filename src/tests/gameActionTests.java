package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ComputerPlayer;

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
		board.setConfigFiles("Layout.csv", "legend.txt", "playerData.txt", "weapons.txt");		
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
			else if(pickedCell.equals(board.getCellAt(11, 3))) test2[11] = true;
		}
		//iterate through the test to assert if it is true
		for(boolean b: test2) assertTrue(b);
	
	
		//Tests to see if there was a room that is in the the range of targets
		boolean test3 = true;
		//Instantiate a new computer player at 22, 3
		testPlayer = new ComputerPlayer("Test Player", "White", 22, 3);
		board.calcTargets(22, 3, 2);
		//loop for a large number of times
		for(int i = 0; i < 10000; i++) {
			//tell the computer to pick a location based off its current location. Hard code row column
			BoardCell pickedCell = testPlayer.pickLocation(board.getTargets());
			//This one should always return the house of mirrors room
			if(!pickedCell.equals(board.getCellAt(21, 5))) test3 = false; 
			testPlayer.setLastRoom("None");
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
			testPlayer.setLastRoom("None");
		}
		assertTrue(test4);
		
		//Tests to see if the room was just visited, can be a random assortment, use previous tests, but extrapolate
		boolean[] test5 = new boolean[2];
		//Instantiate a new computer player at 22, 3
		testPlayer = new ComputerPlayer("Test Player", "White", 22, 3);
		//Set last room name to room in list
		testPlayer.setLastRoom("House of Mirrors");
		board.calcTargets(22, 3, 2);
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
		testPlayer.setLastRoom("House of Mirrors");
		board.calcTargets(2, 22, 2);
		//loop for a large number of times
		for(int i = 0; i < 10000; i++) {
			//tell the computer to pick a location based off its current location. Hard code row column
			BoardCell pickedCell = testPlayer.pickLocation(board.getTargets());
			//This one should always return the house of mirrors room
			if(pickedCell.equals(board.getCellAt(2, 20))) test5[0] = true;
			else if(pickedCell.equals(board.getCellAt(0, 22))) test5[1] = true;
			else if(pickedCell.equals(board.getCellAt(4, 22))) test5[2] = true;
		}
		for(boolean b: test6) assertTrue(b);
	}
}


