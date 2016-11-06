package clueGame;

import javax.swing.JFrame;

public class ClueGame extends JFrame{

	private static Board board;
	ClueGame(){
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "Legend 2.txt", "playerData.txt", "weapons.txt");
		board.initialize();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("GUI Example");
		setSize(900, 900);	
		add(board);
		
	}
	
	public static void main(String[] args) {		
		//GUI Code
		ClueGame cg = new ClueGame();		
		cg.setVisible(true);
	}

}
