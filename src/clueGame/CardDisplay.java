package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class CardDisplay extends JPanel {

	private Board b;
	CardDisplay(Board b){
		this.b =  b;		
	    //setMinimumSize(new Dimension(150,200));
	    setLayout(new GridLayout(1,1));
		add(createCardDisplay());
		setBorder(new TitledBorder (new EtchedBorder(),"My Cards"));
		
	}
	
	
	JPanel createCardDisplay(){
		
		JPanel myCards = new JPanel();
		myCards.setLayout(new GridLayout(3,1));
		
		JPanel outerP = new JPanel();
		JPanel outerR = new JPanel();
		JPanel outerW = new JPanel();
		
		//outerP.setMinimumSize(new Dimension(100,120));
		//outerW.setMinimumSize(new Dimension(100,120));
		//outerR.setMinimumSize(new Dimension(100,120));
		
		outerP.setLayout(new BoxLayout(outerP,BoxLayout.Y_AXIS));
		outerW.setLayout(new BoxLayout(outerW,BoxLayout.Y_AXIS));
		outerR.setLayout(new BoxLayout(outerR,BoxLayout.Y_AXIS));
		
		outerP.setBorder(new TitledBorder (new EtchedBorder(),"People" ));
		outerW.setBorder(new TitledBorder (new EtchedBorder(), "Weapon"));
		outerR.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));
		
		ArrayList<Player> pl =  b.getPlayerList();
		for(Card c : pl.get(0).getCards()){
			switch(c.getCardType()){
			case PERSON:
				outerP.add(createCard(c.getCardName()));
				break;
			case WEAPON:
				outerW.add(createCard(c.getCardName()));
				break;
			case ROOM:
				outerR.add(createCard(c.getCardName()));
				break;
			}
		}
		
		myCards.add(outerP);
		myCards.add(outerR);
		myCards.add(outerW);
		
		return myCards;		
	}
	
	JPanel createCard(String name){
		JPanel cd = new JPanel();
		cd.setLayout(new BorderLayout());
		cd.setBorder(new EtchedBorder());
		cd.setBackground(Color.white);
		cd.add(new JLabel(name), BorderLayout.CENTER);
		
		return cd;
	}
	
	
}
