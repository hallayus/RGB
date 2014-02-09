package net;

import java.util.ArrayList;
import java.util.Arrays;

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

public class NetworkManager implements GuiListener{
	public static final int INIT_KEY = 76;
	public static final int GAME_KEY = 98;
	public static final int CHAT_KEY = 109;
	public static final int PING_KEY = 45;
	public static final int INPUT_KEY = 12;
	
	private ArrayList<NetListener> listeners;
	
	private NetConnection netConn;
	private NetworkState state;
	private byte personalId;
	
	private void processData(byte[] data) 
	{
		int key = (int) data[0];
		String message = new String(Arrays.copyOfRange(data, 2, data.length));
		
		switch(key){
		case INIT_KEY:
			personalId = data[1];
			state = NetworkState.CONNECTED;
			Logger.writeMessage("Received personal ID of: " + personalId, this.getClass());
			break;
		case PING_KEY:
			if(personalId == data[1] && personalId != 0){
				state = NetworkState.CONNECTED;
				Logger.writeMessage("received ping back from server", this.getClass());
			}
			break;
		default:
			break;
		}	
	}
	
	public void update()
	{
		processData(netConn.receive());
	}

	public void start()
	{
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
	
	public void ping()
	{
		if(state == NetworkState.CONNECTED && (personalId != 0)){
			state = NetworkState.PINGING;
			byte[] data = {PING_KEY,personalId};
			netConn.send(data);
		}
	}
	
	public NetworkManager()
	{
		netConn = new NetConnection();
		listeners = new ArrayList<NetListener>();
	}
	
	public void close()
	{
		netConn.close();
	}

	@Override
	public void guiRespond() 
	{
		start();
	}
	
	public void addNetListener(NetListener netListener){
		listeners.add(netListener);
	}
	
	private void publish(String message, int key){
		for(NetListener listener : listeners){
			listener.netUpdate(message, key);
		}
	}
	
}
