package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;
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
	private String roomConfigFile;

	private Board() {
		// Singleton
	}

	public static Board getInstance(){
		return boardInstance;
	}

	public void initialize(){

		loadRoomConfig();
		loadBoardConfig();
		calcAdjacencies();


		return;
	}

	public void loadRoomConfig(){

		String currentCell = "";

		try {
			FileReader reader = new FileReader(roomConfigFile);
		} catch (FileNotFoundException e) {
			System.out.println("Room Configuration File Read Error");
			e.printStackTrace();
		}

		Scanner in = new Scanner(reader);

		currentCell = in.next;


		return;
	}

	public void loadBoardConfig(){
		String currentCell = "";
		FileReader reader = null;
		try {
			reader = new FileReader(boardConfigFile);
		} catch (FileNotFoundException e) {
			System.out.println("board Configuration File Read Error");
			e.printStackTrace();
		}
		Scanner in = new Scanner(reader);
		while(in.hasNext()){
			currentCell = currentCell + in.next();	
		}
		
		
		


		return;
	}

	public void calcAdjacencies(){

		return;
	}

	public void calcTargets(BoardCell cell, int pathLength){

		return;
	}

	public void setConfigFiles(String board, String rooms) {

		boardConfigFile = board;
		roomConfigFile = rooms;

		return;
	}

	public Map<Character, String> getLegend() {
		return rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public BoardCell getCellAt(int r, int c){
		return board[r][c];
	}



}
