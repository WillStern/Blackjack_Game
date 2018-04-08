package main;

public class HardStrategy {
	
	
	private static boolean HIT = true;
	private static boolean STAND = false;
	

	/*
	 * this strategy stands on 17 and hits on 16 like a professional dealer
	 * @
	 */
	public boolean play(Hand hand){
		if(hand.sumCards() < 17){
			return HIT;
		}
		else{
			return STAND;
		}
	}
}

