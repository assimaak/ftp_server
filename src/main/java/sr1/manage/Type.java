package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class Type.
 */
public class Type extends FtpManage {

	/**
	 * Handle the TYPE command.
	 *
	 * @param c the command
	 * @param t the thread corresponding to the client
	 * @return the ftp response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public FtpResponse handle(FtpCommand c, ThreadClient t) throws IOException {
		if (c.getArg().equals("I")) {
			t.setType(false);
			return new FtpResponse(200, "Switching to Binary mode.");
		} else if (c.getArg().equals("A")){
			t.setType(true);
			return new FtpResponse(200,"Switching to ASCII mode.");
		} else {
			return new FtpResponse(500, "Unrecognised TYPE command.");
		}
	}

}
