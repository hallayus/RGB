package state;

import graphics.GraphicsController;
import graphics.Sprite;
import main.Application;
import main.Command;
import controller.InputController;

public class Frame implements State {
	private Sprite sprite;
	private long delta;
	private long startTime;
	private long currTime;
	private State nextState;
	
	
	public void render(GraphicsController gc) {
		gc.render(0,0,sprite);
		
	}

	@Override
	public void update() {
		currTime = System.currentTimeMillis();
		if(currTime - startTime > delta){
			Application.changeState(nextState);
		}
	}

	@Override
	public void getInput(InputController ic) {
	}

	@Override
	public void close() {
		nextState.close();
	}
	
	public Frame(String name,long time,State nextState){
		sprite = new Sprite(name,0,0);
		this.delta = time;
		this.nextState = nextState;
		startTime = System.currentTimeMillis();
	}

}
