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
 * DONE: Finish the test as described on the website
 * Write Text File, OurText.txt
 * 
 * Hand In.
 *  
 */

// Very similar setup to Dr. Rader's tests
public class FileInitTest {

	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 24;

	private static Board board;
	
	// Sets up variables
	@BeforeClass
	public static void setUp() {

		board = Board.getInstance();

		board.setConfigFiles("ClueLayout.csv", "Legend 2.txt", "playerData.txt", "weapons.txt","RoomNameLayout");

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
		
		BoardCell room = board.getCellAt(6, 3);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());

		room = board.getCellAt(11, 2);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());

		room = board.getCellAt(11, 5);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());

		room = board.getCellAt(19, 21);
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
	
	//Test a few room cells to ensure the room initial is correct
	@Test
	public void testRoomInitials(){
		//Test different corners of rooms and all rooms
		assertEquals('L', board.getCellAt(0, 0).getInitial());
		assertEquals('S', board.getCellAt(0, 14).getInitial());
		assertEquals('G', board.getCellAt(8, 19).getInitial());
		assertEquals('E', board.getCellAt(14, 5).getInitial());
		assertEquals ('M', board.getCellAt(13, 17).getInitial());
		assertEquals ('L', board.getCellAt(0, 0).getInitial());
		assertEquals ('B', board.getCellAt(17, 4).getInitial());
		assertEquals ('A', board.getCellAt(20, 11).getInitial());
		assertEquals ('C', board.getCellAt(19, 23).getInitial());
		//Test rooms at the edge of the board
		assertEquals ('L', board.getCellAt(3, 0).getInitial());
		assertEquals ('S', board.getCellAt(0, 11).getInitial());
		assertEquals ('G', board.getCellAt(4, 23).getInitial());
		assertEquals ('C', board.getCellAt(21, 23).getInitial());
		//Test Walkway
		assertEquals ('W', board.getCellAt(20, 14).getInitial());
		//Test Kafadar
		assertEquals ('X', board.getCellAt(10, 11).getInitial());		
	}

}
