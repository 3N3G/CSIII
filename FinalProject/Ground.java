import java.awt.*;
import java.io.IOException;

public class Ground {
	private int[][] terrain = {{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,2,3,0,0,0},
			{0,0,0,2,3,0,0,0},
			{1,1,1,1,1,1,1,1}};
	
	public Ground() {
		
	}
	
	public int[][] terrain() {
		return this.terrain;
	}
	
	public void draw(Graphics g, DrawingPanel panel) throws IOException {		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				//System.out.print(terrain[i][j] + " ");
				if (terrain[i][j] != 0) {
					GridTile tile4 = new GridTile(100*j, 100*i, 1, panel, g);
					tile4.draw(g);
					//System.out.println("nonzero: " + "i: " + i + " j: " + j);
				} else {
					//System.out.println("zero: " + "i: " + i + " j: " + j);
				}
				
				/*
				if (terrain[i][j] == 0) {
					
				} else if (terrain[i][j] == 1) {
					GridTile tile1 = new GridTile(100*i, 100*j, 1, panel, g);
					tile1.draw(g);
				} else if (terrain[i][j] == 2) {
					GridTile tile2 = new GridTile(100*i, 100*j, 2, panel, g);
					tile2.draw(g);
				} else if (terrain[i][j] == 3) {
					GridTile tile3 = new GridTile(100*i, 100*j, 3, panel, g);
					tile3.draw(g);
				} 
				*/
			}
			//System.out.println("<--"+i);
		}
	}
}
