package state;


import graphics.GraphicsController;

import java.net.*;
import java.util.ArrayList;

import net.NetConnection;
import net.NetworkManager;
import player.Player;
import world.GameObject;
import libs.LevelLibrary;
import main.Command;
import main.InputController;


public class Game implements State {
	private NetworkManager networkManager;
	
	private GameObject[][] world;
	private Player player;
	
	
	@Override
	public void render(GraphicsController gc) {
		for(int i = 0;i < world.length;i++){
			for(int j = 0;j < world[i].length;j++){
				GameObject go = world[i][j];
				if(go != null){					
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
	
	public Game(NetworkManager networkManager){
		this.networkManager = networkManager;
		LevelLibrary.init();
		world = LevelLibrary.load("levels/level1");
		player = new Player(640,320,world);
	}
	
	public void close(){
		networkManager.close();
	}
	
	public void start(){
		
	}

}
