package net;

public class NetworkManager implements NetListener{
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
			System.out.println(personalId);
			
			break;
		case PING_KEY:
			if(personalId == data[1]){
				state = NetworkState.CONNECTED;
				System.out.println("Testing successful");
			}
			break;
		default:
			break;
		}	
	}

	public void start(){
		if(netConn != null){
			System.out.println("initialising connection");
			byte[] data = {INIT_KEY,0};
			netConn.send(data);
			state = NetworkState.WAITING;
		}else{
			System.out.println("network connection not yet initialised");
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
	
}
