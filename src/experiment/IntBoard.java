package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	
	private Map<BoardCell, Set<BoardCell>> adj;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;
	private final int ROWS = 4;
	private final int COLS = 4;
	
	public IntBoard() {
		adj = new HashMap<BoardCell, Set<BoardCell>>();
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();	
		grid = new BoardCell[ROWS][COLS];		
	}
/*For this we are simply calculating the cells that a player can move to given
 * a cell.  The restrictions are you can't go off the board so this should 
 * just be a simple for loop with some if statements.
 * */
	public void calcAdjacencies(){
		for(int i = 0; i < ROWS; ++i){
			for(int j = 0; j < COLS; ++j){
				HashSet<BoardCell> adjCells = new HashSet<BoardCell>();
				BoardCell currentCell = new BoardCell(i,j);
				if((i - 1) >= 0){
					BoardCell leftAdj = new BoardCell(i - 1, j);
					adjCells.add(leftAdj);					
				}
				if((j - 1) >= 0){
					BoardCell upperAdj = new BoardCell(i, j - 1);
					adjCells.add(upperAdj);
				}
				if((i + 1) < ROWS){
					BoardCell rightAdj = new BoardCell(i + 1, j);
					adjCells.add(rightAdj);
				}
				if((j + 1) < COLS){
					BoardCell lowerAdj = new BoardCell(i, j + 1);
					adjCells.add(lowerAdj);
				}
				adj.put(currentCell, adjCells);
			}
		}
		return;
	}
	
	public void calcTargets(BoardCell start, int roll){
		// TODO: Complete Method
		return;
	}
	
	public Set<BoardCell> getTargets(){
		return targets;
	}
	
	public Set<BoardCell> getAdjList(BoardCell cell){
		return adj.get(cell);
	}
	
	public BoardCell getCell(int row, int col){
		return null;
	}
}
