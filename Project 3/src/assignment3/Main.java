/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Zahra Atzuri
 * zfa84
 * 15500
 * Slip days used: <0>
 * Git URL:
 * Spring 2018
 */


package assignment3;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Main {

	private static Set<String> dictionary; // Set of Strings with words in dictionary

	public static void main(String[] args) throws Exception {
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file, for student testing and grading only
		// If arguments are specified, read/write from/to files instead of Std IO.

		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default input from Stdin
			ps = System.out;			// default output to Stdout
		}
		initialize();

		// ~*~*~*~*~*~ THIS IS FOR TESTING! ~*~*~*~*~*~
		ArrayList<String> in = parse(kb);	// input

		while(!in.isEmpty()) {
			String start = in.get(0);
			String finish = in.get(1);

			// BFS print
			ArrayList<String> BFS = getWordLadderBFS(start, finish);
			ps.print("\nBreadth First Search: ");
			if (BFS.size() != 2) {
				ps.println("\nA " + (BFS.size() - 2) + "-rung ladder exists between " +
						start.toLowerCase() + " and " + finish.toLowerCase());
			}
			printLadder(BFS);

			// DFS print
			ArrayList<String> DFS = getWordLadderDFS(start, finish);
			ps.print("\nDepth First Search: ");
			if (DFS.size() != 2) {
				ps.println("\nA " + (DFS.size() - 2) + "-rung ladder exists between " +
						start.toLowerCase() + " and " + finish.toLowerCase());
			}
			printLadder(DFS);
			System.out.println();

			in.clear();
			in = parse(kb);

		}

	}


	/**
	 * constructor method for global variable
	 */
	public static void initialize() {
		dictionary = makeDictionary();
	}


	/**
	 * This method creates an ArrayList containing the input words.
	 *
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of Strings containing start word and end word.
	 * If command is /quit, return empty ArrayList.
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> initialWords = new ArrayList<String>();

		System.out.print("Enter a starting and ending words (separate w/ space): ");
		String start = keyboard.next();
		String end = keyboard.next();

		// return an empty ArrayList if "\quit"
		if(start.equals("/quit") || end.equals("/quit")) {
			return initialWords;
		}

		initialWords.add(start.toUpperCase());
		initialWords.add(end.toUpperCase());

		return initialWords;
	}


	/**
	 *  This method finds a word ladder using the Depth First Search method.
	 *
	 * @param start starting word
	 * @param end ending word
	 * @return ArrayList of Strings with word ladder from start to end words using Depth First Search.
	 *  If ladder is empty, return list with just start and end.
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		Set<String> visited = new HashSet<>();  // Set to make sure no words repeated
		ArrayList<String> ladder = new ArrayList<>();   // final ArrayList to print

		// checks if the words have different lengths or not in dictionary
		if(!(dictionary.contains(end.toUpperCase()) &&
				dictionary.contains(start.toUpperCase()))){
			ladder.add(end.toUpperCase());
			ladder.add(start.toUpperCase());

		} else {

			// eventually the DFS will keep going and going and causes an overflow
			// this is totally not good practice, but because the dictionary is so big, it would take a long
			// time to find all the paths
			try{
				ladder = recursive(start.toUpperCase(), end.toUpperCase(), visited);
			} catch(StackOverflowError error){
				ladder = null;
			}

			// if there is no word ladder found
			if(ladder == null) {
				ArrayList<String> empty = new ArrayList<>();

				empty.add(end.toUpperCase());
				empty.add(start.toUpperCase());

				return empty;

			} else {
				ladder.add(start.toUpperCase());
			}
		}

		return ladder;
	}


	/**
	 * This method finds a word ladder using the Breadth First Search method.
	 *
	 * @param start starting word
	 * @param end ending word
	 * @return ArrayList of Strings with word ladder from start to end words.
	 *  If ladder is empty, return list with just start and end.
	 */
	public static ArrayList<String> getWordLadderBFS(String start, String end) {
		Set<String> visited = new HashSet<>();	// Set to make sure no words repeated
		Map<String, String> map = new HashMap<>(); // allows to map path from end word to start
		Queue<String> queue = new LinkedList<>();	// queue for BFS
		ArrayList<String> ladder = new ArrayList<>();	// final ArrayList to print

		// uppercase start and end words
		String startUp = start.toUpperCase();
		String endUp = end.toUpperCase();

		if(!(dictionary.contains(end.toUpperCase()) &&
				dictionary.contains(start.toUpperCase()))){
			ladder.add(endUp);
			ladder.add(startUp);
			return ladder;
		}

		// initial conditions
		visited.add(startUp);
		map.put(startUp, null);
		queue.add(startUp);

		while(!queue.isEmpty()) {
			String top = queue.poll(); // take out the top word to compare

			// end conditions: add to array list by using the map
			if(top.equals(endUp)){
				ladder.add(endUp);
				String key = map.get(endUp);

				while(key != null) {
					ladder.add(key);
					key = map.get(key);
				}
			}

			ArrayList<String> neighbors = neighbors(top, end);

			// loop through neighbors and add to queue
			for (int i = 0; i < neighbors.size(); i++) {
				if(!visited.contains(neighbors.get(i))) {
					queue.add(neighbors.get(i));
					map.put(neighbors.get(i), top);
				}
			}
			visited.addAll(neighbors);
		}

		// if no word ladder is found, add the start and end words and return ArrayList
		if(ladder.isEmpty()){
			ladder.add(endUp);
			ladder.add(startUp);
		}

		return ladder;
	}


	/**
	 * This method prints out the word ladder
	 *
	 * @param ladder ArrayList of Strings containing word ladder
	 * @return void
	 */
	public static void printLadder(ArrayList<String> ladder) {
		// if no word ladder is found
		if(ladder.size() == 2){
			System.out.println("\nno word ladder can be found between " +
					(ladder.get(1)).toLowerCase() + " and " + (ladder.get(0)).toLowerCase());
		}

		else {
			for (int i = ladder.size() - 1; i >= 0; i--) {
				System.out.println((ladder.get(i)).toLowerCase());
			}
		}
	}


	/**
	 * This method finds words that have one letter difference from input word (finds neighbors).
	 *
	 * @param word starting word
	 * @return ArrayList of Strings with words that have one letter difference from input word.
	 *  If no one letter difference words are found, then return empty ArrayList.
	 */
	private static ArrayList<String> neighbors(String word, String end) {
		ArrayList<String> neighbors = new ArrayList<>();
		Set<String> heuristicSet = new HashSet<>();	// set for optimization

		// heuristic neighbors - optimization
		for (int i = 0; i < word.length(); i++) {
			StringBuilder copy = new StringBuilder(word);
			char endChar = end.charAt(i);
			copy.setCharAt(i, endChar);	// replace letter at index

			if (!(copy.toString()).equals(word)) {
				if (dictionary.contains(copy.toString())) {
					neighbors.add(copy.toString());
					heuristicSet.add(copy.toString());
				}
			}
		}

		// normal neighbors
		for (int i = 0; i < word.length(); i++) {
			StringBuilder copy = new StringBuilder(word);

			for (char j = 'A'; j <= 'Z'; j++) {
				copy.setCharAt(i,j); // replace letter at index

				if(!(copy.toString()).equals(word)) {
					// check if word is in dictionary and not in heuristicSet
					if(dictionary.contains(copy.toString()) && !heuristicSet.contains(copy.toString())){
						neighbors.add(copy.toString());
					}
				}
			}

		}
		return neighbors;
	}


	/**
	 * This method is a helper recursive function for the DFS method
	 *
	 * @param word starting word
	 * @param end ending word
	 * @param visit Set of Strings containing words that have been visited
	 * @return ArrayList of Strings with word ladder from start to end words.
	 *  If ladder is empty, return empty ArrayList.
	 */
	private static ArrayList<String> recursive(String word, String end, Set<String> visit) {
		visit.add(word); // add word to visited
		ArrayList<String> neighbors = neighbors(word, end);

		// base case
		if(word.equals(end)) {
			ArrayList<String> ladder = new ArrayList<>();
			return ladder;
		}

		// word is not found case
		else if(neighbors.isEmpty()) {
			return null;
		}

		else {
			for (int i = 0; i < neighbors.size(); i++) {

				// make sure that it wont loop by checking visited
				if(!visit.contains(neighbors.get(i))) {
					ArrayList<String> temp = recursive(neighbors.get(i), end, visit);

					if (temp != null) {
						temp.add(neighbors.get(i));
						return temp;
					}
				}
			}
			return null;
		}
	}


	/* Do not modify makeDictionary */
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}

}
