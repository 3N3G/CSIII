// Gene Yang
// The main class of the game, that runs everything

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;


public class TankGame {
	private ArrayList<Object> itemsToDraw = new ArrayList<>();
	
	private final static int WIDTH = 800;
	private final static int HEIGHT = 500;
	
	public static void main(String[] args) throws IOException {
		boolean running = true;
		
	    DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);
	    Graphics g = panel.getGraphics();
	    panel.setBackground(Color.WHITE);
	    
	    Tank t1 = new Tank();
	    Projectile p1 = new Projectile(30,400,45,10);
	    TileKeyListener listener = new TileKeyListener(panel, t1, g, p1);
		panel.addKeyListener(listener);
		
		Target target = new Target();
		
		//target.setTargetClip(g);
		
		Shape r1 = new Rectangle(20,20,50,50);
		Shape r2 = new Rectangle(30, 30, 50, 50); 
		
		if (r1.intersects((Rectangle2D) r2)) {
			System.out.println("test intersect pass");
		}
		
		boolean targetGone = false;
		
		Ground ground = new Ground();
		
		
	    while (running) {
	    	
	    	p1.shoot();
	    	for (Projectile p : p1.getBullets()) {
	    		p1.draw(g);
	    	}
       	 	t1.draw(g);
       	 	ground.draw(g, panel);
       	 	
       	 	if (!target.collides(p1)) {
       	 		//System.out.println("target: " + target.getShape().toString());
       	 		//System.out.println("bullet: " + p1.getShape().toString());
       	 		target.draw(g);
       	 	} else {
       	 		target.clear(g);
       	 	}
       	 	p1.checkGround(g);
       	 	
       	 	
       	 	/*
       	 	 * long updateTime = System.nanoTime() - initTime;
       	 	 * long wait = (long) (1000.0/30.0 - updateTime/1000000);
       	 	*/
       	 	try {
       	 		Thread.sleep(50);
       	 		
       	 	} catch (Exception e) {
       	 		e.printStackTrace();
       	 	}
       	 	
       	 	g.setColor(Color.WHITE);

       	 	//panel.clear();
       	 	p1.clear(g);
       	 	t1.clear(g);
		}
	}
	
		/** A class for responding to key presses on the drawing panel.
	    */
	   public static class TileKeyListener extends KeyAdapter 
	   {
	      private DrawingPanel panel;
	      private Tank t;
	      private Graphics g;
	      private Projectile p1;
	      
	      public TileKeyListener(DrawingPanel panel, Tank t, Graphics g, Projectile p1) 
	      {
	         this.panel = panel;
	         this.t = t;
	         this.g = g;
	         this.p1 = p1;
	      }
	      
	      public void keyPressed(KeyEvent event) 
	      {
	         int code = event.getKeyCode();
	         if (code == KeyEvent.VK_W) {
	        	  t.addAngle(); // change this to increase angle by 1 degree
	         } else if (code == KeyEvent.VK_D) {
	        	 t.clear(g);
	        	 t.addX(10);
	         } else if (code == KeyEvent.VK_P) {
	        	 p1.reset(t.getX(), t.getY(), t.getAngle(), t.getPower(), g);
	         } else if (code == KeyEvent.VK_A) {
	        	 t.clear(g);
	        	 t.addX(-10);
	         } else if (code == KeyEvent.VK_S) {
	        	 t.minusAngle(); // change this to decrease angle by 1 degree
	         } else if (code == KeyEvent.VK_M) {
	        	 System.out.println("Angle: " + t.getAngle());
	        	 System.out.println();
	         }
	           
	      }
	   }
	   
	
}
