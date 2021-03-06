package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

public class User extends FtpManage {

	@Override
	public FtpResponse handle(FtpCommand command, ThreadClient t) throws IOException {
		if (command.getMessage().equals("USER") && command.getArg().equals("anonymous")) {
			return new FtpResponse(331, "Please specify the password.");
		} else {
			return new FtpResponse(530, "This FTP server is anonymous only.");
		}
	}

}
