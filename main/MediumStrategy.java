package main;

public class MediumStrategy implements Strategy {
	
	private static boolean HIT = true;
	private static boolean STAND = false;
	
	/*
	 * hits on 15 and stands on 16
	 */
	public boolean play(Hand hand){
		if(hand.sumCards() < 16){
			return HIT;
		}
		else{
			return STAND;
		}
	}
	
}
