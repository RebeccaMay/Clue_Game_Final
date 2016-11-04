package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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


	private Set<Card> peopleDeck;
	private Set<Card> weaponDeck;
	private Set<Card> roomDeck;
	private Set<Card> cardDeck;
	private Set<Card> dealingDeck;
	private ArrayList<Player> playerList;
	private int currentPlayer = 0;
	
	private Solution theAnswer;
	
	//variables for GUI components (textbox, buttons, etc.)
	private JTextField txtfieldWhoseTurn;
	private JButton buttonNextPlayer;
	private JButton buttonMakeAccusation;
	private JTextField txtfieldRoll;
	private JTextField txtfieldGuess;
	private JTextField txtfieldResponse;
	
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
		
		
		//GUI code
		setLayout(new GridLayout(2,0));
		JPanel topRow = createTopRow();
		JPanel bottomRow = createBottomRow();
		add(topRow);
		add(bottomRow);
		
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
		return;
	}

	//loads people, adds to playerList and adds people cards to deck
	public void loadPeopleConfig() throws BadConfigFormatException, FileNotFoundException{
		this.playerList = new ArrayList<Player>();
		
		FileReader fr = new FileReader(playerConfigFile);
		Scanner in = new Scanner(fr);
		while(in.hasNextLine()){				
			String[] strs = in.nextLine().split(", ");
			if(strs.length != 4) throw new BadConfigFormatException("Improper number of parameters in playerData");
			this.playerList.add( new Player(strs[0], strs[1],Integer.parseInt(strs[2]),Integer.parseInt(strs[3])));
			Card person = new Card(strs[0],CardType.PERSON);
			this.cardDeck.add(person);
			this.peopleDeck.add(person);
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
			numRows = numRows + 1;
		}
		in.close();
		/*
		 * This splits the entire file into an array of strings which are
		 * separated by A comma. PLEASE NOTE: this next bit of code must be here
		 * because the different CSV files read in differently. Her original one
		 * will read in with no commas separating the last entry in each column.
		 * Ours however will put a comma at the end of each row and the
		 * beginning of each row.
		 */

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
				counter = counter + 1;
				doorDirection = DoorDirection.NONE;
			}
		}

		return;
	}

	/*
	 * This function should be the same as the intBoard tests except for the
	 * following. Don't add rooms to the adjacency list. Add doorWays to
	 * adjacency lists if the door is in the correct direction. I am making
	 * private functions to make this more readable
	 */
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
	public void setConfigFiles(String board, String rooms, String players, String weapons) {

		boardConfigFile = "data/" + board;
		roomConfigFile = "data/" + rooms;
		playerConfigFile = "data/" + players;
		weaponConfigFile = "data/" + weapons;
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

	public void dealCards(){
		int playerNum = 0;
		for (Card c: this.dealingDeck){
			this.playerList.get(playerNum).giveCard(c);
			
			playerNum++;
			if(playerNum >= 6) playerNum = 0;
		}
	}
	
	public boolean checkAccusation(Solution accusation){
		return theAnswer.check(accusation);
	}
	
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
	
	//This getter is for testing only
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
	
	//Function for making the top row of the GUI
	private JPanel createTopRow(){
		
		//top row
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(1,3));
		
		//first column in row
		JPanel firstCol = new JPanel();
		firstCol.setLayout(new GridLayout(3,3));
		JLabel labelWhoseTurn = new JLabel("Whose Turn?",JLabel.CENTER);
		txtfieldWhoseTurn = new JTextField("");
		txtfieldWhoseTurn.setEditable(false);
		
		//multiple panels for proper spacing
		firstCol.add(new JPanel());
		firstCol.add(labelWhoseTurn,BorderLayout.NORTH);
		firstCol.add(new JPanel());
		firstCol.add(new JPanel());
		firstCol.add(txtfieldWhoseTurn,BorderLayout.SOUTH);
		firstCol.add(new JPanel());
		firstCol.add(new JPanel());
		firstCol.add(new JPanel());
		firstCol.add(new JPanel());
		
		//Second column in row
		JPanel secCol = new JPanel();
		secCol.setLayout(new GridLayout(3,1));
		buttonNextPlayer = new JButton("Next Player");
		secCol.add(new JPanel());
		secCol.add(buttonNextPlayer);
		secCol.add(new JPanel());
		
		//Third column in row
		JPanel thirdCol = new JPanel();
		thirdCol.setLayout(new GridLayout(3,1));
		buttonMakeAccusation = new JButton("Make Accusation");
		thirdCol.add(new JPanel());
		thirdCol.add(buttonMakeAccusation);
		thirdCol.add(new JPanel());
		
		//adds the columns to the top row
		jp.add(firstCol);
		jp.add(secCol);
		jp.add(thirdCol);
		
		return jp;
		
	}
	
	//Function for making the bottom row of the GUI
	private JPanel createBottomRow(){
			JPanel jp = new JPanel();
			jp.setLayout(new GridLayout(1,3));
			
			//first column in row
			JPanel firstCol = new JPanel();
			firstCol.setLayout(new GridLayout(1,1));
			
			JPanel t1Panel = new JPanel();
			t1Panel.setLayout(new GridLayout(3,1));
			
			JPanel rollPanel = new JPanel();
			rollPanel.setLayout(new GridLayout(1,2));
			JLabel label1 = new JLabel("Roll",JLabel.CENTER);
			txtfieldRoll = new JTextField("");
			txtfieldRoll.setEditable(false);
			rollPanel.setBorder( new TitledBorder(new EtchedBorder(), "Die"));
			rollPanel.add(label1);
			rollPanel.add(txtfieldRoll);
			
			t1Panel.add(new JPanel());
			t1Panel.add(rollPanel);
			t1Panel.add(new JPanel());
			firstCol.add(t1Panel);
			
			
			
			//second column in row
			JPanel secCol = new JPanel();
			secCol.setLayout(new GridLayout(1,1));
			
			JPanel t2Panel = new JPanel();
			t2Panel.setLayout(new GridLayout(3,1));
			
			JPanel guessPanel = new JPanel();
			guessPanel.setLayout(new GridLayout(1,2));
			JLabel label2 = new JLabel("Guess",JLabel.CENTER);
			txtfieldGuess = new JTextField("");
			txtfieldGuess.setEditable(false);
			guessPanel.setBorder( new TitledBorder(new EtchedBorder(), "Guess"));
			guessPanel.add(label2);
			guessPanel.add(txtfieldGuess);
			
			t2Panel.add(new JPanel());
			t2Panel.add(guessPanel);
			t2Panel.add(new JPanel());
			secCol.add(t2Panel);
			
			//third column in row
			JPanel thirdCol = new JPanel();
			thirdCol.setLayout(new GridLayout(1,1));
			
			JPanel t3Panel = new JPanel();
			t3Panel.setLayout(new GridLayout(3,1));
			
			JPanel responsePanel = new JPanel();
			responsePanel.setLayout(new GridLayout(1,2));
			JLabel label3 = new JLabel("Response",JLabel.CENTER);
			txtfieldResponse = new JTextField("");
			txtfieldResponse.setEditable(false);
			responsePanel.setBorder( new TitledBorder(new EtchedBorder(), "Guess Result"));
			responsePanel.add(label3);
			responsePanel.add(txtfieldResponse);
			
			t3Panel.add(new JPanel());
			t3Panel.add(responsePanel);
			t3Panel.add(new JPanel());
			thirdCol.add(t3Panel);

			jp.add(firstCol);
			jp.add(secCol);
			jp.add(thirdCol);
			return jp;
	}
		
	public static void main(String[] args) {
		Board b = new Board();
		b.setConfigFiles("Layout.csv", "legend.txt", "playerData.txt", "weapons.txt");
		b.initialize();
		
		//GUI Code
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GUI Example");
		frame.setSize(900, 300);	
		frame.add(b);
		frame.setVisible(true);
	}
	
	
}
