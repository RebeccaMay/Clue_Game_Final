package clueGame;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
	private char lastRoom;
	
	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
		this.lastRoom = ' ';
		// TODO Auto-generated constructor stub
	}

	public BoardCell pickLocation(Set<BoardCell> targets) {
		//First find an unvisited room, if any. Must use .isDoorway() as the group before us didn't count doorways 
		// as a part of the room. Altering the code to include doorways as rooms breaks prior tests, so we used .isDoorway() as
		// it provides the same functionality (a room isnt' always a doorway, but a doorway is always a room. 
		for(BoardCell bc: targets) {
			if(bc.isDoorway() && !(bc.getInitial() == lastRoom)) {
				lastRoom = bc.getInitial();
				return bc;
			}
		}
		//Then choose a random cell
		int itemNum = new Random().nextInt(targets.size());
		int iterator = 0;
		for (BoardCell bc: targets) {
			if(iterator == itemNum) return bc;
			iterator += 1;
		}
		BoardCell returnCell = null;
		//Should something error, return something from targets.
		for(BoardCell bc: targets) returnCell = bc;
		return returnCell;
	}

	//Used for testing purposes only
	public void setLastRoom(char lastRoom) {
		this.lastRoom = lastRoom;
	}

	public Solution createSuggestion() {
		Set<Card> peopleDeck = Board.getInstance().getPeopleDeck();
		Set<Card> weaponDeck = Board.getInstance().getWeaponDeck();
		Set<Card> seenPeople = new HashSet<Card>();
		Set<Card> seenWeapons = new HashSet<Card>();
		for (Card c: seenCards) {
			if(c.getCardType() == CardType.PERSON) seenPeople.add(c);
			if(c.getCardType() == CardType.WEAPON) seenWeapons.add(c);
		}
		peopleDeck.removeAll(seenPeople);
		weaponDeck.removeAll(seenWeapons);
		String chosenPerson = "";
		String chosenWeapon = "";
		int peopleNum = new Random().nextInt(peopleDeck.size());
		int weaponNum = new Random().nextInt(weaponDeck.size());
		int iterator = 0;
		for (Card c: peopleDeck) {
			if(iterator == peopleNum) {
				chosenPerson = c.getCardName();
				break;
			}
			iterator += 1;
		}
		iterator = 0;
		for (Card c: weaponDeck) {
			if(iterator == weaponNum) {
				chosenWeapon = c.getCardName();
				break;
			}
			iterator += 1;
		}
		char roomInitial = Board.getInstance().getCellAt(this.row, this.column).getInitial();
		Map<Character, String> roomMap = Board.getInstance().getRoomMap();
		String roomString = roomMap.get(roomInitial);
		Solution givenSolution = new Solution(chosenPerson, chosenWeapon, roomString);
		return givenSolution;
		
	}

}
