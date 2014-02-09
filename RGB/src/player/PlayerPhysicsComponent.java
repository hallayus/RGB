package player;

import java.awt.Rectangle;

import world.GameObject;
import math.Vector2;

public class PlayerPhysicsComponent {
	private float vx = 0, vy = 0; //player velocity
	private boolean grounded = false;
	
	private long lastTime;
	private long currTime;
	private float deltaTime;
	
	
	private Player player;
	private GameObject[][] world;
	
	private static final float MAX_X_SPEED = 32f;
	private static final float MAX_Y_SPEED = 32f;
	private final int BLOCK_SIZE;
	private final float GRAVITY = 0.003f;
	
	public PlayerPhysicsComponent(GameObject[][] world,final int blockSize,Player player){
		this.player = player;
		this.world = world;
		BLOCK_SIZE = blockSize;
		
		lastTime = System.currentTimeMillis();
		currTime = lastTime;
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
		
		float startVx;
		
		//System.out.println(deltaTime);
		
		impulse(0,GRAVITY);

		if(deltaTime != 0){
			float dx = vx * deltaTime;
			float dy = (vy + 0.5f * GRAVITY * deltaTime) * deltaTime;
			
			//System.out.println(dx + " " + dy);
			
			player.translate((int)dx,(int)dy);
		}
		checkCollisions();
		lastTime = currTime;
	}
	
	public void jump(float impulse){

		if(grounded){
			//System.out.println("jumping");
			vy = impulse;
			grounded = false;
		}
	}
	
	public void left(float speed){
		vx = -speed;
	}
	
	public void right(float speed){
		vx = speed;
	}
	
	public void still(){
		vx = 0;
	}
	
	private void checkCollisions(){

		int a = (int) Math.floor(player.getCenterX() / BLOCK_SIZE);
		int b = (int) Math.floor(player.getCenterY() / BLOCK_SIZE);
		
		int dx = 0;
		int dy = 0;
		
		for(int i = a - 1; i <= a + 1;i++){
			for(int j = b - 1; j <= b + 1;j++){ 
				
				if(i >= 0 && j >= 0 && i < world.length && j < world[0].length){
					GameObject go = world[ i ][ j ];
					
					if(go.isSolid() && go.intersects(player)){
						Rectangle r = player.intersection(go);
						
						if(r.getWidth() >= r.getHeight()){
							if(player.getCenterY() >= go.getCenterY()){
								dy = (int) r.getHeight();
								//System.out.println("collided above");
							}else{
								dy = (int) (-r.getHeight());
								grounded = true;
								//System.out.println("collided below");
							}
							player.translate(0,dy);
							vy = 0; //change to bouncy
						}else{
							dx = (int) ((player.getCenterX() >= go.getCenterX())?r.getWidth():(-r.getWidth()));
							
							player.translate((int) dx,0);
							vx = 0; //change to bouncy
						}
						
						
					}
				}
				
			}
		}
		
	}
	
	public void reset(){
		vx = 0;
		vy = 0;
	}
}
