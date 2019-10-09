package gofish_assn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
 * EE422C Project 2 submission by
 * Zahra Atzuri and Mircea Antonescu
 * zfa84 /
 * 15500
 * Spring 2018
 */

/**
 * This class implements the game Go Fish
 * @author Zahra Atzuri and Mircea Antonescu
 * @version 1.0
 */
public class GoFishGame {

	/**
	 * This method contains the implementation of the game
	 */
	public GoFishGame() {
		try {
			// file output stuff
			File oFile = new File("GoFish_results.txt");
			PrintWriter output = new PrintWriter(oFile);

			// create deck
			Deck deck = new Deck();
			deck.shuffle();

			// create players
			Player p1 = new Player("Player 1");
			Player p2 = new Player("Player 2");

			// deal the deck for 7 cards each
			for (int i = 0; i < 14; i++) {
				if (i % 2 == 0)
					p1.addCardToHand(deck.dealCard());
				else
					p2.addCardToHand(deck.dealCard());
			}

			output.println("~*~*~*~* Let's play Go Fish! *~*~*~*~\n");

			while (!isGameOver(p1, p2)) {

				// these are checkers if a player has to go again
				boolean checkP1Turn = true;
				boolean checkP2Turn = true;

				// check for pairs beginning of match
				// 		(no special rules if anyone books a pair)
				while (p1.checkHandForBook()) {
					output.println(p1.getName() + " books the " + rankToString(p1.sameRankInHand()));
				}
				while (p2.checkHandForBook()) {
					output.println(p2.getName() + " books the " + rankToString(p2.sameRankInHand()));
				}

				output.println(); // for prettiness

				// Player 1's turn
				while (checkP1Turn) {
					// if player's hand is not empty
					if (p1.getHandSize() != 0) {
						Card card1 = p1.chooseCardFromHand();

						output.println(p1.getName() + " asks - Do you have a " + rankToString(card1.getRank()));
						// no card in hand found
						if (!p2.cardInHand(card1)) {
							output.println(p2.getName() + " says - Go Fish!");
							Card nc = deck.dealCard();
							output.println(p1.getName() + " draws " + nc.toString());
							p1.addCardToHand(nc);
							checkP1Turn = false;
						}
						// card in hand found
						else {
							output.println(p2.getName() + " says - Yes, I have a " + rankToString(card1.getRank()));
							p1.addCardToHand(p2.removeCardFromHand(card1));
						}
						// checks for books and goes again if true
						if (p1.checkHandForBook()) {
							output.println(p1.getName() + " books the " + rankToString(p1.sameRankInHand()) + "\n");
							checkP1Turn = true;
						}
					}
					// if the player's hand is empty, get a card from deck
					else {
						if (!isGameOver(p1, p2)) {
							output.println(p1.getName() + "'s hand is empty!");
							Card nc = deck.dealCard();
							output.println(p1.getName() + " draws " + nc.toString());
							p1.addCardToHand(nc);
						}
						checkP1Turn = false;
					}
				}

				output.println(); 	// prettiness

				// check if game is over; break if true
				if (isGameOver(p1, p2))
					break;

				// Player 2's turn
				while (checkP2Turn) {
					// if player's hand is not empty
					if (p2.getHandSize() != 0) {
						Card card2 = p2.chooseCardFromHand();

						output.println(p2.getName() + " asks - Do you have a " + rankToString(card2.getRank()));
						// no card in hand found
						if (!p1.cardInHand(card2)) {
							output.println(p1.getName() + " says - Go Fish!");
							Card nc = deck.dealCard();
							output.println(p2.getName() + " draws " + nc.toString());
							p2.addCardToHand(nc);
							checkP2Turn = false;
						}
						// checks for books and goes again if true
						else {
							output.println(p1.getName() + " says - Yes, I have a " + rankToString(card2.getRank()));
							p2.addCardToHand(p1.removeCardFromHand(card2));
						}
						// checks for books and goes again if true
						if (p2.checkHandForBook()) {
							output.println(p2.getName() + " books the " + rankToString(p2.sameRankInHand()) + "\n");
							checkP2Turn = true;
						}

					}
					// if the player's hand is empty, get a card from deck
					else {
						if (!isGameOver(p1, p2)) {
							output.println(p2.getName() + "'s hand is empty!");
							Card nc = deck.dealCard();
							output.println(p2.getName() + " draws " + nc.toString());
							p2.addCardToHand(nc);
						}
						checkP2Turn = false;
					}
				}
			}

			output.println(); // for prettiness

			// this chunk of code checks for the winner of the game
			if(p1.getBookSize() == p2.getBookSize())
				output.println("It's a tie!\n");
			else if(p1.getBookSize() > p2.getBookSize())
				output.println("Player 1 wins with " + p1.getBookSize()/2 + " pairs:\n");
			else
				output.println("Player 2 wins with " + p2.getBookSize()/2 + " pairs:\n");
			// and displays the books e/ player has
			output.println("Player 1's Books:\n" + p1.bookToString());
			output.println("Player 2's Books:\n" + p2.bookToString());

			output.close(); // close the file

		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found!");
		}
	}

	/**
	 * This method converts the card's rank to a string.
	 * @param r rank to convert to string.
	 * @return valString the string containing the rank.
	 */
	public String rankToString(int r) {
		String valStr = ""; // string to return

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
	 * This method checks if the game is over.
	 * @param p1 player 1 object.
	 * @param p2 player 2 object.
	 * @return boolean if game is over.
	 */
	public boolean isGameOver(Player p1, Player p2){
		return p1.getBookSize() + p2.getBookSize() == 52;
	}

}
