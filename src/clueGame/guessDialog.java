package clueGame;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class guessDialog extends JDialog {
	private Board currentBoard;
	
	public guessDialog(Board b){
		
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
	
		JComboBox<String> people = new JComboBox<String>();
		for(Player p: currentBoard.getPlayerList()){
			people.addItem(p.getPlayerName());
		}		
		personGuess.add(people);
		
		return personGuess;
	}
	
	public JPanel room(){
		JPanel room = new JPanel();
		room.setLayout(new GridLayout(1, 1));
		JLabel roomLabel = new JLabel(currentBoard.getCurrentRoom(currentBoard.getCurrentPlayer()), JLabel.CENTER);
		room.add(roomLabel);
		return room;
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
		JComboBox<String> weapons = new JComboBox<String>();
		
		for(Card c: currentBoard.getWeaponDeck()){
			weapons.addItem(c.getCardName());
		}
		
		weaponGuess.add(weapons);
		return weaponGuess;
	}	
}
