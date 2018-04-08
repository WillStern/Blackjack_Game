package main;


@SuppressWarnings("rawtypes")
public class PlayingCard implements Comparable {
	
	public static int MIN = 1;
	public static int MAX = 13; 
	public static int INVALID = -1;
	
	public static int ACE = 1;
	public static int JACK = 11;
	public static int QUEEN = 12;
	public static int KING = 13;
	
	public static String HEARTS = "Hearts";
	public static String CLUBS = "Clubs";
	public static String DIAMONDS = "Diamonds";
	public static String SPADES = "Spades";
	
	private static String types[] = {null, "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
		"Nine", "Ten", "Jack", "Queen", "King"};
	
	private String suit;
	private int type;

	public PlayingCard(String suit, int type) {
		super();
		this.suit = suit;
		this.type = type;
	}
	
	/**
	 * Gives a nice readable name
	 * @param value - the integer value of the card
	 * @return - a spelled out name
	 */
	public static String getName(int value) {
		return types[value];
	}
	
	/**
	 * Given a name, or partially spelled out name, gets the value
	 * @param name - a string such as "ace" or "aces"
	 * @return - the integer value or INVALID
	 */
	public static int determineType(String name)
	{
		for (int i=1; i < types.length; i ++)
		{
			if (name.toLowerCase().contains(types[i].toLowerCase()))
			{
				return i;
			}	
		}
		return INVALID;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	

	
	/**
	 * This assumes that an Ace will have a value of 1
	 * @return
	 */
	public int value()
	{
		return type;
	}
	

	public boolean equalsType(int type) {
		return this.type == type;
	}
	
	/**
	 * A proper equals method for the playing card. Notice that it takes in an Object as the parameter
	 */
	public boolean equals(Object o) {
		if (o == null)
		{
			return false;
		}
		if (o.getClass() == this.getClass())
		{
			PlayingCard card = (PlayingCard) o;
			if (card.suit.equals(this.suit) && (card.type == this.type))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Compares two playing cards. Again, notice that it takes in an Object.
	 */
	public int compareTo(Object o) {
		if ((o == null) || (!(o instanceof PlayingCard)))
		{
			return -100; //kludge
		}
		PlayingCard card = (PlayingCard) o;
		if (this.value() < card.value())
		{
			return -1;
		}
		else if (this.value() == card.value())
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}

	/**
	 * Returns a string version of the card to use when printing
	 */
	public String toString()
	{
		return types[type] + " of " + suit;
	}
	
}
