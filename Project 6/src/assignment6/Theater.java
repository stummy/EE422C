/* MULTITHREADING <MyClass.java>
 * EE422C Project 6 submission by
 * Zahra Atzuri
 * zfa84
 * 15500
 * Slip days used: <0>
 * Spring 2018
 */

package assignment6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Theater{
	private int numRows;
	private int seatsPerRow;
	private String show;

	private List<Seat> availableSeats = new LinkedList<>(); // list of available seats
	private List<Ticket> ticketsSold = new ArrayList<>(); // list of tickets sold

	/**
	 * Represents a seat in the theater
	 * A1, A2, A3, ... B1, B2, B3 ...
	 */
	static class Seat {
		private int rowNum;
		private int seatNum;

		/**
		 * Constructor for Seat
		 * @param rowNum
		 * @param seatNum
		 */
		public Seat(int rowNum, int seatNum) {
			this.rowNum = rowNum;
			this.seatNum = seatNum;
		}

		/**
		 * Accessor for seatNum
		 * @return seatNum
		 */
		public int getSeatNum() {
			return seatNum;
		}

		/**
		 * Accessor for rowNum
		 * @return rowNum
		 */
		public int getRowNum() {
			return rowNum;
		}

		/**
		 * Helper function to change rowNum to respective char (recursive)
		 * @param rowNum row as int
		 * @return char of row
		 */
		private String rowIntToChar(int rowNum){
			if(rowNum <= 0)
				return "";
			rowNum--;
			return rowIntToChar((rowNum-(rowNum%26))/26) + (char)('A' + (rowNum%26));

		}

		/**
		 * TODO: Implement this method to return the full Seat location ex: A1
		 * return the full Seat location ex: A1
		 * @return seat
		 */
		@Override
		public String toString() {
			return "" + rowIntToChar(rowNum) + seatNum;
		}
	}

    /**
	 * Represents a ticket purchased by a client
	 */
	static class Ticket {
		private String show;
		private String boxOfficeId;
		private Seat seat;
	  	private int client;

		/**
		 * Constructor for Ticket
		 * @param show
		 * @param boxOfficeId
		 * @param seat
		 * @param client
		 */
		public Ticket(String show, String boxOfficeId, Seat seat, int client) {
			this.show = show;
			this.boxOfficeId = boxOfficeId;
			this.seat = seat;
			this.client = client;
		}

		/**
		 * Accessor for seat
		 * @return seat
		 */
		public Seat getSeat() {
			return seat;
		}

		/**
		 * Accessor for show
		 * @return show
		 */
		public String getShow() {
			return show;
		}

		/**
		 * Accessor for boxOfficeID
		 * @return boxOfficeID
		 */
		public String getBoxOfficeId() {
			return boxOfficeId;
		}

		/**
		 * Accessor for client
		 * @return client
		 */
		public int getClient() {
			return client;
		}

		/**
		 * Helper function to create the toString of Ticket
		 * @param l length of line
		 * @param dash length of the --- thing
		 * @return spaces for tickets
		 */
		private String ticketHelper(int l, int dash) {
			int sp = dash - l;
			String space = "";

			for (int i = 0; i < sp - 1; i++) {
				space += " ";
			}
			return space;
		}

		/**
		 * TODO: Implement this method to return a string that resembles a ticket
		 * Prints out a ticket
		 * @return ticket
		 */
		@Override
		public String toString() {
			String dash = "-------------------------------";
			String l1 = "| Show: " + show;
			l1 += ticketHelper(l1.length(), dash.length()) + "|";
			String l2 = "| Box Office ID: " + boxOfficeId;
			l2 += ticketHelper(l2.length(), dash.length()) + "|";
			String l3 = "| Seat: " + seat.toString();
			l3 += ticketHelper(l3.length(), dash.length()) + "|";
			String l4 = "| Client: " + client;
			l4 += ticketHelper(l4.length(), dash.length()) + "|";

			String tick = dash + "\r\n" + l1 + "\r\n" + l2 + "\r\n" + l3 + "\r\n" + l4 + "\r\n" +
					dash + "\r\n";
			return tick;
		}
	}

	/**
	 * Constructor for Theater
	 * @param numRows
	 * @param seatsPerRow
	 * @param show
	 */
	public Theater(int numRows, int seatsPerRow, String show) {
		this.numRows = numRows;
		this.seatsPerRow = seatsPerRow;
		this.show = show;

		// creates all the seats based on rows and seats per rows
		for (int i = 1; i <= numRows; i++) {
			for (int j = 1; j <= seatsPerRow; j++) {
				availableSeats.add(new Seat(i,j));
			}
		}
	}

	/**
	 * TODO: Implement this method
	 * Calculates the best seat not yet reserved
	 *
 	 * @return the best seat or null if theater is full
     */
	public synchronized Seat bestAvailableSeat() {
		if(!availableSeats.isEmpty()){
			Seat seat = availableSeats.remove(0);
			return seat;
		}
		else {
			return null;
		}
	}

	/**
	 * TODO: Implement this method
	 * Prints a ticket for the client after they reserve a seat
   	 * Also prints the ticket to the console
	 *
	 * @param seat a particular seat in the theater
	 * @return a ticket or null if a box office failed to reserve the seat
     */
	public synchronized Ticket printTicket(String boxOfficeId, Seat seat, int client) throws InterruptedException {
		if(seat != null) {
			Ticket ticket = new Ticket(show, boxOfficeId, seat, client);

			Thread.sleep(50); // for human readability
			System.out.println(ticket.toString());
			ticketsSold.add(ticket); // add ticket to sold list

			return ticket;
		}
		return null; // if box office failed to reserve seat
	}

	/**
	 * TODO: Implement this method
	 * Lists all tickets sold for this theater in order of purchase
	 * @return list of tickets sold
	 */
	public List<Ticket> getTransactionLog() {
		return ticketsSold;
	}

	/**
	 * Prints statement if theatre full capacity reached
	 */
	public void soldOut(){
		if(ticketsSold.size() == (numRows*seatsPerRow)){
			System.out.println("Sorry, we are sold out!");
		}
	}

}
