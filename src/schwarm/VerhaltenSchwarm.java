package schwarm;

import math.Vektor2D;


public class VerhaltenSchwarm implements Verhalten{
	private Insekt insekt;
	private Steuerungsverhalten steering;
	private final float MAX_VELOCITY = 5;

	public VerhaltenSchwarm(Insekt insekt) {
		this.insekt = insekt;
		this.steering = new Steuerungsverhalten();
	}
	
	@Override
	public void update() {
		Vektor2D lightForce = steering.followLightSource(insekt.position);
		lightForce.mult(0.8);
		steering.applyForce(lightForce);
		
		Vektor2D separationForce = steering.separation(insekt, 10);
		separationForce.mult(1.0);
		steering.applyForce(separationForce);
		Vektor2D alignmentForce = steering.alignment(insekt, 15);
		alignmentForce.mult(0.12);
		steering.applyForce(alignmentForce);
		
		Vektor2D cohesionForce = steering.cohesion(insekt, 15);
		cohesionForce.mult(0.01);
		steering.applyForce(cohesionForce);
		
		insekt.velocity.add(steering.acceleration);
		insekt.velocity.truncate(MAX_VELOCITY);
		insekt.position.add(insekt.velocity);

		steering.resetAcceleration();
	}
}
