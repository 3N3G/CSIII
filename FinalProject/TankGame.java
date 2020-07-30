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
	/**
	 * The main of this function with the while loop that draws everything.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		boolean running = true;
		
	    DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);
	    Graphics g = panel.getGraphics();
	    panel.setBackground(Color.WHITE);
	    
	    Tank t1 = new Tank(g);
	    Projectile p1 = new Projectile(30,400,45,10);
	    TileKeyListener listener = new TileKeyListener(panel, t1, g, p1);
		panel.addKeyListener(listener);
		
		Target target = new Target();
		
		//target.setTargetClip(g);

		Ground ground = new Ground();
		
	    while (running) {
	    	
	    	p1.shoot();
	    	p1.draw(g);
	    	/*for (Projectile p : p1.getBullets()) {
	    		p1.draw(g);
	    	}*/
       	 	t1.draw();
       	 	ground.draw(g, panel);
       	 	       	 	
       	 	
       	 	if (!target.collides(p1)) {
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

       	 	p1.clear(g);
       	 	//t1.clear();
		}
	}
	
		/** A class for responding to key presses on the drawing panel.
		 * 
	    */
	   public static class TileKeyListener extends KeyAdapter 
	   {
	      private DrawingPanel panel;
	      private Tank t;
	      private Graphics g;
	      private Projectile p1;
	      
	      /**
	       * constructs the tileListener, along with parameters to alter as actions
	       * @param panel DrawingPanel that everything is drawn on
	       * @param t Tank that is drawn and moves
	       * @param g Graphics
	       * @param p1 Projectile that's being drawn
	       */
	      public TileKeyListener(DrawingPanel panel, Tank t, Graphics g, Projectile p1) 
	      {
	         this.panel = panel;
	         this.t = t;
	         this.g = g;
	         this.p1 = p1;
	      }
	      
	      /**
	       * Records any keys pressed, and addresses them with the proper action.
	       * @param event key that was pressed
	       */
	      public void keyPressed(KeyEvent event) 
	      {
	         int code = event.getKeyCode();
	         if (code == KeyEvent.VK_W) {
	        	 t.addAngle(); // change this to increase angle by 1 degree
	         } else if (code == KeyEvent.VK_D) {
	        	 if (t.getX()<275) {
	        		 t.addX(10);
	        	 }
	         } else if (code == KeyEvent.VK_P) {
	        	 p1.reset(t.getX(), t.getY(), t.getAngle(), t.getPower(), g);
	         } else if (code == KeyEvent.VK_A) {
	        	 if (t.getX()>0) {
	        		 t.addX(-10);
	        	 }
	         } else if (code == KeyEvent.VK_S) {
	        	 t.minusAngle(); // change this to decrease angle by 1 degree
	         } else if (code == KeyEvent.VK_M) {
	        	 System.out.println("Angle: " + t.getAngle() + " Power: " + t.getPower());
	        	 System.out.println();
	         } else if (code == KeyEvent.VK_UP) {
	        	 t.powerUp();
	         } else if (code == KeyEvent.VK_DOWN) {
	        	 t.powerDown();
	         } else if (code == KeyEvent.VK_O) {
	        	 
	         }
	           
	      }
	   }
	   
	
}
