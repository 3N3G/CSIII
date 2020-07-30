import java.awt.*;
import java.awt.geom.Rectangle2D;

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
			g.fillRect(x, y, 20, 20);
		}
	}
	
	/**
	 * Clears the Target, by drawing white over it
	 */
	public void clear() {
		g.setColor(Color.WHITE);
		g.fillRect(x-1,y-1,22,22);
	}
	
	/**
	 * Returns the rectangle as the border of the target
	 * @return a 20x20 square with top left coordinate x,y
	 */
	public Shape getShape() {
		return new Rectangle(x,y,20,20);
	}
	
}
