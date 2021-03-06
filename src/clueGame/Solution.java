package clueGame;

public class Solution {
	private String person;
	private String weapon;
	private String room;
	
	public Solution(String person, String weapon, String room) {
		super();
		this.person = person;
		this.room = room;
		this.weapon = weapon;
	}
	
	public Solution(){
		
	}

	//Used to set the values of the Solution for testing,
	public void setValues(String name, String weapon, String room) {
		this.person = name;
		this.weapon = weapon;
		this.room = room;
	}

	public boolean check(Solution accusation) {
		if(accusation.person.equals(this.person) && accusation.weapon.equals(this.weapon) && accusation.room.equals(this.room)) return true;
		return false;
	}
	
	
	//Testing Getters only

	public String getPerson() {
		return person;
	}

	public String getWeapon() {
		return weapon;
	}

	public String getRoom() {
		return room;
	}
	
	
}
