package main; 

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

public class InputController {
	private int jump;
	private int left;
	private int right;
	private int up;
	private int down;
	private int up2;
	private int down2;
	private int select;
	
	public InputController(){
		try {
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		jump = Keyboard.KEY_SPACE;
		left = Keyboard.KEY_A;
		right = Keyboard.KEY_D;
		up = Keyboard.KEY_W;
		down = Keyboard.KEY_S;
		up2 = Keyboard.KEY_UP;
		down2 = Keyboard.KEY_DOWN;
		select = Keyboard.KEY_RETURN;
	}
	
	public boolean getJump(){
		if(Keyboard.isKeyDown(jump)){
			return true;
		}
		return false;
	}
	public boolean getRight(){
		if(Keyboard.isKeyDown(right)){
			return true;
		}
		return false;
	}
	public boolean getLeft(){
		if(Keyboard.isKeyDown(left)){
			return true;
		}
		return false;
	}
	public boolean getMenuUp(){
		if(Keyboard.isKeyDown(up) || Keyboard.isKeyDown(up2)){
			return true;
		}
		return false;
	}
	public boolean getMenuDown(){
		if(Keyboard.isKeyDown(down) || Keyboard.isKeyDown(down2)){
			return true;
		}
		return false;
	}

	public boolean getMenuSelect() {
		if(Keyboard.isKeyDown(select) || Keyboard.isKeyDown(jump)){
			return true;
		}
		return false;
	}
}

