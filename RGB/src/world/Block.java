package world;

import java.awt.Rectangle;

import player.StillPhysicsComponent;
import graphics.GraphicsController;
import graphics.Sprite;

public class Block extends GameObject implements PhysicsInterface {

	private static Sprite sprite = new Sprite("textures/block-grey",0,0);
	private StillPhysicsComponent physics;
	
	public Block(int x, int y) 
	{
		super();
		
		this.x = x;
		this.y = y;
		
		physics = new StillPhysicsComponent(x,y);
	}

	@Override
	public boolean resolveCollisions(PhysicsInterface physicsInterface) 
	{
		return true;
	}

	
	public void render(GraphicsController gc)
	{
		gc.render(x, y, sprite);
	}

	@Override
	public void update() {
		
	}

	@Override
	public Rectangle getHitbox() {
		return physics.getHitBox();
	}

}
