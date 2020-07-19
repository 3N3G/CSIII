// Gene Y
// Assignment 10 Hippo.java
// Creating the Hippo class and the critter behaviors associated with it
// CSIII
// 7/17/20

import java.awt.Color;
import java.util.Random;

/**
 * @author geneyang
 */
public class Hippo extends Critter{
	/**
	 * Whether the hippo is hungry. The hippo stays full once it eats enough food.
	 */
	private boolean hungry;
	
	/**
	 * How many times the hippo still has to eat before becoming not hungry.
	 */
	private int hungerIndex;
	
	/**
	 * How much the hippo still has to travel in its direction.
	 */
	private int amountToTravel;
	
	/**
	 * The direction it is currently traveling in.
	 */
	private Direction dir;
	
	
	/**
	 * This constructor sets the hippo's initial direction and hunger index.
	 * Changes color, appearance, and attack strategy depending on whether it is hungry.
	 * Sets the hungry boolean for whether the hippo is hungry.
	 * 
	 * @param Hunger takes in an integer for its hunger limit, sets the initial direction 
	 * and distance traveled in that direction.
	 */
	public Hippo (int Hunger){
		Random rand = new Random();
		int n = rand.nextInt(4);
		Direction[] dirs = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
		this.dir = dirs[n];
		this.amountToTravel = 5;
		
		//Sets hunger value and boolean hungry
		this.hungry = true;
		hungerIndex = Hunger;
	}
	
	/**
	 * This function tells whether the Hippo eats or not. 
	 * Eats if hungry (hunger index > 0), otherwise doesn't.
	 * 
	 * @return whether it eats or not
	 */
	public boolean eat(){
		if(hungerIndex>0){
			hungerIndex--;
			return true;
		}
		hungry = false;
		return false;
	}
	
	/**
	 * This method dictates how the Hippo attacks. It scratches when hungry, otherwise pounces.
	 * 
	 * @param opponent
	 * 
	 * @return the Attack that the Hippo does
	 */
	public Attack fight(String opponent){
		if (hungry){
			return Attack.SCRATCH;
		}
		return Attack.POUNCE;
	}	
	
	
	/**
	 * Gives the color of the Hippo, which is gray when hungry, otherwise white.
	 * 
	 * @return the color of the Hippo
	 */
	public Color getColor(){
		if(hungerIndex > 0){
			return Color.GRAY;
		}
		return Color.WHITE;
	}
	
	/**
	 * Gets the direction of the Hippo for movement.
	 * 
	 * Travels 5 steps in a random direction, then switches direction to a new random direction.
	 * 
	 * @return the direction to move
	 */
	public Direction getMove(){
		if (amountToTravel == 0) {
			Random rand = new Random();
			int n = rand.nextInt(4);
			Direction[] dirs = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
			dir = dirs[n];
			amountToTravel = 5;
		} 
		amountToTravel--;
		return dir;
	}
	
	@Override
	public String toString(){
		return Integer.toString(hungerIndex);
	}
}
