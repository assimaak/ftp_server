package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

public class Cdup extends FtpManage {

	@Override
	public FtpResponse handle(FtpCommand command, ThreadClient t) throws IOException {
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
