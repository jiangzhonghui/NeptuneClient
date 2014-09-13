package test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class PacketSender {
	
	DatagramPacket dp;

	public void sendPacket(byte packet,DatagramSocket socket,String ip,int port){
		try{
		InetAddress host = InetAddress.getByName(ip);
		socket = new DatagramSocket();
		
		byte[] packetArray = {packet};
		dp = new DatagramPacket(packetArray, packetArray.length,host,port);
		
		System.out.println("Sending packet: "+packet);
		socket.send(dp);
		System.out.println("Packet "+packet+" sent!");
		
		}catch(Exception e){
			System.out.println("ERROR: "+e);
			}
	}
	
	public DatagramPacket getPacket(){
		return dp;
	}
	
}
