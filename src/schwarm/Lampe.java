package schwarm;

import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import math.Vektor2D;

public class Lampe extends BasisObjekt{
	private static int objCounter = 0;
	private float radius;
	private float r, g, b, a;
	public ObjektManager objektManager;
	boolean on = true;
	
	public Lampe() {
		this(new Vektor2D(0, 0), 20.0f);
	}
		   
	public Lampe(Vektor2D position) {
      this(position, 20.0f);
	}
	
	public Lampe(Vektor2D position, float radius) {
	      super(position);
	      this.radius = radius;
	      this.r = 1;
	      this.g = 1;
	      this.b = 1;
	      this.id = objCounter++;
	   }
	
	public void setObjektManager(ObjektManager objektManager) {
		this.objektManager = objektManager;
	}
	
	public void toggleLight() {
		if(on) {
			r = (float) 1;
			g = (float) 0.988;
			b = (float) 0.301;
			on = false;
		}else {
			r = (float) 1;
			g = (float) 1;
			b = (float) 1;
			on = true;
		}
	}
	
	@Override
	public void render() {
	      glColor3d(r, g, b);
	      glBegin(GL_TRIANGLE_FAN);
	      glVertex2f((float)position.x, (float)position.y);
	      for (int angle=0; angle<360; angle+=1) {
		         glVertex2f((float)position.x + (float)Math.sin(angle) * radius, (float)position.y + (float)Math.cos(angle) * radius);
		      }
	      glEnd();
	}

}
