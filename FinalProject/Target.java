import java.awt.*;
import java.awt.geom.Rectangle2D;

// a 50x30 rectangle that disappears when hit

public class Target {
	private int x;
	private int y;
	private int hitCount;
	
	public Target() {
		x = 690;
		y = 370;
		hitCount = 0;
	}
	
	/**
	 * Records if this target is hit by a projectile
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
	 * Draws the target when it hasn't been hit enough
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		if (hitCount < 3) {
			g.setColor(Color.GREEN);
			g.fillRect(x, y, 50, 30);
		}
	}
	
	public void clear(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x-1,y-1,52,32);
	}
	
	
	public Shape getShape() {
		return new Rectangle(x,y,50,30);
	}
	
}
