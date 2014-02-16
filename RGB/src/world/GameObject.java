package world;

import graphics.GraphicsController;
import graphics.StillSprite;

import java.awt.Rectangle;
import java.util.ArrayList;

import main.GameComponent;

public abstract class GameObject {

	protected int x, y;
	protected ArrayList<GameObject> children;
	protected ArrayList<GameComponent> components;
	
	public GameObject(){
		children = new ArrayList<GameObject>();
		components = new ArrayList<GameComponent>();
	}
	
	public void render(GraphicsController gc){
		for(GameObject child : children){
			child.render(gc);
		}
	}
	
	public abstract void update();
}
