package math;

import java.awt.Rectangle;

import player.Player;
import world.GameObject;
import world.PhysicsInterface;
import main.GameComponent;
import main.Logger;

public class PlayerPhysicsComponent extends GameComponent {
	private float vx = 0, vy = 0; //player velocity
	private boolean grounded = false;
	
	private long lastTime;
	private long currTime;
	private float deltaTime;
	
	private Rectangle hitbox;
	
	private Player player;
	private GameObject[][] world;
	
	private static final float MAX_X_SPEED = 32f;
	private static final float MAX_Y_SPEED = 32f;
	private final int BLOCK_SIZE = 64;
	private final float GRAVITY = 0.003f;
	
	private final float JUMP_BOOST = -1f;
	private final float WALK_SPEED = 0.2f;
	
	public PlayerPhysicsComponent(int x, int y){
		hitbox = new Rectangle(x,y,BLOCK_SIZE,BLOCK_SIZE);

		lastTime = System.currentTimeMillis();
		currTime = lastTime;
	}
	
	public Rectangle getHitBox(){
		return hitbox;
	}
	
	private void impulse(float x, float y){
		if(deltaTime != 0){
			vx += x * deltaTime;
			vy += y * deltaTime;
		}
		
		if(Math.abs(vx) > MAX_X_SPEED){ 
			vx = MAX_X_SPEED;
			System.out.println("speed capped");
		}
		if(Math.abs(vy) > MAX_Y_SPEED){ 
			vy = MAX_Y_SPEED;
			System.out.println("speed capped");
		}
		
		//System.out.println(vx + " " + vy);
		
	}
	
	public void update(){
		currTime = System.currentTimeMillis();
		deltaTime = (float) ((currTime - lastTime));
		
		//System.out.println(deltaTime);
		
		impulse(0,GRAVITY);

		if(deltaTime != 0){
			float dx = vx * deltaTime;
			float dy = (vy + 0.5f * GRAVITY * deltaTime) * deltaTime;
			
			//System.out.println(dx + " " + dy);
			
			hitbox.x += dx;
			hitbox.y += dy;
		}
		lastTime = currTime;
	}
	
	public void jump(){

		if(grounded){
			Logger.writeMessage("player jumped", this.getClass());
			vy = JUMP_BOOST;
			grounded = false;
		}
	}
	
	public void left(){
		vx = -WALK_SPEED;
	}
	
	public void right(){
		vx = WALK_SPEED;
	}
	
	public void still(){
		vx = 0;
	}
	
	public void reset(){
		vx = 0;
		vy = 0;
	}

	public int getX(){
		return (int) hitbox.getX();
	}
	
	public int getY(){
		return (int) hitbox.getY();
	}
	
	public void resolveCollisions(PhysicsInterface physicsInterface){
		Rectangle objectHitbox = physicsInterface.getHitbox();
				
		int dy = 0, dx = 0;
		if(hitbox.intersects(objectHitbox))
		{
			Rectangle r = hitbox.intersection(objectHitbox);
			if(r.getWidth() >= r.getHeight())
			{
				if(hitbox.getCenterY() >= objectHitbox.getCenterY())
				{
					dy = (int) r.getHeight();
				}
				else
				{
					dy = (int) (-r.getHeight());
					grounded = true;
				}
				
				vy = 0; //change to bouncy
			}
			else
			{
				dx = (int) ((hitbox.getCenterX() >= objectHitbox.getCenterX())?r.getWidth():(-r.getWidth()));
				
				
				vx = 0; //change to bouncy
			}
			hitbox.translate(dx, dy);
		}
	}
}
