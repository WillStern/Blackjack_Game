package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Deck;
import main.PlayingCard;

public class DeckTests {
	
	Deck twoDecks;
	Deck oneDeck;

	@Before
	public void initialize(){
		oneDeck = new Deck(1);
		twoDecks = new Deck(2);
		
	}
	
	@Test
	public void DeckPrintsCorrectly(){
		System.out.println(oneDeck);
		assertEquals(oneDeck.size(), 52);
		System.out.println(twoDecks);
		assertEquals(twoDecks.size(), 104);
	}
	
	@Test 
	public void DeckAddsProperly(){
		
		PlayingCard card = new PlayingCard("Diamonds", 5);
		oneDeck.addCard(card);
		assertEquals(oneDeck.size(), 53);
		//System.out.println(oneDeck);
	}
	
	@Test 
	public void DeckRemovesProperly(){
		
		PlayingCard card = oneDeck.draw(); 
		assertTrue( oneDeck.size() == 51);
		
		
	}
}
