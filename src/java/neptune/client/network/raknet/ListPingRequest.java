package neptune.client.network.raknet;

import java.nio.ByteBuffer;

import neptune.client.util.*;
import neptune.client.network.RakNetPacket;

public class ListPingRequest implements RakNetPacket {
	private ByteBuffer buffer;
	
	public static final byte PID = 0x01;
	public static final byte[] MAGIC = Utils.getMagic();
	public long pingID;
	
	public ListPingRequest(long pingID){
		this.pingID = pingID;
	}
	
	public void encode(){
		buffer = ByteBuffer.allocate(25);
		
		buffer.put(PID);
		buffer.putLong(pingID);
		buffer.put(MAGIC);
	}
	
	public void decode() { };
	
	public ByteBuffer getBuffer(){
		return buffer;
	}

}
