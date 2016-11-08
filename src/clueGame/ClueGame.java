package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ClueGame extends JFrame{

	private static Board board;
	private ControlGUI cgui;
	private CardDisplay pcards;
	
	public ClueGame(){
		board = Board.getInstance();
		board.setConfigFiles("LayoutAPJS.csv", "legendAPJS.txt", "playerDataAPJS.txt", "weaponsAPJS.txt","RoomNameLayoutAPJS.txt");
		board.initialize();
		
		cgui = new ControlGUI(board);
		pcards = new CardDisplay(board);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue Game");
		setLayout(new BorderLayout());
		setSize(625, 930);	
		add(board,BorderLayout.CENTER);
		add(cgui,BorderLayout.SOUTH);
		add(pcards,BorderLayout.EAST);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		
	}
	
	public static void main(String[] args) {		
		//GUI Code
		ClueGame cg = new ClueGame();
		cg.setVisible(true);
	}
	
	public JMenu createFileMenu(){
		JMenu menu = new JMenu("File");
		menu.add(createShowNotesItem());
		menu.add(createExitItem());
		return menu;
	}
	
	public JMenuItem createExitItem(){
		JMenuItem exit = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		}
		exit.addActionListener(new MenuItemListener());
		return exit;
	}
	
	public JMenuItem createShowNotesItem(){
		JMenuItem showNotes = new JMenuItem("Show Notes");
		DetectiveNotes dtn = new DetectiveNotes(board);
		
		class NotesItemListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				dtn.setVisible(true);
			}
		}
		showNotes.addActionListener(new NotesItemListener());
		return showNotes;
	}

}
