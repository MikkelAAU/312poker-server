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
		
		for (int round = 1; round <= 4; round++) {

			switch (round) {

			case 1:
				for (UserThread ut : server.getUsers()) {
					for (int i = 0; i < 2; i++) {
						Card temp = deck.get(0);
						deck.remove(0);
						ut.getUserHand().add(temp);
						ut.sendCard(temp.getCardValue().toString(), temp.getSuit().toString());
					}
				}
				break;

			case 2:

				for (int i = 0; i < 3; i++) {
					Card temp = deck.get(0);
					deck.remove(0);
					for (UserThread ut : server.getUsers()) {
						ut.getUserHand().add(temp);
						ut.sendCard(temp.getCardValue().toString(), temp.getSuit().toString());
					}
				}
				break;

			case 3:
				Card temp = deck.get(0);
				deck.remove(0);
				for (UserThread ut : server.getUsers()) {
					ut.getUserHand().add(temp);
					ut.sendCard(temp.getCardValue().toString(), temp.getSuit().toString());
				}
				break;

			case 4:
				Card temp1 = deck.get(0);
				deck.remove(0);
				for (UserThread ut : server.getUsers()) {
					ut.getUserHand().add(temp1);
					ut.sendCard(temp1.getCardValue().toString(), temp1.getSuit().toString());
				}
				break;
			}
		}
	}	
}
