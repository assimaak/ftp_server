package sr1.manage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.*;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;
import sr1.manage.FtpManage;

public class Mkd extends FtpManage {

	@Override
	public FtpResponse handle(FtpCommand c, ThreadClient t) throws IOException {
		try {
			String name = c.getArg();
			Path p = Paths.get(t.getPath() + "/" + name);
			Files.createDirectories(p);
			return new FtpResponse(257, "New file " + t.getPath() + "/" + name + "created.");
		} catch (IOException e) {
			return new FtpResponse(550, "Failed to create the new directory.");
		}
	}

}
