package neptune.client.network;

import java.nio.ByteBuffer;

public interface RakNetPacket {
	
	void encode();
	void decode();
	
	ByteBuffer getBuffer();

}
