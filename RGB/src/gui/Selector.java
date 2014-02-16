package gui;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import main.InputController;
import main.Logger;
import graphics.GraphicsController;
import graphics.StillSprite;

public class Selector extends GuiObject{
	private static final int OFFSET = 8;
	private static final int SIZE = 64;
	
	private boolean accept = true;
	
	private ArrayList<Option> options;
	private int currOption;
	
	public Selector(Option firstOption){
		super(-SIZE,-SIZE,new StillSprite("selector_red",0,0));
		options = new ArrayList<Option>();
		options.add(firstOption);
		
		currOption = 0;
		
		update();
	}
	
	public void addOption(Option option){
		options.add(option);
	}
	
	public void getInput(InputController ic){
		if(ic.getMenuUp())
		{
			if(accept)
			{
				currOption++;
				currOption %= options.size();
				update();
				accept = false;
			}
		}
		else if(ic.getMenuDown())
		{
			if(accept)
			{
			currOption--;
			currOption = (currOption < 0)?options.size() - 1:currOption;
			update();
			accept = false;
			}
		}
		else if(ic.getMenuSelect())
		{
			if(accept)
			{
			Option selected = options.get(currOption);
			selected.activate();
			accept = false;
			}
		}
		else
		{
			accept = true;
		}
	}
	
	private void update(){
		Logger.writeMessage("current menu option: " + currOption, this.getClass());
		
		GuiObject option = options.get(currOption);
		StillSprite optionSprite = option.getSprite();
		
		Texture optionTexture = optionSprite.getTexture();
		
		
		int optionHeight = 0;
		if(optionTexture != null) optionHeight = optionTexture.getTextureHeight();
		
		this.x = option.getX() - SIZE - OFFSET;
		this.y = (option.getY() + ((optionHeight - SIZE)/2));
	}
}
