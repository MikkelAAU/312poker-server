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
		
		Player p1 = new Player();		// Player obj
		Player p2 = new Player();
		Player p3 = new Player();
		

		deck.printDeck();
		System.out.println("\n------------------SHUFFLING DECK---------------------\n");
		
		deck.shuffle();
		deck.printDeck();
		
		System.out.println("\n------------------Adding players to 'players' ArrayList---------\n");
		
		deck.addPlayer(p1);
		deck.addPlayer(p2);
		deck.addPlayer(p3);
		deck.printPlayers();
			
		System.out.println("\n------------------Starting first round----------------------\n");
		
		deck.roundOne();
		
		System.out.println("\n------------------Player 1 hand:----------------------\n");
		p1.printHand();
		System.out.println("\n------------------Player 2 hand:----------------------\n");
		p2.printHand();
		System.out.println("\n------------------Player 3 hand:----------------------\n");
		p3.printHand();
	}
}
