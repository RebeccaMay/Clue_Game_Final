package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClueGame extends JFrame{

	private static Board board;
	private ControlGUI cgui;
	
	public ClueGame(){
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
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
	}
	
	public static void main(String[] args) {		
		//GUI Code
		ClueGame cg = new ClueGame();
		cg.setVisible(true);
		
		DetectiveNotes detectiveNotes = new DetectiveNotes();
		detectiveNotes.setVisible(true);
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
		class MenuItemListner implements ActionListener{
			public void actionPerformed(ActionEvent e){
				//Need to open Detective Notes
			}
		}
		return showNotes;
	}

}
