package main;
import graphics.GraphicsController;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import controller.InputController;
import state.Frame;
import state.MainMenu;
import state.State;


public class Application{
	//graphic info
	private static State currState;
	private static int width = 1280;
	private static int height = 720;

	public Application(){
		initDisplay();
		currState = new Frame("slides/Splash", 2000, new MainMenu());
		GraphicsController gc = new GraphicsController();
		InputController ic = new InputController();
		
		while(!Display.isCloseRequested()){
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
			
			currState.getInput(ic);
			currState.update();
			currState.render(gc);
			
			Display.update();
			Display.sync(60);
		}
		
		System.out.println("closing game");
		close();
	}
	
	public static void main(String[] args){
		new Application();
	}
	
	public static void changeState(State state){
		currState = state;
	}
	
	public static void close(){
		Display.destroy();
		currState.close();
	}
	
	private static void initDisplay(){
		
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		Display.setVSyncEnabled(true);
		GL11.glEnable(GL11.GL_TEXTURE_2D);               
        
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	
}