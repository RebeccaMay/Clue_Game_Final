package tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

/*
 * Done:
 * Before Test
 * Rooms Test
 * Board Dimensions Test
 * Door Directions Test
 * Number of Doorways Test
 * 
 * TODO:
 * Finish the test as described on the website
 * Write Text File, OurText.txt
 * 
 * Hand In.
 *  
 */

// Very similar setup to Dr. Rader's tests
public class FileInitTest {

	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 21;
	public static final int NUM_COLUMNS = 23;

	private static Board board;
	
	// Sets up variables
	@BeforeClass
	public static void setUp() {

		board = Board.getInstance();

		board.setConfigFiles("ClueLayout.csv", "Legend.txt");

		board.initialize();

		return;
	}
	
	// Test legend file read for correct rooms
	@Test
	public void testRooms() {

		Map<Character, String> legend = board.getLegend();
		assertEquals(LEGEND_SIZE, legend.size());

		// Legend Read Test
		assertEquals("Brown Building", legend.get('B'));
		assertEquals("Alderson Hall", legend.get('A'));
		assertEquals("Marquez Hall", legend.get('M'));
		assertEquals("CTLM", legend.get('C'));
		assertEquals("Green Center", legend.get('G'));
		assertEquals("Coolbaugh Hall", legend.get('O'));
		assertEquals("Stratton", legend.get('S'));
		assertEquals("Berthoud Hall", legend.get('E'));
		assertEquals("Library", legend.get('L'));
		assertEquals("Kafadar", legend.get('X'));
		assertEquals("Walkway", legend.get('W'));

		return;
	}
	
	// Test the correct dimensions for the game board
	@Test
	public void testBoardDimensions() {
		// Ensure we have the proper number of rows and columns
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}

	// Test directions for each door option, and non-door options
	@Test
	public void FourDoorDirections() {
		BoardCell room = board.getCellAt(7, 3);

		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());

		room = board.getCellAt(12, 2);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());

		room = board.getCellAt(12, 5);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());

		room = board.getCellAt(21, 19);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());

		// Test that room pieces that aren't doors know it
		room = board.getCellAt(7, 4);
		assertFalse(room.isDoorway());

		// Test that walkways are not doors
		BoardCell cell = board.getCellAt(0, 6);
		assertFalse(cell.isDoorway());

		return;
	}

	// Test for correct numbers of doors on the board
	@Test
	public void testNumberOfDoorways() {
		int numDoors = 0;
		
		for (int row = 0; row < board.getNumRows(); row++){
			
			for (int col = 0; col < board.getNumColumns(); col++) {
				
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway()){
					numDoors++;
				}
			}
		}
		
		assertEquals(13, numDoors);
	}

}
