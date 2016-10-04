package clueGame;

import java.util.Map;
import java.util.Set;

public class Board {
	
	private static Board boardInstance = new Board();
	
	private int numRows;
	private int numColumns;
	public static int MAX_BOARD_SIZE;
	
	private BoardCell[][] board;
	
	private Map<Character, String> rooms;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	
	private String boardConfigFile;
	private String roomConfigFIle;
	
	private Board() {
		// Singleton
	}
	
	public static Board getInstance(){
		return boardInstance;
	}
	
	public void initialize(){
		
		return;
	}
	
	public void loadRoomConfig(){
		
		return;
	}
	
	public void loadBoardConfig(){
		
		return;
	}
	
	public void calcAdjacencies(){
		
		return;
	}
	
	public void calcTargets(BoardCell cell, int pathLength){
		
		return;
	}

}
