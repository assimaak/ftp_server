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
 * The Class Rnto.
 */
public class Rnto extends FtpManage {

	/**
	 * Handle the RNTO command.
	 *
	 * @param c the command
	 * @param t the thread corresponding to the client
	 * @return the ftp response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public FtpResponse handle(FtpCommand c, ThreadClient t) throws IOException {
		String newname = c.getArg();
		String newpath = "";
		if (newname.startsWith("/")) {
			newpath = newname;
		} else {
			newpath = t.getPath() + "/" + newname;
		}
		String currentpath = t.getPath() + "/" + t.getFileName();
		Path p = Paths.get(currentpath);
		Path pnew = Paths.get(newpath);
		System.out.println(p);
		System.out.println(newpath);
		try {
			Files.move(p, pnew);
			return new FtpResponse(250, "File renamed succesfully.");
		} catch (IOException e) {
			return new FtpResponse(550, "Failed to rename the directory.");
		}
	}

}
