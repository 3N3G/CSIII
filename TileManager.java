// Gene Yang
// Assignment 11 TileManager.java
// Creating the functions that 
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
         Tile tileToRaise = getTopOrBottomTile(x,y,true);
         tileList.remove(tileToRaise);
         tileList.add(tileList.size(), tileToRaise);
      }
	}
	
	/**
	 * Takes the top tile that includes the given xy point, and brings it to the bottom of all tiles.
	 * Does this by moving it to the bottom of the tileList.
	 * 
	 * @param x
	 * @param y
	 */
	public void lower (int x, int y) {
		if (hasHit(x,y)) {
         Tile tileToLower = getTopOrBottomTile(x,y,true);
         tileList.remove(tileToLower);
         tileList.add(0, tileToLower);
      }
	}
	
	/**
	 * Deletes the top tile that includes the given xy point.
	 * Does nothing if there is no tile there.
	 * 
	 * @param x
	 * @param y
	 */
	public void delete(int x, int y) {
		if (hasHit(x,y)) {
         Tile tileToDelete = getTopOrBottomTile(x,y,true);
         tileList.remove(tileToDelete);
      }
	}
	
	/**
	 * Deletes every tile that includes the given xy point.
	 * 
	 * @param x
	 * @param y
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
	 * 
	 * @param width
	 * @param height
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
	 * Gets the very top or very bottom tile at a certain xy value
	 * Technically doesn't need to get bottom ever, but just in case I wrote it,
	 * as they are very similar
	 * 
	 * @param x x coordinate of mouse click
	 * @param y y coordinate of mouse click
	 * @param isTop
	 * @return
	 */
   // boolean true to get top tile, false to get bottom tile
   private Tile getTopOrBottomTile(int x, int y, boolean isTop) {
      Tile top;
      if (isTop) {
         top = tileList.get(tileList.size()-1); 
         // Even if this tile isn't on the mouse point, moving it to the top doesn't matter.
         // However if you want to log actions done, this will show as a move even though nothing
         // changes.
         for (Tile t : tileList) {
            if (t.isHit(x,y)) {
               top = t;
            }
         }
      } else {
         top = tileList.get(0);
         
         for (Tile t : reverseOrder(tileList)) {
            if (t.isHit(x,y)) {
               top = t;
            }
         }
      }
      
      if (hasHit(x,y)) {
         return top;
      }
      
      return null; // should never return this if you check hasHit() before using this
        
   }
   
   /**
    * Given a point, returns if there are tiles on that point
    * 
    * @param x
    * @param y
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
   
   /**
    * Reverses the order of an ArrayList of Tiles. Used to easily get the bottom tile 
    * in a mouse click.
    * 
    * @param orig
    * @return
    */
   private ArrayList<Tile> reverseOrder(ArrayList<Tile> orig) {
      ArrayList<Tile> reversed = new ArrayList<>();
      for (Tile s : orig) {
         reversed.add(0,s);
      }
      return reversed;
   }
}
