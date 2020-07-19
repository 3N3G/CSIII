// Gene Y
// Assignment 10 Vulture.java
// Creating the Vultures class and the critter behaviors associated with it
// Inherits bird properties
// CSIII
// 7/17/20

import java.awt.Color;

/**
 * @author geneyang
 */
public class Vulture extends Bird{
	/**
	 * Whether the vulture is hungry. Controls eating and is changed by fighting.
	 */
	private boolean hungry;
	
	/**
	 * This constructor uses the Bird class, and additionally sets hungry to true.
	 * The same as a bird, but with different color and a hungry mechanism.
	 */
	public Vulture() {
		super();
		this.hungry = true;
	}
	
	/**
	 * This method controls whether the Vulture eats, which is only when it's hungry.
	 * 
	 * @return whether the vulture eats
	 */
	@Override
	public boolean eat() {
		if(hungry){
			hungry = false;
			return true;
		}
		return false;
	}
	
	/**
	 * This method tells the Vulture to fight the way a bird would.
	 * Then it sets hungry to be true.
	 * 
	 * @param opponent the opponents toString. Vulture uses bird fight method
	 * @return the same attack as bird
	 */
	@Override
	public Attack fight(String opponent) {
		Attack attackChoice = super.fight(opponent);
		hungry = true;
		return attackChoice;
	}
	
	/**
	 * Sets the color of the Vulture to be black.
	 */
	@Override
	public Color getColor() {
		return Color.BLACK;
	}

	
}
