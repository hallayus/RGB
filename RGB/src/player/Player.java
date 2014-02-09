package player;

import graphics.Sprite;
import math.Vector2;
import world.GameObject;

public class Player extends GameObject {
	private PlayerPhysicsComponent physics;
	private int startX, startY;
	
	private PlayerDirection direction;
	private Sprite playerLeft;
	private Sprite playerRight;
	
	private  GameObject[][] world;
	
	private final float jumpBoost = -1f;
	private final float walkSpeed = 0.2f;
	
	@Override
	public GameObject clone(int i, int j) {
		return new Player(i,j,world);
	}

	@Override
	public Sprite getSprite() {
		switch(direction){
			case LEFT:
				return playerLeft;
			case RIGHT:
				return playerRight;
			default:
				return playerLeft;
		}
	}
	
	public Player(int x, int y, GameObject[][] world){
		super(null); //isSolid set to true
		this.x = x;
		this.y = y;
		this.startX = x;
		this.startY = y;
		
		direction = PlayerDirection.LEFT;
		
		playerLeft = new Sprite("textures/player_red_left",64,0);
		playerRight = new Sprite("textures/player_red_right",0,0);
		
		this.world = world;
		physics = new PlayerPhysicsComponent(world,BLOCK_SIZE,this);
	}
	
	public void getInput(InputController ic){
		physics.still();
		
		if(ic.getJump()) physics.jump(jumpBoost);
		
		
		if(ic.getLeft()){
			physics.left(walkSpeed); 
			direction = PlayerDirection.LEFT;
		}
		if(ic.getRight()){
			physics.right(walkSpeed); 
			direction = PlayerDirection.RIGHT;
		}
	}
	
	public void update(){
		physics.update();
		if(y > world[0].length * BLOCK_SIZE)
		{
			x = startX;
			y = startY;
		}
		//if(physics.isGrounded) physics.impulse(0,gravity);
	}

	@Override
	public boolean isSolid() {
		return false;
	}
}
