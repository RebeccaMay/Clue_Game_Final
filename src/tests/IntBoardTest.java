package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

/*
 * TODO:
 * Create at least 6 methods to test Target Creation
 * 
 * Done:
 * Created Adjacency Test
 */

public class IntBoardTest {
	IntBoard board;

	@Before
	public void setUp() {
		board = new IntBoard();		
		return;
	}

	// Board Creation Adjacency Test
	// Top Left
	@Test
	public void testTopLeftCorner(){
		BoardCell cell = board.getCell(0,0);		
		Set<BoardCell> testAdjList = board.getAdjList(cell);
		System.out.println(testAdjList.size());
		assertTrue(testAdjList.contains(board.getCell(1,0)));
		assertTrue(testAdjList.contains(board.getCell(0,1)));
		assertEquals(2, testAdjList.size());
	}

	// Bottom Right
	@Test
	public void testBottomRightCorner(){
		BoardCell cell = board.getCell(3,3);
		Set<BoardCell> testAdjList = board.getAdjList(cell);
		assertTrue(testAdjList.contains(board.getCell(3,2)));
		assertTrue(testAdjList.contains(board.getCell(2,3)));
		assertEquals(2, testAdjList.size());
	}

	// RIght Edge
	@Test
	public void testRightEdge(){
		BoardCell cell = board.getCell(1,3);
		Set<BoardCell> testAdjList = board.getAdjList(cell);
		assertTrue(testAdjList.contains(board.getCell(0,3)));
		assertTrue(testAdjList.contains(board.getCell(1,2)));
		assertTrue(testAdjList.contains(board.getCell(2,3)));
		assertEquals(3, testAdjList.size());
	}

	// Left Edge
	@Test
	public void testLeftEdge(){
		BoardCell cell = board.getCell(3,0);
		Set<BoardCell> testAdjList = board.getAdjList(cell);
		assertTrue(testAdjList.contains(board.getCell(2,0)));
		assertTrue(testAdjList.contains(board.getCell(3,1)));
		assertEquals(2, testAdjList.size());
	}

	// Second Column Middle
	@Test
	public void testSecondColMiddle(){
		BoardCell cell = board.getCell(1,1);
		Set<BoardCell> testAdjList = board.getAdjList(cell);
		assertTrue(testAdjList.contains(board.getCell(0,1)));
		assertTrue(testAdjList.contains(board.getCell(1,0)));
		assertTrue(testAdjList.contains(board.getCell(2,1)));
		assertTrue(testAdjList.contains(board.getCell(1,2)));
		assertEquals(4, testAdjList.size());
	}

	// Second From Last Column
	@Test
	public void testSecondLast(){
		BoardCell cell = board.getCell(2,2);
		Set<BoardCell> testAdjList = board.getAdjList(cell);
		assertTrue(testAdjList.contains(board.getCell(1,2)));
		assertTrue(testAdjList.contains(board.getCell(2,1)));
		assertTrue(testAdjList.contains(board.getCell(3,2)));
		assertTrue(testAdjList.contains(board.getCell(2,3)));
		assertEquals(4, testAdjList.size());
	}

	/*Here is the start of the tests for the function that creates targets
	 * There must be 6 of them.  Those include the following:
	 * 
	 * DONE: Top left corner[0,0]
	 * DONE: bottom right corner [3,3]
	 * DONE: a right edge[1,3]
	 * DONE: a left edge[3,0]
	 * DONE: second column middle of grid[1,1]
	 * second from last column, middle of grid [2,2]
	 * This is the outline: 
	public void testTargets0_3()
	{
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
	}
	 * 
	 * */

	//Test for top left corner
	@Test
	public void targetTestTopLeftCorner(){
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 5);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(1, 3)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(2, 3)));
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(3, 2)));
	}

	//Test for bottom right corner
	@Test
	public void targetTestBottomRightCorner(){
		BoardCell cell = board.getCell(3, 3);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(2, 3)));
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(3, 2)));
	}

	//Test for a right edge (1,3)
	@Test
	public void targetTestRightEdge(){
		BoardCell cell = board.getCell(1, 3);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(2, 3)));
		assertTrue(targets.contains(board.getCell(3, 2)));
	}

	//Test for a left edge[3,0] move 2
	@Test
	public void targetTestLeftEdge(){
		BoardCell cell = board.getCell(3, 0);
		board.calcTargets(cell, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(3, 2)));	
	}

	//Test for second column middle of grid[1,1]
	@Test
	public void targetTestSecondColumnMiddleGrid(){
		BoardCell cell = board.getCell(1, 1);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(2, 3)));
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(3, 2)));
	}

	//Test for second from last column, middle of grid [2,2]
	@Test
	public void targetTestLastColumnMiddleGrid(){
		BoardCell cell = board.getCell(2, 2);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(2, 3)));
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(3, 2)));
	}
}


