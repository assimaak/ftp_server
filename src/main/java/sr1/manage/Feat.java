package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;


/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class Feat.
 */
public class Feat extends FtpManage {

	/**
	 * Handles the FEAT command.
	 *
	 * @param c the command
	 * @param t the thread corresponding to the client
	 * @return the ftp response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public FtpResponse handle(FtpCommand c, ThreadClient t) throws IOException {
		return new FtpResponse(211, "Features:\r\n- Check the readme on GitLab\r\n211 End.");
	}

}
