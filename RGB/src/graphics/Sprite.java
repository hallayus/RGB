package graphics;

import java.io.IOException;
import java.io.InputStream;

import main.Logger;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public abstract class Sprite {
	
	protected Texture texture;
	protected int x, y; //coordinate to start drawing. 
	
	private Texture load(String name){
		Texture texture = null;
		try {
			InputStream in = ResourceLoader.getResourceAsStream("res/" + name + ".png");
			texture = TextureLoader.getTexture("PNG", in);
			Logger.writeMessage("Sprite " + name + " has dimensions: " + texture.getHeight() + ", " + texture.getWidth(),this.getClass());
		} catch (IOException e) {
			Logger.writeException(e, this.getClass());
		}
		return texture;
	}
	
	public Sprite(String name, int xOffset, int yOffset){
		texture = load(name);
		x = xOffset;
		y = yOffset;
	}
	
	public abstract Texture getTexture();
	
	public int getX(){ return x; }
	public int getY(){ return y; }

}
