package world;

import java.awt.Rectangle;

import graphics.GraphicsController;
import main.InputController;
import player.Player;

public class RootObject extends GameObject {
	private GameWorld gameWorld;
	private Player player;
	
	public RootObject(){
		gameWorld = new GameWorld("level1");
		player = new Player(640, 320);
		
		children.add(gameWorld);
		children.add(player);
	}

	@Override
	public void update() {
		player.update();
		gameWorld.resolveCollisions(player);
	}
	
	public void getInput(InputController ic){
		player.getInput(ic);
	}
	
	public void render(GraphicsController gc){
		Rectangle hitbox = player.getHitbox();
		
		gc.setCameraPosition((int)hitbox.getCenterX() - 640, 0);
		super.render(gc);
	}
}
