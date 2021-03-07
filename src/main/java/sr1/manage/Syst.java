package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

// TODO: Auto-generated Javadoc
/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class Syst.
 */
public class Syst extends FtpManage {

	/**
	 * Handle the SYST command.
	 *
	 * @param c the command
	 * @param t the thread corresponding to the client
	 * @return the ftp response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public FtpResponse handle(FtpCommand c, ThreadClient t) throws IOException {
		return new FtpResponse(215, System.getProperty("os.name") + " " + System.getProperty("os.version"));
	}

}
