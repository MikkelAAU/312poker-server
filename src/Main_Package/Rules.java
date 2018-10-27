package Main_Package;

import java.util.ArrayList;
import java.util.Collections;

public class Rules {
	public static void main(String[] args) {

		Rules rules = new Rules();
		ArrayList<Card> deck = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Value value : Value.values()) {
				deck.add(new Card(value, suit));
			}
		}
		Collections.shuffle(deck);

		ArrayList<Card> playerHand = new ArrayList<>();
		for (int i = 0; i < 7; i += 1) {
			playerHand.add(deck.get(0));
			deck.remove(0);
		}
		playerHand.add(new Card(Value.Ten, Suit.Spades));
		playerHand.add(new Card(Value.Six, Suit.Hearts));
		playerHand.add(new Card(Value.Nine, Suit.Spades));
		playerHand.add(new Card(Value.Queen, Suit.Clubs));
		playerHand.add(new Card(Value.Eight, Suit.Spades));
		playerHand.add(new Card(Value.Jack, Suit.Spades));
		playerHand.add(new Card(Value.Seven, Suit.Spades));

		Collections.sort(playerHand, Collections.reverseOrder(new SortByValue()));
		System.out.println(playerHand);
		// high card:
		System.out.println("High card: " + playerHand.get(0));
	}



	public static ArrayList<HandResult> checkKinds(ArrayList<Card> playerHand, ArrayList<HandResult> handResult) {
		String kinds = "";
		String result = "";
		try {
			for (int i = 0; i < playerHand.size() - 1; i++) {
				result = "";
				if (playerHand.get(i).getCardValue().getCardValue() == playerHand.get(i + 1).getCardValue()
						.getCardValue()) {
					result = " 2 of a kind of " + playerHand.get(i).getCardValue() + "s";
					handResult.add(new HandResult(i, 1));
					i++;
					if (playerHand.get(i).getCardValue().getCardValue() == playerHand.get(i + 1).getCardValue()
							.getCardValue()) {
						result = " 3 of a kind of " + playerHand.get(i).getCardValue() + "s";
						handResult.add(new HandResult(i-1, 3));
						handResult.remove(handResult.size()-2);
						i++;
						if (playerHand.get(i).getCardValue().getCardValue() == playerHand.get(i + 1).getCardValue()
								.getCardValue()) {
							result = " 4 of a kind of " + playerHand.get(i).getCardValue() + "s";
							handResult.add(new HandResult(i-2, 7));
							handResult.remove(handResult.size()-2);
							i++;
						}
					}
				}
				kinds = kinds.concat(result);
			}
		} catch (IndexOutOfBoundsException e) {
			// coders note: this is FINE
			kinds = kinds.concat(result);
		}

		if(kinds.equals(""))
			kinds = "None";
		System.out.println("Found kinds:" + '\n' + kinds);
		return handResult;
	}

	public static ArrayList<HandResult> checkStraight(ArrayList<Card> playerHand, ArrayList<HandResult> handResult) {
		String straight = "";
		for (int i = 0; i < playerHand.size() - 4; i++) {
			straight = playerHand.get(i).toString() + " ";
			int amountOfStraight = 0;

			// looking for regular straight:
			for (int j = i + 1; j < playerHand.size(); j++) {
				if (playerHand.get(j).getCardValue().getCardValue() == playerHand.get(i).getCardValue().getCardValue()
						- 1 - amountOfStraight) {
					amountOfStraight++;
					straight = straight.concat(playerHand.get(j).toString() + " ");
				}
				if (amountOfStraight == 4) {
					handResult.add(new HandResult(i, 4));
					break;
				}
			}
			if (amountOfStraight == 4)
				break;

			straight = playerHand.get(i).toString() + " ";
			amountOfStraight = 0;
			// looking for low ace straight:

			if (playerHand.get(i).getCardValue().getCardValue() == 14) {
				int fivePosition = 0;
				for (int j = i + 1; j < playerHand.size(); j++) {
					if (playerHand.get(j).getCardValue().getCardValue() == 5 - amountOfStraight) {
						if(playerHand.get(j).getCardValue().getCardValue() == 5)
							fivePosition = j;
						amountOfStraight++;
						straight = straight.concat(playerHand.get(j).toString() + " ");
					}
				}
				if (amountOfStraight == 4) {
					handResult.add(new HandResult(fivePosition, 4));
					break;
				}
			}
			straight = "None";
		}

		System.out.println("Found straight:" + '\n' + straight);
		return handResult;
	}
	public static ArrayList<HandResult> checkSuit(ArrayList<Card> playerHand, ArrayList<HandResult> handResult) {
		String flush = "";
		for (int i = 0; i < playerHand.size() - 4; i++) {
			flush = playerHand.get(i).toString() + " ";
			int amountOfSuits = 0;
			for (int j = i + 1; j < playerHand.size(); j++) {
				if (playerHand.get(i).getSuit().printRankCard().equals(playerHand.get(j).getSuit().printRankCard())) {
					amountOfSuits++;
					flush = flush.concat(playerHand.get(j).toString() + " ");
				}
			}
			if (amountOfSuits == 4) {
				handResult.add(new HandResult(i, 5));
				break;
			}
			flush = "None";
		}

		System.out.println("Found flush:" + '\n' + flush);
		return handResult;
	}

}