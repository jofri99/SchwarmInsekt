package schwarm;

import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import math.Vektor2D;

public class Lampe extends BasisObjekt{
	private static int objCounter = 0;
	public float radius;
	public Vektor2D middle;
	private float r, g, b, a;
	public ObjektManager objektManager;
	public boolean on = false;
	
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
	
	
	@Override
	public void render() {
		if(on) {
			r = (float) 1;
			g = (float) 0.988;
			b = (float) 0.301;
		}else {
			r = (float) 1;
			g = (float) 1;
			b = (float) 1;
		}
	      glColor3d(r, g, b);
	      POGL.renderKreis((float) position.x,(float) position.y, 1, radius);
	}

}
