package graphics;

import org.newdawn.slick.opengl.Texture;


public class StillSprite extends Sprite {
	
	public StillSprite(String name, int xOffset, int yOffset){
		super("textures/" + name,xOffset,yOffset);
	}
	
	public Texture getTexture(){
		return this.texture;
	}
}
