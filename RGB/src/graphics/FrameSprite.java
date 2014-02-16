package graphics;

import org.newdawn.slick.opengl.Texture;

public class FrameSprite extends Sprite {
	public FrameSprite(String name){
		super("slides/" + name,0,0);
	}

	@Override
	public Texture getTexture() {
		return texture;
	}
}
