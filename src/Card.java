
public class Card {
	
	private Suit suit;
	
	private Value value;
    
	// Constructor
    public Card(Value value,Suit suit) {
        
        this.suit = suit;
        this.value = value;
    }
	
	// Methods
	public Suit getSuit() {
		return suit;
	}
	
	public Value getCardValue() {
		return value;
	}
	
	// This is called when printing out the deck to the console.
	public String toString() {
		
		return value + " of " + suit;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

	}

}
