package gui;

import java.util.ArrayList;
import graphics.Sprite;

public class Option extends GuiObject {
	private ArrayList<GuiListener> listeners;
	
	
	public Option(int x, int y, Sprite sprite) {
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
