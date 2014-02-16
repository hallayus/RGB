package graphics;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.*;

public class AnimatedSprite extends Sprite {
	
	
	
	@Override
	public Texture getTexture() {
		return null;
	}

	public AnimatedSprite(String name, int xOffset, int yOffset){
		super("animations/" + name,xOffset,yOffset);

	}
}
