package main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EasyStrategyTest {
	Deck deck = new Deck(1);
	
	Hand hand1 = new Hand();
	Hand hand2 = new Hand();
	
	EasyStrategy strategy = new EasyStrategy();
	PlayingCard FourOfHearts = new PlayingCard("Heart", 4);
	PlayingCard FiveOfHearts = new PlayingCard("Hearts", 5);
	PlayingCard TwoOfDiamonds = new PlayingCard("Diamond", 2);
	PlayingCard ThreeOfHearts = new PlayingCard("Heart", 3);
	PlayingCard TenOfDiamonds = new PlayingCard("Diamond", 10);
	PlayingCard TenOfSpades = new PlayingCard("Spade", 10);
	PlayingCard TenOfClubs = new PlayingCard("Club", 10);
	PlayingCard QueenOfDiamonds = new PlayingCard("Diamond", 12);
	PlayingCard KingOfDiamonds = new PlayingCard("Diamond", 13);
	PlayingCard KingOfClubs = new PlayingCard("Clubs", 13);
	
	@Before 
	public void initialize() {
		
		hand2.addCard(TwoOfDiamonds);
		hand2.addCard(TenOfDiamonds);
		
		hand1.addCard(ThreeOfHearts);
		hand1.addCard(KingOfDiamonds);
	}

	@Test
	public void testPlay() {
		assertEquals(strategy.play(hand2), 1);
		assertEquals(strategy.play(hand1), 2);
	}
	

}
