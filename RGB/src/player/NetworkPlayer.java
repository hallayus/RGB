package player;

import net.NetListener;
import net.NetworkManager;
import graphics.Sprite;
import world.GameObject;

public class NetworkPlayer extends GameObject implements NetListener{
	private byte id;
	
	
	public NetworkPlayer(Sprite sprite) {
		super(sprite);
		x = -BLOCK_SIZE;
		y = -BLOCK_SIZE;
	}

	@Override
	public GameObject clone(int x, int y) {
		return null;
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public void netUpdate(String message, int key) {
		if(key == NetworkManager.INPUT_KEY){
			
		}
	}
}
