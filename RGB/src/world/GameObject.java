package world;

import graphics.Sprite;

import java.awt.Rectangle;

public abstract class GameObject extends Rectangle {

	public static int BLOCK_SIZE = 64;
	protected Sprite sprite;
	protected Sprite tmpSpr;
	
	public abstract GameObject clone(int x, int y);
	
	public Sprite getSprite(){
		return this.sprite;
	}
	
	public GameObject(Sprite sprite){
		this.sprite = sprite;
		setSize(BLOCK_SIZE,BLOCK_SIZE); //call superclass and define the size of gameobjects
	}
	
	public abstract boolean isSolid();
	
}
