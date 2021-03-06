package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;
import sr1.ThreadClient;

public class Pwd extends FtpManage {

	@Override
	public FtpResponse handle(FtpCommand command, ThreadClient t) throws IOException {
		return new FtpResponse(257,"\""+t.getPath()+"\" is the current directory.");
	}

}
