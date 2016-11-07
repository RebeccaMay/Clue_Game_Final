package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ClueGame extends JFrame{

	private static Board board;
	private ControlGUI cgui;
	
	ClueGame(){
		board = Board.getInstance();
		board.setConfigFiles("LayoutAPJS.csv", "legendAPJS.txt", "playerDataAPJS.txt", "weaponsAPJS.txt","RoomNameLayoutAPJS.txt");
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
		
		DetectiveNotes detectiveNotes = new DetectiveNotes();
		detectiveNotes.setVisible(true);
	}
	
	public JMenu createMenu(){
		JMenu menu = JMenu("File");
		menu.add(createFileShowNotesItem());
		menu.add(createFileExitItem());
		return menu;
	}
	
	public JMenuItem createFileExitItem(){
		JMenuItem exit = JMenuItem("Exit");
		return exit;
	}
	
	public JMenuItem createFileShowNotesItem(){
		JMenuItem showNotes = JMenuItem("Show Notes");
		return showNotes;
	}

}
