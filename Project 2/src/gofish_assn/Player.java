package gofish_assn;

import java.util.ArrayList;

/**
 * This class implements functions for a Player object
 * @author Zahra Atzuri and Mircea Antonescu
 * @version 1.0
 */
public class Player {
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<Card> book = new ArrayList<Card>();
	private String name;

	/**
	 * This method creates a Player object
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * This method adds a card to the Player's hand.
	 * @param c is the card to add.
	 * @return void.
	 */
	public void addCardToHand(Card c) {
		hand.add(c);
	}

	/**
	 * This method removes a card from the Player's hand.
	 * @param c is the card to remove.
	 * @return retCard is card that is removed.
	 */
	public Card removeCardFromHand(Card c) {
		Card retCard = new Card();	// card to return
		int rank;	// rank of card

		for (int i = 0; i < hand.size(); i++) {
			// only removes based on rank
			rank = (hand.get(i)).getRank();
			if (c.getRank() == rank) {
				retCard = hand.get(i);
				hand.remove(i);
			}
		}

		return retCard;
	}

	/**
	 * This method returns the Player's name.
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method displays the Player's hand as a String.
	 * @return s the Player's hand.
	 */
	public String handToString() {
		String s = "";	// string to return

		for (int i = 0; i < hand.size(); i++)
			s += hand.get(i) + "\n";

		return s;
	}

	/**
	 * This method displays the Player's book as a String.
	 * @return s the Player's book.
	 */
	public String bookToString() {
		String s = "";	// string to return

		for (int i = 0; i < book.size(); i += 2)
			s += book.get(i) + " and " + book.get(i+1) + "\n";

		return s;
	}

	/**
	 * This is an accessor method for hand size.
	 * @return int
	 */
	public int getHandSize() {
		return hand.size();
	}

	/**
	 * This is an accessor method for the book size
	 * @return int
	 */
	public int getBookSize() {
		return book.size();
	}


	/**
	 * This method will check a players hand for a pair.
	 * If a pair is found, it adds the cards to the book
	 * 		(but NOT REMOVE from hand) and returns true.
	 * @return boolean if pair is found
	 */
    public boolean checkHandForBook() {
		Card c1;	// card 1 to compare
		Card c2;	// card 2 to compare

		for(int i = 0; i < hand.size(); i++){
			for(int j = i + 1; j < hand.size(); j++){
				c1 = hand.get(i);
				c2 = hand.get(j);
				if(c1.getRank() == c2.getRank()){
					// only adds the pair to the book
					book.add(c1);
					book.add(c2);
					return true;
				}
			}
		}
		return false;
    }

	/**
	 * This method chooses the last card on the Player's hand
	 * @return c the last card of the hand
	 */
	public Card chooseCardFromHand() {
		return hand.get(hand.size() - 1);
    }

	/**
	 * This method determines if Player has a Card
	 * @param c the card to find
	 * @return boolean if card is found
	 */
	public boolean cardInHand(Card c) {
		int rank; // rank

		for (int i = 0; i < hand.size(); i++) {
			// finds based on the rank only
			rank = (hand.get(i)).getRank();
			if (c.getRank() == rank)
				return true;
		}
		return false;
	}

	/**
	 * This method returns the rank of a pair in a hand.
	 * 		i.e. it will REMOVE the cards and return the rank.
	 * @return int rank of the pair of cards
	 */
    public int sameRankInHand() {
		Card c1;	// card 1 to compare
		Card c2;	// card 2 to compare

		for(int i = 0; i < hand.size(); i++){
			for(int j = i + 1; j < hand.size(); j++){
				c1 = hand.get(i);
				c2 = hand.get(j);
				if(c1.getRank() == c2.getRank()){
					// removes the pair from the hand
					hand.remove(c1);
					hand.remove(c2);
					return c1.getRank();
				}
			}
		}
		return 0;
    }

}
