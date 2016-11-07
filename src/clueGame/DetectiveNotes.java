package clueGame;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNotes extends JPanel {

	public DetectiveNotes(){
		setLayout(new GridLayout(3, 2));
		add(peoplePanel());
		
	}

	public JPanel peoplePanel(){
		JPanel people = new JPanel();
		people.setLayout(new GridLayout(3, 2));
		people.setBorder(new TitledBorder (new EtchedBorder(), "People"));
		return people;
	}
	
	//public JPanel personGuessPanel(){
		
	//}
	public JPanel roomsPanel(){
		JPanel rooms = new JPanel();
		rooms.setLayout(new GridLayout(3, 2));
		setLayout(new GridLayout(3, 2));
		return rooms;
	}
	//public JPanel roomGuessPanel(){

	//}
	public JPanel weaponsPanel(){
		JPanel weapons = new JPanel();
		weapons.setLayout(new GridLayout(3, 2));
		setLayout(new GridLayout(3, 2));
		return weapons;
	}
	//public JPanel weaponsGuessPanel(){

	//}
	
	
	

}
