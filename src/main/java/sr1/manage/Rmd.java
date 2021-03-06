package sr1.manage;

import java.io.IOException;
import java.nio.file.*;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;


/**
 * @author ASSIMA Arthur
 * @author EL AMMARI Nordine
 * The Class Rmd.
 */
public class Rmd extends FtpManage {

	/**
	 * Handle the RMD command.
	 *
	 * @param c the command
	 * @param t the thread corresponding to the client
	 * @return the ftp response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public FtpResponse handle(FtpCommand c, ThreadClient t) throws IOException {
		String name = c.getArg();
		String path = t.getPath() + "/" + name;
		Path p = Paths.get(path);
		System.out.println(path);
		try {
			Files.delete(p);
			return new FtpResponse(250, "Deletion succesfull.");
		} catch (NoSuchFileException e) {
			return new FtpResponse(530, "File not found.");
		} catch (IOException e1) {
			return new FtpResponse(550, "Permission denied.");
		}
	}
}
