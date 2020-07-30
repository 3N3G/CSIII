// Gene Yang
// Final Assignment DirectionLine.java
// Creates the direction line assigned to the tank, with functions to modify and draw it.
// CSIII
// 7/30/20

import java.awt.*;


public class DirectionLine {
	/**
	 * x value of the original corner of the line
	 */
	private int x;
	/**
	 * y value of the original corner of the line
	 */
	private int y;
	/**
	 * power of the shot
	 */
	private int power;
	/**
	 * angle of the shot
	 */
	private int angle;
	/**
	 * Graphics to draw with
	 */
	private Graphics g;
	/**
	 * {@value DEG_TO_RAD} constant to multiply a degree amount to convert it to radians
	 */
	private static final double DEG_TO_RAD = Math.PI/180.0; 
	
	
	/**
	 * This constructor creates the direction line.
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
	 * @param g Graphics so that clear function can use it
	 */
	public void draw(Graphics g) {
		g.drawLine(x, y, (int)(x+power/2*Math.cos((double)angle*DEG_TO_RAD)),
				(int)(y-power/2*Math.sin(angle*DEG_TO_RAD)));
	}	
	
	/**
	 * Clears the line, by drawing a thicker line in white over it.
	 */
	public void clear() {
		g.setColor(Color.WHITE);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		this.draw(g2);
		g2.setStroke(new BasicStroke(1));
	}
}
