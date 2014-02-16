package graphics;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;


public class GraphicsController {
	
	private int cameraX = 0;
	private int cameraY = 0;

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
					GL11.glVertex2f(i - xOffset - cameraX,j - yOffset - cameraY);
					
					GL11.glTexCoord2f(1,0);
					GL11.glVertex2f(i + t.getTextureWidth() - xOffset - cameraX,j - yOffset - cameraY);
					
					GL11.glTexCoord2f(1,1);
					GL11.glVertex2f(i + t.getTextureWidth() - xOffset - cameraX,j + t.getTextureHeight() - yOffset - cameraY);
					
					GL11.glTexCoord2f(0,1);
					GL11.glVertex2f(i - xOffset - cameraX,j + t.getTextureHeight() - yOffset - cameraY);
				GL11.glEnd();
			}
		}
	}
	
	public void setCameraPosition(int x, int y){
		cameraX = x;
		cameraY = y;
	}
}
