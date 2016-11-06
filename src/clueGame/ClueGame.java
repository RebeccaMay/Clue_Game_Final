package clueGame;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class ClueGame extends JFrame{

	private static Board board;
	private ControlGUI cgui;
	
	ClueGame(){
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "Legend 2.txt", "playerData.txt", "weapons.txt");
		board.initialize();
		
		cgui = new ControlGUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue Game");
		setLayout(new GridLayout(2,1));
		setSize(900, 900);	
		add(board);
		add(cgui);
		
	}
	
	public static void main(String[] args) {		
		//GUI Code
		ClueGame cg = new ClueGame();		
		cg.setVisible(true);
	}

}
