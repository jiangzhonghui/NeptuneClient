package neptune.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.util.Arrays;

import neptune.client.network.raknet.ListPingResponse;
import neptune.client.process.ListPingProcess;
import neptune.client.process.ProcessException;
import neptune.client.util.AppLogger;

public class NeptuneClient {

	public static void main(String[] args) {
		// Main Startup file for the Client.
		Thread.currentThread().setName("AppThread");
		AppLogger logger = new AppLogger();
		
		logger.info("This is Neptune Client");
		long startTime = System.currentTimeMillis();
		while(true){
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			try {
				String input = console.readLine();
				if(input.equals("ping")){
					System.out.print("Enter an IP: ");
					String ip = console.readLine();
					
					long pingID = System.currentTimeMillis() - startTime;
					
					logger.info("Pinging "+ip+"...");
					ListPingProcess pingProcess = new ListPingProcess(ip, 19132, pingID);
					try {
						pingProcess.runProcess();
						ListPingResponse response = pingProcess.getResult();
						if(response != null){
							//logger.info("Recived response ("+response.getBuffer().capacity()+" bytes of data): "+buffer);
							long serverID = response.serverID;
							String name = response.serverName;
							logger.info("Recived response...");
							logger.info("Server's identification long is: "+serverID);
							logger.info("Server name is: "+name);
						}
						else{
							logger.warning("Recived no response from: "+ip);
						}
						
					} catch (ProcessException e) {
						e.printStackTrace();
					}
				}
				else{
					logger.info("Unknown command: "+input);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
