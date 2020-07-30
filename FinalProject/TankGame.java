// Gene Yang
// Final Assignment TankGame.java
// Creates and runs the tank game
// CSIII
// 7/30/20
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import java.applet.*;
import java.net.*;

public class TankGame {
	/**
	 * {@value WIDTH} width of the drawing window
	 */
	private static final int WIDTH = 800;
	/**
	 * {@value HEIGHT} height of the drawing window
	 */
	private static final int HEIGHT = 500;
	/**
	 * {@value DING_SOUND} dinging success noise file name
	 */
	private static final String DING_SOUND = "/Users/geneyang/Documents/workspace/CSIIIFinalProject/src/Ding.wav";
	/**
	 * {@value MOVEMENT_SIZE} amount the tank moves in one keypress
	 */
	private static final int MOVEMENT_SIZE = 10;
	/**
	 * {@value MIDDLE_BORDER} x coordinate of tank when it hits the middle
	 */
	private static final int MIDDLE_BORDER = 275;
	/**
	 * {@value WAIT_PER_FRAME} how long in milliseconds to wait between frames
	 */
	private static final int WAIT_PER_FRAME = 50;
	
	
	/**
	 * The main of this function with the while loop that draws everything. First prints out
	 * a message explaining the game, then creates the drawing panel, its Graphics and Background,
	 * creates a tank and a projectile, the target, the terrain, and keylistener.
	 * 
	 * The while loop contains the game loop. It moves the projectile if any, draws the projectile,
	 * tank, and ground, then checks if the projectile has hit the target, and if the projectile
	 * has hit the ground. 
	 * Then it pauses for 50 milliseconds, and clears the projectile and tank.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to the Tank Game!");
		System.out.println("You control the tank on the left, and are trying to hit");
		System.out.println("the target on the right. You control movement of the tank with the");
		System.out.println("A and D keys, the angle of the shot with W and S keys, and power");
		System.out.println("with the up and down arrow keys. Press P to shoot once you've aimed.");
		System.out.println("Hit the target three times to win.");
		
		boolean running = true;
		
	    DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);
	    Graphics g = panel.getGraphics();
	    panel.setBackground(Color.WHITE);
	    
	    Tank t1 = new Tank(g);
	    Projectile p1 = new Projectile(g);
	    TileKeyListener listener = new TileKeyListener(t1, g, p1);
		panel.addKeyListener(listener);
		
		Target target = new Target(g);

		Ground ground = new Ground();
		
	    while (running) {
	    	p1.shoot();
	    	
	    	p1.draw();
       	 	t1.draw();
       	 	ground.draw(g, panel);
       	 	       	 	
       	 	
       	 	if (!target.collides(p1)) {
       	 		target.draw();
       	 	} else {
       	 		p1.clear();
       	 		// Moves the projectile out of the screen(x>800), so it can't cause any issues
       	 		p1.setX(1000);
       	 		AudioPlayer.playSound(DING_SOUND);
       	 		target.clear();
       	 	}
       	 	
       	 	p1.checkGround();
       	 	
       	 	try {
       	 		Thread.sleep(WAIT_PER_FRAME);
       	 		
       	 	} catch (Exception e) {
       	 		e.printStackTrace();
       	 	}

       	 	p1.clear();
       	 	t1.clear();
		}
	}
	
		/** 
		 * A class for responding to key presses on the drawing panel.
	    */
	   public static class TileKeyListener extends KeyAdapter 
	   {
	      private Tank t;
	      private Graphics g;
	      private Projectile p1;
	      
	      /**
	       * constructs the tileListener, along with parameters to alter as actions
	       * @param t Tank that is drawn and moves
	       * @param g Graphics
	       * @param p1 Projectile that's being drawn
	       */
	      public TileKeyListener(Tank t, Graphics g, Projectile p1) 
	      {
	         this.t = t;
	         this.g = g;
	         this.p1 = p1;
	      }
	      
	      /**
	       * Records any keys pressed, and addresses them with the proper action.
	       * Moves the tank left or right if and only if it will be within the borders, 
	       * @param event key that was pressed
	       */
	      public void keyPressed(KeyEvent event) 
	      {
	         int code = event.getKeyCode();
	         switch (code) {
		         case (KeyEvent.VK_W):
			         t.addAngle(); // change this to increase angle by 1 degree
		         	 break;
		         case (KeyEvent.VK_D):
		        	 if (t.getX()<MIDDLE_BORDER) {
		        		t.addX(MOVEMENT_SIZE);
		        	 }
		         	 break;
		         case (KeyEvent.VK_P):
		        	 p1.reset(t.getX(), t.getY(), t.getAngle(), t.getPower());
		         	 break;
		         case (KeyEvent.VK_A):
		        	if (t.getX()>0) {
		        		t.addX(-1*MOVEMENT_SIZE);// negative to move left
		        	}
		         	break;
		         case (KeyEvent.VK_S):
		        	t.minusAngle(); // change this to decrease angle by 1 degree
		         	break;
		         case (KeyEvent.VK_M): // For testing purposes
		        	System.out.println("Angle: " + t.getAngle() + " Power: " + t.getPower());
		        	System.out.println();
		        	break;
		         case (KeyEvent.VK_UP):
		        	t.powerUp();
		            break;
		         case (KeyEvent.VK_DOWN):
			        t.powerDown();
		            break;
		     }
	           
	      }
	   }
	   
	
}
