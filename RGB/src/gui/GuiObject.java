package gui;

import graphics.Sprite;

public abstract class GuiObject {
	protected int x, y;
	private Sprite sprite;
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public GuiObject(int x, int y, Sprite sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public Sprite getSprite(){
		return sprite;
	}
}
