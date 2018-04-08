package main;

import java.util.ArrayList;

public class Table {
	
	static int STAND = 2;
	static int HIT = 1;
	
	private ArrayList<Player> players;
	private Deck deck;
	private Player dealer = new Player("Dealer");
	private int num_decks = 1;
	private int difficulty = 1;
	
	
	
	
	
	//Return string of the name of winner 
	public String returnWinner() {
		
		int player_score = players.get(0).getHand().sumCards();
		int dealer_score = dealer.getHand().sumCards();
		String winner = "no-one";
		if (player_score > dealer_score)
		{
			winner = "Player Won!"; 
			return winner; 
			
		}
		else if ( dealer_score > player_score){
			winner = "House Won "; 
			return winner; 
		}
		else if ( dealer_score == player_score){
			
			winner = "House Won"; 
			return winner; 
		}
		return winner; 
		
	}
	
	public void resetHands(){
		dealer.removeCards();
		players.get(0).removeCards();
		for(int i =0; i< players.size()-1; i++){
			players.get(i).removeCards();
		}
	}
	
	public  void dealCards()
	{
		if (deck.size() == 0)
		{
			System.out.println("No more cards to draw.");
			return;
		}
		dealer.draw(deck);
		dealer.draw(deck);
		for(int i =0; i < players.size(); i++){
			players.get(i).draw(deck);
			players.get(i).draw(deck);
		}
		//CHANGE FOR GUI:
		//intHands(false);
	}

	public void PlayerToHit(Player player){
		player.draw(deck);
			
	}
	
	public void addPlayer(Player player){
		players.add(player); 
	}
	
	public void askComputerToHit() {
		if(dealer.getHand().sumCards() < 15){
			dealer.draw(deck);
		}
		else{
			//STAND
		}
	}
	
	public void printHands(Player player ){
		//print name 
		System.out.println(player.getName());
		System.out.println(dealer.getHand().toString(true));
		//PRINT COMPUTER INITIAL HAND
		
	}
	
	public void initialize(){
		players = new ArrayList<Player>();
		
		//Player dealer = new Player("Computer");
		
	}
	
	public void startRound(){
		
		resetHands();
		deck = new Deck(num_decks);
		dealCards();
	
	
	}
	
	public Player getPlayer(int index ){
		
		return players.get(index);
		
	}
	
	public Player getHouse(){
		return dealer; 
	}
	
	public boolean houseOver(){
		if (dealer.getHand().sumCards() > 21){
			return true; 
		}
		else{return false; }
	}
	
	public boolean playerOver(Player player){
		if (player.getHand().sumCards() > 21){
			return true; 
		}
		else{return false; }
	}
	
	
		

}
