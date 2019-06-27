package schwarm;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class InputHandler {
	public boolean[] keys = new boolean[65536];
	public boolean mouse1;
	boolean down = false;
	boolean once = true;
	
	public float mouseX,mouseY;
	
	public void tickInput() {
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				keys[Keyboard.getEventKey()] = true;
			} else {
				keys[Keyboard.getEventKey()] = false;
			}
		}
		
		if(Mouse.isButtonDown(0)) {
			mouse1 = true;
		}else {
			mouse1 = false;
			once = true;
		}
		
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
	}
}
