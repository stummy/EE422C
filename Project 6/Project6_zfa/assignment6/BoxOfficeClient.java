/* MULTITHREADING <MyClass.java>
 * EE422C Project 6 submission by
 * Zahra Atzuri
 * zfa84
 * 15500
 * Slip days used: <0>
 * Spring 2018
 */

package assignment6;

public class BoxOfficeClient implements Runnable{
    private String boxOfficeId;
    private int numClients;
    private Theater theater;
    private int ID;

    /**
     * Constructor for BoxOfficeClient
     * @param boxOfficeId
     * @param numClients number of customers
     * @param ID client ID
     * @param theater
     */
    public BoxOfficeClient(String boxOfficeId, int numClients, int ID, Theater theater) {
        this.boxOfficeId = boxOfficeId;
        this.numClients = numClients;
        this.theater = theater;
        this.ID = ID;
    }

    /**
     * Creates/prints out tickets
     */
    @Override
    public void run() {
        for (int i = 1; i <= numClients; i++) {
            try {
                synchronized (theater){
                    // check for best available seat and sell ticket
                    Theater.Seat avail = theater.bestAvailableSeat();
                    theater.printTicket(boxOfficeId, avail, i + ID);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
