import java.util.ArrayList;
import java.util.List;

public class Player {

	ArrayList<Card> hand;


	public Player() {
		this.hand = new ArrayList<Card>();
		//players.add(this);
		//System.out.println("New player: " + this);
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

}