package sr1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;



/**
 * @author Nordine El ammari
 * @author Arthur Assima
 * 
 *         Connexion with an FTP client. 
 *         The thread will be executed when the server 
 *         receives a new client socket.
 */
public class ThreadClient implements Runnable {
	private Socket client;
	@SuppressWarnings("unused")
	private int port;
	

	public ThreadClient(Socket client) throws Exception{
		super();
		this.client = client;
		
	
	}

	@Override
	public void run() {
		try (BufferedWriter controlOut = new BufferedWriter(
				new OutputStreamWriter(this.client.getOutputStream(), StandardCharsets.UTF_8));
				BufferedReader controlIn = new BufferedReader(
						new InputStreamReader(this.client.getInputStream(), StandardCharsets.UTF_8));) {

			FtpController controller = new FtpController(controlOut, controlIn);
			controller.sendResponse(new FtpResponse(220 , "Welcome everyone"));
			boolean isRunning = true;
			while (isRunning) {
				// Waits for a command
				FtpCommand command = controller.readCommand();

				// if nothing read in the control channel, then the communication is ended
				if (command == null) {
					System.out.println("Communication ended by the client. Connection abort.");
					isRunning = false;
					continue;
				}
				FtpResponse response = this.execute(command);
				if (response == null) {
					continue;
				}
				controller.sendResponse(response);

				if (command.getMessage().equals("QUIT")) { // quit command finishes the loop
					isRunning = false;
				}
			}

			// closing the client.
			this.client.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public FtpResponse execute(FtpCommand cmd) {
		return null;
	}
}
