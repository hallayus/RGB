package world;

import graphics.Sprite;

public class Air extends GameObject{

	public Air(int x, int y, Sprite sprite) {
		super(sprite); //isSolid set to false
		this.x = x;
		this.y = y;
	}

	@Override
	public GameObject clone(int i, int j) {
		return new Air(i,j,sprite);
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	
}
