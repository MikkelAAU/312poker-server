import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Deck {
	
	// Collection of cards
	private ArrayList<Card> deck;
	
	// Meant to keep track of the number of cards dealt.
	private int cardsUsed;
	
	private Suit suits[];
	
	private Value values[];
	
	
	// Constructor. Fills a deck.
	public Deck() {
		this.deck = new ArrayList<Card>();
		suits=Suit.values();
		values=Value.values();
		for (Value value : Value.values()){
			for (Suit suit : Suit.values()) {
				deck.add(new Card(value,suit));	
			}
		}
	}
	
	
	// Print all the cards of the deck.
    public void printDeck(){
        System.out.println(deck);
    }


	public void shuffle() {
		
		Collections.shuffle(deck);
		
		}
	
	
	public void dealCard(Player player) {
		//Get next card and add to hand of the player
        Card removedCard = deck.remove(0);
        player.getHand().add(removedCard);
	}
}
