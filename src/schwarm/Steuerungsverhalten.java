package schwarm;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import math.LineareAlgebra;
import math.Vektor2D;

public class Steuerungsverhalten {
	public Vektor2D acceleration;
	private Random zuf = ThreadLocalRandom.current();
	private int lightSource = 1;

	public Steuerungsverhalten() {
		acceleration = new Vektor2D(0, 0);
	}

	public void resetAcceleration() {
		acceleration.mult(0);
	}

	public void applyForce(Vektor2D force) {
		Vektor2D forceHelp = new Vektor2D(force);
		acceleration.add(forceHelp);
	}

	public Vektor2D randomForce() {
		return new Vektor2D(zuf.nextFloat() * 10 - 5, zuf.nextFloat() * 10 - 5);
	}

	public Vektor2D followMousePosition(Vektor2D currentPosition) {
		Vektor2D mousePosition = new Vektor2D(Mouse.getX(), Display.getDisplayMode().getHeight() - Mouse.getY());
		mousePosition.sub(currentPosition);
		mousePosition.normalize();
		return mousePosition;
	}
	
	public Vektor2D followLightSource(Vektor2D currentPosition) {
		Vektor2D lightPos = new Vektor2D();
		switch(lightSource) {
		case 1:
			lightPos = new Vektor2D(200,300);
			break;
		case 2:
			lightPos = new Vektor2D(400,600);
			break;
		case 3: 
			lightPos = new Vektor2D(800,600);
			break;
		}
		
		lightPos.sub(currentPosition);
		lightPos.normalize();
		return lightPos;
	}
	
	public void setLightSource(int lightSource) {
		if(lightSource==1||lightSource==2||lightSource==3) {
			this.lightSource = lightSource;
		}else {
			System.err.println("lightSource need to be 1,2 or 3");
		}
	}

	public Vektor2D separation(Insekt me, float dist) {
		Vektor2D steeringForce = new Vektor2D(0, 0);
		for (int i = 0; i < me.objektManager.getInsektSize(); i++) {
			if (me.id == i)
				continue;

			BasisObjekt bObj = me.objektManager.getInsekt(i);
			if (bObj instanceof Insekt) {
				Insekt bObjF = (Insekt)bObj;
				if (LineareAlgebra.euklDistanz(me.position, bObjF.position) < dist)
					steeringForce.add(LineareAlgebra.sub(me.position, bObjF.position));
			}
		}
		LineareAlgebra.normalize(steeringForce);
		return steeringForce;
	}

	public Vektor2D alignment(Insekt me, float dist) {
		Vektor2D steeringForce = new Vektor2D(0, 0);
		for (int i = 0; i < me.objektManager.getInsektSize(); i++) {
			if (me.id == i)
				continue;

			BasisObjekt bObj = me.objektManager.getInsekt(i);
			if (bObj instanceof Insekt) {
				Insekt bObjF = (Insekt)bObj;
				if (LineareAlgebra.euklDistanz(me.position, bObjF.position) < dist)
					steeringForce.add(bObjF.velocity);
			}
		}

		LineareAlgebra.normalize(steeringForce);
		return steeringForce;
	}

	public Vektor2D cohesion(Insekt me, float dist) {
		Vektor2D steeringForce = new Vektor2D(0, 0);
		for (int i = 0; i < me.objektManager.getInsektSize(); i++) {
			if (me.id == i)
				continue;

			BasisObjekt bObj = me.objektManager.getInsekt(i);
			if (bObj instanceof Insekt) {
				Insekt bObjF = (Insekt)bObj;
				if (LineareAlgebra.euklDistanz(me.position, bObjF.position) < dist)
					steeringForce.add(LineareAlgebra.sub(bObjF.position, me.position));
			}
		}

		LineareAlgebra.normalize(steeringForce);
		return steeringForce;
	}
}
