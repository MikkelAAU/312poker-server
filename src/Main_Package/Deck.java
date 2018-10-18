package Main_Package;

import java.util.ArrayList;
import java.util.Collections;


public class Deck extends Player{

	// Collection of cards
	private ArrayList<Card> deck; 


	private ArrayList<Player> players = new ArrayList<>();
	ArrayList<Card> table = new ArrayList<>();
	ArrayList<Card> burnPile = new ArrayList<>();
	ArrayList<Card> cardComp = new ArrayList<>();

	
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
	
	public void printPlayers(){
		System.out.println(players);
	}
 public void printTable() {
	 System.out.println(table);
 }
 
 public void printCardComp() {
	 System.out.print(cardComp);
 }

	public void shuffle() {

		Collections.shuffle(deck);

	}


	// Deal function for players
	public void deal(Player player, int numbOfCards) {
		//Get next card and add to hand of the player
		for (int i = 0; i < numbOfCards; i++) {
			Card removedCard = deck.remove(0);
			player.getHand().add(removedCard);
		}
	}


	// Deal function for table.
	public void deal(ArrayList<Card> list) {
		//Get next card and add to hand of the player
		Card removedCard = deck.remove(0);
		list.add(removedCard);
	}

	// Burns one card from the deck and adds it to the burn pile.
	public void burn(ArrayList<Card> list) {
		Card removedCard = deck.remove(0);
		list.add(removedCard);
	}
	
	//the cards on the table
	public void cardTable(ArrayList<Card> list) {
		Card removedCard = deck.remove(0);
		list.add(removedCard);
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public void roundOne() {
		for (int i=0; i<players.size(); i++) {
			this.deal(players.get(i), 2);			
		}
	}
	
	public void roundTwo() {
	this.deal(burnPile);
	this.deal(table);
	this.deal(burnPile);
	this.deal(table);
	this.deal(burnPile);
	this.deal(table);
	}
		
	
	public void yourHand() {
		for (int i= 0; i<table.size();i++) {
			this.cardComp.add(table.get(i));
		}		
	}

	
	
	
}

