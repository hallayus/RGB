package graphics;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;


public class GraphicsController {
	//private GraphicsLibrary lib;
	
	public GraphicsController(){
		//lib = new GraphicsLibrary();
	}


	public void render(int i, int j, Sprite sprite) {
		//Texture t = lib.get("textures/block-grey");
		if(sprite != null){
			Texture t = sprite.getTexture();
			if(t != null){
				t.bind();
				if(sprite.getName() == "textures/player_-03") GL11.glColor3f(0.5f,0.5f,1.0f);
				
				int xOffset = sprite.getX();
				int yOffset = sprite.getY();
				
				GL11.glBegin(GL11.GL_QUADS);
					GL11.glTexCoord2f(0,0);
					GL11.glVertex2f(i - xOffset,j - yOffset);
					
					GL11.glTexCoord2f(1,0);
					GL11.glVertex2f(i + t.getTextureWidth() - xOffset,j - yOffset);
					
					GL11.glTexCoord2f(1,1);
					GL11.glVertex2f(i + t.getTextureWidth() - xOffset,j + t.getTextureHeight() - yOffset);
					
					GL11.glTexCoord2f(0,1);
					GL11.glVertex2f(i - xOffset,j + t.getTextureHeight() - yOffset);
				GL11.glEnd();
			}
		}
	}
}
