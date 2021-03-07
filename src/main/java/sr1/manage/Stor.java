package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class Stor.
 */
public class Stor extends FtpManage {

	/**
	 * Handles the STOR command.
	 *
	 * @param c the command
	 * @param t the thread corresponding to the client
	 * @return the ftp response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public FtpResponse handle(FtpCommand c, ThreadClient t) throws IOException {
		String name = c.getArg();
		t.getController().sendResponse(new FtpResponse(150, "OK to send data."));
		try {
			if (t.getType()==true) { //Ascii
				t.getData().uploadBinary(name);
				return new FtpResponse(226, "Transfer completed.");
			} else if (t.getType()==false) { //Binary
				t.getData().uploadASCII(name);
				return new FtpResponse(226, "Transfer completed.");
			}
		} catch (IOException e) {
			return new FtpResponse(552, "Failed to transfer.");
		}
		return null;
	}

}
