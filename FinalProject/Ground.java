// Gene Yang
// Final Assignment Ground.java
// Creates the terrain, with a 5x8 array representing the 5x8 size 100 tiles
// CSIII
// 7/30/20

import java.awt.*;
import java.io.IOException;

public class Ground {
	/**
	 * Array with 0's in places without a tile, and 1's with a tile.
	 */
	private static final int[][] terrain = {
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,1,1,0,0,0},
			{0,0,0,1,1,0,0,0},
			{1,1,1,1,1,1,1,1}
			};
	/**
	 * {@value TERRAIN_TILES_WIDTH} width of the array
	 */
	private static final int TERRAIN_TILES_WIDTH = 8;
	/**
	 * {@value TERRAIN_TILES_LENGTH} length of the array
	 */
	private static final int TERRAIN_TILES_LENGTH = 5;
	/**
	 * {@value GRID_TILE_SIZE} size of each square tile
	 */
	private static final int GRID_TILE_SIZE = 100;
	
	/**
	 * @return terrain of the map, which is a flat plain with a wall in the middle
	 */
	public int[][] getTerrain() {
		return Ground.terrain;
	}
	
	/**
	 * Draws the ground by by drawing tiles whenever there's a 1 in the 2D array.
	 * 
	 * @param g Graphics to draw with
	 * @param panel DrawingPanel to draw on
	 * @throws IOException
	 */
	public void draw(Graphics g, DrawingPanel panel) throws IOException {		
		for (int i = 0; i < TERRAIN_TILES_LENGTH; i++) {
			for (int j = 0; j < TERRAIN_TILES_WIDTH; j++) {
				if (terrain[i][j] != 0) {
					GridTile tile = new GridTile(GRID_TILE_SIZE*j, GRID_TILE_SIZE*i);
					tile.draw(g);
				}
			}
		}
	}
}
