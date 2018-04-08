package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.PlayingCard;
import main.Hand;

public class HandTests {
	
	Hand hand_with_blackjack;
	Hand hand_with_value_23;
	Hand hand_with_value_20_ace_high;


	@Before
	public void initialize(){
		hand_with_blackjack = new Hand();
		hand_with_value_23 = new Hand();
		hand_with_value_20_ace_high = new Hand();
		
		PlayingCard AceOfClubs = new PlayingCard("Club",1);
		PlayingCard JackOfHearts = new PlayingCard("Heart",11);
		PlayingCard QueenOfHearts = new PlayingCard("Heart",12);
		PlayingCard AceOfSpades = new PlayingCard("Spade",1);
		PlayingCard ThreeOfHearts = new PlayingCard("Heart",3);
		PlayingCard KingOfClubs = new PlayingCard("Club",13);
		PlayingCard ThreeOfDiamonds = new PlayingCard("Diamond",3);
		PlayingCard SixOfDiamonds = new PlayingCard("Diamond",6);

		
		hand_with_blackjack.addCard(AceOfClubs);
		hand_with_blackjack.addCard(JackOfHearts);
		
		hand_with_value_23.addCard(KingOfClubs);
		hand_with_value_23.addCard(QueenOfHearts);
		hand_with_value_23.addCard(ThreeOfHearts);
		
		hand_with_value_20_ace_high.addCard(AceOfSpades);
		hand_with_value_20_ace_high.addCard(ThreeOfDiamonds);
		hand_with_value_20_ace_high.addCard(SixOfDiamonds);


		
	}
	
	@Test
	public void Hand_hasBlackjack_WorksCorrectly(){
		assertTrue(hand_with_blackjack.hasBlackjack());
	}
	
	@Test
	public void Hand_CorrectlyCalculatesValue(){
		assertEquals(hand_with_value_23.sumCards() , 23);
	}
	
	@Test
	public void Hand_CorrectlyCalculatesValue20(){
		assertEquals(hand_with_value_20_ace_high.sumCards() , 20);
	}
	
	@Test 
	public void HandIsCorrectSize(){
		int sizeOfHand1 = hand_with_blackjack.numCards();
		int sizeOfHand2 = hand_with_value_20_ace_high.numCards();
		assertEquals(sizeOfHand1, 2);
		assertEquals(sizeOfHand2, 3);
	}
}
