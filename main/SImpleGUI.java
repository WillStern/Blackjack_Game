package main;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;
import java.lang.Integer;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.File;
import java.lang.Integer;
import java.util.ArrayList;


public class SImpleGUI extends JFrame implements ActionListener
{  
   JPanel myPanel;
	JButton start; // button to search to find best path
	JButton stand;
	JLabel myLabel;
   JLabel title;
   JLabel opponentTitle;
   JLabel playerTitle;
   JTextArea opponentHand;
   JTextArea playerHand;
   JTextArea myArrival;
   JButton hit;
   JTextField myTime;
  
   Table table = new Table();
   Player newPlayer = new Player("Player");
   int input;
  
 

	public SImpleGUI()
	{
		setSize(800,800); // set window size
		setDefaultCloseOperation(EXIT_ON_CLOSE); // allow GUI to close
		
		myPanel = new JPanel();
		start = new JButton("Start ");
		start.addActionListener(this);
		start.setBounds(300,100,300,50);
		start.setFont(new Font("Times New Roman", Font.PLAIN, 30));// sets my font to be bigger
      

		hit = new JButton("Hit");
		hit.addActionListener(this);
		hit.setBounds(300,500,100,100);
		hit.setFont(new Font("Times New Roman", Font.PLAIN, 25));// sets my font to be bigger
		
		stand = new JButton("Stand");
		stand.addActionListener(this);
		stand.setBounds(525,500,100,100);
		stand.setFont(new Font("Times New Roman", Font.PLAIN, 25));// sets my font to be bigger

		title = new JLabel("Welcome To BlackJack ");
		title.setBounds(275,25,500,100);
		title.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		playerTitle = new JLabel("Player Cards");
		playerTitle.setBounds(525,200,500,150);
		playerTitle.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		opponentTitle = new JLabel("House Cards " );
		opponentTitle.setBounds(250,200,500,150);
		opponentTitle.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		

      opponentHand = new JTextArea();
      opponentHand.setEditable(false);
      opponentHand.setBounds(250,300, 150,150);
      opponentHand.setFont(new Font("Times New Roman", Font.PLAIN, 20));

                  
      playerHand = new JTextArea();
      playerHand.setEditable(false);
      playerHand.setBounds(525,300, 150,150);
      playerHand.setFont(new Font("Times New Roman", Font.PLAIN, 20));
      
    
		
		myPanel.setLayout(null); // no layout manager this time (thats and improvement i think)
	
		myPanel.add(start);
		myPanel.add(stand);
		myPanel.add(hit);
		myPanel.add(title);
		myPanel.add(opponentTitle);
		myPanel.add(playerTitle);
		myPanel.add(opponentHand);
		myPanel.add(playerHand);
		add(myPanel);
		setVisible(true);			// allow it to be seen		
	}

	public void actionPerformed(ActionEvent evt) 
	{
		if(evt.getSource() == stand)	
       {
         if (!table.playerOver(newPlayer))
         {

			//Computer turn 
        	 table.askComputerToHit(); 
			   playerHand.setText(table.getPlayer(0).getHand().toString(true));
	    	   opponentHand.setText(table.getHouse().getHand().toString(true));
			
         }
         
         playerHand.append("Winner is : \n");
         playerHand.append(table.returnWinner());
         opponentHand.setText(table.getHouse().getHand().toString(true));

			
		
        
      

       }

        
      if(evt.getSource() == hit)	
       {
    	 table.PlayerToHit(newPlayer);
    	   playerHand.setText(table.getPlayer(0).getHand().toString(true));
    	   opponentHand.setText(table.getHouse().getHand().toString(true));
          

      }
       if(evt.getSource() == start)
       { 
    	   //start game 
    	   table.initialize();
    	   table.addPlayer(newPlayer);
    	   table.startRound();
    	   playerHand.setText("Hello");
    	   playerHand.setText(table.getPlayer(0).getHand().toString(true));
    	   opponentHand.setText(table.getHouse().getHand().toString(true));
    	  
    	   
         
    	  // System.out.println(input);
    	        
       }
    }
}