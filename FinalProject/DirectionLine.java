import java.util.*;
import java.awt.*;


public class DirectionLine {
	private int x;
	private int y;
	private int power;
	private int angle;
	private Graphics g;
	
// Constructor taking in x, y, power, angle
// draw 
	/**
	 * This constructor 
	 * @param t
	 * @param g
	 */
	public DirectionLine(Tank t, Graphics g) {
		this.x = (int) t.getX();
		this.y = (int) t.getY();
		this.power = t.getPower();
		this.angle = t.getAngle();
		this.g = g;
	}
	
	/**
	 * @return x value of the base of the line
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return y value of the base of the line
	 */
	public int getY() {
		return y;
	}	
	
	/**
	 * Draws the direction line in the direction that the projectile will shoot, proportional
	 * to the power of the shot.
	 */
	public void draw() {
		g.drawLine(x, y, (int)(x+power/2*Math.cos((double)angle*Math.PI/180)), (int)(y-power/2*Math.sin(angle*Math.PI/180)));
	}	
	
	/**
	 * Clears the line, by drawing a thicker line in white over it.
	 */
	public void clear() {
		g.setColor(Color.WHITE);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(x, y, (int)(x+power/2*Math.cos((double)angle*Math.PI/180)), (int)(y-power/2*Math.sin(angle*Math.PI/180)));
		g2.setStroke(new BasicStroke(1));
	}
}
