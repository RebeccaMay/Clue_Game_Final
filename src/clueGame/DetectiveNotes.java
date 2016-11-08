package clueGame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNotes extends JPanel {

	public DetectiveNotes(){
		setLayout(new GridLayout(3, 2));
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
		
		JRadioButton player1 = new JRadioButton("Chad Bricky");		//We should probably read these in from a file
		people.add(player1);
		JRadioButton player2 = new JRadioButton("Mary M.");
		people.add(player2);
		JRadioButton player3 = new JRadioButton("Bozo the Bafoon");
		people.add(player3);
		JRadioButton player4 = new JRadioButton("Milton Dundershire");
		people.add(player4);
		JRadioButton player5 = new JRadioButton("Crystal Sunshine");
		people.add(player5);
		JRadioButton player6 = new JRadioButton("LtCdr Dan");
		people.add(player6);
		
		return people;
	}
	
	public JPanel personGuessPanel(){
		JPanel personGuess = new JPanel();
		personGuess.setBorder(new TitledBorder (new EtchedBorder(), "Person Guess"));
		return personGuess;
	}
	
	public JPanel roomsPanel(){
		JPanel rooms = new JPanel();
		rooms.setLayout(new GridLayout(3, 3));
		rooms.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		
		JRadioButton room1 = new JRadioButton("Bouncy Castle");		//We should probably read these in from a file
		rooms.add(room1);
		JRadioButton room2 = new JRadioButton("House of Mirrors");
		rooms.add(room2);
		JRadioButton room3 = new JRadioButton("Corn Maze");
		rooms.add(room3);
		JRadioButton room4 = new JRadioButton("Haunted House");
		rooms.add(room4);
		JRadioButton room5 = new JRadioButton("Ring Toss");
		rooms.add(room5);
		JRadioButton room6 = new JRadioButton("Hay Ride");
		rooms.add(room6);
		JRadioButton room7 = new JRadioButton("Petting Zoo");
		rooms.add(room7);
		JRadioButton room8 = new JRadioButton("Ticket Booth");
		rooms.add(room8);
		JRadioButton room9 = new JRadioButton("Circus Tent");
		rooms.add(room9);
		
		return rooms;
	}
	public JPanel roomGuessPanel(){
		JPanel roomGuess = new JPanel();
		roomGuess.setBorder(new TitledBorder (new EtchedBorder(), "Room Guess"));
		return roomGuess;
	}
	public JPanel weaponsPanel(){
		JPanel weapons = new JPanel();
		weapons.setLayout(new GridLayout(3, 2));
		weapons.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		
		JRadioButton weapon1 = new JRadioButton("Balloon Animal");
		weapons.add(weapon1);
		JRadioButton weapon2 = new JRadioButton("Goat");
		weapons.add(weapon2);
		JRadioButton weapon3 = new JRadioButton("Broken Mirror");
		weapons.add(weapon3);
		JRadioButton weapon4 = new JRadioButton("Cash Register");
		weapons.add(weapon4);
		JRadioButton weapon5 = new JRadioButton("Brass Knuckles");
		weapons.add(weapon5);
		JRadioButton weapon6 = new JRadioButton("Elephant Hook");
		weapons.add(weapon6);
		
		return weapons;
	}
	public JPanel weaponsGuessPanel(){
		JPanel weaponGuess = new JPanel();
		weaponGuess.setBorder(new TitledBorder (new EtchedBorder(), "Weapon Guess"));
		return weaponGuess;
	}
	
	
	//for testing purposes
	
	public static void main(String[] args) {	
		JFrame panel = new JFrame();
		panel.setSize(600, 900);
		DetectiveNotes detectiveNotes = new DetectiveNotes();
		panel.add(detectiveNotes);
		panel.setVisible(true);
	}
	
	
}
