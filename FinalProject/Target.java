// Gene Yang
// Final Assignment Target.java
// Creates a Target, and the functions controlling behaviors when hit
// CSIII
// 7/30/20

import java.awt.*;

// a 20x20 rectangle that disappears when hit

public class Target {
	/**
	 * How many times the target's been hit
	 */
	private int hitCount;
	/**
	 * Graphics to draw with
	 */
	private Graphics g;
	/**
	 * {@value x} x value of the target
	 */
	private final int x = 690;
	/**
	 * {@value y} y value of the target
	 */
	private final int y = 380;
	/**
	 * {@value SIDE_LENGHT} side length of the square target
	 */
	private static final int SIDE_LENGTH = 20;
	
	/**
	 * This constructor sets the Graphics, and resets hitCount to 0. 
	 * @param g Graphics to draw with
	 */
	public Target(Graphics g) {
		hitCount = 0;
		this.g = g;
	}
	
	/**
	 * Records if this target is hit by a projectile, if so, increases the hitCount by 1
	 * 
	 * @param p the projectile to test
	 * @return whether the projectile collided with 
	 */
	public boolean collides(Projectile p) {
		
		if (this.getShape().getBounds2D().intersects(p.getShape().getBounds2D())) {
			hitCount++;
			return true;
		}
		return false;
	}
	
	/**
	 * Draws the target when it hasn't been hit three times yet
	 */
	public void draw() {
		if (hitCount < 3) {
			g.setColor(Color.RED);
			g.fillRect(x, y, SIDE_LENGTH, SIDE_LENGTH);
		}
	}
	
	/**
	 * Clears the Target, by drawing white over it
	 */
	public void clear() {
		g.setColor(Color.WHITE);
		g.fillRect(x-1,y-1,SIDE_LENGTH+2,SIDE_LENGTH+2);
		// Fills the rectangle, with an extra unit to either side
	}
	
	/**
	 * Returns the rectangle as the border of the target
	 * @return a 20x20 square with top left coordinate x,y
	 */
	public Shape getShape() {
		return new Rectangle(x,y,SIDE_LENGTH,SIDE_LENGTH);
	}
	
}
