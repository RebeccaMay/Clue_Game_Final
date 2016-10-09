package experiment;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private Map<BoardCellExperiment, Set<BoardCellExperiment>> adj;
	private Set<BoardCellExperiment> visited;
	private Set<BoardCellExperiment> targets;
	private BoardCellExperiment[][] grid;
	private final int ROWS = 4;
	private final int COLS = 4;

	public IntBoard() {
		adj = new HashMap<BoardCellExperiment, Set<BoardCellExperiment>>();
		visited = new HashSet<BoardCellExperiment>();
		targets = new HashSet<BoardCellExperiment>();	
		grid = new BoardCellExperiment[ROWS][COLS];
		for(int i = 0; i < ROWS; ++i){
			for(int j = 0; j < COLS; ++j){
				BoardCellExperiment cell = new BoardCellExperiment(i,j);
				grid[i][j] = cell;
			}
		}
		calcAdjacencies();
	}
	
	/*For this we are simply calculating the cells that a player can move to given
	 * a cell.  The restrictions are you can't go off the board so this should 
	 * just be a simple for loop with some if statements.
	 * */
	public void calcAdjacencies(){
		for(int i = 0; i < ROWS; ++i){
			for(int j = 0; j < COLS; ++j){
				HashSet<BoardCellExperiment> adjCells = new HashSet<BoardCellExperiment>();
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

	public void calcTargets(BoardCellExperiment start, int roll){
		
		// Clear Sets
		visited.clear();
		targets.clear();
		
		// Add start to visited
		visited.add(start);		
		findAllTargets(start, roll);		
		return;
	}
	
	public void findAllTargets(BoardCellExperiment start, int pathLength){
		
		// Get the adjacency list
		Set<BoardCellExperiment> adj;
		adj = getAdjList(start);		
		for (BoardCellExperiment adjCell: adj){
			
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
	

	public Set<BoardCellExperiment> getTargets(){
		return targets;
	}

	public Set<BoardCellExperiment> getAdjList(BoardCellExperiment cell){
		return adj.get(cell);
	}

	public BoardCellExperiment getCell(int row, int col){
		return grid[row][col];
	}
	public static void main(String[] args) {
		IntBoard board = new IntBoard();
		board.calcAdjacencies();
	}
}
