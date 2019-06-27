package schwarm;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2f;
import java.io.File;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import org.lwjgl.opengl.Display;

import math.LineareAlgebra;
import math.Vektor2D;


public class SchwarmInsekt extends LWJGLBasisFenster{
	private ObjektManager insekten,lampen;
	private Lampe[] lampenArr = new Lampe[3];
	private InputHandler input = new InputHandler();
	public SchwarmInsekt() {
		super("Es werde Licht",1000,800);
		initDisplay();
		insekten = ObjektManager.getExemplar();
		erzeugeInsekten(700); 
		lampenArr[0] = new Lampe(new Vektor2D(100,100),(float)30);
		lampenArr[0].setObjektManager(lampen);
		lampenArr[1] = new Lampe(new Vektor2D(400,200),(float)30);
		lampenArr[1].setObjektManager(lampen);
		lampenArr[2] = new Lampe(new Vektor2D(300,400),(float)30);
		lampenArr[2].setObjektManager(lampen);
		
		lampenArr[0].on = true;
	}
	
	private void erzeugeInsekten(int anz) {
		Random rand = ThreadLocalRandom.current();
		for (int i = 0; i < anz; i++) {
			Insekt insekt = new Insekt(new Vektor2D(rand.nextInt(640), rand.nextInt(480)),
					new Vektor2D(0, rand.nextFloat() + 1), rand.nextInt(3) + 1, rand.nextFloat(), 
					rand.nextFloat(), rand.nextFloat());
			insekt.setVerhalten(new VerhaltenSchwarm(insekt,1));
			insekt.setObjektManager(insekten);
			insekten.registriereInsekt(insekt);
		}
	}
	
	private Vektor2D mirrorCoordsY(Vektor2D coords) {
		Vektor2D mirrVec = new Vektor2D();
		if(coords.y >400) {
			mirrVec.x = coords.x;
			double diff = coords.y-400;
			mirrVec.y = 400 -diff;
		} else {
			mirrVec.x = coords.x;
			double diff = 400-coords.y;
			mirrVec.y = 400+diff;
		}
		return mirrVec;
	}

	public void tick() {
		input.tickInput();
		if(input.mouse1 && input.once) {
			input.once = false;
			Vektor2D click = new Vektor2D(input.mouseX,input.mouseY);
			click = mirrorCoordsY(click);
			if(click.x > 109 && click.x < 203 &&click.y >  115 && click.y < 218) {
				//turn light on
				if(!lampenArr[0].on) {
					lampenArr[0].on = true;
					lampenArr[1].on = false;
					lampenArr[2].on = false;
					for(int i = 1; i<=insekten.getInsektSize();i++) {
						Insekt in = insekten.getInsekt(i);
						in.setVerhalten(new VerhaltenSchwarm(in,1));
					}
				}else {
					lampenArr[0].on = false;
				}
				
			} else if(click.x > 577 && click.x <  673 &&click.y >  281 && click.y < 384) {
				//turn light on
				if(!lampenArr[1].on) {
					lampenArr[0].on = false;
					lampenArr[1].on = true;
					lampenArr[2].on = false;
					for(int i = 1; i<=insekten.getInsektSize();i++) {
						Insekt in = insekten.getInsekt(i);
						in.setVerhalten(new VerhaltenSchwarm(in,2));
					}
				}else {
					lampenArr[1].on = false;
				}
			}else if(click.x > 420 && click.x < 516 &&click.y > 618 && click.y < 717) {
				//turn light on
				if(!lampenArr[2].on) {
					lampenArr[0].on = false;
					lampenArr[1].on = false;
					lampenArr[2].on = true;
					for(int i = 1; i<=insekten.getInsektSize();i++) {
						Insekt in = insekten.getInsekt(i);
						in.setVerhalten(new VerhaltenSchwarm(in,3));
					}
				}else {
					lampenArr[2].on = false;
				}
			}
			
			
			
		}
	}
	
	@Override
	public void renderLoop() {
		// TODO Auto-generated method stub
		glEnable(GL_DEPTH_TEST);

		while (!Display.isCloseRequested()) {
			glClearColor(0.1f, 0.2f, 0.3f, 1);
			glClear(GL_COLOR_BUFFER_BIT);

			// ist ja 2d
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, 640, 480, 0, 0, 1);
			glMatrixMode(GL_MODELVIEW);
			glDisable(GL_DEPTH_TEST);
			this.tick();
			
			lampenArr[0].render();
			lampenArr[1].render();
			lampenArr[2].render();
			for (int i = 1; i <= insekten.getInsektSize(); i++) {
				Insekt aktInsekt = insekten.getInsekt(i);
				aktInsekt.render();
				aktInsekt.update();
			}

			Display.update();
		}
		Display.destroy();
	}

	public static void main(String args[]) {
		new SchwarmInsekt().start();
	}
}
