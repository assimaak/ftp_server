package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class Cdup.
 */
public class Cdup extends FtpManage {

	/**
	 * Handle.
	 *
	 * @param c the command
	 * @param t the thread corresponding to the client
	 * @return the ftp response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public FtpResponse handle(FtpCommand c, ThreadClient t) throws IOException {
		int cpt = 0;
		cpt = t.getPath().length() - t.getPath().replace("/", "").length();
		if (cpt <= 1) {
			t.setPath("/");
		} else {
			t.setPath(t.getPath().substring(0, t.getPath().lastIndexOf('/')));
		}
		return new FtpResponse(200, "Directory succesfully changed.");
	}

}
