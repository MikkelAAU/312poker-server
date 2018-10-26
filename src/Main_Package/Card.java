package Main_Package;

public class Card {
	
	private Suit suit;
	
	private Value value;
    
	// Constructor
    public Card (Value value,Suit suit) {
        
        this.suit = suit;
        this.value = value;
    }
	
	// Gets card suit
	public Suit getSuit() {
		return suit;
	}
	
	// Gets card value
	public Value getCardValue() {
		return value;
	}
	
	// This is called when printing out the deck to the console.
	public String toString() {
		
		return value + " of " + suit;
	}
}
