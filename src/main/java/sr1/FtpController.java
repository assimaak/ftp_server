package sr1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class FtpController.
 */
public class FtpController {
	private BufferedWriter writer;
	private BufferedReader reader;

	public FtpController(BufferedWriter writer, BufferedReader reader) {
		super();
		this.writer = writer;
		this.reader = reader;
	}

	/**
	 * Sends an FTP response to the client through the controller.
	 * 
	 * @param response the FTP response to send
	 * @throws IOException when a network error on write through socket
	 */
	public void sendResponse(FtpResponse response) throws IOException {
		System.out.println("> " + response.getCode() + " " + response.getMsg());
		this.writer.write(response.toString());
		this.writer.flush();
	}

	/**
	 * Reads a command of the FTP client through the control socket connection.
	 * 
	 * @return an instance of the FTP command based on the message sent by the FTP
	 *         client.
	 * @throws IOException if a network error on read through socket
	 */
	public FtpCommand readCommand() throws IOException {
		String line = this.reader.readLine();
		if (line == null) {
			return null;
		}

		String[] mots = line.split(" ");
		if (mots.length > 1) { //Command with an argument (ex : CWD)
			String param = mots[1];
			for (int i = 2; i < mots.length; i++) {
				param += " " + mots[i];
			}

			System.out.println("Command received : " + mots[0] + " " + param);
			return new FtpCommand(mots[0], param);
		} else {
			System.out.println("Command received : " + mots[0]);
			return new FtpCommand(mots[0]);
		}
	}

	public BufferedWriter getWriter() {
		return writer;
	}

	public BufferedReader getReader() {
		return reader;
	}
}
