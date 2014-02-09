package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import main.Logger;

public class NetConnection implements Runnable {
	private boolean open = true;
	private ArrayList<NetListener> listeners;
	
	private static final String REMOTE_ADDR = "localhost";
	private static final int REMOTE_PORT = 8765;
	
	private DatagramSocket ds;
	private DatagramPacket dp;
	private byte[] data;
	
	private static InetAddress ia;
	
	public void run() {
		data = new byte[1024];
		dp = new DatagramPacket(data, 1024);
		
		
		while(open){	
			try {
				ds.receive(dp);
				data = dp.getData();
				
				updateListeners(data);
			} catch (IOException e) {
				Logger.writeException(e, this.getClass());
				return;
			}	
		}	
		
	}
	
	public NetConnection(){
		
		listeners = new ArrayList<NetListener>();
		try {
			ds = new DatagramSocket();
		} catch (SocketException e) {
			Logger.writeException(e, this.getClass());
		}
		
	}
	
	public void send(byte[] data) {
		try {
			ds.send(new DatagramPacket(data,data.length));
			Logger.writeMessage("sent data: " + new String(data), this.getClass());
		} catch (IOException e) {
			Logger.writeException(e, this.getClass());
		}
	}
	public void start(){
		try 
		{
			ia = InetAddress.getByName(REMOTE_ADDR);
			ds.connect(new InetSocketAddress(ia,REMOTE_PORT));
			(new Thread(this)).start();
			Logger.writeMessage("connected to server at: " + REMOTE_ADDR + ":" + REMOTE_PORT, this.getClass());
			
		} 
		catch (SocketException e) 
		{
			Logger.writeException(e, this.getClass());
			return;
		} 
		catch (UnknownHostException e2) 
		{
			Logger.writeException(e2, this.getClass());
			return;
		}
	}
	
	
	public void close(){
		open = false;
		ds.close();
	}
	
	public void addNetListener(NetListener listener){
		listeners.add(listener);
	}
	
	private void updateListeners(byte[] data){
		for(NetListener listener : listeners){
			listener.netUpdate(data);
		}
	}
}
