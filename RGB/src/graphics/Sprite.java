package graphics;

import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Sprite {
	private Texture texture;
	private String name;
	private int x, y; //coordinate to start drawing.
	
	private void load(String name){
		try {
			InputStream in = org.newdawn.slick.util.ResourceLoader.getResourceAsStream("res/" + name + ".png");
			texture = TextureLoader.getTexture("PNG", in);
			System.out.println("Sprite with " + name + " has dimensions: " + texture.getHeight() + ", " + texture.getWidth());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(texture == null){
			System.out.println(name + " texture not loaded");
		}
		
	}
	
	public Sprite(String name, int xOffset, int yOffset){
		load(name);
		x = xOffset;
		y = yOffset;
	}
	
	public Texture getTexture(){
		return this.texture;
	}
	
	public String getName(){
		return name;
	}
	
	public int getX(){ return x; }
	public int getY(){ return y; }
}
