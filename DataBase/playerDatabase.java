package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.w3c.dom.NameList;

import main.Player;


//This class connects to the database table playerInfo which i have created. this allows us to update and create new players and log their analytics 
//info saved in database 
// : ID, name, games PLayed, win ratio, games lost 
public class playerDatabase {
	
	String sqlInsert, strSelect,strUpdate;
	Connection conn;
	Statement stmt; 
	Integer playerID;
	String name; 
	int losses; 
	int gamesPlayed;
	float ratio; 
	
	Integer port = 3306;
	
	int chips; 
	
	/**
	 * Constructor that initializes connection to database server 
	 */
	
	public playerDatabase(){
		try {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:" + port.toString() + "/analytics?user=root&password=root");
		stmt = conn.createStatement();
	
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Creates a new table called CasinoTableInfo in your analytics database 
	 */
	public void  createNewTable(){
		try {
			
			//create our new table
			String sql = "create table casinotableinfo ( " +
					"id int NOT NULL AUTO_INCREMENT, " +
					"name varchar(50), " +
					"gamesPlayed int, " +
					"winningPercentage float, " +
					"losses int, " +
					"pokerChips int, " + 
					"primary key (id));";
			stmt.execute(sql);
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		

		
	}
	
	/**
	 * Add a new player to the database 
	 * @param newPlayer
	 */
	public void addNewPlayer(Player newPlayer){
		
		String name = newPlayer.getName();
		//TODO add the charlie verson of ID numbers 
		playerID = newPlayer.getID();
		
		try {
			
			
		sqlInsert = "insert into casinotableinfo " // need a space
				+ "values (" + playerID.toString() + ", '" + name + "', 0, 0, 0, 0)";
		System.out.println("The SQL query is: " + sqlInsert);  // Echo for debugging
		int countInserted = stmt.executeUpdate(sqlInsert);
		System.out.println(countInserted + " records inserted.\n");
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	
	

	
	/**
	 * Returns arraylist of player names 
	 * @return
	 */
	public ArrayList<String> getAllNames(){
		
		ArrayList<String> nameList = new ArrayList<String>();
		
		try {
			
			String strSelect = "select * from casinotableinfo";
			System.out.println("The SQL query is: " + strSelect);  // Echo For debugging
			ResultSet rset = stmt.executeQuery(strSelect);
			while(rset.next()) {   // Move the cursor to the next row
				nameList.add(rset.getString("name"));
				
			}
						
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		 return nameList;	
		
		
	}
	
	/**
	 * Returns arraylist of all ID in order they are stored in the database 
	 * @return
	 */
	public ArrayList<Integer> getAllIds(){
		
		ArrayList<Integer> idList = new ArrayList<Integer>();
		
		try {
			
			String strSelect = "select * from casinotableinfo";
			System.out.println("The SQL query is: " + strSelect);  // Echo For debugging
			ResultSet rset = stmt.executeQuery(strSelect);
			while(rset.next()) {   // Move the cursor to the next row
				idList.add(rset.getInt("id"));
				
			}
						
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		 return idList;	
		
		
	}
	
	

	


	/**
	 * Returns the number of games played 
	 * @param id
	 * @return
	 */
	public int getGamesPlayed(int id){
		
		try {
		
		strSelect = "select gamesPlayed from casinotableinfo where id = " + id;
		System.out.println("The SQL query is: " + strSelect);
		System.out.println();
		
		ResultSet rset = stmt.executeQuery(strSelect);
		
		//Step 4: Process the result set by stepping through using next().
		//For each row, retrieve the contents  using the column names
		System.out.println("The records selected are:");;
		int rowCount = 0;
		while (rset.next()) {
			gamesPlayed = rset.getInt("gamesPlayed");
			System.out.println("Losses : " + losses);;
			++rowCount;
		}
		
		System.out.println("Total number of records = " + rowCount);
		
	}catch(SQLException ex) {
		ex.printStackTrace();
	}
		
		return gamesPlayed; 

	
}
	
	public int getLosses(int id){
		
		try {
		
		strSelect = "select losses from casinotableinfo where id = " + id;
		System.out.println("The SQL query is: " + strSelect);
		System.out.println();
		
		ResultSet rset = stmt.executeQuery(strSelect);
		
		//Step 4: Process the result set by stepping through using next().
		//For each row, retrieve the contents  using the column names
		System.out.println("The records selected are:");;
		int rowCount = 0;
		while (rset.next()) {
			losses = rset.getInt("losses");
			System.out.println("Losses : " + losses);;
			++rowCount;
		}
		
		System.out.println("Total number of records = " + rowCount);
		
	}catch(SQLException ex) {
		ex.printStackTrace();
	}
		
		return losses; 

	
}
	
	
public float getRatio(int id){
		
		try {
		
		strSelect = "select winningPercentage from casinotableinfo where id = " + id;
		System.out.println("The SQL query is: " + strSelect);
		System.out.println();
		
		ResultSet rset = stmt.executeQuery(strSelect);
		
		//Step 4: Process the result set by stepping through using next().
		//For each row, retrieve the contents  using the column names
		System.out.println("The records selected are:");;
		int rowCount = 0;
		while (rset.next()) {
			ratio = rset.getFloat("winningPercentage");
			System.out.println("Ratio : " + ratio);;
			++rowCount;
		}
		
		System.out.println("Total number of records = " + rowCount);
		
	}catch(SQLException ex) {
		ex.printStackTrace();
	}
		
		return ratio; 

	
}
	
	
	/**
	 * returns the name of a player in the database relating to the ID given 
	 * @param id
	 * @return
	 */
	public String getName(int id){
		
			try {
			
			strSelect = "select name from casinotableinfo where id = " + id;
			System.out.println("The SQL query is: " + strSelect);
			System.out.println();
			
			ResultSet rset = stmt.executeQuery(strSelect);
			
			//Step 4: Process the result set by stepping through using next().
			//For each row, retrieve the contents  using the column names
			System.out.println("The records selected are:");;
			int rowCount = 0;
			while (rset.next()) {
				name = rset.getString("name");
				System.out.println("Name  : " + name);;
				++rowCount;
			}
			
			System.out.println("Total number of records = " + rowCount);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
			
			return name; 
	
		
	}
	
	
	
	/**
	 * Updates player info in database to include that he won his most recent game 
	 * @param player
	 */
	public void playerWon(Player player){
		
		playerID = player.getID();
		
		try {
			strUpdate = "update casinotableinfo set gamesPlayed = gamesPlayed + 1, winningPercentage = (gamesPlayed - losses)/gamesPlayed where id = "+ playerID;
			System.out.println("The SQL query is: " + strUpdate);  // Echo for debugging
			int countUpdated = stmt.executeUpdate(strUpdate);
			System.out.println(countUpdated + " records affected.");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Updates player info in database to include that he lost his most recent game 
	 * @param player
	 */
	public void playerLost(Player player){
		playerID = player.getID();
		
		try {
			strUpdate = "update casinotableinfo set gamesPlayed = gamesPlayed + 1, winningPercentage = (gamesPlayed - (losses + 1))/gamesPlayed, losses = losses + 1 where id = "+ playerID;
			System.out.println("The SQL query is: " + strUpdate);  // Echo for debugging
			int countUpdated = stmt.executeUpdate(strUpdate);
			System.out.println(countUpdated + " records affected.");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Updates the database that this player lost this amount of chips 
	 * @param player
	 * @param bet
	 */
	public void lostBet(Player player, int bet){
		playerID = player.getID();
		
		try {
			strUpdate = "update casinotableinfo set pokerChips = pokerChips - " + bet + " where id = "+ playerID;
			System.out.println("The SQL query is: " + strUpdate);  // Echo for debugging
			int countUpdated = stmt.executeUpdate(strUpdate);
			System.out.println(countUpdated + " records affected.");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * will update the database that this player won this amount of chips 
	 * @param player
	 * @param bet
	 */
	public void wonBet(Player player, int bet){
		playerID = player.getID();
		
		try {
			strUpdate = "update casinotableinfo set pokerChips = pokerChips + " + bet + " where id = "+ playerID;
			System.out.println("The SQL query is: " + strUpdate);  // Echo for debugging
			int countUpdated = stmt.executeUpdate(strUpdate);
			System.out.println(countUpdated + " records affected.");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	
	/**
	 * Checks to see if the player has enough chips to bet 
	 * @param player
	 * @param bet
	 * @return boolean true if player can bet 
	 */
	public boolean canBet(Player player, int bet){
		
	playerID = player.getID();
	chips = 0; 

		try {
			
			strSelect = "select pokerChips from casinotableinfo where id = " + playerID;
			System.out.println("The SQL query is: " + strSelect);
			System.out.println();
			
			ResultSet rset = stmt.executeQuery(strSelect);
			
			//Step 4: Process the result set by stepping through using next().
			//For each row, retrieve the contents  using the column names
			System.out.println("The records selected are:");;
			int rowCount = 0;
			while (rset.next()) {
				chips = rset.getInt("pokerChips");
				System.out.println("Poker chips : " + chips);;
				++rowCount;
			}
			
			System.out.println("Total number of records = " + rowCount);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		
		//see if the chips are more than the bet 
		if ( bet <=  chips){
			return true; 
		}
		else{
			return false; 
		}
			
	}
	
	/**
	 * Returns the number of chips the player currently has in the database 
	 * @param player
	 * @return
	 */
	public int getChips(int id){
		
		playerID = id;
		
			try {
			
			strSelect = "select pokerChips from casinotableinfo where id = " + playerID;
			System.out.println("The SQL query is: " + strSelect);
			System.out.println();
			
			ResultSet rset = stmt.executeQuery(strSelect);
			
			//Step 4: Process the result set by stepping through using next().
			//For each row, retrieve the contents  using the column names
			System.out.println("The records selected are:");;
			int rowCount = 0;
			while (rset.next()) {
				chips = rset.getInt("pokerChips");
				System.out.println("Poker chips : " + chips);;
				++rowCount;
			}
			
			System.out.println("Total number of records = " + rowCount);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		
		return chips; 
	}
	
	

}
