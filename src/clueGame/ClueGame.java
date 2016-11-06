package clueGame;

import javax.swing.JFrame;

public class ClueGame extends JFrame{

	private static Board board;
	ClueGame(){
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "Legend 2.txt", "playerData.txt", "weapons.txt");
		board.initialize();
	}
	
	public static void main(String[] args) {		
		//GUI Code
		ClueGame cg = new ClueGame();
		cg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cg.setTitle("GUI Example");
		cg.setSize(900, 300);	
		cg.add(board);
		cg.setVisible(true);
	}

}
