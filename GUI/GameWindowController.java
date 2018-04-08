package GUI;
import java.io.IOException;

import DataBase.playerDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Blackjack;
import main.Deck;
import main.Player;
import main.PlayingCard;

public class GameWindowController 
{
	
	private Player current_player;
	private Main main;
	private int num_decks = 1;
	private boolean card_counter_activated = false;
	private String dealer_difficulty = "Easy";
	int playerID; 
	
	

	private Blackjack blackjackGame;
	private boolean newRound;
	private playerDatabase database = new playerDatabase(); 
	
	@FXML private ImageView player_card_1;
	@FXML private ImageView player_card_2;
	@FXML private ImageView player_card_3;
	@FXML private ImageView player_card_4;
	@FXML private ImageView player_card_5;
	@FXML private ImageView dealer_card_1;
	@FXML private ImageView dealer_card_2;
	@FXML private ImageView dealer_card_3;
	@FXML private ImageView dealer_card_4;
	@FXML private ImageView dealer_card_5;

	@FXML private TextField inputBet_textField;
	@FXML private TextField mainDisplay_textField;
	@FXML private TextField totalCash_textField;
	@FXML private TextField playerScore_textField;
	
	//getters and setters:
	
	public void setCurrentPlayerID(int id){
		playerID = id;
		
	}
	public int getNum_decks() {
		return num_decks;
	}
	public void setNum_decks(int num_decks) {
		this.num_decks = num_decks;
	}
	public boolean isCard_counter_activated() {
		return card_counter_activated;
	}
	public void setCard_counter_activated(boolean card_counter_activated) {
		this.card_counter_activated = card_counter_activated;
	}
	public String getDealer_difficulty() {
		return dealer_difficulty;
	}
	public void setDealer_difficulty(String dealer_difficulty) {
		this.dealer_difficulty = dealer_difficulty;
	}
	// Add a public no-args constructor
	public GameWindowController() 
	{
	}
	
	@FXML
	private void initialize() 
	{
		
	
		
	}
	
	@FXML
	public void openMainWindow() throws IOException{
		Stage current_stage = (Stage) mainDisplay_textField.getScene().getWindow();
	    // do what you have to do
	    current_stage.close();
	    
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartMenuDisplay.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(root1));  
        stage.show();
 
	}
	

	@FXML
	public void stand() throws InterruptedException 
	{
		if(newRound){
			mainDisplay_textField.setText("Waiting...");
			blackjackGame.getPlayer(0).removeCards();
			blackjackGame.getPlayer(1).removeCards();
			blackjackGame.dealCards();
			initializeHandImages();
			newRound = false;
			updateScore();
			return;
		}
		System.out.println("Player Stands!");
		//show dealers 1st card
		String card_path = getCardPath(blackjackGame, 1, 0);
		System.out.println(card_path);
		javafx.scene.image.Image dealers_card = new javafx.scene.image.Image(card_path);
		dealer_card_1.setImage(dealers_card);
		//ask dealer to hit (max three hits)
		for(int i=0; i<3; i++){
			if(blackjackGame.askComputerToHit(blackjackGame.getPlayer(1)))
				blackjackGame.getPlayer(1).draw(blackjackGame.getDeck());
		}
		setImages();
		//if dealer busted:
		if(blackjackGame.getPlayer(1).busted()){
			System.out.println("DEALER BUSTED!");
			database.playerWon(current_player);
			mainDisplay_textField.setText("Dealer busted on " + blackjackGame.getPlayer(1).getHand().sumCards() + "!");
			updateScore();
			newRound = true;
			//add winnings to total cash
		}
		else{
			Player human = blackjackGame.getPlayer(0);
			Player dealer = blackjackGame.getPlayer(1);
			int dealer_score = dealer.getHand().sumCards();
			int human_score = human.getHand().sumCards();
			if(dealer_score > human_score){
				//DEALER WINS
				System.out.println("DEALER WINS! " + blackjackGame.getPlayer(1).getHand().sumCards());
				database.playerLost(current_player);
				mainDisplay_textField.setText("Dealer wins with " + blackjackGame.getPlayer(1).getHand().sumCards() + "!");
				updateScore();
				newRound = true;
			}
			else if(dealer_score < human_score){
				//PLAYER WINS
				System.out.println("PLAYER WINS!");
				database.playerWon(current_player);
				mainDisplay_textField.setText("You win with " + blackjackGame.getPlayer(0).getHand().sumCards() + "!");
				updateScore();
				newRound = true;
			}
			else{
				//TIE GOES TO DEALER
				System.out.println("TIE GOES TO DEALER!");
				database.playerLost(current_player);
				mainDisplay_textField.setText("Tie of " + blackjackGame.getPlayer(0).getHand().sumCards() + " goes to dealer!");
				updateScore();
				newRound = true;
			}
		}
	}
	
	
	
	@FXML
	public void placeBet() 
	{
		System.out.println("bet placed ");
		String betAmount = inputBet_textField.getText();
		totalCash_textField.setText(betAmount);
	}
	
	@FXML
	public void takeHit()
	{
		if(blackjackGame.getDeck().size() < 10){
			blackjackGame.getDeck().initializeDeck();
		}
		if(newRound){
			mainDisplay_textField.setText("Waiting...");
			blackjackGame.getPlayer(0).removeCards();
			blackjackGame.getPlayer(1).removeCards();
			blackjackGame.dealCards();
			initializeHandImages();
			newRound = false;
			updateScore();

		}
		else{
			System.out.println("PLAYER HIT!");
			Deck deck = blackjackGame.getDeck();
			PlayingCard card_drawn = deck.draw();
			blackjackGame.getPlayer(0).addCard(card_drawn);
			System.out.println(blackjackGame.getPlayer(0).getHand().toString(true));
			int card_num = blackjackGame.getPlayer(0).numCards();
			String card_path;
			javafx.scene.image.Image card;
			System.out.println(card_num);
			switch(card_num){
			case 3:
				card_path = getCardPath(blackjackGame, 0, 2);
				System.out.println(card_path);
				card = new javafx.scene.image.Image(card_path);
				player_card_3.setImage(card);
				updateScore();
				break;
			case 4:
				card_path = getCardPath(blackjackGame, 0, 3);
				System.out.println(card_path);
				card = new javafx.scene.image.Image(card_path);
				player_card_4.setImage(card);
				updateScore();
				break;
			case 5:
				card_path = getCardPath(blackjackGame, 0, 4);
				System.out.println(card_path);
				card = new javafx.scene.image.Image(card_path);
				player_card_5.setImage(card);
				updateScore();
				break;
			}
		}
		if(blackjackGame.getPlayer(0).busted()){
			//DISPLAY BUSTED
			//show dealers 1st card
			String card_path = getCardPath(blackjackGame, 1, 0);
			javafx.scene.image.Image dealers_card = new javafx.scene.image.Image(card_path);
			dealer_card_1.setImage(dealers_card);
			blackjackGame.getPlayer(1).removeCards();
			System.out.println(card_path);
			System.out.println("PLAYER BUSTED!");
			database.playerLost(current_player);
			//TODO add bets 
			mainDisplay_textField.setText("You busted on " + blackjackGame.getPlayer(0).getHand().sumCards() + "!");
			newRound = true;
			updateScore();
		}
		
	}
	
	//gets the file path for the png of the card desired
	public String getCardPath(Blackjack blackjackGame, int player_index, int card_index){
		String card_suit;
		card_suit = blackjackGame.getPlayer(player_index).getHand().getCard(card_index).getSuit();
		card_suit = card_suit.toLowerCase();
		int card_type;
		card_type = blackjackGame.getPlayer(player_index).getHand().getCard(card_index).getType();
		String card_path = "images/" + card_type + "_of_" + card_suit + ".png";
		return card_path;
	}

	//Displays all cards (shows face down ones)
	public void setImages(){
		String card_path = 	getCardPath(blackjackGame, 0, 0);
		javafx.scene.image.Image first_card = new javafx.scene.image.Image(card_path);
		player_card_1.setImage(first_card);
		card_path = getCardPath(blackjackGame, 0, 1);
		javafx.scene.image.Image second_card = new javafx.scene.image.Image(card_path);
		player_card_2.setImage(second_card);
		if(blackjackGame.getPlayer(0).numCards() > 2){
			card_path = getCardPath(blackjackGame, 0, 2);
			javafx.scene.image.Image third_card = new javafx.scene.image.Image(card_path);
			player_card_3.setImage(third_card);
		}
		if(blackjackGame.getPlayer(0).numCards() > 3){
			card_path = getCardPath(blackjackGame, 0, 3);
			javafx.scene.image.Image forth_card = new javafx.scene.image.Image(card_path);
			player_card_4.setImage(forth_card);
		}
		if(blackjackGame.getPlayer(0).numCards() > 4){
			card_path = getCardPath(blackjackGame, 0, 4);
			javafx.scene.image.Image fifth_card = new javafx.scene.image.Image(card_path);
			player_card_5.setImage(fifth_card);
		}
		card_path = getCardPath(blackjackGame, 1, 0);
		javafx.scene.image.Image dealers_first_card = new javafx.scene.image.Image(card_path);
		dealer_card_1.setImage(dealers_first_card);
		card_path = getCardPath(blackjackGame, 1, 1);
		javafx.scene.image.Image dealers_second_card = new javafx.scene.image.Image(card_path);
		dealer_card_2.setImage(dealers_second_card);
		if(blackjackGame.getPlayer(1).numCards() > 2){
			card_path = getCardPath(blackjackGame, 1, 2);
			javafx.scene.image.Image third_card = new javafx.scene.image.Image(card_path);
			dealer_card_3.setImage(third_card);
		}
		if(blackjackGame.getPlayer(1).numCards() > 3){
			card_path = getCardPath(blackjackGame, 1, 3);
			javafx.scene.image.Image forth_card = new javafx.scene.image.Image(card_path);
			dealer_card_4.setImage(forth_card);
		}
		if(blackjackGame.getPlayer(1).numCards() > 4){
			card_path = getCardPath(blackjackGame, 1, 4);
			javafx.scene.image.Image fifth_card = new javafx.scene.image.Image(card_path);
			dealer_card_5.setImage(fifth_card);
		}
	}
	
	//Displays cards as they should be in beginning of round
	public void initializeHandImages(){
		dealer_card_3.setImage(null);
		dealer_card_4.setImage(null);
		dealer_card_5.setImage(null);
		player_card_3.setImage(null);
		player_card_4.setImage(null);
		player_card_5.setImage(null);
		String card_path = 	getCardPath(blackjackGame, 0, 0);
		javafx.scene.image.Image first_card = new javafx.scene.image.Image(card_path);
		player_card_1.setImage(first_card);
		card_path = getCardPath(blackjackGame, 0, 1);
		javafx.scene.image.Image second_card = new javafx.scene.image.Image(card_path);
		player_card_2.setImage(second_card);
		javafx.scene.image.Image dealers_first_card = new javafx.scene.image.Image("images/back_of_card.png");
		dealer_card_1.setImage(dealers_first_card);
		card_path = getCardPath(blackjackGame, 1, 1);
		javafx.scene.image.Image dealers_second_card = new javafx.scene.image.Image(card_path);
		dealer_card_2.setImage(dealers_second_card);
	}
	
	public void setMain(Main gui_Loader) {
		main = gui_Loader;
		
	}
	public void updateScore(){
		int player_score = blackjackGame.getPlayer(0).getHand().sumCards();
		String playerScore = String.valueOf(player_score);
		playerScore_textField.setText(playerScore);
	}

	public void initializeData(int _num_decks, boolean _card_counter_activated, String difficulty, Player _player) {
		num_decks = _num_decks;
		//current_player.setID(playerID);
		card_counter_activated = _card_counter_activated;
		dealer_difficulty = difficulty;
		current_player = _player;
		System.out.println("DATA:");
        System.out.println("Decks: " + num_decks);
        System.out.println("Difficulty: " + dealer_difficulty);
        System.out.println("Card Counter: " + card_counter_activated);
        System.out.println("PLayer  : " + current_player);
		blackjackGame = new Blackjack(_num_decks);
		blackjackGame.setPlayer(current_player);
		if(dealer_difficulty.equals(null)){
			blackjackGame.getPlayer(1).setStrategy(1);
		}
		else if(dealer_difficulty.equals("Easy"))
			blackjackGame.getPlayer(1).setStrategy(1);
		else if(dealer_difficulty.equals("Medium"))
			blackjackGame.getPlayer(1).setStrategy(2);
		else if(dealer_difficulty.equals("Hard"))
			blackjackGame.getPlayer(1).setStrategy(3);
		
		//Set inital hand:
		if(num_decks == 1)
		mainDisplay_textField.setText("Playing with "+_num_decks+" deck");
		else
		mainDisplay_textField.setText("Playing with "+_num_decks+" decks");
		blackjackGame.dealCards();
		initializeHandImages();
	}
}