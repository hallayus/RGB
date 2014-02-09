package net;

import gui.GuiListener;
import main.Logger;

/*
 * Some notes on this class: 
 * 	RGB used UDP composed of byte arrays.
 *  the first byte of this array always denotes the type of message. The finals below indicate what packet types are expected
 *  the second byte of this array is the players unique key, which is a signed byte not equal to 0.
 *  the rest of the message is a byte representation of a CSV file containing name value pairs. 
 *  this while be redone soon, but I wanted some working networking to continue with gameplay
 */

public class NetworkManager implements NetListener, GuiListener{
	private static final int INIT_KEY = 76;
	private static final int GAME_KEY = 98;
	private static final int CHAT_KEY = 109;
	private static final int PING_KEY = 45;
	private static final int INPUT_KEY = 12;
	
	private NetConnection netConn;
	private NetworkState state;
	private byte personalId;
	
	public void netUpdate(byte[] data) {
		int key = (int) data[0];
		
		switch(key){
		case INIT_KEY:
			
			personalId = data[1];
			state = NetworkState.CONNECTED;
			Logger.writeMessage("Received personal ID of: " + personalId, this.getClass());
			break;
		case PING_KEY:
			if(personalId == data[1]){
				state = NetworkState.CONNECTED;
				Logger.writeMessage("received ping back from server", this.getClass());
			}
			break;
		default:
			break;
		}	
	}

	public void start(){
		if(netConn != null){
			Logger.writeMessage("connecting to server...", this.getClass());
			netConn.start();
			
			byte[] data = {INIT_KEY,0};
			netConn.send(data);
			state = NetworkState.WAITING;
		}else{
			Logger.writeMessage("NetConnection not yet initialised", this.getClass());
		}
	}
	
	public void ping(){
		if(state == NetworkState.CONNECTED && (personalId != 0)){
			state = NetworkState.PINGING;
			byte[] data = {PING_KEY,personalId};
			netConn.send(data);
		}
	}
	
	public NetworkManager(){
		netConn = new NetConnection();
		netConn.addNetListener(this);
	}
	
	public void close(){
		netConn.close();
	}

	@Override
	public void guiRespond() {
		start();
	}
	
}
