package Main_Package;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Player {

	public ArrayList<Card> hand;
	public int balance;
	public boolean fold;
	public int current_bet;
	//   public Card[] pokerHand;

	public Player() {
		this.hand = new ArrayList<Card>();
		this.balance = 100;
		this.fold = false;
		this.current_bet = 0;
	}



	public ArrayList<Card> getHand() {
		return hand;
	}

	// Print all the cards in the player's hand.
	public void printHand(){
		System.out.println(hand);
	}

	@Override
	public String toString() {
		return "Player{" +
				"hand=" + hand +
				'}';
	}

	
	public void reset() {
		current_bet = 0;
		fold = false;
	}

	
	public void draw(Card c){
		for (int i=0; i<5; i++) {
			if (hand.get(i) ==null){
				hand.set(i, c);
				break;
			}
		}
	}


}