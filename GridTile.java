import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GridTile {
	private int x;
	private int y;
	private int orientation;
	private BufferedImage flatImage;
	
	
	public GridTile(int x, int y, int orientation, DrawingPanel p, Graphics g) throws IOException {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
		this.flatImage = ImageIO.read(new File("/Users/geneyang/Documents/workspace/CSIIIFinalProject/src/Tile.png"));
		
	}
	

	public void draw(Graphics g) {
		g.drawImage(flatImage, x, y, x+100, y+100, 0, 0, 100, 100, null);
		/*
		if (orientation == 1) {
			g.drawImage(flatImage, x, y, x+100, y+100, 0, 0, 369, 343, null);
		} else if (orientation == 2) {
			
		}
		*/
	}
	
}
