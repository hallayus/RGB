package state;


import graphics.GraphicsController;
import main.Command;
import main.InputController;

public interface State {

	void render(GraphicsController gc);
	void update();
	void getInput(InputController ic);
	
	void start();
	void close();

}
