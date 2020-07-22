// Gene Yang
// Assignment 11 RectangleTile.java
// Creating the class for a Rectangle Tile, which has all the functions inherited from Tile
// CSIII
// 7/21/20

import java.awt.Color;
import java.awt.Graphics;

public class RectangleTile extends Tile{
	/**
	 * Constructs a rectangle tile using the super's constructor.
	 * Because the rectangle is the same as it's border, we can directly call super
	 * without any changes.
	 * 
	 * @param x x coordinate of top left corner
	 * @param y y coordinate of top left corner
	 * @param w width of rectangle
	 * @param h height of rectangle
	 * @param c color of rectangle
	 */
	public RectangleTile(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	/**
	 * Draws the given rectangle. For some reason, only draws half the border
    * 
    * @param the Graphics that are used to draw the rectangle
	 */
	@Override
	public void draw(Graphics g) {
      g.setColor(getColor());
      g.fillRect(getX(), getY(), getWidth(), getHeight());
      g.setColor(Color.BLACK);
      g.drawRect(getX(), getY(), getWidth(), getHeight());
	}

	/**
	 * Gives whether the rectangle contains the given point, by ensuring the x value is
	 * greater then the x value of the leftmost side, and less than the rightmost side,
	 * and similarly for y value.
	 * 
	 * @param x x value of the point
	 * @param y y value of the point
	 * @return whether the rectangle contains the point with that xy value.
	 */
	@Override
	public boolean isHit(int x, int y) {
		boolean inX = (super.getX() < x) && (x < super.getX()+super.getWidth());
		boolean inY = (super.getY() < y) && (y < super.getY()+super.getHeight());
		return inX && inY;
	}
	
	/**
	 * @return the toString of the rectangle, such as: "Rectangle: x=1,y=4,w=2,h=7".
	 */
	@Override
	public String toString() {
		return "Rectangle: " + super.toString();
	}

}
