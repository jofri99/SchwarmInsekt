package schwarm;

import java.awt.Canvas;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public abstract class LWJGLBasisFenster {
	private int WIDTH, HEIGHT;
	   private String TITLE;
	   
	   public LWJGLBasisFenster() {
	      this("BasisFenster", 640, 480);
	   }

	   public LWJGLBasisFenster(int width, int height) {
	      this("BasisFenster", width, height);
	   }
	   
	   public LWJGLBasisFenster(String title, int width, int height) {
	      WIDTH  = width;
	      HEIGHT = height;
	      TITLE  = title;
	   }
	   
	   public int getWidth() {
		   return WIDTH;
	   }
	   
	   public int getHeight() {
		   return HEIGHT;
	   }
	   
	   public void initDisplay(Canvas c) {
	      try {
	         Display.setParent(c);
	      } catch (LWJGLException e) {
	         e.printStackTrace();
	      }
	      
	      initDisplay();
	   }
	   
	   public void initDisplay() {
	      try {
	         Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
	         Display.setTitle(TITLE);
	         Display.create();
	      } catch (LWJGLException e) {
	         e.printStackTrace();
	      }      
	   }
	   
	   public abstract void renderLoop();
	   
	   public void start() {     
	      renderLoop();
	      Display.destroy();
	      System.exit(0);
	   }
}
