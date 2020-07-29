import java.awt.*;
import java.util.*;

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
		this.dX = Math.cos((double)angle*Math.PI/180) * power;
		this.dY = Math.sin((double)angle*Math.PI/180) * power;
		bullets.add(this);
	}
	
	public ArrayList<Projectile> getBullets () {
		return this.bullets;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
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
		this.dX = Math.cos((double)angle*Math.PI/180) * power;
		this.dY = Math.sin((double)angle*Math.PI/180) * power;
		while (!bullets.isEmpty()) {
			bullets.remove(0);
		}
		bullets.add(this);
	}
	
	/**
	 * Draws the projectile
	 * 
	 * @param g
	 */
	
	public void draw(Graphics g) {
		if (exists) {
			g.setColor(Color.RED);
			g.fillOval((int)x-10, (int)y-10, 20, 20);
		} else {
			g.setColor(Color.WHITE);
			g.fillOval((int)x-10, (int)y-10, 20, 20);
		}
	}
	
	public void shoot() {
		this.x += this.dX;
		this.y -= this.dY;
		
		this.dY -= 1.0/3.0; // goes down 10 per second, 30 fps means 1/3
	}
	
	public Shape getShape() {
		return new Rectangle ((int)x-10,(int)y-10,20,20);
	}
	
	/**
	 * Checks if the projectile has hit any walls/the ground
	 * 
	 * 
	 * @return
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
