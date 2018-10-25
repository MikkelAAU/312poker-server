package Main_Package;

import java.util.ArrayList;
import java.util.Collections;

public class HandleASession implements Runnable {
	
	private Server server;

	public HandleASession(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// set blind (someone who has money in the pot from the beginning)
		int currentHighestBet = 10;
		server.getUsers().get(server.getUsers().size()-1).setMyBet(currentHighestBet);
		server.getUsers().get(server.getUsers().size()-1).setBettingCash(currentHighestBet);

		// set up a deck of 52 cards
		ArrayList<Card> deck = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Value value : Value.values()) {
				deck.add(new Card(value, suit));
			}
		}
		//shuffle the deck
		Collections.shuffle(deck);
		
		//
		for (UserThread ut : server.getUsers()) {
			ut.sendInt(server.getUsers().size());
		}
		
	}

}
