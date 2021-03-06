package sr1.manage;

import java.io.IOException;

import sr1.FtpCommand;
import sr1.FtpResponse;

public class Pass extends FtpManage {

	@Override
	public FtpResponse handle(FtpCommand command) throws IOException {
		return new FtpResponse(230,"Login succesfull");
	}

}
