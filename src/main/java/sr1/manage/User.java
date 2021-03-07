package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class User.
 */
public class User extends FtpManage {

	/**
	 * Handle the USER command.
	 *
	 * @param c the command
	 * @param t the thread corresponding to the client
	 * @return the ftp response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public FtpResponse handle(FtpCommand c, ThreadClient t) throws IOException {
		if (c.getMessage().equals("USER") && c.getArg().equals("anonymous")) {
			return new FtpResponse(331, "Please specify the password.");
		} else {
			return new FtpResponse(530, "This FTP server is anonymous only.");
		}
	}

}
