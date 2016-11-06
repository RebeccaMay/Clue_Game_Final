package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlGUI extends JPanel{

	//variables for GUI components (textbox, buttons, etc.)
	private JTextField txtfieldWhoseTurn;
	private JButton buttonNextPlayer;
	private JButton buttonMakeAccusation;
	private JTextField txtfieldRoll;
	private JTextField txtfieldGuess;
	private JTextField txtfieldResponse;

	ControlGUI(){
		//GUI code
		setLayout(new GridLayout(2,1));
		JPanel topRow = createTopRow();
		JPanel bottomRow = createBottomRow();
		add(topRow);
		add(bottomRow);
	}



	//Function for making the top row of the GUI
	private JPanel createTopRow(){

		//top row
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(1,3));

		//first column in row
		JPanel firstCol = new JPanel();
		firstCol.setLayout(new GridLayout(3,3));
		JLabel labelWhoseTurn = new JLabel("Whose Turn?",JLabel.CENTER);
		txtfieldWhoseTurn = new JTextField("");
		txtfieldWhoseTurn.setEditable(false);

		//multiple panels for proper spacing
		firstCol.add(new JPanel());
		firstCol.add(labelWhoseTurn,BorderLayout.NORTH);
		firstCol.add(new JPanel());
		firstCol.add(new JPanel());
		firstCol.add(txtfieldWhoseTurn,BorderLayout.SOUTH);
		firstCol.add(new JPanel());
		firstCol.add(new JPanel());
		firstCol.add(new JPanel());
		firstCol.add(new JPanel());

		//Second column in row
		JPanel secCol = new JPanel();
		secCol.setLayout(new GridLayout(3,1));
		buttonNextPlayer = new JButton("Next Player");
		secCol.add(new JPanel());
		secCol.add(buttonNextPlayer);
		secCol.add(new JPanel());

		//Third column in row
		JPanel thirdCol = new JPanel();
		thirdCol.setLayout(new GridLayout(3,1));
		buttonMakeAccusation = new JButton("Make Accusation");
		thirdCol.add(new JPanel());
		thirdCol.add(buttonMakeAccusation);
		thirdCol.add(new JPanel());

		//adds the columns to the top row
		jp.add(firstCol);
		jp.add(secCol);
		jp.add(thirdCol);

		return jp;

	}

	//Function for making the bottom row of the GUI
	private JPanel createBottomRow(){
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(1,3));

		//first column in row
		JPanel firstCol = new JPanel();
		firstCol.setLayout(new GridLayout(1,1));

		JPanel t1Panel = new JPanel();
		t1Panel.setLayout(new GridLayout(3,1));

		JPanel rollPanel = new JPanel();
		rollPanel.setLayout(new GridLayout(1,2));
		JLabel label1 = new JLabel("Roll",JLabel.CENTER);
		txtfieldRoll = new JTextField("");
		txtfieldRoll.setEditable(false);
		rollPanel.setBorder( new TitledBorder(new EtchedBorder(), "Die"));
		rollPanel.add(label1);
		rollPanel.add(txtfieldRoll);

		t1Panel.add(new JPanel());
		t1Panel.add(rollPanel);
		t1Panel.add(new JPanel());
		firstCol.add(t1Panel);



		//second column in row
		JPanel secCol = new JPanel();
		secCol.setLayout(new GridLayout(1,1));

		JPanel t2Panel = new JPanel();
		t2Panel.setLayout(new GridLayout(3,1));

		JPanel guessPanel = new JPanel();
		guessPanel.setLayout(new GridLayout(1,2));
		JLabel label2 = new JLabel("Guess",JLabel.CENTER);
		txtfieldGuess = new JTextField("");
		txtfieldGuess.setEditable(false);
		guessPanel.setBorder( new TitledBorder(new EtchedBorder(), "Guess"));
		guessPanel.add(label2);
		guessPanel.add(txtfieldGuess);

		t2Panel.add(new JPanel());
		t2Panel.add(guessPanel);
		t2Panel.add(new JPanel());
		secCol.add(t2Panel);

		//third column in row
		JPanel thirdCol = new JPanel();
		thirdCol.setLayout(new GridLayout(1,1));

		JPanel t3Panel = new JPanel();
		t3Panel.setLayout(new GridLayout(3,1));

		JPanel responsePanel = new JPanel();
		responsePanel.setLayout(new GridLayout(1,2));
		JLabel label3 = new JLabel("Response",JLabel.CENTER);
		txtfieldResponse = new JTextField("");
		txtfieldResponse.setEditable(false);
		responsePanel.setBorder( new TitledBorder(new EtchedBorder(), "Guess Result"));
		responsePanel.add(label3);
		responsePanel.add(txtfieldResponse);

		t3Panel.add(new JPanel());
		t3Panel.add(responsePanel);
		t3Panel.add(new JPanel());
		thirdCol.add(t3Panel);

		jp.add(firstCol);
		jp.add(secCol);
		jp.add(thirdCol);
		return jp;
	}	
}
