package sr1.manage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class Rnfr.
 */
public class Rnfr extends FtpManage {

	/**
	 * Handle the RNFR command.
	 *
	 * @param c the command
	 * @param t the thread corresponding to the client
	 * @return the ftp response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public FtpResponse handle(FtpCommand c, ThreadClient t) throws IOException {
		String name = c.getArg();
		Path p = Paths.get(t.getPath() + "/" + name);
		if (Files.exists(p)) {
			System.out.println(name);
			t.setFilename(name);
			return new FtpResponse(350,"File or directory exists, ready for destination name.");
		}
		return new FtpResponse(550, "Failed to rename the directory.");
	}

}
