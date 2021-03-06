package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

public class Auth extends FtpManage {

	@Override
	public FtpResponse handle(FtpCommand command, ThreadClient t) throws IOException {
		return new FtpResponse(530, "Please login with USER and PASS.");
	}

}
