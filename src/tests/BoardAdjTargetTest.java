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
		board.setConfigFiles("ClueLayout.csv", "Legend 2.txt", "playerData.txt", "weapons.txt");
		board.initialize();
	}

	// Test Walkway Adjacencies
	@Test
	public void testAdjWalkway() {
		Set<BoardCell> testList;
		
		// Only Walkways
		testList = board.getAdjList(1, 5);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(0, 5)));
		assertTrue(testList.contains(board.getCellAt(1, 6)));
		assertTrue(testList.contains(board.getCellAt(1, 4)));
		assertTrue(testList.contains(board.getCellAt(2, 5)));
		
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
		assertTrue(testList.contains(board.getCellAt(20, 0)));
		assertTrue(testList.contains(board.getCellAt(21, 1)));
		
		// Next to room (no door)
		testList = board.getAdjList(4, 4);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(4, 5)));
		assertTrue(testList.contains(board.getCellAt(5, 4)));
		assertTrue(testList.contains(board.getCellAt(3, 4)));
		
		//Next to room (no door) 2.
		testList = board.getAdjList(2, 15);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(1, 15)));
		assertTrue(testList.contains(board.getCellAt(3, 15)));
		assertTrue(testList.contains(board.getCellAt(2, 16)));
		
		
		// Next to room (next to door but not the entrance)
		testList = board.getAdjList(6, 4);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(6, 5)));
		assertTrue(testList.contains(board.getCellAt(5, 4)));
		assertTrue(testList.contains(board.getCellAt(7, 4)));
		
		// Inside room
		testList = board.getAdjList(1, 1);
		assertEquals(0, testList.size());

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
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(6, 3)));
		
		// Up
		testList = board.getAdjList(15, 9);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(16, 9)));
		
		// Down
		testList = board.getAdjList(7, 3);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(6, 3)));
		
		// Doorway Exit Up
		testList = board.getAdjList(19, 3);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(18, 3)));
		
		// Doorway Exit Down
		testList = board.getAdjList(4, 11);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(5, 11)));

		return;
	}
	
	// Test targets for room Entrances
	@Test
	public void testTargetsRoomEntrance(){
			
		board.calcTargets(5, 16, 3);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(14, targets.size());
		// Doors
		assertTrue(targets.contains(board.getCellAt(4, 18)));
		assertTrue(targets.contains(board.getCellAt(6, 18)));
		
		board.calcTargets(10, 17, 5);
		targets= board.getTargets();
		assertEquals(18, targets.size());
		// Doors
		assertTrue(targets.contains(board.getCellAt(13, 17)));
		assertTrue(targets.contains(board.getCellAt(6, 18)));
		
		return;
	}
	
	// Test targets for room Exits
	@Test
	public void testTargetsRoomExit(){
		
		board.calcTargets(20, 15, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(1, targets.size());
		// Single Result
		assertTrue(targets.contains(board.getCellAt(20, 14)));
		
		board.calcTargets(19, 21, 1);
		targets= board.getTargets();
		assertEquals(1, targets.size());
		// Single Result
		assertTrue(targets.contains(board.getCellAt(18, 21)));

		return;
	}
	
	// Test targets for door entrance less than path length
	@Test
	public void testTargetsRoomEntranceShortcut() {
		
		board.calcTargets(18, 20, 4);
		Set<BoardCell> targets= board.getTargets();
		
		assertEquals(10, targets.size());
		
		// Doors
		assertTrue(targets.contains(board.getCellAt(19, 21)));
		assertTrue(targets.contains(board.getCellAt(16, 21)));
		
		return;
	}
	
	// Test targets for specified number of steps
	//
	@Test
	public void testTargetsOne(){
		
		board.calcTargets(10, 21, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(10, 22)));
		assertTrue(targets.contains(board.getCellAt(11, 21)));
		
		return;
	}
	
	@Test
	public void testTargetsTwo(){
		
		// also on a corner
		board.calcTargets(6, 7, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(7, 6)));
		assertTrue(targets.contains(board.getCellAt(6, 9)));
		assertTrue(targets.contains(board.getCellAt(5, 6)));
		
		board.calcTargets(17, 3, 2);
		targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(15, 3)));
		assertTrue(targets.contains(board.getCellAt(16, 2)));
		assertTrue(targets.contains(board.getCellAt(16, 4)));
		// Door
		assertTrue(targets.contains(board.getCellAt(19, 3)));
		
		return;
	}
	
	@Test
	public void testTargetsThree(){
		
		board.calcTargets(3, 6, 3);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(14, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 6)));
		assertTrue(targets.contains(board.getCellAt(3, 5)));
		assertTrue(targets.contains(board.getCellAt(5, 7)));
		
		return;
	}
	
	/*@Test
	public void testTargetsSix(){
		
		board.calcTargets(0, 20, 6);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCellAt(1, 15)));
		assertTrue(targets.contains(board.getCellAt(2, 16)));
		assertTrue(targets.contains(board.getCellAt(3, 17)));
		
		return;
	}
	*/

}
