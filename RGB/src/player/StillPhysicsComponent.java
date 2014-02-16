package player;

import java.awt.Rectangle;

public class StillPhysicsComponent extends GameComponent{
	private Rectangle hitbox;
	private final int BLOCK_SIZE = 64;
	@Override
	public void update() {}

	public Rectangle getHitBox(){
		return hitbox;
	}
	
	public StillPhysicsComponent(int x, int y){
		hitbox = new Rectangle(x,y,BLOCK_SIZE,BLOCK_SIZE);
	}
	
	public int getX(){
		return (int) hitbox.getX();
	}
	
	public int getY(){
		return (int) hitbox.getY();
	}
}
