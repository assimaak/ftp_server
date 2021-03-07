package sr1.manage;

import java.io.IOException;
import java.nio.file.*;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;


/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class Cwd.
 */
public class Cwd extends FtpManage {

	/**
	 * Handles the CWD command.
	 *
	 * @param c the command
	 * @param t the thread corresponding to the client
	 * @return the ftp response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public FtpResponse handle(FtpCommand c, ThreadClient t) throws IOException {
		String path = c.getArg();
		if (path.startsWith("/")) {
			t.setPath(path);
		} else if (t.getPath().equals("/")) {
			t.setPath(t.getPath() + path);
		} else {
			t.setPath(t.getPath() + "/" + path);
		}
		Path p = Paths.get(t.getPath());
		if (Files.exists(p)) {
			return new FtpResponse(250, "Directory successfully changed.");
		} else {
			return new FtpResponse(550, "Failed to change directory");
		}

	}

}
