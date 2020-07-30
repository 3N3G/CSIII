// Gene Yang
// Final Assignment GridTile.java
// Creates a 100x100 grid tile with a fixed image.
// CSIII
// 7/30/20

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GridTile {
	private int x;
	private int y;
	private BufferedImage flatImage;
	private final int GRID_TILE_SIZE = 100;
	private final File TILE_IMAGE = new File("/Users/geneyang/Documents/workspace/CSIIIFinalProject/src/Tile.png");
	
	/**
	 * Sets the gridtile's location and image.
	 * 
	 * @param x x value of the gridtile's top left
	 * @param y y value of the gridtile's top left
	 * @throws IOException
	 */
	public GridTile(int x, int y) throws IOException {
		this.x = x;
		this.y = y;
		this.flatImage = ImageIO.read(TILE_IMAGE);
	}
	

	/**
	 * Draws the gridtile at the specified location.
	 * 
	 * @param g Graphics to draw with
	 */
	public void draw(Graphics g) {
		g.drawImage(flatImage, x, y, x+GRID_TILE_SIZE, y+GRID_TILE_SIZE, 0, 0, GRID_TILE_SIZE, GRID_TILE_SIZE, null);
	}
	
}
