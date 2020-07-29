import java.awt.Color;
import java.awt.Graphics;

public class Tank {
	private double x;
	private double y;
	private double dX;
	private double dY;
	private int angle;
	private int power;
	private int health;
	
	public Tank() {
		this.angle = 45;
		this.power = 15;
		this.health = 60;
		this.x = 30;
		this.y = 385;
	}
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public int getAngle() {
		return this.angle;
	}
	
	public void addAngle() {
		this.angle ++;
	}
	
	public void minusAngle() {
		this.angle --;
	}
	
	public int getPower() {
		return this.power;
	}

	public int getHealth() {
		return this.health;
	}
	
	public void hit() {
		this.health = this.health - 20;
	}
	
	public void addX(int x) {
		this.x += x;
	}
	
	public void addY(int y) {
		this.y += y;
	}
	
	public void draw(Graphics g) {
		//g.setColor(Color.WHITE);
		//g.fillRect(0, 0, 800, 500);
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, 25, 15);
		g.setColor(Color.BLACK);
		g.drawRect((int)x, (int)y, 25, 15);
	}
	
	public void clear(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)x-1, (int)y-1, 27, 17);
	}
	
}
