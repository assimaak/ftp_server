package sr1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import sr1.manage.*;

/**
 * @author Nordine El ammari
 * @author Arthur Assima
 * 
 *         Connexion with an FTP client. The thread will be executed when the
 *         server receives a new client socket.
 */
public class ThreadClient implements Runnable {
	private Socket client;
	@SuppressWarnings("unused")
	private int port;
	private String path = "/";
	private boolean type; // true = ASCII, false = BINARY
	private ServerSocket passive;
	private FtpData data;
	private FtpController controller;
	private String filename;

	public ThreadClient(Socket client) throws Exception {
		super();
		this.client = client;
		this.type = true;

	}

	/**
	 * Runs the serverftp's communication.
	 */
	@Override
	public void run() {
		try (BufferedWriter controlOut = new BufferedWriter(
				new OutputStreamWriter(this.client.getOutputStream(), StandardCharsets.UTF_8));
				BufferedReader controlIn = new BufferedReader(
						new InputStreamReader(this.client.getInputStream(), StandardCharsets.UTF_8));) {

			controller = new FtpController(controlOut, controlIn);
			controller.sendResponse(new FtpResponse(220, "Welcome everyone"));
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
				if (response.getMsg().contains("Entering passive mode")) { // Opening a new socket and FtpData to send/
																			// retrieve data
					Socket s = passive.accept();
					data = new FtpData(s, this);
					continue;
				}
				if (command.getMessage().equals("QUIT")) { // quit command finishes the loop
					isRunning = false;
				}
			}

			// closing the client.
			this.client.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Execute the command received by the server.
	 *
	 * @param cmd the command
	 * @return the ftp response
	 * @throws NullPointerException the null pointer exception
	 */
	public FtpResponse execute(FtpCommand cmd) throws NullPointerException {
		FtpManage m = new ManageFactory().managesMap().get(cmd.getMessage()); // We use the FtpManage corresponding to
																				// the command
		try {
			return m.handle(cmd, this);
		} catch (IOException e) {
			return new FtpResponse(500, "Error, your command is unrecognized/not implemented yet.");
		} catch (NullPointerException e) {
			return new FtpResponse(500, "Error, your command is unrecognized/not implemented yet.");
		}
	}

	/**
	 * Gets the current path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the current path.
	 *
	 * @param path the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Gets the type of the data (ascii or binary).
	 *
	 * @return the type
	 */
	public boolean getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(boolean type) {
		this.type = type;
	}

	/**
	 * Sets the passive server socket.
	 *
	 * @param s the new passive
	 */
	public void setPassive(ServerSocket s) {
		this.passive = s;
	}

	/**
	 * Gets the FtpData for the data transfers.
	 *
	 * @return the FtpData
	 */
	public FtpData getData() {
		return data;
	}

	/**
	 * Gets the FtpController.
	 *
	 * @return the controller
	 */
	public FtpController getController() {
		return controller;
	}

	/**
	 * Sets the filename to rename a file.
	 *
	 * @param name the new filename
	 */
	public void setFilename(String name) {
		this.filename = name;

	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return this.filename;
	}

	/**
	 * Gets the socket of the client.
	 *
	 * @return the socket
	 */
	public Socket getSocket() {
		return this.client;
	}

}
