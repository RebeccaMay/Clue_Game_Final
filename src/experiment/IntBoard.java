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
	
	public IntBoard() {
		adj = new HashMap<BoardCell, Set<BoardCell>>();
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		// Populate grid
	}

	public void calcAdjacencies(){
		// TODO: Complete Method
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
		// TODO: Complete Method
		return null;
	}
	
	public BoardCell getCell(int row, int col){
		return null;
	}
	
}
