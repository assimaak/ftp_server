package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

public class Type extends FtpManage {

	@Override
	public FtpResponse handle(FtpCommand command, ThreadClient t) throws IOException {
		if (command.getArg().equals("I")) {
			t.setType(false);
			return new FtpResponse(200, "Switching to Binary mode.");
		} else if (command.getArg().equals("A")){
			t.setType(true);
			return new FtpResponse(200,"Switching to ASCII mode.");
		} else {
			return new FtpResponse(500, "Unrecognised TYPE command.");
		}
	}

}
