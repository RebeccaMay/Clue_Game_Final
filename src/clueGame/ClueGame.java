package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class ClueGame extends JFrame{

	private static Board board;
	private ControlGUI cgui;
	
	ClueGame(){
		board = Board.getInstance();
		board.setConfigFiles("Layout.csv", "legend.txt", "playerData.txt", "weapons.txt","RoomNameLayout2.txt");
		board.initialize();
		
		cgui = new ControlGUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue Game");
		setLayout(new BorderLayout());
		setSize(625, 900);	
		add(board,BorderLayout.CENTER);
		add(cgui,BorderLayout.SOUTH);
		
	}
	
	public static void main(String[] args) {		
		//GUI Code
		ClueGame cg = new ClueGame();		
		cg.setVisible(true);
	}

}
