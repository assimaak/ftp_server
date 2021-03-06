package sr1.manage;

import java.io.IOException;
import java.nio.file.*;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

public class Cwd extends FtpManage {

	@Override
	public FtpResponse handle(FtpCommand command, ThreadClient t) throws IOException {
		String path = command.getArg();
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
