package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Blackjack {
	static int STAND = 2;
	static int HIT = 1;
	
	private static ArrayList<Player> players;
	private static Deck deck;
	private static Player dealer = new Player("Dealer");
	private static int num_decks = 1;
	private static int difficulty = 1;
	
	
	public Blackjack(int number_of_decks){
		players = new ArrayList<>();
		deck = new Deck(number_of_decks);
		Player human = new Player("Human");
		// Human player will always be players.get(0)
		players.add(human); 
		// dealer  will always be players.get(1)
		players.add(dealer); 
	}
	
	public static void addPlayers(ArrayList<Player> addedPlayers){
		players.addAll(addedPlayers);
	}
	
	public static void addPlayer(Player player){
		players.add(player);
	}
	
	public static Deck getDeck(){
		return deck;
	}
	
	public static void setPlayer(Player player){
		players.set(0, player);
	}
	
	public static Player getPlayer(int index){
		return players.get(index);
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////
	public static int getStrategyNumber(Player computer) {
		boolean valid = false;
		Scanner scanner_input = new Scanner(System.in);
		int user_input = 1;
		while (!valid)
		{
			System.out.println("Choose strategy:  ");
			System.out.println("	1. Easy");
			System.out.println("    2. Medium");
			System.out.println("    3. Hard");
		
		
			try {
				user_input = scanner_input.nextInt();
				
				scanner_input.nextLine();
				if ((user_input > 0) && (user_input <= 3))
				{
					valid = true;
				}
			}
			//this will catch the mismatch and prevent the error
			catch(InputMismatchException ex)
			{
				//still need to gobble up the end of line
				scanner_input.nextLine();
			}
			if (!valid)
			{
				System.out.println("Invalid entry -- enter a number between 1 and 4");
			}
		}
		return user_input;
	}
	
	
	/*
	 * get valid integer user input for bet
	 */
	public static int askPlayerForBet(Player player){
		boolean valid = false;
		System.out.println("How much would you like to bet?");
		Scanner in = new Scanner(System.in);
		int bet = 5;
		while (!valid)
		{
		
			try {
				bet = in.nextInt();
				
				in.nextLine();
				if (bet >= 5)
				{
					valid = true;
				}
			}
			//this will catch the mismatch and prevent the error
			catch(InputMismatchException ex)
			{
				//still need to gobble up the end of line
				in.nextLine();
			}
			if (!valid)
			{
				System.out.println("The minimum bet is $5.  Please enter at least this amount.");
			}
		}
		return bet;
	}
	
	/** 
	 * Asks the for the new Player's name and call addPlayer
	 * 
	 */
	public static int selectNumberOfPlayers(){
		boolean valid = false;
		System.out.println("How many computer players:");
		Scanner in = new Scanner(System.in);
		int selection = 1;
		while (!valid)
		{

			try {
				selection = in.nextInt();

				in.nextLine();
				if (selection > 0)
				{
					valid = true;
				}
			}
			//this will catch the mismatch and prevent the error
			catch(InputMismatchException ex)
			{
				//still need to gobble up the end of line
				in.nextLine();
			}
			if (!valid)
			{
				System.out.println("Invalid entry");
			}
		}
		
		return selection;
	};
	
	/** 
	 * Asks the player if they want to hit or stand 
	 * will change players hand to reflect decision
	 */
	public static boolean askPlayerToHit(Player player){
		
		boolean valid = false;
		Scanner in = new Scanner(System.in);
		int selection = 1;
		while (!valid)
		{
			System.out.println("Would you like to Hit or Stand : ");
			System.out.println("1: Hit");
			System.out.println("2: Stand");

			try {
				selection = in.nextInt();

				in.nextLine();
				if ((selection > 0) && (selection <= 2))
				{
					valid = true;
				}
			}
			//this will catch the mismatch and prevent the error
			catch(InputMismatchException ex)
			{
				//still need to gobble up the end of line
				in.nextLine();
			}
			if (!valid)
			{
				System.out.println("Invalid entry, standing...");
				return true;
			}
		}
		
		if ( selection == HIT)
		{
			player.draw(deck);
			return false;
		
		}
		else if (selection == STAND){
			//nothing
			return true;
		}
		return false;	
	}
	
	public static boolean askComputerToHit(Player player) {
		return player.playStrategy();
	}
	
	
	/**
	 * deals 2 cards to all players
	 */
	public static void resetHands(){
		dealer.removeCards();
		players.get(0).removeCards();
		for(int i =0; i< players.size()-1; i++){
			players.get(i).removeCards();
		}
	}
	
	public static void dealCards()
	{
		if (deck.size() == 0)
		{
			System.out.println("No more cards to draw.");
			return;
		}
		
		for(int i =0; i < players.size(); i++){
			players.get(i).draw(deck);
			players.get(i).draw(deck);
		}
		//CHANGE FOR GUI:
		printHands(false);
	}
	
	public static void printHands(boolean showAll){
		System.out.println("--------------------------------");
		//PRINT DEALER HAND
		System.out.println("Dealer:");
		System.out.println(dealer.getHand().toString(showAll));
		//PRINT COMPUTER INITIAL HAND
		for(int i =1; i < players.size(); i++){
			System.out.println(players.get(i).getName() + ":");
			System.out.println(players.get(i).getHand().toString(showAll));
		}
		//PRINT HUMAN HAND
		System.out.println("Your hand:");
		System.out.println(players.get(0).getHand().toString(true));
		System.out.println("--------------------------------");
	}
	
	private static void playerMenu()
	{
		boolean valid = false;
		Scanner in = new Scanner(System.in);
		int selection = 1;
		while (!valid)
		{
			System.out.println("Choose the player option: ");
			System.out.println("1: Start Round ");
			System.out.println("2: Select Number of Players");
			System.out.println("3: View Player Analytics ");
			System.out.println("4: Select Difficulty");
			System.out.println("5: Number of Decks");
			 
			System.out.print("> ");
			try {
				selection = in.nextInt();

				in.nextLine();
				if ((selection > 0) && (selection <= 5))
				{
					valid = true;
				}
			}
			//this will catch the mismatch and prevent the error
			catch(InputMismatchException ex)
			{
				//still need to gobble up the end of line
				in.nextLine();
			}
			if (!valid)
			{
				System.out.println("Invalid entry -- enter a number between 1 and 3");
				playerMenu();
			}
			
		}
		switch(selection){
		case 1:
			//START ROUND
			resetHands();
			deck = new Deck(num_decks);
			dealCards();
			Player userPlayer = players.get(0);
			//This needs to get printed somewhere
			int bet = askPlayerForBet(userPlayer);
			boolean busted = false;
			boolean dealerBusted = false;
			boolean playerStands = false;
			while(!busted && !dealerBusted && !playerStands){
				playerStands = askPlayerToHit(userPlayer);
				if(playerStands){
					askComputerToHit(dealer);
					printHands(true);
					boolean won = checkWinner();
					userPlayer.updatePokerChips(bet, won);
					break;
				}
				for(int i = 1; i < players.size()-1; i++){
					askComputerToHit(players.get(i));
				}
				askComputerToHit(dealer);
				printHands(false);
				busted = userPlayer.busted();
				if(busted){
					printHands(true);
					System.out.println("--------------------------------");
					System.out.println("You busted on " + players.get(0).getHand().sumCards());
					System.out.println("--------------------------------");

				}
				dealerBusted = dealer.busted();
				if(dealerBusted){
					printHands(true);
					System.out.println("--------------------------------");

					System.out.println("Dealer busted on " + dealer.getHand().sumCards() +  ". You win!");
					System.out.println("--------------------------------");

				}
			}
			playerMenu();
		case 2:
			//SELECT NUMBER OF PLAYERS
			int numberOfPlayers = selectNumberOfPlayers();
			for(int i=0; i<numberOfPlayers; i++){
				String comp_name = "Computer " + (i+1);
				Player computer = new Player(comp_name);
				players.add(computer);
			}
			playerMenu();
		case 3:
			// GAME STATS
		case 4:
			// DIFFICULTY
			selectDifficulty();
			playerMenu();
		case 5:
			// NUMBER OF DECKS
			num_decks = selectNumberOfDecks();
			playerMenu();
		}
	}

	

	private static boolean checkWinner() {
		System.out.println("--------------------------------");
		boolean userWon;
		int player_score = players.get(0).getHand().sumCards();
		int dealer_score = dealer.getHand().sumCards();
		if(players.get(0).getHand().hasBlackjack()){
			System.out.println("Blackjack, You win!");
			userWon = true;
		}
		else if(dealer_score > player_score){
			System.out.println("Dealer: " + dealer_score);
			System.out.println("You: " + player_score);
			System.out.println("You lose!");
			userWon = false;
		}
		else if(dealer_score < player_score){
			System.out.println("Dealer: " + dealer_score);
			System.out.println("You: " + player_score);
			System.out.println("You win!");
			userWon = true;
		}
		else{
			System.out.println("Dealer: " + dealer_score);
			System.out.println("You: " + player_score);
			System.out.println("Tie goes to the dealer, you lose!");
			userWon = false;
		}
		System.out.println("--------------------------------");
		return userWon;
	}

	private static void selectDifficulty() {
		int strategyNumber = getStrategyNumber(dealer);
		dealer.setStrategy(strategyNumber);
	}

	
	private static int selectNumberOfDecks() {
		boolean valid = false;
		Scanner in = new Scanner(System.in);
		int selection = 1;
		while (!valid)
		{
			System.out.println("Choose the number of decks: ");
			System.out.print("> ");
			try {
				selection = in.nextInt();

				in.nextLine();
				if ((selection > 0) && (selection <= 6))
				{
					valid = true;
				}
			}
			//this will catch the mismatch and prevent the error
			catch(InputMismatchException ex)
			{
				//still need to gobble up the end of line
				in.nextLine();
			}
			if (!valid)
			{
				System.out.println("Invalid entry -- enter a number between 1 and 6");
				playerMenu();
			}
		}
		return selection;
	}


	
	public void print(){
		for(int i=1; i<players.size(); i++){
			System.out.println(players.get(i));
		}
	}

	public static void main(String[] args){
		
		//playerMenu();

		
		
	}

	public void setDeck(Deck _deck) {
		deck = _deck;
	}
}
