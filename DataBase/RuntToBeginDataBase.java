package DataBase;
import java.sql.*;   

//this class is used to test the functionallity of the playerDatabase class 
import main.Player;


//Used this class to confirm that the database was working the way I hoped 

public class RuntToBeginDataBase {    // Save as "JdbcUpdateTest.java"
	public static void main(String[] args) {
		
		Player newPlayer = new Player("Janet"); 
		newPlayer.setID(10005);
		Player newPlayer1 = new Player("Drew"); 
		newPlayer.setID(10006);
		Player newPlayer2 = new Player("Calvin"); 
		newPlayer.setID(10007);
		Player newPlayer3 = new Player("Will"); 
		newPlayer.setID(10008);
		Player newPlayer4 = new Player("Harry..."); 
		newPlayer.setID(10009);
		playerDatabase database = new playerDatabase(); 
		database.createNewTable();
		
		
		//THis will use the player ID to edit the database 
		database.addNewPlayer(newPlayer);
		database.addNewPlayer(newPlayer1);
		database.addNewPlayer(newPlayer2);
		database.addNewPlayer(newPlayer3);
		database.addNewPlayer(newPlayer4);
		
		System.out.println(database.getAllNames().toString());
		System.out.println(database.getAllIds().toString());
		
	//	database.playerWon(newPlayer);
	//	database.wonBet(newPlayer, 100);
	//	database.playerLost(newPlayer);
	//	database.lostBet(newPlayer, 50);
		
	//	boolean AbleToBet; 
	//	AbleToBet = database.canBet(newPlayer, 51);
	//	if (AbleToBet){
	//		System.out.println("Able to bet 51");
	//	}
	//	else{System.out.println("Not able to bet 50");}
		
				
		
		
		
	}
	
}