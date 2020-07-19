// Gene Y
// Assignment 10 Bird.java
// Creates the Bird class and the critter behaviors associated with it
// CSIII
// 7/17/20

import java.awt.Color;

/**
 * @author geneyang
 */

public class Bird extends Critter{
	/**
	 * The current direction that the bird will next travel.
	 */
	private Direction currentDirection;
	
	/**
	 * The previous direction that the bird traveled.
	 */
	private Direction lastDirection;
	
	/**
	 * How many moves left in one direction.
	 */
	private int movesLeft;
	
	
	/**
	 * The constructor sets the direction the Bird starts in, the last move, 
	 * and the number of moves left before it needs to change direction.
	 * 
	 * Birds move in a square, never eat, and change appearance based on direction.
	 */
	public Bird (){
		currentDirection = Direction.NORTH;
		movesLeft = 2; // 3 steps per direction
	}
	
	/**
	 * Whether the bird should eat.
	 * 
	 * @return false, Bird never eats
	 */
	public boolean eat() {
		return false;
	}
	
	/**
	 * Roars at ants, otherwise pounces.
	 * 
	 * @param opponent toString of the opponent
	 * @return the Attack pounce
	 */
	public Attack fight(String opponent) {
		if (opponent.equals("%")){ // the toString of an ant
			return Attack.ROAR;
		}
		return Attack.POUNCE;
	}
	
	/**
	 * Gets the color of the bird.
	 * 
	 * @return the color blue, all birds are blue
	 */
	public Color getColor() {
		return Color.BLUE;
	}
	/**
	 * Gets the direction of the bird for movement.
	 * Goes three steps north, east, south, west, then repeats .
	 * 
	 * @return the direction
	 */
	public Direction getMove() {
		if(currentDirection == Direction.NORTH){
			if (movesLeft == 0) {
				currentDirection = Direction.EAST;
				movesLeft = 3;
			}
			movesLeft--;
			lastDirection = Direction.NORTH;
			return Direction.NORTH;
		}
		
		if(currentDirection == Direction.EAST){
			if (movesLeft == 0) {
				currentDirection = Direction.SOUTH;
				movesLeft = 3;
			}
			movesLeft--;
			lastDirection = Direction.EAST;
			return Direction.EAST;
		}
		
		if(currentDirection == Direction.SOUTH){
			if (movesLeft == 0) {
				currentDirection = Direction.WEST;
				movesLeft = 3;
			}
			movesLeft--;
			lastDirection = Direction.SOUTH;
			return Direction.SOUTH;
		}
		
		if (movesLeft == 0) {
			currentDirection = Direction.NORTH;
			movesLeft = 3;
		}
		movesLeft--;
		lastDirection = Direction.WEST;
		return Direction.WEST;
	}
	
	@Override
	public String toString() { 
		// Using the direction booleans alone is hard because that marks it's next move
		if (lastDirection == Direction.EAST){
			return ">";
		}
		if (lastDirection == Direction.SOUTH){
			return "V";
		}
		if (lastDirection == Direction.WEST){
			return "<";
		}
		return "^";
	}	

}
