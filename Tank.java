import java.awt.Color;
import java.awt.Graphics;

public class Tank {
	/**
	 * x value of tank
	 */
	private double x;
	/**
	 * y value of tank
	 */
	private double y;
	/**
	 * Angle of tank's shot trajectory
	 */
	private int angle;
	/**
	 * Power of tank's shot
	 */
	private int power;
	/**
	 * Direction line showing angle and power of shot
	 */
	private DirectionLine dir;
	/**
	 * Graphics to draw with
	 */
	private Graphics g;
	/**
	 * {@value ARMY_GREEN} color for army green
	 */
	private final Color ARMY_GREEN = new Color(25,33,13);
	/**
	 * {@value TANK_WIDTH} width of a tank
	 */
	private final int TANK_WIDTH = 25;
	/**
	 * {@value TANK_HEIGHT} height of a tank
	 */
	private final int TANK_HEIGHT = 15;
	
	/**
	 * this constructor creates a tank with predetermined angle, power, x, y, and direction line.
	 * @param g
	 */
	public Tank(Graphics g) {
		this.angle = 45;
		this.power = 150;
		this.x = 35;
		this.y = 385;
		this.dir = new DirectionLine(this, g);
		this.g = g;
	}
	
	/**
	 * @return x coordinate of the tank's top left corner
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * @return y coordinate of the tank's top left corner
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * @return angle of the trajectory to shoot
	 */
	public int getAngle() {
		return this.angle;
	}
	
	/**
	 * Increases the angle of the trajectory by 1, unless it's already at 90 degrees, the max
	 */
	public void addAngle() {
		if (this.angle < 90) {
			dir.clear();
			this.angle ++;
			dir = new DirectionLine(this, g);
		}
	}
	
	/**
	 * Decreases the angle of the trajectory by 1, unless it's already 0 degrees, the minimum
	 */
	public void minusAngle() {
		if (this.angle > 0) {
			dir.clear();
			this.angle --;
			dir = new DirectionLine(this, g);
		}
	}
	
	/**
	 * @return power of the projectile that gets shot
	 */
	public int getPower() {
		return this.power;
	}
	
	/**
	 * Adds a number to the x coordinate of the tank, hence moving it forwards or backwards. 
	 * @param x value to add to the x coordinate of the tank
	 */
	public void addX(int x) {
		this.clear();
		dir.clear();
		this.x += x;
		dir = new DirectionLine(this, g);
		this.draw();
	}
	
	/**
	 * Draws the tank as a rectangle with an army green filling and a black border.
	 */
	public void draw() {
		g.setColor(ARMY_GREEN);
		g.fillRect((int)x, (int)y, TANK_WIDTH, TANK_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect((int)x, (int)y, TANK_WIDTH, TANK_HEIGHT);
		dir.draw();
	}
	
	/**
	 * Clears the tank with a white rectangle.
	 */
	public void clear() {
		g.setColor(Color.WHITE);
		g.fillRect((int)x-1, (int)y-1, TANK_WIDTH + 2, TANK_HEIGHT + 2);
		// covers 1 unit to each side of the tank with white
	}
	
	/**
	 * Increases the power of the projectile shot by 5, up to the max, which is 200.
	 */
	public void powerUp() {
		if (power<200) {
			dir.clear();
			power = power + 5;
			dir = new DirectionLine(this, g);
		}
	}
	
	/**
	 * Decreases the power of the projectile shot by 5, down to the min, which is 0.
	 */
	public void powerDown() {
		if (power > 0) {
			dir.clear();
			this.power = power - 5;
			dir = new DirectionLine(this, g);
		}
	}	
	
	/**
	 * Gets the direction of the projectile shot.
	 * @return direction, as an angle from 0 to 90
	 */
	public DirectionLine getDir() {
		return this.dir;
	}
	
}
