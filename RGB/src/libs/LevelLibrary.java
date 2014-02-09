package libs;

import graphics.Sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import player.Player;


import main.Application;

import world.Air;
import world.Block;
import world.GameObject;

public class LevelLibrary {
	private static HashMap<Integer,GameObject> objectMap;
	
	public static GameObject[][] load(String name){
		BufferedImage img = null;
		GameObject[][] level = null;
		
		
		try {
		    img = ImageIO.read(new File("res/"+ name + ".png"));
		    
		    level = new GameObject[img.getWidth()][img.getHeight()];
		    		
    		for(int i = 0;i < img.getWidth();i++){
    			for(int j = 0;j < img.getHeight();j++){
    				
    				//System.out.println(img.getRGB(i,j));
    				
    				Integer k = Integer.valueOf(img.getRGB(i,j));
    				GameObject base = getObject(k);
    				
    				int blockSize = GameObject.BLOCK_SIZE;
    				
    				if(base != null){
    					level[i][j] = base.clone(i * blockSize,j * blockSize);
    				}
    			}
    		}
		    
		} catch (IOException e) {
			e.printStackTrace();
			Application.close();
		}
		return level;
		
	}
	
	public static void init(){
		objectMap = new HashMap<Integer,GameObject>();
		Sprite spr = new Sprite("textures/block-grey",0,0);
		
		
		objectMap.put(-65536, new Block(0,0,spr)); //red - maraschino
		objectMap.put(-7274634, new Block(0,0,spr));
		objectMap.put(-60158, new Block(0,0,spr));
		objectMap.put(-1, new Air(0,0,null));
		
	}
	
	private static GameObject getObject(Integer key){
		if(objectMap.containsKey(key)){
			return objectMap.get(key);
		}
		System.out.println("error loading level, unrecognised pixel colour: " + key);
		return null;
	}
}
