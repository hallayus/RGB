package state;

import graphics.GraphicsController;
import graphics.Sprite;
import gui.GuiListener;
import gui.GuiObject;
import gui.Option;
import gui.Selector;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import net.NetConnection;
import net.NetworkManager;
import main.Application;
import main.Command;
import main.InputController;
import main.Logger;

public class MainMenu implements State, GuiListener {
	
	private ArrayList<GuiObject> gui;
	private Selector selector;
	private NetworkManager networkManager;
	
	private static final int refreshRate = 5000;
	private long lastRefresh;
	
	
	@Override
	public void render(GraphicsController gc) {
		for(GuiObject object : gui){
			gc.render(object.getX(), object.getY(), object.getSprite());
		}
	}

	@Override
	public void update() {}

	@Override
	public void getInput(InputController ic) {
		/*
		if(ic.getJump()){
			networkManager.ping();
		}
		*/
		selector.getInput(ic);
	}
	
	public MainMenu(){
		networkManager = new NetworkManager();	
		gui = new ArrayList<GuiObject>();
	}    
	
	public void close(){
		networkManager.close();
	}

	@Override
	public void start() {
		Logger.writeMessage("Starting main menu", this.getClass());

		Option play = new Option(320,320,new Sprite("/textures/play_option",0,0));
		Option connect = new Option(320,480,new Sprite("/textures/connect_option",0,0));
		selector = new Selector(play);
		
		selector.addOption(connect);
		
		play.addGuiListener(this);
		connect.addGuiListener(networkManager);
		connect.addGuiListener(this);
		
		gui.add(play);
		gui.add(connect);
		gui.add(selector);
	}

	@Override
	public void guiRespond() {
		Application.changeState(new Game(networkManager));
	}
}
