package state;


import graphics.GraphicsController;
import main.Command;
import controller.InputController;

public interface State {

	void render(GraphicsController gc);
	void update();
	void getInput(InputController ic);
	void close();

}
