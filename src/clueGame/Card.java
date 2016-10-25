package clueGame;

public class Card {
	private String cardName;
	private CardType cardType;
	
	public Card(String name, CardType type){
		this.cardName = name;
		this.cardType = type;
	}
	//public boolean equals() {
	//	return false;
	//}
	
	@Override
	public boolean equals(Object o) {
		Card c = (Card)o;
		return this.cardName.equals(c.cardName) && this.cardType.equals(c.cardType);
	}
	@Override
	public String toString() {
		return "Card [cardName=" + cardName + ", cardType=" + cardType + "]";
	}
	
	//Testing Getters
	public String getCardName() {
		return cardName;
	}
	
}

