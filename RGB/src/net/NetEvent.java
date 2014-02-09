package net;

import java.net.DatagramPacket;
import java.util.Arrays;

public class NetEvent {
	private static final int INIT_KEY = 76;
	private static final int GAME_KEY = 98;
	private static final int CHAT_KEY = 109;
	private static final int PING_KEY = 45;
	private static final int INPUT_KEY = 12;
	
	private byte playerId;
	private int eventType;
	private String message;
	
	public NetEvent(DatagramPacket packet){
		byte[] data = packet.getData();
		
		eventType = data[0];
		playerId = data[1];
		message = new String(Arrays.copyOfRange(data,2,data.length));
	
	}
	
	public int getPlayerId(){
		return playerId;
	}

	public int getEventType() {
		return eventType;
	}

	public String getMessage() {
		return message;
	}

}
