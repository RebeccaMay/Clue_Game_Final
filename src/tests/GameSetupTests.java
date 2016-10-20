package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;

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
		fail("Not yet implemented");
	}

}
