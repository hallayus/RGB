package libs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.Application;
import main.Logger;
import world.Block;
import world.GameObject;
import world.PhysicsInterface;

public class LevelLoader {
	private PhysicsInterface[][] level;
	private ArrayList<GameObject> world;
	
	private static final int BLOCK_SIZE = 64;
	
	private static final int BLOCK_CODE = -65536;
	private static final int AIR_CODE = -1;
	
	
	public PhysicsInterface[][] load(String name){
		BufferedImage img;
		
		world.clear();
		
		try {
		    img = ImageIO.read(new File("res/levels/"+ name + ".png"));	
			level = new PhysicsInterface[img.getWidth()][img.getHeight()];
		 
		    
    		for(int i = 0;i < img.getWidth();i++){
    			for(int j = 0;j < img.getHeight();j++){
    				
    				//System.out.println(img.getRGB(i,j));
    				
    				int k = img.getRGB(i,j);
    				PhysicsInterface physicsObject = addObject(k,i,j);
    				
    				
    				if(physicsObject != null){
    					level[i][j] = physicsObject;
    				}
    			}
    		}
		    
		} catch (IOException e) {
			Logger.writeException(e, LevelLoader.class);
			e.printStackTrace();
			Application.close();
		}
		return level;
		
	}
	
	private PhysicsInterface addObject(int key,int x, int y){
		switch(key){
			case BLOCK_CODE:
				Block block = new Block(x * BLOCK_SIZE,y * BLOCK_SIZE);
				world.add(block);
				return block;
			case AIR_CODE:
				return null;
			default:
				Logger.writeMessage("error loading level, unrecognised pixel colour: " + key, LevelLoader.class);
				return null;
				
		}
	}
	
	public LevelLoader(){
		world = new ArrayList<GameObject>();
	}
	
	public ArrayList<GameObject> getWorld(){
		return world;
	}
}
