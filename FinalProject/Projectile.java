import java.awt.*;
import java.util.*;
import java.io.*;
import java.applet.*;
import java.net.*;
import javax.sound.sampled.*;

public class Projectile{
	private double x;
	private double y;
	private double dX;
	private double dY;
	private boolean exists;
	private ArrayList<Projectile> bullets = new ArrayList<>();
	private Graphics g;
	
	private final int PROJECTILE_RADIUS = 10;
	private final double DEG_TO_RAD = Math.PI/180.0; 
	private static final String SHOT_SOUND = "/Users/geneyang/Documents/workspace/CSIIIFinalProject/src/Shot.wav";
	private static final String EXPLOSION_SOUND= "/Users/geneyang/Documents/workspace/CSIIIFinalProject/src/Explosion+5.wav";
	private final double TILE_SIZE = 100;
	private final int PANEL_WIDTH = 800;
	private final int PANEL_HEIGHT = 500;
	
	public Projectile(Graphics g) {
		this.g = g;
	}
	
	/**
	 * Sets the x coordinate of the projectile. Used to get rid of the projectile.
	 * @param x value to set it
	 */
	public void setX(int x) {
		this.x = x;
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
	public void clear() {
		g.setColor(Color.WHITE);
		g.fillOval((int)x-(PROJECTILE_RADIUS + 1), (int)y-(PROJECTILE_RADIUS + 1), 2*(PROJECTILE_RADIUS + 1), 2*(PROJECTILE_RADIUS + 1));
	}
	
	/**
	 * Resets the projectile
	 * 
	 * @param initX
	 * @param initY
	 * @param angle
	 * @param power
	 */
	public void reset(double initX, double initY, int angle, int power) {
		AudioPlayer.playSound(SHOT_SOUND);
		this.clear();
		
		this.exists = true;
		this.x = initX;
		this.y = initY;
		this.dX = Math.cos((double)angle*DEG_TO_RAD) * power / 10;
		this.dY = Math.sin((double)angle*DEG_TO_RAD) * power / 10;
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
	
	public void draw() {
		if (exists) {
			g.setColor(Color.BLUE);
			g.fillOval((int)x-PROJECTILE_RADIUS, (int)y-PROJECTILE_RADIUS, 2*PROJECTILE_RADIUS, 2*PROJECTILE_RADIUS);
		} else {
			g.setColor(Color.WHITE);
			g.fillOval((int)x-PROJECTILE_RADIUS, (int)y-PROJECTILE_RADIUS, 2*PROJECTILE_RADIUS, 2*PROJECTILE_RADIUS);
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
		return new Rectangle ((int)x-PROJECTILE_RADIUS,(int)y-PROJECTILE_RADIUS,2*PROJECTILE_RADIUS,2*PROJECTILE_RADIUS);
	}
	
	/**
	 * Checks if the projectile has hit any walls/the ground. If it does, remove that bullet,
	 * so it doesn't get drawn in the future, and disappears.
	 * First, if it is in the boundary of the panel, and the tile of the terrain it's on is nonzero
	 * meaning there is a grid tile there, then it will disappear. Otherwise, nothing happens.
	 * 
	 * It also plays the explosion sound, and removes the bullet and its influence after hitting 
	 * the ground.
	 * 
	 * @return Whether the projectile collides with the walls
	 */
	public boolean checkGround() {
		// If the projectile is within the boundary of the panel
		if ((this.x < PANEL_WIDTH) && (this.y < PANEL_HEIGHT) && (this.x > 0) && (this.y > 0)) {
			int squareX = (int) (this.x/TILE_SIZE);
			int squareY = (int) (this.y/TILE_SIZE);
			
			if (new Ground().getTerrain()[squareY][squareX] != 0) {
			    //Play the explosion sound
			    AudioPlayer.playSound(EXPLOSION_SOUND);

			    this.clear();
				exists = false;
				this.x = 900; // Random number greater than 800, the width
				bullets.remove(this);
				return true;

			}
		}
		return false;
		
	}

	
	
}
