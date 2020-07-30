// Gene Yang
// Final Assignment Projectile.java
// Creates a Projectile and the classes to modify it
// CSIII
// 7/30/20

import java.awt.*;
import java.util.*;
import java.io.*;
import java.applet.*;
import java.net.*;
import javax.sound.sampled.*;

public class Projectile{
	/**
	 * x value of the projectile
	 */
	private double x;
	
	/**
	 * y value of the projectile
	 */
	private double y;
	
	/**
	 * Change in x value of the projectile
	 */
	private double dX;
	
	/**
	 * Change in y value of the projectile
	 */
	private double dY;
	
	/**
	 * Whether the projectile exists
	 */
	private boolean exists;
	
	/**
	 * All bullets to be drawn. Ensures that only new ones are drawn.
	 */
	private ArrayList<Projectile> bullets = new ArrayList<>();
	
	/**
	 * Graphics to draw with
	 */
	private Graphics g;
	
	/**
	 * {@value PROJECTILE_RADIUS} radius of the projectile
	 */
	private static final int PROJECTILE_RADIUS = 10;
	
	/**
	 * {@value DEG_TO_RAD} constant to multiply a number by to turn it into radians from degrees
	 */
	private static final double DEG_TO_RAD = Math.PI/180.0; 
	
	/**
	 * {@value SHOT_SOUND} filename for the shot sound
	 */
	private static final String SHOT_SOUND =
			"/Users/geneyang/Documents/workspace/CSIIIFinalProject/src/Shot.wav";
	
	/**
	 * {@value EXPLOSION_SOUND} filename for the explosion sound
	 */
	private static final String EXPLOSION_SOUND =
			"/Users/geneyang/Documents/workspace/CSIIIFinalProject/src/Explosion+5.wav";
	
	/**
	 * {@value TILE_SIZE} the side length of a tile
	 */
	private static final double TILE_SIZE = 100;
	
	/**
	 * {@value PANEL_WIDTH} width of the panel
	 */
	private static final int PANEL_WIDTH = 800;
	
	/**
	 * {@value PANEL_HEIGHT} height of the panel
	 */
	private static final int PANEL_HEIGHT = 500;
	
	/**
	 * {@value POWER_SCALE} how much the power value was scaled up
	 */
	private static final int POWER_SCALE = 10;
	
	/**
	 * {@value X_OUT_OF_BOUNDARY} an x value that is outside of the boundary of the panel
	 */
	private static final int X_OUT_OF_BOUNDARY = 1000;
	
	/**
	 * {@value GRAVITY_CONSTANT} how much gravity pulls at change in y
	 */
	private static final double GRAVITY_CONSTANT = 1.0/2.0;	
	
	/**
	 * this constructor brings in the Graphics variable g that allows for drawing 
	 * elsewhere in the class.
	 * @param g
	 */
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
		// The top left corner of the circle's rectangle bound starts one unit up and left,
		// and it reaches out to one unit below and to the right of the bottom right corner.
		g.fillOval((int)x-(PROJECTILE_RADIUS + 1), (int)y-(PROJECTILE_RADIUS + 1),
				2*(PROJECTILE_RADIUS + 1), 2*(PROJECTILE_RADIUS + 1));
	}
	
	/**
	 * Resets the projectile
	 * 
	 * @param initX X value that it starts at
	 * @param initY Y value that it starts at
	 * @param angle angle of the trajectory
	 * @param power power of the shot
	 */
	public void reset(double initX, double initY, int angle, int power) {
		AudioPlayer.playSound(SHOT_SOUND);
		this.clear();
		
		this.exists = true;
		
		this.x = initX;
		this.y = initY;
		
		// The power scales up 10 times too much, so it needs to get scaled down for calculation
		this.dX = Math.cos((double)angle*DEG_TO_RAD) * power / POWER_SCALE; 
		this.dY = Math.sin((double)angle*DEG_TO_RAD) * power / POWER_SCALE;
		
		bullets.clear();
		bullets.add(this);
	}
	
	/**
	 * Draws the projectile, a red ball.
	 */
	
	public void draw() {
		if (exists) {
			g.setColor(Color.BLUE);
			g.fillOval((int)x-PROJECTILE_RADIUS, (int)y-PROJECTILE_RADIUS,
					2*PROJECTILE_RADIUS, 2*PROJECTILE_RADIUS);
		} else {
			g.setColor(Color.WHITE);
			g.fillOval((int)x-PROJECTILE_RADIUS, (int)y-PROJECTILE_RADIUS,
					2*PROJECTILE_RADIUS, 2*PROJECTILE_RADIUS);
		}
	}
	
	/**
	 * Moves the projectile by a certain amount dx and dy. Then it lowers dy, the amount to 
	 * change the next y value by.
	 */
	public void shoot() {
		this.x += this.dX;
		this.y -= this.dY;
		
		// Gravity lowers the amount it travels vertically by a certain constant
		this.dY -= GRAVITY_CONSTANT; 
	}
	
	/**
	 * @return the rectangle that bounds the circle that is the projectile
	 */
	public Shape getShape() {
		return new Rectangle ((int)x-PROJECTILE_RADIUS, (int)y-PROJECTILE_RADIUS,
				2*PROJECTILE_RADIUS, 2*PROJECTILE_RADIUS);
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
				// Moves the projectile out of the screen, so it can't cause any issues
				this.x = X_OUT_OF_BOUNDARY;
				bullets.remove(this);
				return true;

			}
		}
		return false;
		
	}

	
	
}
