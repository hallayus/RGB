package gui;

import graphics.StillSprite;

public abstract class GuiObject {
	protected int x, y;
	private StillSprite sprite;
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public GuiObject(int x, int y, StillSprite sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public StillSprite getSprite(){
		return sprite;
	}
}
