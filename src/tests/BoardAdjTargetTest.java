package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTest {

	// Primary Game Board
	private static Board board;

	@BeforeClass
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueLegend.txt");
		board.initialize();
	}

	// Test Walkway Adjacencies
	@Test
	public void testAdjWalkway() {
		Set<BoardCell> testList;
		
		// Only Walkways
		testList = board.getAdjList(5, 2);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(5, 1)));
		assertTrue(testList.contains(board.getCellAt(5, 3)));
		assertTrue(testList.contains(board.getCellAt(4, 2)));
		assertTrue(testList.contains(board.getCellAt(6, 2)));
		
		// Left edge
		testList = board.getAdjList(11, 0);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(10, 0)));
		assertTrue(testList.contains(board.getCellAt(11, 1)));
		assertTrue(testList.contains(board.getCellAt(12, 0)));

		// Right edge
		testList = board.getAdjList(10, 23);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(10, 22)));
		assertTrue(testList.contains(board.getCellAt(11, 23)));
		assertTrue(testList.contains(board.getCellAt(9, 23)));

		// Top edge
		testList = board.getAdjList(0, 17);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(0, 16)));
		assertTrue(testList.contains(board.getCellAt(0, 18)));
		assertTrue(testList.contains(board.getCellAt(1, 17)));

		// Bottom edge
		testList = board.getAdjList(21, 13);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(21, 12)));
		assertTrue(testList.contains(board.getCellAt(21, 14)));
		assertTrue(testList.contains(board.getCellAt(20, 13)));
		
		// Corner
		testList = board.getAdjList(21, 0);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCellAt(11, 0)));
		assertTrue(testList.contains(board.getCellAt(12, 1)));
		
		// Next to room (no door)
		testList = board.getAdjList(4, 4);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(4, 5)));
		assertTrue(testList.contains(board.getCellAt(5, 4)));
		assertTrue(testList.contains(board.getCellAt(3, 4)));
		
		
		// Next to room (next to door but not the entrance)
		testList = board.getAdjList(6, 4);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(6, 5)));
		assertTrue(testList.contains(board.getCellAt(5, 4)));
		assertTrue(testList.contains(board.getCellAt(4, 4)));
		
		// Inside room
		testList = board.getAdjList(1, 1);
		assertEquals(3, testList.size());

		return;
	}

	// Test adjacencies inside rooms
	@Test
	public void testAdjInsideRooms()
	{
		// Test a corner
		Set<BoardCell> testList = board.getAdjList(0, 0);
		assertEquals(0, testList.size());
		
		return;
	}
	
	// Test adjacencies with doors
	@Test
	public void testAdjDoorway() {
		Set<BoardCell> testList;
		
		// Left
		testList = board.getAdjList(11, 1);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(11, 2)));
		
		// Right
		testList = board.getAdjList(7, 3);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(11, 5)));
		
		// Up
		testList = board.getAdjList(15, 9);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(16, 9)));
		
		// Down
		testList = board.getAdjList(7, 3);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(6, 3)));
		
		// Doorway 1
		testList = board.getAdjList(19, 3);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(18, 3)));
		
		// Doorway 2
		testList = board.getAdjList(4, 11);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(5, 11)));

		return;
	}
	
	// Test targets for room Entrances
	@Test
	public void testTargetsRoomEntrance(){
			
		
		return;
	}
	
	// Test targets for room Exits
	@Test
	public void testTargetsRoomExit(){
		
		
		return;
	}
	
	// Test targets for specified number of steps
	//
	@Test
	public void testTargetsOne(){
		
		return;
	}
	
	@Test
	public void testTargetsTwo(){
		
		return;
	}
	
	@Test
	public void testTargetsThree(){
		
		return;
	}
	
	@Test
	public void testTargetsSix(){
		
		return;
	}

}
