import java.awt.*;
import java.util.*;
import java.io.*;

public class Projectile{
	private double x;
	private double y;
	private double dX;
	private double dY;
	private boolean exists;
	private ArrayList<Projectile> bullets = new ArrayList<>();
	
	
	public Projectile(double initX, double initY, int angle, int power) {
		this.x = initX;
		this.y = initY;
		this.dX = Math.cos((double)angle*Math.PI/180) * power / 10;
		this.dY = Math.sin((double)angle*Math.PI/180) * power / 10;
		bullets.add(this);
	}
	
	/**
	 * @return current x value of the projectile
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * @return current y value of the projectile
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Clears the projectile by drawing a white circle around the projectile's locatoin
	 * @param g Graphics 
	 */
	public void clear(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int)x-11, (int)y-11, 22, 22);
	}
	
	/**
	 * Resets the projectile
	 * 
	 * @param initX
	 * @param initY
	 * @param angle
	 * @param power
	 */
	public void reset(double initX, double initY, int angle, int power, Graphics g) {
		this.clear(g);
		
		this.exists = true;
		this.x = initX;
		this.y = initY;
		this.dX = Math.cos((double)angle*Math.PI/180) * power / 10;
		this.dY = Math.sin((double)angle*Math.PI/180) * power / 10;
		while (!bullets.isEmpty()) {
			bullets.remove(0);
		}
		bullets.add(this);
	}
	
	/**
	 * Draws the projectile, a red ball.
	 * 
	 * @param g
	 */
	
	public void draw(Graphics g) {
		if (exists) {
			g.setColor(Color.BLUE);
			g.fillOval((int)x-10, (int)y-10, 20, 20);
		} else {
			g.setColor(Color.WHITE);
			g.fillOval((int)x-10, (int)y-10, 20, 20);
		}
	}
	
	/**
	 * Moves the projectile by a certain amount dx and dy. Then it lowers dy, the amount to 
	 * change the next y value by.
	 */
	public void shoot() {
		this.x += this.dX;
		this.y -= this.dY;
		
		this.dY -= 1.0/2.0; 
	}
	
	/**
	 * @return the rectangle that bounds the circle that is the projectile
	 */
	public Shape getShape() {
		return new Rectangle ((int)x-10,(int)y-10,20,20);
	}
	
	/**
	 * Checks if the projectile has hit any walls/the ground. If it does, remove that bullet,
	 * so it doesn't get drawn in the future, and disappears.
	 * 
	 * @return Whether the projectile collides with the walls
	 */
	public boolean checkGround(Graphics g) {
		if ((this.x < 800) && (this.y < 500) && (this.x > 0) && (this.y > 0)) {
			int squareX = (int) (this.x/100.0);
			int squareY = (int) (this.y/100.0);
			
			if (new Ground().terrain()[squareY][squareX] != 0) {
				this.clear(g);
				exists = false;
				this.x = 900;
				bullets.remove(this);
				return true;

			}
		}
		return false;
		
	}

	
	
}
