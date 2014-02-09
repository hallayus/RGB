package player; 

import org.lwjgl.input.Keyboard;

public class InputController implements Runnable {
	private int jump;
	private int left;
	private int right;
	
	
	public InputController(){
		jump = Keyboard.KEY_SPACE;
		left = Keyboard.KEY_A;
		right = Keyboard.KEY_D;
	}

	@Override
	public void run() {
		
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
}

