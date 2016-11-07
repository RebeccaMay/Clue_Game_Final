package tests;

/*
 * This program tests that adjacencies and targets are calculated correctly.
 * 
 * Note, we used most of the original test structures and comments, but some tests were consolidated and served
 * multiple testing goals.
 */

import java.util.Set;


import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTests {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance and initialize it		
		board = Board.getInstance();
		board.setConfigFiles("LayoutAPJS.csv", "legendAPJS.txt", "playerDataAPJS.txt", "weaponsAPJS.txt","RoomNameLayoutAPJS.txt");		
		board.initialize();
	}

	// Ensure that player does not move around within room
	// These cells are ORANGE on the planning spreadsheet
	@Test
	public void testAdjacenciesInsideRooms()
	{
		// Test a corner
		Set<BoardCell> testList = board.getAdjList(3, 0);
		assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(1,11);
		assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(7,21);
		assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(22,0);
		assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(19,15);
		assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(15,5);
		assertEquals(0, testList.size());
	}

	// Ensure that the adjacency list from a doorway is only the
	// walkway. NOTE: This test could be merged with door 
	// direction test. 
	// These tests are PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		Set<BoardCell> testList = board.getAdjList(2,20);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(2,21)));
		
		// TEST DOORWAY LEFT 
		testList = board.getAdjList(9,16);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(9,15)));
		
		//TEST DOORWAY DOWN
		testList = board.getAdjList(0,5);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(1,5)));
		
		//TEST DOORWAY UP
		testList = board.getAdjList(6,19);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(5,19)));
		
		
	}
	
	// Test adjacency at entrance to rooms
	// These tests are GREEN in planning spreadsheet
	@Test
	public void testAdjacencyDoorways()
	{
		// Test beside a door direction RIGHT 1
		Set<BoardCell> testList = board.getAdjList(2, 21);
		assertTrue(testList.contains(board.getCellAt(2, 20)));
		assertTrue(testList.contains(board.getCellAt(2, 22)));
		assertEquals(2, testList.size());
		
		// Test beside a door direction RIGHT 2
		testList = board.getAdjList(22, 21);
		assertTrue(testList.contains(board.getCellAt(22, 20)));
		assertTrue(testList.contains(board.getCellAt(22, 22)));
		assertTrue(testList.contains(board.getCellAt(21, 21)));
		assertEquals(3, testList.size());				
		
		// Test beside a door direction DOWN
		testList = board.getAdjList(1, 4);
		assertTrue(testList.contains(board.getCellAt(2, 4)));
		assertTrue(testList.contains(board.getCellAt(0, 4)));
		assertTrue(testList.contains(board.getCellAt(1, 5)));
		assertTrue(testList.contains(board.getCellAt(1, 3)));
		assertEquals(4, testList.size());
		
		// Test beside a door direction LEFT
		testList = board.getAdjList(14, 0);
		assertTrue(testList.contains(board.getCellAt(14, 1)));
		assertTrue(testList.contains(board.getCellAt(13, 0)));
		assertTrue(testList.contains(board.getCellAt(15, 0)));
		assertEquals(3, testList.size());
		
		// Test beside a door direction UP
		testList = board.getAdjList(18, 7);
		assertTrue(testList.contains(board.getCellAt(18, 8)));
		assertTrue(testList.contains(board.getCellAt(18, 6)));
		assertTrue(testList.contains(board.getCellAt(19, 7)));
		assertTrue(testList.contains(board.getCellAt(17, 7)));
		assertEquals(4, testList.size());
	}

	// Test a variety of walkway scenarios
	// These tests are MAGENTA on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on top edge of board next to door, not proper side to enter
		Set<BoardCell> testList = board.getAdjList(0, 3);
		assertTrue(testList.contains(board.getCellAt(0, 2)));
		assertTrue(testList.contains(board.getCellAt(1, 3)));
		assertEquals(2, testList.size());
		
		// Test on left edge of board, three walkway pieces
		testList = board.getAdjList(9, 0);
		assertTrue(testList.contains(board.getCellAt(9, 1)));
		assertTrue(testList.contains(board.getCellAt(10, 0)));
		assertEquals(2, testList.size());

		// Test between two rooms, walkway above, bottom edge
		testList = board.getAdjList(22, 4);
		assertTrue(testList.contains(board.getCellAt(21, 4)));
		assertEquals(1, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(15,15);
		assertTrue(testList.contains(board.getCellAt(15, 16)));
		assertTrue(testList.contains(board.getCellAt(15, 14)));
		assertTrue(testList.contains(board.getCellAt(14, 15)));
		assertTrue(testList.contains(board.getCellAt(16, 15)));
		assertEquals(4, testList.size());
		
		// Test between door and food court (X), walkway on left and right
		testList = board.getAdjList(11, 9);
		assertTrue(testList.contains(board.getCellAt(11, 10)));
		assertTrue(testList.contains(board.getCellAt(11, 8)));
		assertTrue(testList.contains(board.getCellAt(12,9)));
		assertEquals(3, testList.size());
		
		// Test on right edge of board
		testList = board.getAdjList(16, 22);
		assertTrue(testList.contains(board.getCellAt(15, 22)));
		assertTrue(testList.contains(board.getCellAt(17, 22)));
		assertTrue(testList.contains(board.getCellAt(16, 21)));
		assertEquals(3, testList.size());
	}
	
	
	// Tests of just walkways, 1 step, includes on edge of board
	// and beside room
	// Have already tested adjacency lists on all four edges, will
	// only test two edges here
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(18, 22, 1);
		Set<BoardCell> targets= board.getTargets();		
		assertTrue(targets.contains(board.getCellAt(17, 22)));
		assertTrue(targets.contains(board.getCellAt(18, 21)));	
		assertTrue(targets.contains(board.getCellAt(19, 22)));	
		assertEquals(3, targets.size());
		
		board.calcTargets(20, 20, 1);
		targets= board.getTargets();		
		assertTrue(targets.contains(board.getCellAt(20, 21)));
		assertEquals(1, targets.size());		
	}
	
	// Tests of just walkways, 2 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(22, 11, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(21, 10)));
		assertTrue(targets.contains(board.getCellAt(20, 11)));
		
		board.calcTargets(2, 22, 2);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 22)));
		assertTrue(targets.contains(board.getCellAt(2, 20)));	
		assertTrue(targets.contains(board.getCellAt(4, 22)));			
	}
	
	// Tests of just walkways, 4 steps
	// These are LIGHT BLUE on the planning spreadsheet
	// Also allows us to get in room via shortcut
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(21, 4, 4);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(21, 5)));
		assertTrue(targets.contains(board.getCellAt(19, 6)));
		assertTrue(targets.contains(board.getCellAt(18, 5)));
		assertTrue(targets.contains(board.getCellAt(17, 4)));
		
		// Includes a path that doesn't have enough length
		board.calcTargets(10, 4, 4);
		targets= board.getTargets();
		assertEquals(19, targets.size());
		assertTrue(targets.contains(board.getCellAt(9, 3)));
		assertTrue(targets.contains(board.getCellAt(9, 5)));	
		assertTrue(targets.contains(board.getCellAt(11, 3)));	
		assertTrue(targets.contains(board.getCellAt(11, 5)));			
		assertTrue(targets.contains(board.getCellAt(10, 6)));
		assertTrue(targets.contains(board.getCellAt(10, 2)));
		assertTrue(targets.contains(board.getCellAt(8, 4)));
		assertTrue(targets.contains(board.getCellAt(12, 4)));
		assertTrue(targets.contains(board.getCellAt(9, 1)));
		assertTrue(targets.contains(board.getCellAt(8 ,2)));
		assertTrue(targets.contains(board.getCellAt(7, 3)));
		assertTrue(targets.contains(board.getCellAt(6, 4)));
		assertTrue(targets.contains(board.getCellAt(7, 5)));
		assertTrue(targets.contains(board.getCellAt(8, 6)));
		assertTrue(targets.contains(board.getCellAt(10, 0)));
		assertTrue(targets.contains(board.getCellAt(11, 7)));
		assertTrue(targets.contains(board.getCellAt(12, 6)));
		assertTrue(targets.contains(board.getCellAt(12, 2)));
		assertTrue(targets.contains(board.getCellAt(11, 1)));
	}	
	
	// Tests of just walkways plus one door, 6 steps
	// These are LIGHT BLUE on the planning spreadsheet
	// This completes target into room as well

	@Test
	public void testTargetsSixSteps() {
		board.calcTargets(0, 0, 6);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 2)));
		assertTrue(targets.contains(board.getCellAt(1, 1)));	
		assertTrue(targets.contains(board.getCellAt(2, 0)));
		
		assertTrue(targets.contains(board.getCellAt(0, 4)));
		assertTrue(targets.contains(board.getCellAt(1, 3)));	
		assertTrue(targets.contains(board.getCellAt(2, 2)));	
		
		assertTrue(targets.contains(board.getCellAt(1, 5)));	
		assertTrue(targets.contains(board.getCellAt(2, 4)));
	}	





	// Test getting out of a room
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testRoomExit()
	{
		// Take one step, essentially just the adj list
		board.calcTargets(19, 17, 1);
		Set<BoardCell> targets= board.getTargets();
		// Ensure doesn't exit through the wall
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(18,17)));
		
		
		// Take two steps
		board.calcTargets(21, 9, 2);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(22, 10)));
		assertTrue(targets.contains(board.getCellAt(21, 11)));
		assertTrue(targets.contains(board.getCellAt(20, 10)));
	}

}
