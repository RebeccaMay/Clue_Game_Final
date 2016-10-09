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

	// Private Constructor.  Initializes data structures.
	private Board() {
		// Singleton
		rooms = new HashMap<Character, String>();
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		targets = new HashSet<BoardCell>();

	}

	//  Makes sure only one instance of Board can exist;
	public static Board getInstance(){
		return boardInstance;
	}

	// Initializes the Board by loading config files and calculating adjacencies
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

	// Takes the room config file loads the rooms
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

	// Takes the board config file and loads cells into the board
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

	/*This function should be the same as the intBoard tests except for the following.
	 * Don't add rooms to the adjacency list.
	 * Add doorWays to adjacency lists if the door is in the correct direction.
	 * I am making private functions to make this more readable
	 * */
	private boolean upWorks(int row, int col){
		boolean works = true;
		//Makes sure rooms have no adj's
		if(board[row][col].isRoom()){
			works = false;
		}
		//Makes sure if the cell is a doorway that the person can only leave the correct direction
		if(board[row][col].isDoorway() && !(board[row][col].getDoorDirection() == DoorDirection.UP)){
			works = false;
		}
		//Checks the edge of board
		if(row - 1 < 0){
			works = false;
		}
		//Checks to make sure room spaces are added to the board
		else if(board[row - 1][col].isRoom()){
			works = false;
		}
		//Checks to make sure the doorways are added correctly.
		else if(board[row - 1][col].isDoorway() && !(board[row - 1][col].getDoorDirection() == DoorDirection.DOWN)){
			works = false;
		}
		return works;
	}
	private boolean downWorks(int row, int col){
		boolean works = true;
		if(board[row][col].isRoom()){
			works = false;
		}
		if(board[row][col].isDoorway() && !(board[row][col].getDoorDirection() == DoorDirection.DOWN)){
			works = false;
		}
		if(row + 1 >= numRows){
			works = false;
		}
		else if(board[row + 1][col].isRoom()){
			works = false;
		}
		else if(board[row + 1][col].isDoorway() && !(board[row + 1][col].getDoorDirection() == DoorDirection.UP)){
			works = false;
		}
		return works;
	}
	private boolean leftWorks(int row, int col){
		boolean works = true;
		if(board[row][col].isRoom()){
			works = false;
		}
		if(board[row][col].isDoorway() && !(board[row][col].getDoorDirection() == DoorDirection.LEFT)){
			works = false;
		}
		if(col - 1 < 0){
			works = false;
		}
		else if(board[row][col - 1].isRoom()){
			works = false;
		}
		else if(board[row][col - 1].isDoorway() && !(board[row][col - 1].getDoorDirection() == DoorDirection.RIGHT)){
			works = false;
		}
		return works;
	}
	private boolean rightWorks(int row, int col){
		boolean works = true;
		if(board[row][col].isRoom()){
			works = false;
		}
		if(board[row][col].isDoorway() && !(board[row][col].getDoorDirection() == DoorDirection.RIGHT)){
			works = false;
		}
		if(col + 1 >= numColumns){
			works = false;
		}
		else if(board[row][col + 1].isRoom()){
			works = false;
		}
		else if(board[row][col + 1].isDoorway() && !(board[row][col + 1].getDoorDirection() == DoorDirection.LEFT)){
			works = false;
		}
		return works;
	}

	
	
	
	public void calcAdjacencies(){
		for(int row = 0; row < numRows; ++row){
			for(int col = 0; col < numColumns; ++col){
				HashSet<BoardCell> adjCells = new HashSet<BoardCell>();
				if(upWorks(row, col)){
					adjCells.add(board[row-1][col]);					
				}
				if(leftWorks(row, col)){
					adjCells.add(board[row][col - 1]);
				}
				if(downWorks(row, col)){
					adjCells.add(board[row + 1][col]);
				}
				if(rightWorks(row, col)){
					adjCells.add(board[row][col + 1]);
				}
				adjMatrix.put(board[row][col], adjCells);
			}
		}
		return;
	}

	// Returns the list of all adjacent cells to cell r, c
	public Set<BoardCell> getAdjList(int row, int col){		
		return adjMatrix.get(board[row][col]);
	}

	// Calculates all targets for a cell given the cell and the length of the path
	public void calcTargets(BoardCell cell, int pathLength){
		return;
	}

	// Calculates all targets for a cell given r, c and the length of the path
	public void calcTargets(int r, int c, int pathLength){
		return;
	}

	// Returns the list of targets calculated previously
	public Set<BoardCell> getTargets(){
		return null;
	}

	// Sets the config files for the board and rooms.
	public void setConfigFiles(String board, String rooms) {

		boardConfigFile = "configFiles/" + board;
		roomConfigFile = "configFiles/" + rooms;

		return;
	}

	// Returns a map of each room and its initial.
	public Map<Character, String> getLegend() {
		return rooms;
	}

	// Returns the total number of rows on the board.
	public int getNumRows() {
		return numRows;
	}

	// Returns the total number of columns on the board.
	public int getNumColumns() {
		return numColumns;
	}

	// Returns a Board cell at row, column
	public BoardCell getCellAt(int row, int col) {
		return board[row][col];
	}


}
