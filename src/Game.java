public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("\n------------------Filling deck----------------------\n");
		
		Deck deck = new Deck();			// Deck is automatically filled when instantiated.
		
		deck.printDeck();
		System.out.println("\n------------------SHUFFLING DECK----------------------\n");
		
		deck.shuffle();
		deck.printDeck();
			
		System.out.println("\n------------------Dealing cards----------------------\n");
		
		Player p1,p2,p3;
		p1 = new Player();
		p2 = new Player();
		p3 = new Player();
		
		Player[] players = {p1,p2,p3};
		
		// Deals 2 cards to each Player object in the Player[].
		for (int i=0; i<players.length; i++) {
			
			deck.deal(players[i], 2);
		
		}
		
		System.out.println("\n------------------Player 1 hand:----------------------\n");
		p1.printHand();
		System.out.println("\n------------------Player 2 hand:----------------------\n");
		p2.printHand();
		System.out.println("\n------------------Player 3 hand:----------------------\n");
		p3.printHand();
		

	}

}
