package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
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
		rooms = new HashMap<Character, String>();
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		targets = new HashSet<BoardCell>();
		
	}


	public static Board getInstance(){
		return boardInstance;
	}
	public void initialize(){
		try {
			loadRoomConfig();
		} catch (BadConfigFormatException e) {
			e.printStackTrace();
		}
		try {
			loadBoardConfig();
		} catch (BadConfigFormatException e) {
			e.printStackTrace();
		}
		calcAdjacencies();

		return;
	}

	public void loadRoomConfig() throws BadConfigFormatException {

		String room = "";
		char initial = ' ';
		String[] fields = new String[3];
		
		FileReader reader = null;

		try {
			reader = new FileReader(roomConfigFile);
		} catch (FileNotFoundException e) {
			System.out.println("Room Configuration File Read Error");
			e.printStackTrace();
			return;
		}

		Scanner in = new Scanner(reader);

		while (in.hasNext()) {
			room = in.nextLine();
			
			fields = room.split(",");
			
			// Get room initial
			initial = fields[0].charAt(0);

			// Get Room
			room = fields[1].substring(1, fields[1].length());

			// Add to rooms map
			rooms.put(initial, room);
			fields[2] = fields[2].substring(1, fields[2].length());
			if(!((fields[2].equalsIgnoreCase("card") || (fields[2].equalsIgnoreCase("other"))))){
				throw new BadConfigFormatException();
			}
		}
		return;
	}

	public void loadBoardConfig() throws BadConfigFormatException{
		//Reads in the file into a string
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
			currentCell = currentCell + ",";
			numRows = numRows + 1;
		}
		in.close();
		/*This splits the entire file into an array of strings which are separated by
		A comma.
		PLEASE NOTE: this next bit of code must be here because the different
		CSV files read in differently.  Her original one will read in with
		no commas separating the last entry in each column.  Ours however will
		put a comma at the end of each row and the beginning of each row. */
		
		//Must remove extra commas from differently formated csv files
		for(int i = 0; i < currentCell.length() -1;++i){
			if((currentCell.charAt(i) == ',') && (currentCell.charAt(i + 1) == ',')){
				currentCell = currentCell.substring(0, i) + currentCell.substring(i + 2);			
			}
		}
		//Splits each cells info into a entry in the cellLabels array
		String [] cellLabels = currentCell.split(",");
		numColumns = (cellLabels.length / numRows);
		/*Because if there are incorrect entries the full amount of cells
		 * will not be read in so if there are an incorrect number of 
		 * rows to columns this will throw an exception.
		 * */
		if((numRows * numColumns) != cellLabels.length){
			throw new BadConfigFormatException();
		}
		board = new BoardCell[numRows][numColumns];
		
		//Puts in all the cells into the array.
		int counter = 0;
		DoorDirection doorDirection = DoorDirection.NONE;
		for(int row = 0; row < numRows;++row){
		for(int col = 0; col < numColumns;++col){
				if(cellLabels[counter].length() == 2){
					if(cellLabels[counter].charAt(1) == 'U'){
						doorDirection = DoorDirection.UP;
					}
					else if(cellLabels[counter].charAt(1) == 'D'){
						doorDirection = DoorDirection.DOWN;
					}
					else if(cellLabels[counter].charAt(1) == 'R'){
						doorDirection = DoorDirection.RIGHT;
					}
					else if(cellLabels[counter].charAt(1) == 'L'){
						doorDirection = DoorDirection.LEFT;
					}
					else{
						doorDirection = DoorDirection.NONE;
					}
				}
				BoardCell cell = new BoardCell(row,col,cellLabels[counter].charAt(0),doorDirection);
				if(!rooms.containsKey(cell.getInitial())){
					throw new BadConfigFormatException();
				}
				board[row][col] = cell;
				counter = counter + 1;
				doorDirection = DoorDirection.NONE;
			}
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

		boardConfigFile = "configFiles/" + board;
		roomConfigFile = "configFiles/" + rooms;

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

	public BoardCell getCellAt(int row, int col) {
		return board[row][col];
	}


}
