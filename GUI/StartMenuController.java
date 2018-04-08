package GUI;


import java.io.IOException;

import DataBase.playerDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Player;

public class StartMenuController {

	private ObservableList<String> difficulties = FXCollections.observableArrayList("Easy", "Medium", "Hard");

	
	@FXML private Button startGameButton; 
	@FXML private Button playerAnalyticsButton;
	@FXML private Button switchPlayerButton;
	@FXML private Button settingButton; 
	
	@FXML private Slider numberOfDecks_slider; 
	@FXML private RadioButton cardCounter_button; 
	@FXML private ChoiceBox<String> difficulty_choiceBox;
	@FXML private Text ratioTextField, nameTextField, chipsTextField; 
	


	private int number_of_decks;
	private boolean card_counter_activated;
	private String difficulty; 
	private playerDatabase database; 
	int currentIndex; 
	
	private Player player; 
	
	public StartMenuController() 
	{
	}
	
	@FXML
	private void initialize() 
	{
		difficulty_choiceBox.setItems(difficulties);
		
		database = new playerDatabase();
		String firstPlayerName = database.getAllNames().get(0);
		int firstPlayerID = database.getAllIds().get(0);
		player = new Player(firstPlayerName);
		player.setID(firstPlayerID);
		currentIndex = 0;
		difficulty_choiceBox.setValue("Easy");
		showAnalytics();
		
		
		
		
	}
	
	@FXML
	public void close(){
		Stage current_stage = (Stage) startGameButton.getScene().getWindow();
	    // do what you have to do
	    current_stage.close();
	}
	
	@FXML
	public void openBlackjackWindow() throws IOException{
	    Stage current_stage = (Stage) startGameButton.getScene().getWindow();
	    // do what you have to do
	    current_stage.close();
		
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplayBlackjack.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        
        
        number_of_decks = (int) numberOfDecks_slider.getValue();
        card_counter_activated = !cardCounter_button.isDisabled();
        String card_counter_status = "Off";
        if(card_counter_activated)
        card_counter_status = "On";
        difficulty = (String) difficulty_choiceBox.getValue();
        
        GameWindowController controller = 
        		fxmlLoader.<GameWindowController>getController();
        //controller.setCurrentPlayerID(player.getID());
        System.out.println("Attempting to initialize data:");
        System.out.println("Decks: " + number_of_decks);
        System.out.println("Difficulty: " + difficulty);
        System.out.println("Card Counter: " + card_counter_status);
        controller.initializeData(number_of_decks, card_counter_activated, difficulty, player);
        
        Stage new_stage = new Stage();
        new_stage.initModality(Modality.APPLICATION_MODAL);
        new_stage.initStyle(StageStyle.UNDECORATED);
        new_stage.setTitle("Blackjack");
        new_stage.setScene(new Scene(root1));  
        new_stage.show();
	}
	
	@FXML 
	public void showAnalytics(){
		int id = player.getID();
		String name = database.getName(id);
		int chips = database.getChips(id);
		float ratio = database.getRatio(id);
		/*if (database.getGamesPlayed(id) != 0 ){
			
		
		ratio = database.getGamesPlayed(id) - database.getLosses(id) / database.getGamesPlayed(id);
		
		
		}
		else { ratio = 0;}
		*/
		
		ratioTextField.setText(String.valueOf(ratio));
		nameTextField.setText(name);
		chipsTextField.setText(String.valueOf(chips));
		
		
		
	}
	
	@FXML void nextPlayer(){
		currentIndex += 1; 
		currentIndex = currentIndex % database.getAllIds().size();
		int NewID = database.getAllIds().get(currentIndex);
		System.out.println(NewID+ " : is new ID ");
		String name = database.getName(NewID);
		
		player = new Player(name);
		player.setID(NewID);
		showAnalytics();
	}
}
