package world;

import java.awt.Rectangle;

import libs.LevelLoader;
import math.Vec2;

public class GameWorld extends GameObject implements PhysicsInterface {
	private PhysicsInterface[][] world;
	private static int BLOCK_SIZE = 64;

	@Override
	public boolean resolveCollisions(PhysicsInterface physicsInterface) {
		PhysicsInterface physics = physicsInterface;
		Rectangle hitbox = physics.getHitbox();
		
		int a = (int) Math.floor(hitbox.getCenterX() / BLOCK_SIZE);
		int b = (int) Math.floor(hitbox.getCenterY() / BLOCK_SIZE);
		
		for(int i = a - 1; i <= a + 1;i++){
			for(int j = b - 1; j <= b + 1;j++){ 
				
				if(i >= 0 && j >= 0 && i < world.length && j < world[0].length){
					PhysicsInterface go = world[ i ][ j ];
					if(go != null) physics.resolveCollisions(go);
				}
				
			}
		}
		return true;
	}
	
	public GameWorld(String file){
		LevelLoader loader = new LevelLoader();
		world = loader.load(file);
		children = loader.getWorld();
	}

	public void update() {
		
	}

	@Override
	public Rectangle getHitbox() {
		return null;
	}

}
