package gofish_assn;

import java.util.ArrayList;

import gofish_assn.Card.Suits;
import java.util.Random;

/**
 * This class implements functions for a Deck object.
 * @author Zahra Atzuri and Mircea Antonescu
 * @version 1.0
 */
public class Deck {
	private ArrayList<Card> deck = new ArrayList<Card> ();
	final int NUM_CARDS = 52;  //for this kind of deck

	/**
	 * This method creates a new sorted deck.
	 */
	public Deck() {
		for (int i = 0; i < 4; ++i) {           // loop for suits
			for (int j = 1; j < 14; ++j) {      // loop for value
				if(i == 0)
					deck.add(new Card(j, Suits.club));
				else if (i == 1)
					deck.add(new Card(j, Suits.diamond));
				else if (i == 2)
					deck.add(new Card(j, Suits.heart));
				else
					deck.add(new Card(j, Suits.spade));
			}
		}
	}
	/**
	 * This method shuffles the deck.
	 * @return void
	 */
	public void shuffle() {
        Random rnd = new Random(); // creates a new Random object

		for (int i = 0; i < 500; i++) {
			// generate random indexes and swap cards
		    int index1 = rnd.nextInt(NUM_CARDS);
            int index2 = rnd.nextInt(NUM_CARDS);

            Card c1 = deck.get(index1);
            Card c2 = deck.get(index2);

            deck.set(index1, c2);
            deck.set(index2, c1);
        }
	}

	/**
	 * This method prints the deck.
	 * @return void
	 */
	public void printDeck() {
        for (int i = 0; i < deck.size(); i++) {
            System.out.println(deck.get(i));
        }
	}

	/**
	 * This method deals a card from the deck.
	 * @return top card of the deck.
	 */
	
	public Card dealCard() {
        Card top = deck.get(0);	// top card of deck

        deck.remove(0);
        return top;
	}
	

}
