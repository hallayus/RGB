package world;

import graphics.Sprite;

public class Block extends GameObject {


	public Block(int x, int y, Sprite sprite) {
		super(sprite); //isSolid set to true
		
		this.x = x;
		this.y = y;
	}

	public GameObject clone(int i, int j) {
		return new Block(i,j,sprite);
	}

	@Override
	public Sprite getSprite() {
		return sprite; //property of superclass
	}

	@Override
	public boolean isSolid() {
		return true;
	}

}
