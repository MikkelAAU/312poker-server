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
		UserThread actingPlayer;
		// set blind (someone who has money in the pot from the beginning)
		int currentHighestBet = 10;
		int totalPot = currentHighestBet;
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
			for (int i = 0; i < server.getUsers().size(); i++) {
				actingPlayer = server.getUsers().get(i);
				if (!actingPlayer.isFolded()) {
					actingPlayer.sendBoolean(true);
					for (UserThread ut : server.getUsers()) {
						if (ut != actingPlayer)
							ut.sendBoolean(false);
					}

					String defaultMessage = "It is currently " + actingPlayer.getUserName()
							+ "s turn. Wait until it's yours turn.";

					server.sendToAll(defaultMessage, actingPlayer);

					String message = "It's your acting turn." + '\n' + "Your current money Stack is: "
							+ server.getUsers().get(i).getBettingCash() + '\n';
					if (actingPlayer.getMyBet() == currentHighestBet) {
						message = message.concat("You match the current highest bet: " + currentHighestBet + '\n'
								+ "Use these commands: check raise fold");
					} else {
						message = message.concat("The current highest bet is: " + currentHighestBet + '\n'
								+ "Use these commands: call raise fold");
					}

					actingPlayer.sendMessage(message);

					boolean correctCommand = false;
					do {
						String command = actingPlayer.readString();
						switch (command) {
						
						// Command for the player to check 
						case "check":
							actingPlayer.sendMessage("You checked this turn");
							server.sendToAll(actingPlayer.getUserName() + " decided to check", actingPlayer);
							correctCommand = true;
							break;
							
							// Command for the player to raise
						case "raise":						
							currentHighestBet = actingPlayer.readInt();
							totalPot += currentHighestBet - actingPlayer.getMyBet();
							actingPlayer.setBettingCash(currentHighestBet - actingPlayer.getMyBet());
							actingPlayer.setMyBet(currentHighestBet);
							actingPlayer.sendMessage("You raised to: " + currentHighestBet);
							server.sendToAll(actingPlayer.getUserName() + " decided to raise by: ", actingPlayer);
							correctCommand = true;
							break;

							// Command for the player to fold 
						case "fold":
							// folding command
							actingPlayer.setFolded(true);
							actingPlayer.sendMessage("You folded your hand");
							server.sendToAll(actingPlayer.getUserName() + " decided to fold his hand",
									actingPlayer);
							correctCommand = true;
							break;

						case "call":
							// calling command
							actingPlayer.setBettingCash(currentHighestBet - actingPlayer.getMyBet());
							totalPot += currentHighestBet - actingPlayer.getMyBet();
							actingPlayer.setMyBet(currentHighestBet);
							actingPlayer.sendMessage("You called to: " + currentHighestBet);
							server.sendToAll(actingPlayer.getUserName() + " decided to call highest bet: ",
									actingPlayer);
							correctCommand = true;
							break;
							// If the player write an invalid command in the commandline 
						default:
							actingPlayer.sendMessage("Incorrect command");
							System.out.println("Invalid command");
							break;
						}

					} while (!correctCommand);
				} else {
					for (UserThread ut : server.getUsers()) {
						ut.sendBoolean(false);
					}
					server.sendToAll(actingPlayer.getUserName() + " this player folded his hand", actingPlayer);
					server.sendToAll("Proceeding to next player: ", actingPlayer);
					actingPlayer.sendMessage("You folded your hand");
					actingPlayer.sendMessage("Proceeding to next player");

				}
			}
		}
	}	
}
