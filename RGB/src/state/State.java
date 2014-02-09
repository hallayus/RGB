package state;


import player.InputController;
import graphics.GraphicsController;
import main.Command;

public interface State {

	void render(GraphicsController gc);
	void update();
	void getInput(InputController ic);
	void close();

}
