import java.util.ArrayList;

public class Game {
	public Player players[];
	public Deck deck;

	public Game(ArrayList<UserThread> users) {
		players = new Player[users.size()];
		for (int i = 0; i<users.size(); i++)
			players[i] = users.get(i).player;
		deck = new Deck();
		for (Player p: players)
			p.reset();
	}

	public void start() {
		for (int i = 0; i<5; i++){
			for (Player p: players){
			}
		}

	}


	public static void main(String[] args) {


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
