package main;
import java.util.ArrayList;

import main.Hand;


public class Player {

	private String name;
	private Hand myHand;
	private Integer playerID; 
	private static EasyStrategy strategy1 = new EasyStrategy();
	private static MediumStrategy strategy2 = new MediumStrategy();
	private static HardStrategy strategy3 = new HardStrategy();
	private int strategyNumber = 1;
	private int pokerChips;

	/**
	 * 
	 * Creates a Player and gives them a name
	 * @param name
	 */
	public Player(String name)
	{
		this.name = name;
		myHand = new Hand();
		pokerChips = 2000;
	}
	
	public void setStrategy(int _strategyNumber){
		strategyNumber = _strategyNumber;
	}
	
	public Hand getHand(){
		return myHand;
	}
	public String getName()
	{
		return this.name;
	}

	public int numCards()
	{
		return myHand.numCards();
	}

	public void removeCards(){
		myHand.removeCards();
	}
	/**
	 * 
	 * Draws a card from the deck and places it in the player's hand
	 * @param the deck of cards
	 * @return the card dealt
	 */
	public PlayingCard draw(Deck deck)
	{
		PlayingCard cardDealt = deck.draw();
		myHand.addCard(cardDealt);
		return cardDealt;
	}
	
	/**
	 * Puts card back into deck (used for undo)
	 * @param the deck of cards
	 * @param card to put back on deck
	 * @return the card dealt
	 */
	public PlayingCard putBack(PlayingCard card, Deck deck)
	{
		deck.addCard(card);
		myHand.removeCard(card);
		return card;
	}
	
	/**
	 * Puts a card back in the player's hand
	 * @param card the card being added
	 */
	public void addCard(PlayingCard card)
	{
		myHand.addCard(card);
	}
	
	
	/**
	 * Requests a hand from the player by giving its type. If found, the cards are removed from the hand and
	 * returned to the calling program
	 * @param choice - the card we are looking for
	 * @return - any cards found
	 */
	public ArrayList<PlayingCard> request(int choice)
	{
		return myHand.findCards(choice);
	}
	
	/**
	 * Used to add a list of cards to the player's hand
	 * @param newCards - cards being added
	 */
	public void addCards(ArrayList<PlayingCard> newCards)
	{
		myHand.addCards(newCards);
	}
	//returns true if player busted:
	public boolean busted(){
		if(myHand.sumCards() > 21){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * based on local strategy number use the corresponding strategy to hit or stand
	 * @return - 1 for hit 2 for stand
	 */
	public boolean playStrategy() {
		switch (strategyNumber){
			case 1:
				return strategy1.play(myHand);
			case 2:
				return strategy2.play(myHand);
			case 3:
				return strategy3.play(myHand);
			default:
				return false;
		}
	}
	
	/**
	 * if bet was one add to poker chips if lost subtract
	 * @param bet
	 * @param won
	 * @return number of poker chips
	 */
	public int updatePokerChips(int bet, boolean won){
		if (won){
			pokerChips = pokerChips + bet;
		}
		else{
			pokerChips = pokerChips - bet;
		}
		return pokerChips;
	}
		
	/**
	 * Used to create a string representation of the player so it can be printed.
	 */
	public String toString()
	{
		String player = "\n" + name + ":\n";
		player += myHand.toString();
		return player;
	}

	public Integer getID() {
		
		return playerID;
	}
	
	public void setID(Integer ID){
		playerID = ID; 
		
	}
	
}
