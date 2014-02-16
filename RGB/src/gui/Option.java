package gui;

import java.util.ArrayList;
import graphics.StillSprite;

public class Option extends GuiObject {
	private ArrayList<GuiListener> listeners;
	
	
	public Option(int x, int y, StillSprite sprite) {
		super(x, y, sprite);
		listeners = new ArrayList<GuiListener>();
	}

	public void activate(){
		for(GuiListener listener : listeners){
			listener.guiRespond();
		}
	}
	
	public void addGuiListener(GuiListener listener){
		listeners.add(listener);
	}
}
