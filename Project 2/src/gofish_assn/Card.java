package gofish_assn;

/**
 * This class implements functions for a Card object.
 * @author Zahra Atzuri and Mircea Antonescu
 * @version 1.0
 */
public class Card {
	
	public enum Suits {club, diamond, heart, spade};
	
	final static int TOP_RANK = 13; //King
	final static int LOW_RANK = 1; //Ace
	
	private int rank;  //1 is an Ace
	private Suits suit;

	/**
	 * This method creates a default Card object.
	 */
	public Card() {
		rank = 1;
		suit = Suits.spade;
	}


	/**
	 * This method creates a Card object.
	 * @param r is the rank of the card.
	 * @param s is the suit of the card.
	 */
	public Card(int r, char s) {
		this.rank = r;
		this.suit = toSuit(s);
	}

	/**
	 * This method creates a Card object.
	 * @param r is the rank of the card.
	 * @param s is the suit of the card.
	 */
	public Card(int r, Suits s) {
		this.rank = r;
		this.suit = s;
	}

	/**
	 * This method converts a char to a suit.
	 * @param c is the suit of the card as a char.
	 * @return Suit or null.
	 */
	private Suits toSuit(char c) {
		if (c == 'c')
			return Suits.club;
		else if (c == 'd')
			return Suits.diamond;
		else if (c == 'h')
			return Suits.heart;
		else if	(c == 's')
			return Suits.spade;

		return null;
	}

	/**
	 * This method returns the string of the suit.
	 * @param s is the suit of the card.
	 * @return suitStr is the string of the suit.
	 */
	private String suitToString(Suits s) {
		String suitStr = "";	// string to return

		if(s == Suits.club)
			suitStr = "Clubs";
		else if(s == Suits.diamond)
			suitStr = "Diamonds";
		else if(s == Suits.heart)
			suitStr = "Hearts";
		else if(s == Suits.spade)
			suitStr = "Spades";

		return suitStr;
	}

	/**
	 * This method returns the rank of the suit.
	 * @param r is the suit of the card as a char.
	 * @return valString is the string of the rank.
	 */
	private String rankToString(int r) {
		String valStr = "";		// string to return

		if(r >= 2 && r <= 10){
			if(r == 10)
				valStr += "10";
			else
				valStr += r;
		}
		else if(r == 11)
			valStr = "Jack";
		else if(r == 12)
			valStr = "Queen";
		else if(r == 13)
			valStr = "King";
		else if(r == 1)
			valStr = "Ace";

		return valStr;
	}

	/**
	 * This is an accessor method for rank.
	 * @return rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * This is an accessor method for suit.
	 * @return Suit
	 */
	public Suits getSuit() {
		return suit;
	}

	/**
	 * This is the toString for the Card class.
	 * @return s string containing rank and suit.
	 */
	public String toString() {
		String s = "";	// string to return
		
		s += rankToString(getRank()) + " of " + suitToString(getSuit());
		
		return s;
	}
}
