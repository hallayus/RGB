package player;

import java.awt.Rectangle;

import graphics.GraphicsController;
import graphics.StillSprite;
import main.InputController;
import math.PlayerPhysicsComponent;
import world.GameObject;
import world.PhysicsInterface;

public class Player extends GameObject implements PhysicsInterface {
	private PlayerPhysicsComponent physicsComponent;
	private int startX, startY;
	
	private static final int SIZE = 64;
	
	private PlayerDirection direction;
	private StillSprite playerLeft;
	private StillSprite playerRight;
	

	@Override
	public void render(GraphicsController gc) {
		switch(direction){
			case LEFT:
				gc.render(x, y, playerLeft);
				break;
			case RIGHT:
				gc.render(x, y, playerRight);
				break;
			default:
				gc.render(x, y, playerLeft);
		}
	}
	
	public Player(int x, int y){

		this.x = x;
		this.y = y;
		this.startX = x;
		this.startY = y;
		
		direction = PlayerDirection.LEFT;
		
		playerLeft = new StillSprite("player_red_left",64,0);
		playerRight = new StillSprite("player_red_right",0,0);

		physicsComponent = new PlayerPhysicsComponent(x,y);
	}
	
	public void getInput(InputController ic){
		physicsComponent.still();
		
		if(ic.getJump()) physicsComponent.jump();
		
		
		if(ic.getLeft()){
			physicsComponent.left(); 
			direction = PlayerDirection.LEFT;
		}
		if(ic.getRight()){
			physicsComponent.right(); 
			direction = PlayerDirection.RIGHT;
		}
	}
	
	public void update(){
		physicsComponent.update();
		x = physicsComponent.getX();
		y = physicsComponent.getY();
		
		if(y > 1800) //hack until node based system
		{
			x = startX;
			y = startY;
		}		
	}

	@Override
	public boolean resolveCollisions(PhysicsInterface physicsInterface) 
	{
		if(physicsInterface.resolveCollisions(this))
		{
		physicsComponent.resolveCollisions(physicsInterface);
		x = physicsComponent.getX();
		y = physicsComponent.getY();
		}
		return true;

	}

	@Override
	public Rectangle getHitbox() {
		return physicsComponent.getHitBox();
	}
}
