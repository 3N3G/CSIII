import java.awt.*;
import java.awt.geom.Rectangle2D;

// a 20x20 rectangle that disappears when hit

public class Target {
	private int x;
	private int y;
	private int hitCount;
	private Graphics g;
	
	public Target(Graphics g) {
		x = 690;
		y = 380;
		hitCount = 0;
		this.g = g;
	}
	
	/**
	 * Records if this target is hit by a projectile
	 * 
	 * @param p the projectile to test
	 * @return whether the projectile collided with 
	 */
	public boolean collides(Projectile p) {
		
		if (this.getShape().getBounds2D().intersects(p.getShape().getBounds2D())) {
			System.out.println("Hit");
			hitCount++;
			return true;
		}
		return false;
	}
	
	/**
	 * Draws the target when it hasn't been hit enough
	 * 
	 * @param g
	 */
	public void draw() {
		if (hitCount < 10) {
			g.setColor(Color.RED);
			g.fillRect(x, y, 20, 20);
		}
	}
	
	/**
	 * Clears the Target, by drawing white over it
	 * @param g 
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
