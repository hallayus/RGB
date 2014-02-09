package state;


import graphics.GraphicsController;

import java.net.*;
import java.util.ArrayList;

import net.NetConnection;
import player.InputController;
import player.Player;
import world.GameObject;
import libs.LevelLibrary;
import main.Command;


public class Game implements State {
	private NetConnection nc;
	
	private ArrayList<Object> assets;
	private GameObject[][] world;
	private Player player;
	
	
	@Override
	public void render(GraphicsController gc) {
		for(int i = 0;i < world.length;i++){
			for(int j = 0;j < world[i].length;j++){
				GameObject go = world[i][j];
				if(go != null){
					
					//System.out.println(world[i][j].getSprite());
					gc.render((int)go.getX(),(int) go.getY(), world[i][j].getSprite());
				}
			}
		}
		gc.render((int)player.getX(),(int)player.getY(),player.getSprite());
	}

	@Override
	public void update() {
		player.update();
	}

	@Override
	public void getInput(InputController ic) {
		player.getInput(ic);
	}
	
	public Game(NetConnection nc){
		this.nc = nc;
		assets = new ArrayList<Object>();
		LevelLibrary.init();
		world = LevelLibrary.load("levels/level1");
		player = new Player(0,0,world);
	}
	
	public void close(){
		nc.close();
	}

}
