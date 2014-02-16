package state;


import graphics.GraphicsController;

import java.net.*;
import java.util.ArrayList;

import net.NetConnection;
import net.NetworkManager;
import player.Player;
import world.GameObject;
import world.LevelLoader;
import world.RootObject;
import main.Command;
import main.InputController;


public class Game implements State {
	private NetworkManager networkManager;
	
	private RootObject root;
	
	
	@Override
	public void render(GraphicsController gc) {
		root.render(gc);
	}

	@Override
	public void update() {
		root.update();
	}

	@Override
	public void getInput(InputController ic) {
		root.getInput(ic);
	}
	
	public Game(NetworkManager networkManager){
		this.networkManager = networkManager;
		root = new RootObject();
	}
	
	public void close(){
		networkManager.close();
	}
	
	public void start(){
		
	}

}
