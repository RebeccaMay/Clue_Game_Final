package clueGame;

import java.util.Set;

public class HumanPlayer extends Player {

	public HumanPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
	}
	
	@Override
	public void makeMove(Set<BoardCell> targets, int r, int c){
		row = r;
		column = c;
	}

}
