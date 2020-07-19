// Gene Y
// Assignment 10 Lion.java
// Creating the Lion class and the critter behaviors associated with it
// CSIII
// 7/17/20

import java.awt.Color;
import java.util.Random;

/**
 * @author geneyang
 */
public class Lion extends Critter {
	/**
	 * Whether the lion has mated in its lifetime or not.
	 */
	private boolean mated;
	
	/**
	 * What color the lion will stay as its entire life: red or yellow.
	 */
	private Color color;
	
	/**
	 * {@value ANT_STRING} the toString for both lion and ant
	 */
	private static final String ANT_LION_STRING = "%";

	/**
	 * The color maroon.
	 */
	private static final Color MAROON = new Color(102, 0, 51);
	
	/**
	 * The color yellow.
	 */
	private static final Color YELLOW = new Color(255,204,0);
	
	/**
	 * This constructor sets the lions color to be one of the two.
	 * 
	 * Red ones go up, Yellow down.
	 * 
	 * The Lion will pretend to be an ant, and counter what others would do to an ant.
	 * Movement when not mated yet is to gather in an area to increase chance of mating.
	 * Once mated, it will move, sweeping the entire map.
	 */
	public Lion() {
		Color[] colors = {MAROON, YELLOW};
		Random rand = new Random();
		this.color = colors[rand.nextInt(2)];	
	}
	
	/**
	 * Controls whether the lion eats or not.
	 * 
	 * Eats when it is safe to do so.
	 * However, lions that have mated can also eat.
	 * 
	 * @return boolean for whether the lion will eat
	 */
	public boolean eat() {
		// Only eat when it is safe to do so (except for ants)
		if (hasNoHostileNeighbor() || mated) { 
			return true;
		} 
		
		return false;
	}

	/**
	 * Controls what attack to do given the opponent.
	 * 
	 * Attacks animals knowing that the animals think it is an ant.
	 * 
	 * @param opponent
	 * @return which attack to do
	 */
	public Attack fight(String opponent) {
		if ((opponent.equals("S"))) { // Stone
			return Attack.POUNCE;
		}
		
		if ((opponent.equals(">") || opponent.equals("V") || opponent.equals("^") 
				|| opponent.equals("<"))) { // Bird
			return Attack.POUNCE;
		}
		
		if (opponent.equals(ANT_LION_STRING)) { // Ant
			return Attack.ROAR;
		}
		if (isHippo(opponent) && !opponent.equals("0")) { // Hungry Hippo
			return Attack.ROAR;
		}
		else if (opponent.equals("0")) { // Not hungry Hippo
			return Attack.SCRATCH;
		}
		
		return Attack.POUNCE;
	}

	/**
	 * Sets color as either one of the two colors of the Lakeside lion logo: 
	 * Maroon is (102,0,51) and yellow is (255,204,0).
	 * 
	 * @return the lion's color
	 */
	
	public Color getColor() {
		return color;
	}

	@Override
	public void mateEnd() {
		mated = true; // After a lion mates, mated becomes true
	}
	
	/**
	 * Controls where the lion moves. When not mated, it heads for the center.
	 * Once mated, it sweeps the entire map.
	 * 
	 * @return The direction the lion heads in. When not mated, it heads for the center
	 * 		   when mated, it sweeps the entire map
	 */
	public Direction getMove() {
		
		int height = super.getHeight();
		int width = super.getWidth();
		int x = this.getX();
		int y = this.getY();
		
		if (mated) { 
			// don't need to mate anymore, goes in a systematic way to cover every square
			
			// avoid collisions and getting stuck
			if (getNeighbor(Direction.WEST).equals("%") 
					|| getNeighbor(Direction.EAST).equals("%")) {
				
				Direction[] dirs = {Direction.NORTH, Direction.SOUTH};
				Random rand = new Random();
				return dirs[rand.nextInt(2)];
			}
			
			//new Color(102,0,51), new Color(255,204,0)
			if (color.equals(MAROON)) {
				
				// Alternates going all the way left, up, all the way right, up, etc.
				if (y % 2 == 0) { 
					if (x < width - 1) {
						return Direction.EAST;
					} else {
						return Direction.NORTH;
					}
				} else {
					if (x > 0) {
						return Direction.WEST;
					} else {
						return Direction.NORTH;
					}
				}
			} else if (color.equals(YELLOW)) {
				// Alternates going all the way left, down, all the way right, down, etc.
				if (y % 2 == 0) { 
					if (x < width - 1) {
						return Direction.EAST;
					} else {
						return Direction.SOUTH;
					}
				} else {
					if (x > 0) {
						return Direction.WEST;
					} else {
						return Direction.SOUTH;
					}
				}
			}
		} 
		// If the lion hasn't mated yet
		//kind of herds them to the middle, where hopefully they have a greater chance of mating
		if (x < 3*width/7) return Direction.EAST;
		else if (x > 4*width/7) return Direction.WEST;
		else if (y < 3*height/7) return Direction.SOUTH;
		else if (y > 4*height/7) return Direction.NORTH;
		Direction[] dirs = {Direction.SOUTH, Direction.WEST, Direction.NORTH, 
				Direction.EAST, Direction.CENTER};
		Random rand = new Random();
		return dirs[rand.nextInt(5)];
	}
	
	@Override
	public String toString() { // pretends to be an ant
		return "%";
	}
	

	/**
	 * Helper function for if the lion has any hostile neighbors.
	 * Returns true if and only if all four directions are " " or "%".
	 * 
	 * @return if lion has no hostile neighbors
	 */
	public boolean hasNoHostileNeighbor() {
		return ((hasNeighbor(" ", Direction.NORTH)||hasNeighbor(ANT_LION_STRING, Direction.NORTH))
			&&(hasNeighbor(" ", Direction.EAST)||hasNeighbor(ANT_LION_STRING, Direction.EAST))
			&&(hasNeighbor(" ", Direction.SOUTH)||hasNeighbor(ANT_LION_STRING, Direction.SOUTH))
			&&(hasNeighbor(" ", Direction.WEST)||hasNeighbor(ANT_LION_STRING, Direction.WEST))
			);
	}
	
	/**
	 * Helper function for if the lion has a specific neighbor in a specific direction.
	 * 
	 * @param s the toString of the critter
	 * @param d the direction we want to test
	 * @return whether the lion neighbors a "s" critter in direction d
	 */
	public boolean hasNeighbor(String s, Direction d) {
		return getNeighbor(d).equals(s);
	}
	
	
	/**
	 * Returns whether a string is a number, and therefore either a hippo or someone 
	 * who disguised themselves as a hippo.
	 * 
	 * @param s the string of the critter we want to examine
	 * @return whether the parameter is a number(hippo)
	 */
	public boolean isHippo(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
