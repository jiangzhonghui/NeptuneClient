package test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

public class Test {
	String username = "COW_PC";
	String ip = "192.168.1.3";
	int port = 19132;
	DatagramSocket socket = null ;
	
	PacketSender packetSender = new PacketSender();
	PacketReciever packetReciever = new PacketReciever();
	
	
	
	

	   public void run()
	   {
	      
	      try{
	    	  packetSender.sendPacket(PacketList.CONNECTED_PING_OPEN_CONNECTIONS, socket, ip, port);
	    	  packetReciever.getPacket(socket, packetSender.getPacket());
	    	  packetReciever.printPacket(packetSender.getPacket());
	    	  
	    	  
	    	  
	      }
	      
          //catch(SocketTimeoutException e){System.out.println("Timed out, maybe sent wrog packets? :'( ");}
	      
	      catch( Exception e )
	      {
	         System.out.println( e ) ;
	      }
	      finally
	      {
	         if( socket != null );
	           // socket.close() ;
	      }
	   }
}
