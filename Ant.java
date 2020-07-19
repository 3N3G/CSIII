// Gene Y
// Assignment 10 Ant.java
// Creates the Ant class and the critter behaviors associated with it
// CSIII
// 7/17/20



import java.awt.Color;

/**
 * @author geneyang
 */
public class Ant extends Critter {
	
	/**
	 * Whether the ant is moving vertically.
	 */
	private boolean walkUpDown;
	
	/**
	 * Whether the ant moving southeast or northeast.
	 */
	private boolean walkSouth;
	
	/**
	 * The constructor sets the path type of the ant (south and east or north and east) as well as
	 * whether the ant is walking vertically.
	 * 
	 * Ants are represented by a "%" character.
	 * 
	 * @param walkSouth is a boolean for whether it goes southeast or northeast
	 */
	public Ant(boolean walkSouth) {
		this.walkUpDown = true; //Whether it is walking vertically at a move
		this.walkSouth = walkSouth;
	}

	/**
	 * Tells what color the ant should be.
	 * 
	 * @return the color red
	 */
	public Color getColor() {
		return Color.red;
	}

	/**
	 * Tells whether the ant should eat.
	 * 
	 * @return true, ants always eat
	 */
	public boolean eat() {
		return true;
	}

	/**
	 * Tells what attack the ant should do, which is always scratch.
	 * 
	 * @return the attack scratch 
	 */
	public Attack fight(String opponent) {
		return Attack.SCRATCH;
	}

	/**
	 * This function gets the direction for the movement of the ant.
	 * Movement of the ant depends on the walkSouth variable, which controls whether it moves
	 * south and east, or north and east.
	 * 
	 * @return The direction that the ant will move
	 */
	public Direction getMove() {
		if (walkUpDown) {
			walkUpDown = false;
			if (walkSouth) return Direction.SOUTH;
			return Direction.NORTH;
		}
		walkUpDown = true;
		return Direction.EAST;
	}

	@Override
	public String toString() {
		return "%"; // an ant
	}

}
