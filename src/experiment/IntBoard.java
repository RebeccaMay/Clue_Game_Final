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
		for(int i = 0; i < ROWS; ++i){
			for(int j = 0; j < COLS; ++j){
				BoardCell cell = new BoardCell(i,j);
				grid[i][j] = cell;
			}
		}
	}
	
	/*For this we are simply calculating the cells that a player can move to given
	 * a cell.  The restrictions are you can't go off the board so this should 
	 * just be a simple for loop with some if statements.
	 * */
	public void calcAdjacencies(){
		for(int i = 0; i < ROWS; ++i){
			for(int j = 0; j < COLS; ++j){
				HashSet<BoardCell> adjCells = new HashSet<BoardCell>();
				if((i - 1) >= 0){
					adjCells.add(grid[i-1][j]);					
				}
				if((j - 1) >= 0){
					adjCells.add(grid[i][j - 1]);
				}
				if((i + 1) < ROWS){
					adjCells.add(grid[i + 1][j]);
				}
				if((j + 1) < COLS){
					adjCells.add(grid[i][j + 1]);
				}
				adj.put(grid[i][j], adjCells);
			}
		}
		return;
	}

	public void calcTargets(BoardCell start, int roll){
		
		// Clear Sets
		visited.clear();
		targets.clear();
		
		// Add start to visited
		visited.add(start);		
		findAllTargets(start, roll);		
		return;
	}
	
	public void findAllTargets(BoardCell start, int pathLength){
		
		// Get the adjacency list
		Set<BoardCell> adj;
		adj = getAdjList(start);		
		for (BoardCell adjCell: adj){
			
			// If cell is already visited continue to next cell
			if (visited.contains(adjCell)){
				continue;
			}
			
			// Add cell to visited list
			visited.add(adjCell);
			
			// Add cell to targets if path length is 1, or recurse through to the next cell
			if (pathLength == 1){
				targets.add(adjCell);
			}
			else {
				findAllTargets(adjCell, pathLength - 1);
			}
			
			// Remove cell from visited list
			visited.remove(adjCell);
		}	
		return;
	}
	

	public Set<BoardCell> getTargets(){
		return targets;
	}

	public Set<BoardCell> getAdjList(BoardCell cell){
		calcAdjacencies();
		return adj.get(cell);
	}

	public BoardCell getCell(int row, int col){
		return grid[row][col];
	}
	public static void main(String[] args) {
		IntBoard board = new IntBoard();
		board.calcAdjacencies();
	}
}
