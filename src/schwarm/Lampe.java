package schwarm;

import math.Vektor2D;

public class Lampe extends BasisObjekt{
	private static int objCounter = 0;
	private float radius;
	private float r, g, b, a;
	public ObjektManager objektManager;
	boolean on = false;
	
	public Lampe() {
		this(new Vektor2D(0, 0), 20.0f, 1, 1, 0);
	}
		   
	public Lampe(Vektor2D position) {
      this(position, 20.0f, 1, 1, 0);
	}
	
	public Lampe(Vektor2D position, float radius, float r, float g, float b) {
	      super(position);
	      this.radius = radius;
	      this.r=r;
	      this.g=g;
	      this.b=b;
	      this.id = ++objCounter;
	   }
	
	public void setObjektManager(ObjektManager objektManager) {
		this.objektManager = objektManager;
	}
	
	public void toggleLight() {
		if(on) {
			on = false;
		}else {
			on = true;
		}
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

}
