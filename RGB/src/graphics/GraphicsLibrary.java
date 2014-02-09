package graphics;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class GraphicsLibrary {
	private HashMap<String,Texture> TextureLibrary = new HashMap<String,Texture>();
	
	public Texture get(String index){
		if(TextureLibrary.containsKey(index)){
			return TextureLibrary.get(index);
		}else{
			return null;
		}
	}
	
	private void Load(String name){
		Texture t;
		InputStream in = ResourceLoader.getResourceAsStream("res/" + name + ".png");
		try {
			t = TextureLoader.getTexture("PNG",in);
			
			if(t == null){
				System.out.println(name + " texture not loaded");
				return;
			}
			TextureLibrary.put(name, t);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public GraphicsLibrary(){
		
		Load("textures/player_-03");
		Load("textures/block-grey");
		Load("slides/Splash");
		Load("slides/Blank");
		Load("slides/splash01");
		Load("slides/splash02");
		Load("slides/splash03");
		Load("slides/splash04");
		Load("slides/splash05");
		Load("slides/splash06");
		System.out.println("textures loaded");
	}
}
