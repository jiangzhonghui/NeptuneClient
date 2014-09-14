package neptune.client.process;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;

import neptune.client.network.raknet.ListPingRequest;
import neptune.client.network.raknet.ListPingResponse;

public class ListPingProcess implements Process{
	protected String ip;
	protected int port;
	protected long pingID;
	
	private DatagramSocket sock;
	private boolean success;
	private ListPingResponse responsePkt = null;
	
	public ListPingProcess(String ip, int port, long pingID){
		this.ip = ip;
		this.port = port;
		this.pingID = pingID;
	}
	
	public void runProcess() throws ProcessException{
		try {
			sock = new DatagramSocket();
		
			ListPingRequest request = new ListPingRequest(pingID);
			request.encode();
			
			DatagramPacket pkt = new DatagramPacket(request.getBuffer().array(), request.getBuffer().capacity());
			pkt.setAddress(InetAddress.getByName(ip));
			pkt.setPort(port);
			
			sock.send(pkt);
			System.out.println("Sent data");
			sock.setSoTimeout(5000);
			
			DatagramPacket response = new DatagramPacket(new byte[256], 256);
			try{
				sock.receive(response);
				success = true;
			} catch(SocketTimeoutException e){
				success = false;
			} finally {
				sock.close();
			}
			
			if(success == true){
				byte[] buffer = response.getData();
				if(buffer[0] != 0x1c){
					throw new ProcessException("Unknown packet recived: "+buffer[0]);
				}
				else{
					ListPingResponse rsPkt = new ListPingResponse(buffer);
					rsPkt.decode();
					this.responsePkt = rsPkt;
				}
			}
			else{
				System.out.println("Failed to recive response...");
			}
			
			
		} catch (SocketException e) {
			throw new ProcessException("Socket Exception: "+e.getCause().getMessage());
		} catch (UnknownHostException e) {
			throw new ProcessException("Could not figure out host of: "+ip);
		} catch (IOException e) {
			throw new ProcessException("Could not send data: "+e.getCause().getMessage());
		}
		
	}
	
	public ListPingResponse getResult(){
		if(responsePkt != null){
			return responsePkt;
		}
		else{
			return null;
		}
		
	}

}
