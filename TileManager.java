// Gene Yang
// Assignment 11 TileManager.java
// Creates the functions that modify a list of tiles
// CSIII
// 7/21/20

import java.util.*;
import java.awt.*;

public class TileManager {
    /**
    * An ArrayList of Tiles, with order indicating the order that the Tile is drawn on the screen.
    */
	private ArrayList<Tile> tileList;
	
	/**
	 * This constructor initializes a new ArrayList for the tileList.
	 */
	public TileManager () {
		tileList = new ArrayList<>();
	}
	
	/**
	 * Adds a given tile to the ArrayList.
	 * This shows up on the screen with a new shape on the top.
	 * If the parameter is null, adds nothing and returns zero.
	 * 
	 * @param tile
	 * @return Whether there is a tile to add
	 */
	public boolean addTile (Tile tile) {
		if (tile == null) {
			return false;
		}
		tileList.add(tile);
		return true;
	}
	
	/**
	 * Draws all the Tiles in the tileList, in order from first to last.
	 * 
	 * @param g
	 */
	public void drawAll (Graphics g) {
		for (Tile t : tileList) {
			t.draw(g);
		}
	}
	
	/**
	 * Takes the top tile that includes the given xy point, and brings it to the top of all tiles.
	 * Does this by moving it to the top of the tileList.
	 * 
	 * @param x x coordinate of the mouse click point
	 * @param y y coordinate of the mouse click point
	 */
	public void raise (int x, int y) {
      if (hasHit(x,y)) {
         Tile tileToRaise = getTopTile(x,y);
         tileList.remove(tileToRaise);
         tileList.add(tileList.size(), tileToRaise);
      }
	}
	
	/**
	 * Takes the top tile that includes the given xy point, and brings it to the bottom of all tiles.
	 * Does this by moving it to the bottom of the tileList.
	 * 
	 * @param x x coordinate of the mouse click point
	 * @param y y coordinate of the mouse click point
	 */
	public void lower (int x, int y) {
		if (hasHit(x,y)) {
         Tile tileToLower = getTopTile(x,y);
         tileList.remove(tileToLower);
         tileList.add(0, tileToLower);
      }
	}
	
	/**
	 * Deletes the top tile that includes the given xy point.
	 * Does nothing if there is no tile there.
	 * 
	 * @param x x coordinate of the mouse click point
	 * @param y y coordinate of the mouse click point
	 */
	public void delete(int x, int y) {
		if (hasHit(x,y)) {
         Tile tileToDelete = getTopTile(x,y);
         tileList.remove(tileToDelete);
      }
	}
	
	/**
	 * Deletes every tile that includes the given xy point.
	 * 
	 * @param x x coordinate of the mouse click point
	 * @param y y coordinate of the mouse click point
	 */
	public void deleteAll(int x, int y) {
		while (hasHit(x,y)) {
         delete(x,y);
      }
	}
	
	/**
	 * Shuffles all the tiles to within a certain boundary.
	 * Used to make sure that all the new tile positions 
	 * are within the borders of the drawing window.
    * Shuffles within (0,0) and (width, height).
	 * 
	 * @param width the width of the drawing window
	 * @param height the height of the drawing window
	 */
	public void shuffle(int width, int height) {
      System.out.println("Start shuffle " + width + ", " + height);
      Random rand = new Random();
      for (Tile t : tileList) {
         int newX = rand.nextInt(width-t.getWidth());
         int newY = rand.nextInt(height-t.getHeight());
         t.setX(newX);
         t.setY(newY);
      }
		Collections.shuffle(tileList);
      System.out.println("End shuffle" + width + ", " + height);
	}
   
	/**
	 * Gets the very top tile at a certain xy value
	 * 
	 * @param x x coordinate of mouse click
	 * @param y y coordinate of mouse click
	 * @return Top tile if it exists, otherwise null
	 */
   private Tile getTopTile(int x, int y) {
      
      for (int i = tileList.size()-1;i >= 0; i--) {
         if (tileList.get(i).isHit(x,y)) {
            return tileList.get(i);
         }
      }
      return null;
   }
   
   /**
    * Given a point, returns if there are tiles on that point
    * 
    * @param x x coordinate of the point
    * @param y y coordinate of the point
    * @return Whether any tile is hit by that xy value
    */
   private boolean hasHit(int x, int y) {
      boolean hasHit = false;
      for (Tile t : tileList) {
         if (t.isHit(x,y)) {
            hasHit = true;
         }
      }
      return hasHit;
   }
   
}
