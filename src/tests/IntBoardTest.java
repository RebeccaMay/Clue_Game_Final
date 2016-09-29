package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

public class IntBoardTest {
	IntBoard board;
	
	@Before
	public void setUp() {
		board = new IntBoard();
		return;
	}
	
	// Board Creation Adjacency Test
	@Test
	public void testBottomRightCorner(){
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testAdjList = board.getAdjList(cell);
		assertTrue(testAdjList.contains(board.getCell(1,0)));
		assertTrue(testAdjList.contains(board.getCell(0,1)));
		assertEquals(2, testAdjList.size());
	}
	
	@Test
	public void testTopRightCorner(){
		BoardCell cell = board.getCell(3,3);
		Set<BoardCell> testAdjList = board.getAdjList(cell);
		assertTrue(testAdjList.contains(board.getCell(3,2)));
		assertTrue(testAdjList.contains(board.getCell(2,3)));
		assertEquals(2, testAdjList.size());
	}
	
	@Test
	public void testRightEdge(){
		BoardCell cell = board.getCell(1,3);
		Set<BoardCell> testAdjList = board.getAdjList(cell);
		assertTrue(testAdjList.contains(board.getCell(0,3)));
		assertTrue(testAdjList.contains(board.getCell(1,2)));
		assertTrue(testAdjList.contains(board.getCell(2,3)));
		assertEquals(2, testAdjList.size());
	}
	
	@Test
	public void testLeftEdge(){
		BoardCell cell = board.getCell(3,0);
		Set<BoardCell> testAdjList = board.getAdjList(cell);
		assertTrue(testAdjList.contains(board.getCell(2,0)));
		assertTrue(testAdjList.contains(board.getCell(3,1)));
		assertEquals(2, testAdjList.size());
	}

}
