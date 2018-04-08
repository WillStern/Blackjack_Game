package GUI;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application
{
	
	
	public static void main(String[] args) 
	{
		Application.launch(args);
	}

	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws IOException
	{
		this.primaryStage = primaryStage;
		mainWindow();
	}
	
	public void gameWindow() throws IOException{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("DisplayBlackjack.fxml"));
		SplitPane pane = loader.load();
	
		
		GameWindowController gameWindowController = loader.getController();
		
		Scene scene = new Scene(pane);
		// Set the Scene to the Stage
		primaryStage.setScene(scene);
		// Set the Title to the Stage
		//primaryStage.setTitle("Blackjack");
		// Display the Stage
		primaryStage.show();
		
	}
	
	public void mainWindow() throws IOException{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("StartMenuDisplay.fxml"));
		AnchorPane pane = loader.load();
	
		
		StartMenuController startMenuController  = loader.getController();
		
		Scene scene = new Scene(pane);
		// Set the Scene to the Stage
		primaryStage.setScene(scene);
		// Set the Title to the Stage
		//primaryStage.setTitle("Blackjack");
		// Display the Stage
		primaryStage.show();
		
	}
}