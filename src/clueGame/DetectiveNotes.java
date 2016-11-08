package clueGame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNotes extends JDialog {

	private Board currentBoard;
	
	public DetectiveNotes(Board b){
		currentBoard = b;
		setLayout(new GridLayout(3, 2));
		setSize(1000, 900);	
		setTitle("Detective Notes");
		JPanel panel1 = peoplePanel();
		add(panel1);
		JPanel panel2 = personGuessPanel();
		add(panel2);
		JPanel panel3 = roomsPanel();
		add(panel3);
		JPanel panel4 = roomGuessPanel();
		add(panel4);
		JPanel panel5 = weaponsPanel();
		add(panel5);
		JPanel panel6 = weaponsGuessPanel();
		add(panel6);
	}

	public JPanel peoplePanel(){
		JPanel people = new JPanel();
		people.setLayout(new GridLayout(3, 2));
		people.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		
		for(Player p: currentBoard.getPlayerList()){
			people.add(new JCheckBox(p.getPlayerName()));
		}
		
		return people;
	}
	
	public JPanel personGuessPanel(){
		JPanel personGuess = new JPanel();
		personGuess.setBorder(new TitledBorder (new EtchedBorder(), "Person Guess"));
		
		JComboBox<String> people = new JComboBox<String>();
		for(Player p: currentBoard.getPlayerList()){
			people.addItem(p.getPlayerName());
		}		
		personGuess.add(people);
		
		return personGuess;
	}
	
	public JPanel roomsPanel(){
		JPanel rooms = new JPanel();
		rooms.setLayout(new GridLayout(3, 3));
		rooms.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		
		for(String s: currentBoard.getRoomMap().values()){
			rooms.add(new JCheckBox(s));
		}
				
		return rooms;
	}
	public JPanel roomGuessPanel(){
		JPanel roomGuess = new JPanel();
		roomGuess.setBorder(new TitledBorder (new EtchedBorder(), "Room Guess"));
		JComboBox<String> rooms = new JComboBox<String>();
		for(String s: currentBoard.getRoomMap().values()){
			rooms.addItem(s);
		}
		roomGuess.add(rooms);
		
		return roomGuess;
	}
	public JPanel weaponsPanel(){
		JPanel weapons = new JPanel();
		weapons.setLayout(new GridLayout(3, 2));
		weapons.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		
		for(Card c: currentBoard.getWeaponDeck()){
			weapons.add(new JCheckBox(c.getCardName()));
		}
		
		return weapons;
	}
	public JPanel weaponsGuessPanel(){
		JPanel weaponGuess = new JPanel();
		weaponGuess.setBorder(new TitledBorder (new EtchedBorder(), "Weapon Guess"));
		
		JComboBox<String> weapons = new JComboBox<String>();
		
		for(Card c: currentBoard.getWeaponDeck()){
			weapons.addItem(c.getCardName());
		}
		
		weaponGuess.add(weapons);
		return weaponGuess;
	}	
	
}
