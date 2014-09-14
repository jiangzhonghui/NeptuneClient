package neptune.client.network.raknet;

import java.nio.ByteBuffer;

import neptune.client.network.RakNetPacket;
import neptune.client.util.Utils;

public class ListPingResponse implements RakNetPacket{
	private ByteBuffer buffer;
	
	public byte PID;
	public long pingID;
	public long serverID;
	public byte[] MAGIC = new byte[16];
	public short serverNameLength;
	public String serverName;
	
	public ListPingResponse(byte[] buffer){
		this.buffer = ByteBuffer.wrap(buffer);
	}
	
	public void decode(){
		PID = buffer.get();
		pingID = buffer.getLong();
		serverID = buffer.getLong();
		buffer.get(MAGIC);
		serverNameLength = buffer.getShort();
		byte[] serverBytes = new byte[serverNameLength];
		buffer.get(serverBytes);
		
		serverName = new String(serverBytes);
	}
	
	public void encode() { };
	
	public ByteBuffer getBuffer(){
		return this.buffer;
	}

}
