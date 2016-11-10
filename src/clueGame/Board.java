package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class Board extends JPanel{

	private static Board boardInstance = new Board();

	private int numRows;
	private int numColumns;
	public static int MAX_BOARD_SIZE;

	private BoardCell[][] board;

	private Map<Character, String> rooms;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;

	private String boardConfigFile;
	private String roomConfigFile;
	private String playerConfigFile;
	private String weaponConfigFile;
	private String roomNameLayoutFile;


	private Set<Card> peopleDeck;
	private Set<Card> weaponDeck;
	private Set<Card> roomDeck;
	private Set<Card> cardDeck;
	private Set<Card> dealingDeck;
	private ArrayList<Player> playerList;
	private int currentPlayer = 0;
	
	private int rollNum;
	private boolean humanDone = true;
	
	private Solution theAnswer;
	
	
	
	// Private Constructor. Initializes data structures.
	private Board() {
		// Singleton
		rooms = new HashMap<Character, String>();
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();

		cardDeck = new HashSet<Card>();
		peopleDeck = new HashSet<Card>();
		weaponDeck = new HashSet<Card>();
		roomDeck = new HashSet<Card>();
		dealingDeck = new HashSet<Card>();
		theAnswer = new Solution();
		this.setMinimumSize(new Dimension(400,400));
		this.addMouseListener(new cellListener());
	}

	// Makes sure only one instance of Board can exist;
	public static Board getInstance() {
		return boardInstance;
	}

	// Initializes the Board by loading config files and calculating adjacencies
	public void initialize() {

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
		
		try{
			loadPeopleConfig();
		}
		catch (BadConfigFormatException e) {
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try{
			loadWeaponConfig();
		}
		catch (BadConfigFormatException e) {
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try{
			loadRoomNameLayout();
		}
		catch (BadConfigFormatException e) {
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		calcAdjacencies();
		createSolution();
		dealCards();
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

			if (!((fields[2].equalsIgnoreCase("card") || (fields[2].equalsIgnoreCase("other"))))) {
				throw new BadConfigFormatException();
			}
			else if(fields[2].equalsIgnoreCase("card")){
				Card roomCard = new Card(room,CardType.ROOM);
				this.cardDeck.add(roomCard);
				this.roomDeck.add(roomCard);
			}
		}
		in.close();
		return;
	}

	//loads people, adds to playerList and adds people cards to deck
	public void loadPeopleConfig() throws BadConfigFormatException, FileNotFoundException{
		this.playerList = new ArrayList<Player>();
		
		FileReader fr = new FileReader(playerConfigFile);
		Scanner in = new Scanner(fr);
		
		int numPlayer = 0;		
		
		while(in.hasNextLine()){				
			String[] strs = in.nextLine().split(", ");
			if(strs.length != 4) throw new BadConfigFormatException("Improper number of parameters in playerData");
			
			if (numPlayer == 0)
				this.playerList.add( new HumanPlayer(strs[0], strs[1],Integer.parseInt(strs[2]),Integer.parseInt(strs[3])));
			else
				this.playerList.add( new ComputerPlayer(strs[0], strs[1],Integer.parseInt(strs[2]),Integer.parseInt(strs[3])));
			
			
			Card person = new Card(strs[0],CardType.PERSON);
			this.cardDeck.add(person);
			this.peopleDeck.add(person);
			numPlayer++;
		}
		in.close();
	}
	
	
	//loads weapons, places weapon cards within cardDeck
	public void loadRoomNameLayout() throws BadConfigFormatException, FileNotFoundException{		
		FileReader fr = new FileReader(roomNameLayoutFile);
		Scanner in = new Scanner(fr);
		while(in.hasNextLine()){				
			String[] strs = in.nextLine().split(", ");
			if(strs.length != 3) throw new BadConfigFormatException("Improper number of parameters in roomNameLayout");
			
			String name = strs[0];
			int row = Integer.parseInt(strs[1]);
			int col = Integer.parseInt(strs[2]);
					
			board[row][col].setRoomName(name);
		}
		in.close();
	}
	
	//loads weapons, places weapon cards within cardDeck
	public void loadWeaponConfig() throws BadConfigFormatException, FileNotFoundException{		
		FileReader fr = new FileReader(weaponConfigFile);
		Scanner in = new Scanner(fr);
		while(in.hasNextLine()){	
			Card weapon = new Card(in.nextLine().trim(),CardType.WEAPON);
			this.cardDeck.add(weapon);
			this.weaponDeck.add(weapon);
		}
		in.close();
	}
	
	// Takes the board config file and loads cells into the board
	public void loadBoardConfig() throws BadConfigFormatException {
		// Reads in the file into a string
		String currentCell = "";
		FileReader reader = null;
		try {
			reader = new FileReader(boardConfigFile);
		} catch (FileNotFoundException e) {
			System.out.println("board Configuration File Read Error");
			e.printStackTrace();
		}
		Scanner in = new Scanner(reader);
		while (in.hasNext()) {
			currentCell = currentCell + in.next();
			currentCell = currentCell + ",";
			numRows++;
		}
		in.close();

		// Must remove extra commas from differently formated csv files
		for (int i = 0; i < currentCell.length() - 1; ++i) {
			if ((currentCell.charAt(i) == ',') && (currentCell.charAt(i + 1) == ',')) {
				currentCell = currentCell.substring(0, i) + currentCell.substring(i + 2);
			}
		}
		// Splits each cells info into a entry in the cellLabels array
		String[] cellLabels = currentCell.split(",");
		numColumns = (cellLabels.length / numRows);
		/*
		 * Because if there are incorrect entries the full amount of cells will
		 * not be read in so if there are an incorrect number of rows to columns
		 * this will throw an exception.
		 */
		if ((numRows * numColumns) != cellLabels.length) {
			throw new BadConfigFormatException();
		}
		board = new BoardCell[numRows][numColumns];

		// Puts in all the cells into the array.
		int counter = 0;
		DoorDirection doorDirection = DoorDirection.NONE;
		for (int row = 0; row < numRows; ++row) {
			for (int col = 0; col < numColumns; ++col) {
				if (cellLabels[counter].length() == 2) {
					if (cellLabels[counter].charAt(1) == 'U') {
						doorDirection = DoorDirection.UP;
					} else if (cellLabels[counter].charAt(1) == 'D') {
						doorDirection = DoorDirection.DOWN;
					} else if (cellLabels[counter].charAt(1) == 'R') {
						doorDirection = DoorDirection.RIGHT;
					} else if (cellLabels[counter].charAt(1) == 'L') {
						doorDirection = DoorDirection.LEFT;
					} else {
						doorDirection = DoorDirection.NONE;
					}
				}
				BoardCell cell = new BoardCell(row, col, cellLabels[counter].charAt(0), doorDirection);
				if (!rooms.containsKey(cell.getInitial())) {
					throw new BadConfigFormatException();
				}
				board[row][col] = cell;
				counter++;
				doorDirection = DoorDirection.NONE;
			}
		}

		return;
	}

	//Function to calculate all adjacent boardcells for each boardcell
	public void calcAdjacencies(){
		for(int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				HashSet<BoardCell> temp = new HashSet<BoardCell>();
				if(!board[i][j].isRoom()) {
					if(board[i][j].isDoorway()) {
						switch(board[i][j].getDoorDirection()){
						case RIGHT:
							if(i+1 < numRows) temp.add(board[i+1][j]);
							break;
						case DOWN:
							if(j+1 < numColumns) temp.add(board[i][j+1]);
							break;
						case LEFT:
							if(i-1 >= 0) temp.add(board[i-1][j]);
							break;
						case UP:
							if(j-1 >= 0) temp.add(board[i][j-1]);
							break;
						case NONE:
							break;
						default:
							break;
						}
					}
					if(board[i][j].isWalkway()) {
						if(board[i][j].getInitial() != 'X') {
							if(i-1 >= 0 && (!board[i-1][j].isRoom() || board[i-1][j].getDoorDirection() == DoorDirection.DOWN) && board[i-1][j].getInitial() != 'X') temp.add(board[i-1][j]);
							if(i+1 < numRows && (!board[i+1][j].isRoom() || board[i+1][j].getDoorDirection() == DoorDirection.UP) && board[i+1][j].getInitial() != 'X') temp.add(board[i+1][j]);
							if(j-1 >= 0 && (!board[i][j-1].isRoom() || board[i][j-1].getDoorDirection() == DoorDirection.RIGHT) && board[i][j-1].getInitial() != 'X') temp.add(board[i][j-1]);
							if(j+1 < numColumns && (!board[i][j+1].isRoom() || board[i][j+1].getDoorDirection() == DoorDirection.LEFT) && board[i][j+1].getInitial() != 'X') temp.add(board[i][j+1]);
						}
					}
				} else {
					switch(board[i][j].getDoorDirection()) {
					case DOWN:
						if(i+1 < numRows) temp.add(board[i+1][j]);
						break;
					case LEFT:
						if(j-1 > 0) temp.add(board[i][j-1]);
						break;
					case NONE:
						break;
					case RIGHT:
						if(j+1 < numColumns) temp.add(board[i][j+1]);
						break;
					case UP:
						if(i-1 > 0) temp.add(board[i-1][j]);
						break;
					default:
						break;
					}
				}
				adjMatrix.put(board[i][j], temp);
				
			}
		}
	}

	// Returns the list of all adjacent cells to cell r, c
	public Set<BoardCell> getAdjList(int row, int col) {
		return adjMatrix.get(board[row][col]);
	}

	// Calculates all targets for a cell given r, c and the length of the path
	public void calcTargets(int r, int c, int pathLength) {
		
		// Clears out targets and visited cells list
		visited.clear();
		targets.clear();
		
		// Gets the starting point cell, saves as start
		BoardCell start = getCellAt(r, c);
		
		// Adds starting point to visited list
		visited.add(start);
		// Calls findAllTargets for the given start and path length
		// (recursive function)
		findAllTargets(start, pathLength);
		
		return;
	}

	// Overloaded Method
	// Calculates all targets for a cell given the cell and the length of the path
	public void calcTargets(BoardCell cell, int pathLength) {
		// Clears out targets and visited cells list
		visited.clear();
		targets.clear();

		// Adds starting point to visited list
		visited.add(cell);
		// Calls findAllTargets for the given start and path length
		// (recursive function)
		findAllTargets(cell, pathLength);

		return;
	}
	
	public void findAllTargets(BoardCell cell, int pathLength){
		
		// Gets the adjacency list for the current cell
		Set<BoardCell> adjList;
		adjList = adjMatrix.get(cell);
		
		for (BoardCell adjCell: adjList){
			
			// If cell is already visited continue to next cell
			if (visited.contains(adjCell)) {
				// Skip the rest
				continue;
			}

			// Add cell to visited list
			visited.add(adjCell);

			// Add cell to targets if path length is 1, or recurse through to
			// the next cell
			if (pathLength == 1) {
				targets.add(adjCell);
			} 
			else if(adjCell.isDoorway()){
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

	// Returns the list of targets calculated previously
	public Set<BoardCell> getTargets() {
		return targets;
	}

	// Sets the config files for the board and rooms.
	public void setConfigFiles(String board, String rooms, String players, String weapons, String rlayout) {

		boardConfigFile = "data/" + board;
		roomConfigFile = "data/" + rooms;
		playerConfigFile = "data/" + players;
		weaponConfigFile = "data/" + weapons;
		roomNameLayoutFile = "data/" + rlayout;
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
	
	//Function that pulls 1 card of each type out of the deck to be the solution to the game
	//The set called dealingDeck will no longer be a deck including all of the cards
	public void createSolution(){
		Random rand = new Random();
		int rnum = rand.nextInt(weaponDeck.size());
		int counter = 0;
		String person = "", room = "", weapon = "";

		for (Card c: weaponDeck){
			if(counter == rnum){
				weapon = c.getCardName();
			}
			else{
				dealingDeck.add(c);
			}
			counter++;
		}
		counter = 0;
		for (Card c: roomDeck){
			if(counter == rnum){
				room = c.getCardName();
			}
			else{
				dealingDeck.add(c);
			}
			counter++;
		}
		counter = 0;
		for (Card c: peopleDeck){
			if(counter == rnum){
				person = c.getCardName();
			}
			else{
				dealingDeck.add(c);
			}
			counter++;
		}
		
		theAnswer.setValues(person, weapon, room);
	}

	//Deals the cards from the deck that is already missing the solution
	public void dealCards(){
		int playerNum = 0;
		for (Card c: this.dealingDeck){
			this.playerList.get(playerNum).giveCard(c);
			
			playerNum++;
			if(playerNum >= 6) playerNum = 0;
		}
	}
	
	//Function that compares a players acccusation to the solution
	//if this is correct, the player wins the game
	public boolean checkAccusation(Solution accusation){
		return theAnswer.check(accusation);
	}
	
	//Will loop through each player until one can disprove the suggestion (if someone can)
	public Card handleSuggestion(Solution solution) {
		//Loop runs for the size of the player list
		int i = currentPlayer + 1;
		if (i == playerList.size()) i = 0;
		while(i != currentPlayer) {
			Player p = playerList.get(i);
			Card possibleCard = p.disproveSuggestion(solution);
			if(!(possibleCard == null)) return possibleCard;
			i++;
			if (i == playerList.size()) i = 0;
		}
		return null;
	}
	
	//This getter is for testing only
	public Set<Card> getDeck(){
		return this.cardDeck;
	}
	
	
	public ArrayList<Player> getPlayerList(){
		return this.playerList;
	}

	//For Testing Purposes Only
	public void setSolution(String name, String weapon, String room) {
		// TODO Auto-generated method stub
		this.theAnswer.setValues(name, weapon, room);
	}

	//For Testing Purposes Only
	public void forceGiveCard(Player testPlayer, Card cardToGive) {
		// TODO Auto-generated method stub
		testPlayer.giveCard(cardToGive);
	}
	public Set<Card> getPeopleDeck() {
		return peopleDeck;
	}

	public Set<Card> getWeaponDeck() {
		return weaponDeck;
	}

	public Set<Card> getRoomDeck() {
		return roomDeck;
	}
	
	public Map<Character, String> getRoomMap() {
		return rooms;
	}
	
	//This function only is used for testing
	public void forceSetPlayerList(ArrayList<Player> testPlayerList) {
		// TODO Auto-generated method stub
		this.playerList.clear();
		for(Player p: testPlayerList) {
			this.playerList.add(p);
		}
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for (int i=0;i<numRows;i++){
			for (int j=0;j<numColumns;j++){
				board[i][j].draw(g);
			}
		}
		if (currentPlayer == 0){
			for (BoardCell b: targets){
				b.draw(g, Color.CYAN);
			}
		}
		
		for(Player p: playerList){
			p.draw(g);
		}
		repaint();
	}
	
	public Player getCurrentPlayer(){
		return playerList.get(currentPlayer);
	}
	
	public int getRollNum(){
		return rollNum;
	}
	
	public void nextTurn(){
		if(humanDone){
			Random rand = new Random();
			rollNum = rand.nextInt(5) + 1;
			
			calcTargets(playerList.get(currentPlayer).getRow(), playerList.get(currentPlayer).getCol(), rollNum);
			
			if (currentPlayer != 0){
				playerList.get(currentPlayer).makeMove(targets, 0, 0);
			}
			else{
				humanDone = false;
			}
			repaint();
		}	
	}
	
	private class cellListener implements MouseListener{

		public void mouseClicked (MouseEvent event){
			if (currentPlayer == 0){
				int clickedRow = event.getY()/26;
				int clickedCol = event.getX()/26;
				playerList.get(currentPlayer).makeMove(targets, clickedRow, clickedCol);
				
			}
		}

		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {	}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
}
