package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mysql.fabric.xmlrpc.base.Data;

import DataBase.playerDatabase;
import main.Player;

public class DataBaseTest {
	playerDatabase database;
	
	//Member of my database 
	
	@Before
	public void initialize(){
		database = new playerDatabase();	
		//Make a new table called casinotableinfo
		database.createNewTable();
		//table should auto increment, but just in case you should set a player ID to be safe if your getting duplicate ID's 
		Player alreadyMember = new Player("Jack");
		alreadyMember.setID(3000);
		
		
	}
	
	/**
	 * Test that the wonBet method works 
	 */
	@Test 
	public void testAddingToPlayerChips(){
		Player newPlayer = new Player("Jon");
		//set ID because im not sure quite how auto increment will work 
		newPlayer.setID(100);
		database.addNewPlayer(newPlayer);
		
		database.playerWon(newPlayer);
		database.wonBet(newPlayer, 1000);
		assertEquals(database.getChips(newPlayer), 1000);
		//should reset the players chips value 
		database.lostBet(newPlayer, 1000);
	}
	
	/**
	 * Tests that the lost bet method works 
	 */
	@Test
	public void testRemovingPlayerChips(){
		
		Player newPlayer = new Player("Trent");
		//set ID because im not sure quite how auto increment will work 
		newPlayer.setID(100);
		database.addNewPlayer(newPlayer);
		
		database.playerWon(newPlayer);
		database.wonBet(newPlayer, 1000);
		
		//should reset the playesrs chips value 
		database.lostBet(newPlayer, 500);
		assertEquals(database.getChips(newPlayer), 500);
		
		//Reset value of chips 
		database.lostBet(newPlayer, 500);
		
		
		
	}

}
