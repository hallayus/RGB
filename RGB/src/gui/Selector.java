package gui;

import java.util.ArrayList;

import main.InputController;
import main.Logger;
import graphics.GraphicsController;
import graphics.Sprite;

public class Selector extends GuiObject{
	private static final int OFFSET = 8;
	private static final int SIZE = 64;
	
	private boolean accept = true;
	
	private ArrayList<Option> options;
	private int currOption;
	
	public Selector(Option firstOption){
		super(firstOption.getX() - SIZE - OFFSET,firstOption.getY(),new Sprite("textures/selector_red",0,0));
		options = new ArrayList<Option>();
		options.add(firstOption);
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
		Sprite optionSprite = option.getSprite();
		this.x = option.getX() - SIZE - OFFSET;
		this.y = option.getY();
	}
}
