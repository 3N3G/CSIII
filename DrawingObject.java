import java.awt.*;
import java.util.*;


public abstract class DrawingObject {
	private ArrayList<Object> objList = new ArrayList<>();
	private double x;
	private double y;
	private double width;
	private double height;
	private Color color;
	
	public DrawingObject(double x, double y, double width, double height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public abstract void draw(Graphics g);
	public ArrayList<Object> getList() {
		return objList;
	}
	
	
}
