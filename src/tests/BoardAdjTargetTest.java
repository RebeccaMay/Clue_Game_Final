package tests;

import static org.junit.Assert.assertEquals;

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
		testList = board.getAdjList(0, 0);
		
		// Left edge

		// Right edge

		// Top edge

		// Bottom edge
		
		// Next to room (no door)
		
		// Next to room (next to door but not the entrance)
		
		// Inside room
		

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
		
		// Right
		
		// Up
		
		// Down
		
		// Doorway 1
		
		// Doorway 2

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
