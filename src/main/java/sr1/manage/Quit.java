package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;


/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class Quit.
 */
public class Quit extends FtpManage {

	/**
	 * Handles the QUIT command.
	 *
	 * @param c the command
	 * @param t the thread corresponding to the client
	 * @return the ftp response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public FtpResponse handle(FtpCommand c, ThreadClient t) throws IOException {
		try {
			t.getSocket().close();
			return new FtpResponse(221, "Server Closed.");
		} catch (IOException e) { 
			return new FtpResponse(500,"Error");
		}
	}

}
