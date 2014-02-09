package state;

import graphics.GraphicsController;

import java.io.IOException;
import java.net.*;

import player.InputController;
import net.NetConnection;
import net.NetworkManager;
import main.Application;
import main.Command;

public class MainMenu implements State {
	
	private NetConnection conn;
	private NetworkManager networkManager;
	
	private static final int refreshRate = 5000;
	private long lastRefresh;
	
	
	@Override
	public void render(GraphicsController gc) {
		//if(conn.getPlayers(0)){ gc.render(0, 0, "splash04"); }else{ gc.render(0, 0, "splash01"); };
		//if(conn.getPlayers(0)){ gc.render(0, 0, "splash05"); }else{ gc.render(0, 0, "splash02"); };
		//if(conn.getPlayers(0)){ gc.render(0, 0, "splash06"); }else{ gc.render(0, 0, "splash03"); };
	}

	@Override
	public void update() {
		//Application.changeState(new Game(conn));
		/*
		long currTime = System.currentTimeMillis();
		
		if((currTime - lastRefresh) > refreshRate){
			lastRefresh = currTime;
			try {
				ds.send(signal);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(conn.getPlayers(0) && conn.getPlayers(1) && conn.getPlayers(2)){
			Application.changeState(new Frame("PlayersConnected",5000,new Game(ds)));
		}
		*/
		
		
	}

	@Override
	public void getInput(InputController ic) {
		if(ic.getJump()){
			networkManager.ping();
		}
	}
	
	public MainMenu(){
		System.out.println("starting main menu");
		
		lastRefresh = System.currentTimeMillis();
		
		//create connection to server
		networkManager = new NetworkManager();	
		networkManager.start();
		
	}    
	
	public void close(){
		networkManager.close();
	}
}
