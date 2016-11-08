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

	public DetectiveNotes(){
		setLayout(new GridLayout(3, 2));
		setSize(625, 900);	
		
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
		
		JCheckBox player1 = new JCheckBox("Chad Bricky");		//We should probably read these in from a file
		people.add(player1);
		JCheckBox player2 = new JCheckBox("Mary M.");
		people.add(player2);
		JCheckBox player3 = new JCheckBox("Bozo the Bafoon");
		people.add(player3);
		JCheckBox player4 = new JCheckBox("Milton Dundershire");
		people.add(player4);
		JCheckBox player5 = new JCheckBox("Crystal Sunshine");
		people.add(player5);
		JCheckBox player6 = new JCheckBox("LtCdr Dan");
		people.add(player6);
		
		return people;
	}
	
	public JPanel personGuessPanel(){
		JPanel personGuess = new JPanel();
		personGuess.setBorder(new TitledBorder (new EtchedBorder(), "Person Guess"));
		
		JComboBox<String> people = new JComboBox<String>();
		people.addItem("Chad Bricky");
		people.addItem("Mary M.");
		people.addItem("Bozo the Bafoon");
		people.addItem("Milton Dundershire");
		people.addItem("Crystal Sunshine");
		people.addItem("LtCdr Dan");
		
		personGuess.add(people);
		
		return personGuess;
	}
	
	public JPanel roomsPanel(){
		JPanel rooms = new JPanel();
		rooms.setLayout(new GridLayout(3, 3));
		rooms.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		
		JCheckBox room1 = new JCheckBox("Bouncy Castle");		//We should probably read these in from a file
		rooms.add(room1);
		JCheckBox room2 = new JCheckBox("House of Mirrors");
		rooms.add(room2);
		JCheckBox room3 = new JCheckBox("Corn Maze");
		rooms.add(room3);
		JCheckBox room4 = new JCheckBox("Haunted House");
		rooms.add(room4);
		JCheckBox room5 = new JCheckBox("Ring Toss");
		rooms.add(room5);
		JCheckBox room6 = new JCheckBox("Hay Ride");
		rooms.add(room6);
		JCheckBox room7 = new JCheckBox("Petting Zoo");
		rooms.add(room7);
		JCheckBox room8 = new JCheckBox("Ticket Booth");
		rooms.add(room8);
		JCheckBox room9 = new JCheckBox("Circus Tent");
		rooms.add(room9);
		
		return rooms;
	}
	public JPanel roomGuessPanel(){
		JPanel roomGuess = new JPanel();
		roomGuess.setBorder(new TitledBorder (new EtchedBorder(), "Room Guess"));
		
		JComboBox<String> rooms = new JComboBox<String>();
		rooms.addItem("Bouncy Castle");
		rooms.addItem("House of Mirrors");
		rooms.addItem("Corn Maze");
		rooms.addItem("Haunted House");
		rooms.addItem("Ring Toss");
		rooms.addItem("Hay Ride");
		rooms.addItem("Petting Zoo");
		rooms.addItem("Ticket Booth");
		rooms.addItem("Circus Tent");
		
		roomGuess.add(rooms);
		
		return roomGuess;
	}
	public JPanel weaponsPanel(){
		JPanel weapons = new JPanel();
		weapons.setLayout(new GridLayout(3, 2));
		weapons.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
		
		JCheckBox weapon1 = new JCheckBox("Balloon Animal");
		weapons.add(weapon1);
		JCheckBox weapon2 = new JCheckBox("Goat");
		weapons.add(weapon2);
		JCheckBox weapon3 = new JCheckBox("Broken Mirror");
		weapons.add(weapon3);
		JCheckBox weapon4 = new JCheckBox("Cash Register");
		weapons.add(weapon4);
		JCheckBox weapon5 = new JCheckBox("Brass Knuckles");
		weapons.add(weapon5);
		JCheckBox weapon6 = new JCheckBox("Elephant Hook");
		weapons.add(weapon6);
		
		return weapons;
	}
	public JPanel weaponsGuessPanel(){
		JPanel weaponGuess = new JPanel();
		weaponGuess.setBorder(new TitledBorder (new EtchedBorder(), "Weapon Guess"));
		
		JComboBox<String> weapons = new JComboBox<String>();
		weapons.addItem("Balloon Animal");
		weapons.addItem("Goat");
		weapons.addItem("Broken Mirror");
		weapons.addItem("Cash Register");
		weapons.addItem("Brass Knuckles");
		weapons.addItem("Elephant Hook");
		
		weaponGuess.add(weapons);
		
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
