package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class List.
 */
public class List extends FtpManage {

	/**
	 * Handles the LIST command.
	 *
	 * @param c the command
	 * @param t the thread corresponding to the client
	 * @return the ftp response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public FtpResponse handle(FtpCommand c, ThreadClient t) throws IOException {
		try {
			t.getController().sendResponse(new FtpResponse(150, "Here comes the directory listing."));
			t.getData().listing();
			return new FtpResponse(226, "Directory send ok.");
		} catch (IOException e) {
			return new FtpResponse(530, "Failed to send the directory.");
		}
	}

}
