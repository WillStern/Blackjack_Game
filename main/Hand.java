package main;
import java.util.ArrayList;
import java.util.Collections;

import main.PlayingCard;


public class Hand {
	
	private ArrayList<PlayingCard> cards;


	/**
	 * Creates an empty hand of cards
	 */
	public Hand() {
		super();
		cards = new ArrayList<PlayingCard>();
	}
	
	/**
	 * Returns how many cards are in the hand
	 * @return number of cards held
	 */
	public int numCards() 
	{
		return cards.size();
	}
	
	/**
	 * Returns specified card in hand
	 * @return card in that position
	 */
	public PlayingCard getCard(int index){
		if(index < 0 || index > cards.size()-1){
			System.out.println("Invaild card index");
			return null;
		}
		else{
			return cards.get(index);
		}
	}
	
	/**
	 * Returns cards in the hand
	 * @return cards held
	 */
	public ArrayList<PlayingCard> getCards(){
		return cards;
	}
	/**
	 * Adds a new card to the hand and sorts it
	 * @param card - card being removed from hand
	 */
	public void removeCard(PlayingCard card)
	{
		cards.remove(card);
	}
	
	/**
	 * Adds a new card to the hand and sorts it
	 * @param newcard - card being added
	 */
	public void addCard(PlayingCard newcard)
	{
		cards.add(newcard);
	}
	
	public void addCards(ArrayList<PlayingCard> newCards)
	{
		cards.addAll(newCards);
	}
	
	/**
	 * Finds all the cards with a particular value in a hand. If a match is found the
	 * cards are removed from the hand and returned to the calling program.
	 * @param value - the card we are looking for
	 * @return - a list of all matching cards
	 */
	public ArrayList<PlayingCard> findCards(int value)
	{
		ArrayList<PlayingCard>found = new ArrayList<PlayingCard>();
		for (PlayingCard card: cards)
		{
			if (card.value() == value)
			{
				found.add(card);
			}
		}
		return found;
	}
	/**
	 * Returns the total value of all cards in hand. 
	 * If aces are present it treats them like 1s unless there is room for 11 without busting
	 * @return the total value of all cards held
	 */
	public int sumCards() {
		int sum = 0;
		int numAces = 0;
		for (PlayingCard card: cards){
			int cardNum = card.getType();
			if (cardNum == 1){
				numAces ++;
			}
			if (cardNum > 10) {
				cardNum = 10;
			}
			sum += cardNum;
		}
		if ((sum <= 11) && (numAces > 0)){
			sum += 10;
		}
		return sum;
		}
	/**
	 * Returns true if hand is blackjack. 
	 * (Ace and jack, queen, or king)
	 */
	public boolean hasBlackjack(){
		ArrayList<Integer> initial_hand = new ArrayList<>();
		initial_hand.add(cards.get(0).getType());
		initial_hand.add(cards.get(1).getType());
		
		if(initial_hand.contains(1) && (initial_hand.contains(11) || initial_hand.contains(12) || initial_hand.contains(13))){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns a string representation of the hand so we can print it out.
	 */
	public String toString(boolean ownerOfHand)
	{
		String allCards = "";
		if(ownerOfHand){
			for (PlayingCard card : this.cards)
			{
				allCards += card.toString() + "\n";
			}
			return allCards;
		}
		else{
			int sizeOfHand = cards.size();
			PlayingCard card = null;
			allCards += "Face Down Card\n";
			for (int i=1; i<sizeOfHand; i++)
			{
				card = cards.get(i);
				allCards += card.toString() + "\n";
			}
		return allCards;
		}
	}

	public void removeCards() {
		cards.removeAll(cards);
	}
}
