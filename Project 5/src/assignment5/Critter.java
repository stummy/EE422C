/* CRITTERS Critter.java
 * EE422C Project 5 submission by
 * Mircea Antonescu
 * mca2357
 * 15500
 * Zahra Atzuri
 * zfa84
 * 15500
 * Slip days used: <0>
 * Spring 2018
 */

package assignment5;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return Color.BLACK;
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	protected final String look(int direction, boolean steps) {
		int num = 1;
		int temp_x_coord = x_coord;
		int temp_y_coord = y_coord;

		if(steps){
			num = 2;
		}

		switch(++direction){
			case 1:
				temp_x_coord += num;
				break;
			case 2:
				temp_x_coord += num;
				temp_y_coord -= num;
				break;
			case 3:
				temp_y_coord -= num;
				break;
			case 4:
				temp_x_coord -= num;
				temp_y_coord -= num;
				break;
			case 5:
				temp_x_coord -= num;
				break;
			case 6:
				temp_x_coord -= num;
				temp_y_coord += num;
				break;
			case 7:
				temp_y_coord += num;
				break;
			case 8:
				temp_x_coord += num;
				temp_y_coord += num;
				break;
			default:
				break;
		}

		if(temp_x_coord > Params.world_width - 1) {
			temp_x_coord = (temp_x_coord % (Params.world_width - 1)) - 1;
		}
		if(temp_y_coord > Params.world_height - 1) {
			temp_y_coord = (y_coord % (Params.world_height - 1)) - 1;
		}
		if(temp_x_coord < 0) {
			temp_x_coord += Params.world_width;
		}
		if(temp_y_coord < 0) {
			temp_y_coord += Params.world_height;
		}

		energy -= Params.look_energy_cost;

		return checkLook(temp_x_coord, temp_y_coord);
	}

	// helper bc we lazy f u
	private static String checkLook(int temp_x, int temp_y){
		for (Critter c : population) {
			if((c.x_coord == temp_x) && (c.y_coord == temp_y) ){
				return c.toString();
			}
		}
		return null;
	}

	/* rest is unchanged from Project 4 */


	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}

	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}


	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }

	private int energy = 0;
	protected int getEnergy() { return energy; }

	private int x_coord;
	private int y_coord;

	/**
	 * moves the critter one step in the given direction
	 */
	protected final void walk(int direction) {
		if(energy > Params.walk_energy_cost){
			move(direction, 1);
		}
		energy -= Params.walk_energy_cost;
	}

	/**
	 * moves the critter 2 steps in a given direction
	 */
	protected final void run(int direction) {
		if(energy > Params.run_energy_cost){
			move(direction, 2);
		}
		energy -= Params.run_energy_cost;
	}

	/**
	 * helper function for wun and walk
	 */
	protected final void move(int direction, int num) {
		switch(++direction){
			case 1:
				x_coord += num;
				break;
			case 2:
				x_coord += num;
				y_coord -= num;
				break;
			case 3:
				y_coord -= num;
				break;
			case 4:
				x_coord -= num;
				y_coord -= num;
				break;
			case 5:
				x_coord -= num;
				break;
			case 6:
				x_coord -= num;
				y_coord += num;
				break;
			case 7:
				y_coord += num;
				break;
			case 8:
				x_coord += num;
				y_coord += num;
				break;
			default:
				break;
		}

		if(x_coord > Params.world_width - 1) {
			x_coord = (x_coord % (Params.world_width - 1)) - 1;
		}
		if(y_coord > Params.world_height - 1) {
			y_coord = (y_coord % (Params.world_height - 1)) - 1;
		}
		if(x_coord < 0) {
			x_coord += Params.world_width;
		}
		if(y_coord < 0) {
			y_coord += Params.world_height;
		}

	}

	/**
	 * subtracts the rest energy for critters who don't move in a turn
	 */
	protected final void rest(){
		energy -= Params.rest_energy_cost;
	}

	/**
	 * takes a critter offspring and puts it into the baby List until the timeStep is over
	 * @param offspring given from a subclass
	 * @param direction spawns the offspring one step next to the parent in the given direction
	 */
	protected final void reproduce(Critter offspring, int direction) {
		if(energy > Params.min_reproduce_energy) {
			offspring.energy = (int) Math.floor(.5 * energy);
			this.energy = (int) Math.ceil(.5 * energy);

			offspring.x_coord = x_coord;
			offspring.y_coord = y_coord;
			offspring.energy += Params.walk_energy_cost;
			offspring.walk(direction);

			babies.add(offspring);
		}
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String opponent);

	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
			Class c = Class.forName(critter_class_name);
			Critter cr = (Critter) c.newInstance();
			cr.energy = Params.start_energy;
			cr.x_coord = getRandomInt(Params.world_width) + 1;
			cr.y_coord = getRandomInt(Params.world_height) + 1;
			population.add(cr);

		}
		catch(ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			throw new InvalidCritterException(critter_class_name);
		}
	}

	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();

		for (Critter c : population) {
			if(c.getClass().getName().equalsIgnoreCase(critter_class_name)){
				result.add(c);
			}
		}
		return result;
	}

	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static String runStats(List<Critter> critters) {
		String ret = "";
		ret += critters.size() + " critters as follows -- ";
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			critter_count.merge(crit_string, 1, (a, b) -> a.intValue() + b);
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			ret += (prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		return ret;
	}

	/* the TestCritter class allows some critters to "cheat". If you want to
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here.
	 *
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}

		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}

		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}

		protected int getX_coord() {
			return super.x_coord;
		}

		protected int getY_coord() {
			return super.y_coord;
		}


		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}

		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
	}

	/**
	 * does one time step: moves critters, makes them fight and deletes the dead ones at the end
	 */
	public static void worldTimeStep() {
		ArrayList<Integer> movedCritters = new ArrayList<>();	// checks for critters that moved

		for (int i = 0; i < population.size(); i++){
			int x_cord = population.get(i).x_coord;
			int y_cord = population.get(i).y_coord;

			population.get(i).doTimeStep();

			if((x_cord != population.get(i).x_coord) && (y_cord != population.get(i).y_coord)){
				movedCritters.add(i);
			}
		}

		for(int i = 0; i < population.size(); i++){
			for(int j = 0; j < population.size(); j++) {
				if (i != j) {
					Critter cr1 = population.get(i);
					Critter cr2 = population.get(j);
					int x_cord_cr1 = cr1.x_coord;
					int y_cord_cr1 = cr1.y_coord;
					int x_cord_cr2 = cr2.x_coord;
					int y_cord_cr2 = cr2.y_coord;

					if ((cr1.x_coord == cr2.x_coord) && (cr1.y_coord == cr2.y_coord)) {
						// check if fight
						boolean cr1_f = cr1.fight(cr2.toString());
						boolean cr2_f = cr2.fight(cr1.toString());

						// check if moved after fight & add to moved if true
						if (!(cr1.x_coord == cr2.x_coord) && !(cr1.y_coord == cr2.y_coord)) {
							if((cr1.x_coord != x_cord_cr1) && (cr1.y_coord != y_cord_cr1)){
								movedCritters.add(i);
							}
							else if((cr2.x_coord != x_cord_cr2) && (cr2.y_coord != y_cord_cr2)){
								movedCritters.add(j);
							}
						}

						// if both critters moved
						if (movedCritters.contains(i) && movedCritters.contains(j)) {
							selectWinner(cr1,cr2);
						}
						// if one or the other does not move
						else if (!movedCritters.contains(i) || !movedCritters.contains(j)) {

							// check if both want to fight
							if (cr1_f && cr2_f) {
								selectWinner(cr1, cr2);
							}

							// neither wants to fight
							else{

								// check if cr1 doesn't want to fight and doesn't move
								if(!cr1_f && !movedCritters.contains(i)){

									// check if Algae
									if(cr1.toString().equals("@")){
										cr2.energy += cr1.energy;
										cr1.energy = 0;
									}
									else{
										if(cr1.energy > Params.run_energy_cost){
											cr1.run(0);
										}
										else{
											cr1.walk(0);
										}
									}
								}

								// check if cr2 doesn't want to fight and doesn't move
								if(!cr2_f && !movedCritters.contains(j)){

									// check if Algae
									if(cr2.toString().equals("@")){
										cr1.energy += cr2.energy;
										cr2.energy = 0;
									}
									else{
										if(cr2.energy > Params.run_energy_cost){
											cr2.run(4);
										}
										else{
											cr2.walk(4);
										}
									}
								}
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < population.size(); i++) {
			Critter c = population.get(i);

			// take into account resting critters
			if(!movedCritters.contains(c)){
				c.rest();
			}
			//remove dead critters;
			if(c.energy <= 0){
				population.remove(i);
			}
		}

		// add the babies
		for (int i = 0; i < babies.size(); i++) {
			population.add(babies.get(i));

		}
		babies.clear();
	}


	/**
	 * helper function that selects a winner when two critters fight
	 */
	protected static void selectWinner(Critter cr1, Critter cr2){
		int cr1_energy = 0;
		int cr2_energy = 0;

		if (cr1.fight(cr2.toString()) && (cr1.energy > 0)) {
			cr1_energy = getRandomInt(cr1.energy) + 1;
			assert (cr1_energy > 0);
		}
		if (cr2.fight(cr1.toString()) && (cr2.energy > 0)) {
			cr2_energy = getRandomInt(cr2.energy) + 1;
			assert (cr2_energy > 0);
		}

		// if neither want to fight
		if (cr1_energy == 0 && cr2_energy == 0) {
			cr1.energy = 0;
			cr2.energy = 0;
		}
		// if cr1 wants to fight (or both)
		else if (cr1_energy >= cr2_energy) {
			cr1.energy += cr2.energy / 2;
			cr2.energy = 0;
		}
		// if cr2 wants to fight
		else {
			cr2.energy += cr1.energy / 2;
			cr1.energy = 0;
		}
	}

	/**
	 * creates the world and displays the critters on it
	 */
	public static void displayWorld() {
		WorldView.paint();

		int height = Params.world_height + 1;
		int width = Params.world_width + 1;

		// add critters
		for(int i = 0; i < population.size(); i++){
			Critter cr = population.get(i);
			WorldView.drawCritter(cr.x_coord,cr.y_coord, cr.viewShape(), cr.viewOutlineColor(),cr.viewFillColor());

		}

//		for(int i = 0; i < Params.world_height*WorldView.tileSize; i+=WorldView.tileSize){
//			for (int j = 0; j < Params.world_width*WorldView.tileSize; j+=WorldView.tileSize) {
//				for(Critter crit : population){
//					if(i != crit.x_coord && j != crit.y_coord){
//						Rectangle tile = new Rectangle(WorldView.tileSize, WorldView.tileSize);
//						tile.setStroke(Color.BLACK);
//						tile.setFill(Color.OLIVEDRAB);
//						Main.worldGrid.add(tile, i, j);
//					}
//				}
//			}
//		}
	}

}
