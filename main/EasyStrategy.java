package main;

public class EasyStrategy {
	private static boolean HIT = true;
	private static boolean STAND = false;

	
	/*
	 * hits on 12 and stands on 13 (not good strategy)
	 */
	public boolean play(Hand hand){
		if(hand.sumCards() < 13){
			return HIT;
		}
		else{
			return STAND;
		}
	}
	
	
}