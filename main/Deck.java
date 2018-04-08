package main;


import java.util.ArrayList;
import java.util.Collections;

import main.PlayingCard;


public class Deck {
	private ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
	private int deckSize;
	private int count;
	private int suggestedBet;
	private int MINBET = 5;
	

	public Deck(int deckSize)
	{
		deck = new ArrayList<PlayingCard>();
		this.deckSize = deckSize;
		if(deckSize > 6){
			System.out.println("Maximum 6 decks. Set to 6 decks instead of: " + deckSize);
			deckSize = 6;
		}
		else if(deckSize < 1){
			System.out.println("Minimum 1 deck. Set to 1 deck instead of: " + deckSize);
			deckSize = 1;
		}
		this.initializeDeck();
	}
	
	/**
	 * Used to get how many cards are in the deck
	 * @return
	 */
	public int size()
	{
		return deck.size();
	}
	
	
	/*
	 * for card counting get the count based on the cards drawn
	 */
	public int getCount()
	{
		return count;
	}
	
	public int getNumberOfDecks()
	{
		return deckSize;
	}
	
	public ArrayList<PlayingCard> getCards()
	{
		return deck;
	}
	
	public void setNumDecks(int value)
	{
		deckSize = value;
	}
	
	/**
	 * Used to add a suit of cards to the deck
	 * @param suit
	 */
	public void addSuit(String suit)
	{
		//Normally we'd use PlayingCard.MAX but we want the option of smaller decks
		for (int i = PlayingCard.MIN; i <= PlayingCard.MAX; i++)
		{
			PlayingCard newCard = new PlayingCard(suit, i);
			deck.add(newCard);
		}
	}
	
	/**
	 * Initializes a deck of cards with all four suits
	 */
	public  void initializeDeck()
	{
		for(int j = 0; j < deckSize; j++){
			addSuit(PlayingCard.CLUBS);
			addSuit(PlayingCard.DIAMONDS);
			addSuit(PlayingCard.HEARTS);
			addSuit(PlayingCard.SPADES);
			Collections.shuffle(deck);
		}
	}
	
	/**
	 * Draws a card from the top of the deck. Assumes the deck has been shuffled!
	 * @return - the card that has been drawn
	 */
	public PlayingCard draw()
	{
		PlayingCard card = deck.get(0);
		deck.remove(0);
		if((card.getType() >= 10) || card.getType() == 1){
			count--;
		}
		else if(card.getType() < 6){
			count++;
		}
		return card;
	}

	/**
	 * Puts card back into Deck 
	 * @param card
	 */
	public void addCard(PlayingCard card) {
		deck.add(card);
		System.out.println("Card: " + card + " placed back into deck.");
	}
	
	public int setSuggestedBet(){
		if (count < 1){
			return MINBET;
		}
		int numberOfDecks = getNumberOfDecks();
		int bet = MINBET * count / numberOfDecks;
		return bet;
	}

	/** 
	 * Prints out the deck as a string 
	 */
	public String toString(){
		String printed_deck = "";
		int num_cards = deck.size();
		//Collections.sort(deck); // un-comment to get sorted print
		System.out.println("Deck of " + num_cards + " cards.");
		for(int i = 0; i < deck.size(); i++){
			printed_deck += (i+1) + " : " + deck.get(i) + "\n";
		}
		return printed_deck;
	}
}
