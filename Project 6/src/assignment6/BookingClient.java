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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Thread;

public class BookingClient {

    private final Map<String, Integer> office;
    private final Theater theater;

    public static void main(String[] args) throws InterruptedException {
        System.out.println();

        // initialize conditions
        Map<String, Integer> office = new HashMap<>();

//        // Example from PDF
//        Theater theater = new Theater(3, 5, "Ouija");
//        office.put("BX1", 3);
//        office.put("BX3", 3);
//        office.put("BX2", 4);
//        office.put("BX5", 3);
//        office.put("BX4", 3);

//        // Test 1
//        Theater theater = new Theater(100, 1, "Dunkirk");
//        office.put("BX1", 50);
//        office.put("BX2", 100);

        // Test 2
        Theater theater = new Theater(500, 2, "La La Land");
        office.put("BX1", 400);
        office.put("BX2", 600);

        // create new runnable
        BookingClient bc = new BookingClient(office, theater);

        // create and run threads
        List<Thread> box = bc.simulate();
        for (Thread t: box) {
            t.join();
        }

        theater.soldOut(); // print if sold out
        System.out.println();

//        // testing
//        int totalTick = theater.getTransactionLog().size();
//        System.out.println("Size: " + totalTick);
//
//        // testing
//        for (int i = 0; i < totalTick; i++) {
//            System.out.println(theater.getTransactionLog().get(i));
//        }
    }


  /**
   * TODO: Implement this constructor
   * @param office maps box office id to number of customers in line
   * @param theater the theater where the show is playing
   */
  public BookingClient(Map<String, Integer> office, Theater theater) {
      this.theater = theater;
      this.office = office;
  }

  /**
   * TODO: Implement this method
   * Starts the box office simulation by creating (and starting) threads
   * for each box office to sell tickets for the given theater
   *
   * @return list of threads used in the simulation,
   *         should have as many threads as there are box offices
   */
	public List<Thread> simulate() {
        List<Thread> threads = new ArrayList<>(); // List for all the threads together
        int clientID = 0; // creates unique IDs for customers

        for (String key: office.keySet()) {
            Thread thread = new Thread(new BoxOfficeClient(key, office.get(key), clientID, theater));
            threads.add(thread);
            thread.start();
            clientID += office.get(key);
        }
        return threads;
	}
}
