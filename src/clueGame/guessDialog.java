package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class guessDialog extends JDialog {
	private Board currentBoard;
	private Solution guess;
	private JComboBox<String> weapons, rooms, people;
	private boolean isAccusation;
	private boolean submitted;
	JButton submitButton;
	
	public guessDialog(Board b, boolean isAccustion){
		this.isAccusation = isAccusation;
	}
	
	public JPanel person(){
		JPanel person = new JPanel();
		person.setLayout(new GridLayout(1, 1));
		JLabel personLabel = new JLabel("Person", JLabel.CENTER);
		person.add(personLabel);
		return person;
	}
	
	public JPanel personGuessPanel(){
		JPanel personGuess = new JPanel();
	
		people = new JComboBox<String>();
		for(Player p: currentBoard.getPlayerList()){
			people.addItem(p.getPlayerName());
		}		
		personGuess.add(people);
		
		return personGuess;
	}
	
	public JPanel room(){
		JPanel room = new JPanel();
		room.setLayout(new GridLayout(1, 1));
		JLabel roomLabel = new JLabel("Your Room", JLabel.CENTER);
		room.add(roomLabel);
		return room;
	}
	
	public JPanel roomsGuessPanel(){
		JPanel roomOption = new JPanel();
		roomOption.setLayout(new GridLayout(1,1));
		rooms = new JComboBox<String>();
		
		if(isAccusation){
			for(Card c: currentBoard.getRoomDeck()){
				rooms.addItem(c.getCardName());
			}
		}
		else{
			rooms.addItem(currentBoard.getCurrentRoom(currentBoard.getCurrentPlayer()));
		}
		roomOption.add(rooms);
		return roomOption;
	}
	
	public JPanel weapon(){
		JPanel weapon = new JPanel();
		weapon.setLayout(new GridLayout(1, 1));
		JLabel weaponLabel = new JLabel("Weapon", JLabel.CENTER);
		weapon.add(weaponLabel);
		return weapon;
	}
	
	public JPanel weaponsGuessPanel(){
		JPanel weaponGuess = new JPanel();
		weapons = new JComboBox<String>();
		
		for(Card c: currentBoard.getWeaponDeck()){
			weapons.addItem(c.getCardName());
		}
		
		weaponGuess.add(weapons);
		return weaponGuess;
	}
	
	public JPanel submitButton(){
		JPanel submit = new JPanel();
		submit.setLayout(new GridLayout(1,1));
		submitButton = new JButton("Submit");
		submit.add(submitButton);
		submitButton.addActionListener(new submitActionListener());
		return submit;
	}
	
	public JPanel cancelButton(){
		JPanel cancel = new JPanel();
		cancel.setLayout(new GridLayout(1,1));
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new exitActionListener());
		cancel.add(cancelButton);
		return cancel;
	}
	
	public class submitActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == submitButton){
				submitted = true;
			}
			else{
				submitted = false;
			}
		}
	}
	
	public class exitActionListener implements ActionListener{
		public void actionPerfromed(ActionEvent e){
			System.exit(0);
		}

		@Override
		public void actionPerformed(ActionEvent e) {}
	}
	
	public Solution getSolution(){
		guess.setValues((String)people.getSelectedItem(), (String)weapons.getSelectedItem(), (String)rooms.getSelectedItem());
		return guess;
	}
}
