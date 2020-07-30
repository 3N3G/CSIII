// Gene Yang
// Final Assignment DirectionLine.java
// Creates the direction line assigned to the tank, with functions to modify and draw it.
// CSIII
// 7/30/20

import java.awt.*;


public class DirectionLine {
	private int x;
	private int y;
	private int power;
	private int angle;
	private Graphics g;
	private final double DEG_TO_RAD = Math.PI/180.0; 
	
	
// Constructor taking in x, y, power, angle
// draw 
	/**
	 * This constructor creats the direction line
	 * @param t Tank that has the direction
	 * @param g Graphics
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
		g.drawLine(x, y, (int)(x+power/2*Math.cos((double)angle*DEG_TO_RAD)), (int)(y-power/2*Math.sin(angle*DEG_TO_RAD)));
	}	
	
	/**
	 * Clears the line, by drawing a thicker line in white over it.
	 */
	public void clear() {
		g.setColor(Color.WHITE);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(x, y, (int)(x+power/2*Math.cos((double)angle*DEG_TO_RAD)), (int)(y-power/2*Math.sin(angle*DEG_TO_RAD)));
		g2.setStroke(new BasicStroke(1));
	}
}
