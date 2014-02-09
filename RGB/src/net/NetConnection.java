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
	
	private DatagramSocket ds;
	private DatagramPacket dp;
	private byte[] data;
	
	private static final int remotePort = 8765;
	private static InetAddress ia;
	
	public void run() {
		data = new byte[1024];
		dp = new DatagramPacket(data, 1024);
		
		
		while(open){	
			try {
				//the server sends a packet whose first byte contains the number of player that connected.
				ds.receive(dp);
				data = dp.getData();
				
				updateListeners(data);
			} catch (IOException e) {
				Logger.write(e.getMessage(), this.getClass());
				return;
			}	
			
		}	
		
	}
	
	public NetConnection(){
		
		listeners = new ArrayList<NetListener>();
		try 
		{
			ia = InetAddress.getByName("localhost");
			ds = new DatagramSocket();
			ds.connect(new InetSocketAddress(ia,remotePort));
			
		} 
		catch (SocketException e) 
		{
			Logger.write(e.getMessage(), this.getClass());
		} 
		catch (UnknownHostException e2) 
		{
			e2.printStackTrace();
			System.out.println("could not find server");
			return;
		}
		
		Thread t = new Thread(this);
		t.start();
	}
	
	public void send(byte[] data) {
		try {
			ds.send(new DatagramPacket(data,data.length));
			System.out.println("sent data: " + new String(data));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close(){
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
